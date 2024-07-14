/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant;

/**
 *
 * @author Nhat Anh
 */
public class Constant {
	
	public static final String messageStudentExist ="Student exist";

    public static final String NOT_EXIST_ID = "ID not exist";

    public static final String messageNameNotMatchWithId ="Wrong name";

    public static final String CONDITION_ID = "^(?i)(HE|HS)\\d{6}$";
    
    public static final String CONDITION_SEMESTER = "^[a-zA-Z0-9]{1,}$";
    
    public static final String CONDITION_COURSE_NAME = "^(?i)(JAVA)|(.NET)|(C\\/C\\+\\+)$";
    
    public static final String CONDITION_STUDENT_NAME = "^[a-zA-Z]{1,}$";
}
