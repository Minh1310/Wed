/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import dto.ProductDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Discount;

/**
 *
 * @author Admin
 */
public class DiscountDAO extends DBContext {

    // get all discount
    public List<Discount> getAllDiscount() {
        List<Discount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"//1
                + "      ,[name]\n"//2
                + "      ,[description]\n"//3
                + "      ,[discount_percent]\n"//4
                + "      ,[active]\n"//5
                + "      ,[created_at]\n"//6
                + "      ,[modified_at]\n"//7
                + "  FROM [dbo].[discounts]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount d = new Discount();

                d.setId(rs.getInt(1));
                d.setName(rs.getString(2));
                d.setDescription(rs.getString(3));
                d.setDiscountPercent(rs.getInt(4));
                d.setActive(rs.getBoolean(5));
                d.setCreatedAt(rs.getDate(6));
                d.setModifiedAt(rs.getDate(7));

                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
