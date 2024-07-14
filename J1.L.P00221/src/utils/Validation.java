/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Nhat Anh
 */
public class Validation {

    /**
     * Don't let anyone instantiate this class.
     */
    private Validation() {
    }

    private final static Scanner SCANNER = new Scanner(System.in);
    
    /**
     * 
     * @param messageInfo
     * @param messageErrorOutOfRange
     * @param messageErrorInvalidNumber
     * @param min
     * @param max
     * @return 
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min, int max
    ) {
        do {
            try {
                System.out.println(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);

            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);

    }
    
    /**
     * 
     * @param messageInfo
     * @param messageErrorOutOfRange
     * @param messageErrorInvalidNumber
     * @param min
     * @param max
     * @return 
     */
    public static double getDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min, double max
    ) {
        do {
            try {
                System.out.println(messageInfo);
                Double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);

            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);

    }
    
    /**
     * 
     * @param messageInfo
     * @param messageErrorFormat
     * @param messageErrorInvalidString
     * @param conditionString
     * @return 
     */
    public static String getString(
            String messageInfo,
            String messageErrorFormat,
            String messageErrorInvalidString,
            final String conditionString
    ) {
        do {
            try {
                System.out.println(messageInfo);
                String texts = SCANNER.nextLine();
                if (texts.matches(conditionString)) {
                    return texts;
                }
                System.out.println(messageErrorFormat);

            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidString);
            }
        } while (true);

    }
}
     