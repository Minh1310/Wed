/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductDAO extends DBContext {

    // Just format query product to display list product 
    private final String QUERY_PRODUCT = """
            select products.name, MIN(products.price) as price ,
            products.img1, products.img2,AVG(rating.rating) as AVGrating ,
            discounts.discount_percent, discounts.active, product_status.name
            from products
            LEFT JOIN  product_status on products.status_id = product_status.id
            LEFT JOIN  discounts on products.discount_id = discounts.id
            LEFT JOIN  rating on products.id = rating.product_id                             
                                         """;
    private final String GROUP_BY_PRODUCT = """
            group by products.name, products.price, products.img1, products.img2, 
            discounts.discount_percent, discounts.active,product_status.name, products.modified_at
                                            """;

    private final String ORDER_BY_UPDATE_DATE = " order by products.modified_at desc ";

    // Method to retrieve all products
    public List<Product> getAll(ProductStatus status) throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT id, category_id, discount_id, status_id, brand_id, ");
            queryBuilder.append("name, quantity, price, size, color, description, img1, img2, created_at, modified_at ");
            queryBuilder.append("FROM products");

            if (status != null) {
                queryBuilder.append(" WHERE status_id = ?");
            }

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            if (status != null) {
                preparedStatement.setInt(1, status.getId());
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setDiscountId(resultSet.getInt("discount_id"));
                product.setStatusId(resultSet.getInt("status_id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice((float) resultSet.getDouble("price"));
                product.setSize(resultSet.getString("size"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setImg1(resultSet.getString("img1"));
                product.setImg2(resultSet.getString("img2"));
                product.setCreatedAt(resultSet.getDate("created_at"));
                product.setModifiedAt(resultSet.getDate("modified_at"));
                products.add(product);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return products;
    }

    // Method to retrieve products with optional filters
    public List<Product> getFilteredProducts(Double minPrice, Double maxPrice,
            String searchKeyword, Integer categoryId, String size, String color,
            ProductStatus status) throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT id, category_id, discount_id, status_id, brand_id, ");
            queryBuilder.append("name, quantity, price, size, color, description, img1, img2, created_at, modified_at ");
            queryBuilder.append("FROM products WHERE 1=1");

            if (minPrice != null) {
                queryBuilder.append(" AND price >= ?");
            }

            if (maxPrice != null) {
                queryBuilder.append(" AND price <= ?");
            }

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                queryBuilder.append(" AND (name LIKE ? OR description LIKE ?)");
            }

            if (categoryId != null) {
                queryBuilder.append(" AND category_id = ?");
            }

            if (size != null && !size.isEmpty()) {
                queryBuilder.append(" AND size = ?");
            }

            if (color != null && !color.isEmpty()) {
                queryBuilder.append(" AND color = ?");
            }

            if (status != null) {
                queryBuilder.append(" AND status_id = ?");
            }

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            int parameterIndex = 1;

            if (minPrice != null) {
                preparedStatement.setDouble(parameterIndex++, minPrice);
            }

            if (maxPrice != null) {
                preparedStatement.setDouble(parameterIndex++, maxPrice);
            }

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                preparedStatement.setString(parameterIndex++, "%" + searchKeyword + "%");
                preparedStatement.setString(parameterIndex++, "%" + searchKeyword + "%");
            }

            if (categoryId != null) {
                preparedStatement.setInt(parameterIndex++, categoryId);
            }

            if (size != null && !size.isEmpty()) {
                preparedStatement.setString(parameterIndex++, size);
            }

            if (color != null && !color.isEmpty()) {
                preparedStatement.setString(parameterIndex++, color);
            }

            if (status != null) {
                preparedStatement.setInt(parameterIndex, status.getId());
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setDiscountId(resultSet.getInt("discount_id"));
                product.setStatusId(resultSet.getInt("status_id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice((float) resultSet.getDouble("price"));
                product.setSize(resultSet.getString("size"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setImg1(resultSet.getString("img1"));
                product.setImg2(resultSet.getString("img2"));
                product.setCreatedAt(resultSet.getDate("created_at"));
                product.setModifiedAt(resultSet.getDate("modified_at"));
                products.add(product);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return products;
    }

// Method to retrieve a Product by its ID
    public Product getProductById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            // SQL query to select a product by its ID
            String query = "SELECT id, category_id, discount_id, status_id, brand_id, "
                    + "name, quantity, price, size, color, description, img1, img2, created_at, modified_at "
                    + "FROM products WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a Product object
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setDiscountId(resultSet.getInt("discount_id"));
                product.setStatusId(resultSet.getInt("status_id"));
                product.setBrandId(resultSet.getInt("brand_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice((float) resultSet.getDouble("price"));
                product.setSize(resultSet.getString("size"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setImg1(resultSet.getString("img1"));
                product.setImg2(resultSet.getString("img2"));
                product.setCreatedAt(resultSet.getDate("created_at"));
                product.setModifiedAt(resultSet.getDate("modified_at"));
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return product;
    }

    /**
     * Use to get total of product
     * 
     * @return a number is total of product and return 0 if empty
     */
    public int getTotalProduct(){
        String sql = "SELECT COUNT(distinct name) FROM Products";
        try (PreparedStatement pre = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
                ResultSet rs = pre.executeQuery()) {
            while(rs.next()){
                return rs.getInt(1);
            }       
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * Use to get all distinct color in product
     *
     * @return string list distinct color in product
     */
    public List<String> getAllColor() {
        List<String> list = new LinkedList<>();
        String sql = "SELECT distinct color FROM Products";
        try (PreparedStatement pre = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
                ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, "Error fetching products", ex);
        }
        return list;
    }

    /**
     * Use to get all size of product have given name
     * 
     * @param name is given product name
     * @return a list size of product
     */
    public List<Integer> getAllSizeByName(String name) {
        List<Integer> list = new LinkedList<>();
        String sql = "select distinct size from products where products.name = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to get all color of product have given name
     * 
     * @param name is given product name
     * @return a list color of product
     */
    public List<String> getAllColorByName(String name) {
        List<String> list = new LinkedList<>();
        String sql = "select distinct color from products where products.name = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to get product order by modified date
     * 
     * @param from is start row in database
     * @param to   is next how much row from start
     * @return a list product order by modified date
     */
    public List<Product> getProductOrderByDate(int from, int to) {
        List<Product> list = new LinkedList<>();
        StringBuilder sql = new StringBuilder(QUERY_PRODUCT);
        sql.append(GROUP_BY_PRODUCT);
        sql.append(ORDER_BY_UPDATE_DATE);
        sql.append("OFFSET ? ROWS FETCH NEXT ? rows ONLY; ");
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1, from);
            pre.setInt(2, to);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2), rs.getString(3),
                        rs.getString(4),
                        new Ratting(rs.getInt(5)),
                        new Discount(rs.getInt(6), rs.getBoolean(7)),
                        new ProductStatus(rs.getString(8)));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to get product order by rating
     * 
     * @param rating is rating that you want order
     * @param size   is size of list you want get
     * @return a list product order by rating
     */
    public List<Product> getProductByRating(int rating, int size) {
        List<Product> list = new LinkedList<>();
        StringBuilder sql = new StringBuilder(QUERY_PRODUCT);
        sql.append(GROUP_BY_PRODUCT);
        sql.append(" having AVG(rating.rating) >= ? ");
        sql.append(ORDER_BY_UPDATE_DATE);
        sql.append("OFFSET 0 ROWS FETCH NEXT ? rows ONLY;");
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setFloat(1, rating);
            pre.setInt(2, size);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2), rs.getString(3),
                        rs.getString(4),
                        new Ratting(rs.getInt(5)),
                        new Discount(rs.getInt(6), rs.getBoolean(7)),
                        new ProductStatus(rs.getString(8)));
                list.add(product);
                product.getProductStatus().getName();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     *  Use to get product have given name
     * 
     * @param name is name product you want search
     * @param from is start row in database
     * @param to   is next how much row from start
     * @return a list product have given name
     */
    public List<Product> search(String name, int from, int to) {
        List<Product> list = new LinkedList<>();
        StringBuilder sql = new StringBuilder(QUERY_PRODUCT);
        sql.append(" where LOWER(products.name) like ? ");
        sql.append(GROUP_BY_PRODUCT);
        sql.append(ORDER_BY_UPDATE_DATE);
        sql.append("OFFSET ? ROWS FETCH NEXT ? rows ONLY;");
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setString(1, "%" + name.toLowerCase() + "%");
            pre.setInt(2, from);
            pre.setInt(3, to);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2), rs.getString(3),
                        rs.getString(4),
                        new Ratting(rs.getInt(5)),
                        new Discount(rs.getInt(6), rs.getBoolean(7)),
                        new ProductStatus(rs.getString(8)));
                list.add(product);
                product.getProductStatus().getName();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to get latest product
     * 
     * @param size is size product you want get
     * @return a list latest product
     */
    public List<Product> getProductLatest(int size) {
        List<Product> list = new LinkedList<>();
        StringBuilder sql = new StringBuilder(QUERY_PRODUCT);
        sql.append(GROUP_BY_PRODUCT);
        sql.append(ORDER_BY_UPDATE_DATE);
        sql.append("OFFSET 0 ROWS FETCH NEXT ? rows ONLY;");
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1, size);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2), rs.getString(3),
                        rs.getString(4),
                        new Ratting(rs.getInt(5)),
                        new Discount(rs.getInt(6), rs.getBoolean(7)),
                        new ProductStatus(rs.getString(8)));
                list.add(product);
                product.getProductStatus().getName();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to get product follow by category
     * 
     * @param category    is parent category
     * @param subCategory is child category
     * @param from        is start row in database
     * @param to          is next how much row from start
     * @return a list product have given category
     */
    public List<Product> getProductByCategory(String category, String subCategory,
            int from, int to) {
        List<Product> list = new LinkedList<>();
        StringBuilder sql = new StringBuilder(QUERY_PRODUCT);
        sql.append("""
                   where products.name in (select products.name
                   from products
                   left join product_subcate on products.id = product_subcate.product_id
                   left outer join subcategories on product_subcate.subcategory_id = subcategories.id
                   left outer join categories on categories.id = subcategories.category_id
                                """);
        if (category != null) {
            sql.append(" where categories.name like ? ");
        }
        if (subCategory != null) {
            sql.append(" and subcategories.name like ? ");
        }
        sql.append(" group by products.name) ");
        sql.append(GROUP_BY_PRODUCT);
        sql.append(ORDER_BY_UPDATE_DATE);
        sql.append("OFFSET ? ROWS FETCH NEXT ? rows ONLY;");
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (subCategory != null) {
                pre.setString(1, category);
                pre.setString(2, subCategory);
                pre.setInt(3, from);
                pre.setInt(4, to);
            } else {
                pre.setString(1, category);
                pre.setInt(2, from);
                pre.setInt(3, to);
            }

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2), rs.getString(3),
                        rs.getString(4),
                        new Ratting(rs.getInt(5)),
                        new Discount(rs.getInt(6), rs.getBoolean(7)),
                        new ProductStatus(rs.getString(8)));
                list.add(product);
                product.getProductStatus().getName();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to make query follow fields in filter
     * 
     * @param filter is class have fields that following
     * @return a string is query to take product follow fields in filter
     */
    public String getSqlOfFilter(Filter filter){
        StringBuilder sql = new StringBuilder(QUERY_PRODUCT);
        sql.append(" LEFT JOIN  brands on brands.id = products.brand_id ");
        sql.append("""
                   left join product_subcate on products.id = product_subcate.product_id
                   left outer join subcategories on product_subcate.subcategory_id = subcategories.id
                   left outer join categories on categories.id = subcategories.category_id """);
        sql.append(" where 1=1 ");
        if (filter.getNameSearch() != null) {
            sql.append(" and LOWER(products.name) like ? ");
        }
        if (filter.getCategory() != null) {
            sql.append(" and categories.name like ?  ");
        }

        if (filter.getSubCategory() != null) {
            sql.append(" and subcategories.name like ? ");
        }

        if (filter.getColor() != null) {
            sql.append(" and products.color in ");
            sql.append(Support.generateSqlQuery(filter.getColor())).append(" ");
        }
        if (filter.getBrand() != null) {
            sql.append(" and brands.name in ");
            sql.append(Support.generateSqlQuery(filter.getBrand())).append(" ");
        }
        if (filter.getPrice() != null) {
            String[] price = filter.getPrice();
            sql.append("and ( ");
            for (int i = 0; i < price.length; i++) {
                switch (price[i]) {
                    case "1" ->
                        sql.append("(products.price > 0 and products.price <= 50)");
                    case "2" ->
                        sql.append(" (products.price > 50 and products.price <100)");
                    case "3" ->
                        sql.append("(products.price >= 100 and products.price <= 150)");
                    case "4" ->
                        sql.append("(products.price >150)");
                    default ->
                        sql.append("1=1");
                }
                if (i != price.length - 1) {
                    sql.append(" or ");
                }
            }
            sql.append(" ) ");
        }
        sql.append(GROUP_BY_PRODUCT);
        sql.append(", brands.name ");
        sql.append(ORDER_BY_UPDATE_DATE);
        sql.append("OFFSET ? ROWS FETCH NEXT ? rows ONLY;");
        return sql.toString();
    }
    
    /**
     * Use to get product have given value fields
     * 
     * @param filter is class have fields that following
     * @param from        is start row in database
     * @param to          is next how much row from start
     * @return a list product following filter
     */
    public List<Product> getProductByFilter(Filter filter, int from, int to) {
        List<Product> list = new LinkedList<>();
        try {
            PreparedStatement pre = connection.prepareStatement(
                    getSqlOfFilter(filter), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            int insertIndex = 0;
            if (filter.getNameSearch() != null) {
                pre.setString(++insertIndex, filter.getNameSearch());
            }
            if (filter.getCategory() != null) {
                pre.setString(++insertIndex, filter.getCategory());
            }
            if (filter.getSubCategory() != null) {
                pre.setString(++insertIndex, filter.getSubCategory());
            }
            if (filter.getColor() != null) {
                for(String a : filter.getColor()){
                    pre.setString(++insertIndex, a);
                }            
            }
            if (filter.getBrand() != null) {
                for(String a : filter.getBrand()){
                    pre.setString(++insertIndex, a);
                }
            }
            pre.setInt(++insertIndex, from);
            pre.setInt(++insertIndex, to);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2), rs.getString(3),
                        rs.getString(4),
                        new Ratting(rs.getInt(5)),
                        new Discount(rs.getInt(6), rs.getBoolean(7)),
                        new ProductStatus(rs.getString(8)));
                list.add(product);
                product.getProductStatus().getName();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Use to get product have unique name with keys are sizes and colors
     * description, categories, rating, status
     *
     * @param rs is ResultSet take from input
     * @return list product have unique name
     */
    public List<SubProducts> getProductUniqueName(ResultSet rs) {
        Set<SubProducts> list = new HashSet<>();
        // Convert incomingDataList to a Map to aggregate sizes and colors
        // description, categories, rating, status
        Map<SubProducts, ProductAggregation> productMap = new HashMap<>();
        //Use to set product have same also have same sizes and colors
        // description, categories, rating, status
        try {
            while (rs.next()) {
                SubProducts key = new SubProducts(
                        rs.getString(1), rs.getInt(2),
                        null, null, null,
                        rs.getString(6), rs.getString(7), null,
                        rs.getInt(9), rs.getInt(10),
                        null, rs.getString(12));
                ProductAggregation aggregation
                        = productMap.computeIfAbsent(key, k -> new ProductAggregation());
                aggregation.sizes.add(rs.getInt(3));
                aggregation.colors.add(rs.getString(4));
                aggregation.description.add(rs.getString(5));
                aggregation.rating.add(rs.getInt(8));
                aggregation.status.add(rs.getString(11));
            }
            // Convert map tp set
            list = productMap.entrySet().stream()
                    .map((Map.Entry<SubProducts, ProductAggregation> entry) -> {
                        SubProducts key = entry.getKey();
                        ProductAggregation aggregation = entry.getValue();
                        int[] sizes = aggregation.sizes.stream().mapToInt(i -> i).toArray();
                        String[] colors = aggregation.colors.toArray(String[]::new);
                        String[] description = aggregation.description.toArray(String[]::new);
                        int[] rating = aggregation.rating.stream().mapToInt(i -> i).toArray();
                        String[] status = aggregation.status.toArray(String[]::new);
                        return new SubProducts(key.getName(),
                                key.getPrice(), sizes, colors,
                                description,
                                key.getImg1(), key.getImg2(),
                                rating, key.getDiscount(),
                                key.getDiscount_status(),
                                status,
                                key.getBrand_name());
                    })
                    .collect(Collectors.toSet());

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return new LinkedList<>(list);
    }

    /**
     * Use to search product have same given name
     *
     * @param name is name or text take from input
     * @return list product have same name with given name
     */
    public List<SubProducts> searchUniqueName(String name) {
        List<SubProducts> list = new LinkedList<>();
        String sql = """
                    select products.name, products.price, products.size, products.color, products.description,
                    products.img1, products.img2, rating.rating, discounts.discount_percent, discounts.active, 
                    product_status.name, brands.name
                    from products
                    LEFT JOIN product_status on products.status_id = product_status.id
                    LEFT JOIN discounts on products.discount_id = discounts.id
                    LEFT JOIN rating on products.id = rating.product_id                
                    LEFT JOIN brands on products.brand_id = brands.id
                    where products.name like ?
                    """;
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setString(1, "%" + name + "%");
            ResultSet rs = pre.executeQuery();
            list = getProductUniqueName(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    // Priduct with status not off
    public Product getProductDetailForCart(String name, String color, int size){
        String sql = """
        SELECT products.id
                       ,products.name
                       ,products.color
                       ,products.size
                       ,products.price
                       ,products.quantity
                       ,discount_percent
                       ,discounts.active
                       ,product_status.name
                       FROM [dbo].[products]
                       FULL OUTER JOIN product_status on products.status_id = product_status.id
                       FULL OUTER JOIN discounts on discounts.id = products.discount_id 
                       where lower(product_status.name) not like 'off'
                       and products.name like ?
                       and products.color like ?
                       and products.size = ?
                         """;
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);  
            pre.setString(1, name);
            pre.setString(2,  color);
            pre.setInt(3, size);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1), rs.getString(2), 
                        rs.getInt(6), rs.getFloat(5),
                        rs.getString(4), rs.getString(3), 
                        new Discount(rs.getInt(7), rs.getBoolean(8)),
                        new ProductStatus(rs.getString(9)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     // Priduct with status not off
    public Product getInforProductById(int id){
        String sql = """
        SELECT products.id
                       ,products.name
                       ,products.color
                       ,products.size
                       ,products.price
                       ,products.quantity
                       ,discount_percent
                       ,discounts.active
                       ,product_status.name
                       FROM [dbo].[products]
                       FULL OUTER JOIN product_status on products.status_id = product_status.id
                       FULL OUTER JOIN discounts on discounts.id = products.discount_id 
                       where products.id = ?
                         """;
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);  
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1), rs.getString(2), 
                        rs.getInt(6), rs.getFloat(5),
                        rs.getString(4), rs.getString(3), 
                        new Discount(rs.getInt(7), rs.getBoolean(8)),
                        new ProductStatus(rs.getString(9)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        String[] arr = {"1", "2"};
        String[] color = {"Pink", "Grey"};
        String[] brand = {"Other"};
        Filter filter = new Filter("viewByFilter",
                null, null, null, null, brand,
                null);
//        List<Product> ls = productDAO.getProductByFilter(filter, 0, 50);
//        ls.forEach(a -> {
//            System.out.println(a.getName());
//        });
        Product po = productDAO.getProductDetailForCart("Boat Shoes", "White", 39);
        System.out.println(po.toString());

    }

}
