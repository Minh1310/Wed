/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.List;

/**
 *
 * @author Nhat Anh
 */
public class Support {

    /**
     * Use to print array
     * 
     * @param array is given array
     * @return a string after print array
     */
    public static String printArray(String[] array) {
        String result = "";
        for (String element : array) {
            result = result + "\'" + element + "\'" + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

        /**
     * Use to print array
     * 
     * @param array is given array
     * @return a string after print array
     */
    public static String printArray(int[] array) {
        String result = "";
        for (int element : array) {
            result = result + element + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    public static boolean exist(String name, String[] array) {
        for (String a : array) {
            if (name.equalsIgnoreCase(a)) {
                return true;
            }
        }
        return false;
    }

        /**
     * Use to print array follow by 'a', 'b', ...
     * 
     * @param arr is given array
     * @return a string after print array
     */
    public static String generateSqlQuery(String[] arr) {
        // Generate the SQL query with the appropriate number of placeholders
        StringBuilder sql = new StringBuilder("(");
        for (int i = 0; i < arr.length; i++) {
            sql.append("?");
            if (i < arr.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");
        return sql.toString();
    }

    /**
     * Use to calculate average of integer array
     * 
     * @param array is given array
     * @return a number is average
     */
    public static int average(Integer[] array) {
        int result = 0;
        int count = 0;
        for (Integer a : array) {
            result += a;
            count++;
        }
        return result / count;
    }

            /**
     * Use to print list
     * 
     * @param ls is given list
     * @return a string after print list
     */
    public static String print(List<String> ls) {
        String string = "";
        for (String a : ls) {
            string = string + a + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder("");
        int length = 2;
        String[] arr = {"m", "k"};

        System.out.println(generateSqlQuery(arr));
    }
}
