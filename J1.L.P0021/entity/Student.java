/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.List;

import constant.Constant;
import utils.Validation;

/**
 *
 * @author Nhat Anh
 */
public class Student {
    private String id;
    private String studentName;
    private String semester;
    private String courseName;

    public Student(String id,
            String studentName,
            String semester,
            String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Students{" + "Id=" + id + ", studentName=" + studentName + ", semester=" + semester + ", courseName="
                + courseName + '}';
    }

    /**
     * 
     * @param list
     */
    public void input(List<Student> list) {
        do{
            this.id = Validation.getString(
                "Enter your ID: ",
                "Must follow fomat: HE171754",
                Constant.CONDITION_ID).toUpperCase();
            this.studentName = Validation.getString(
                "Enter student name: ",
                "Must follow fomat: Minh",
                Constant.CONDITION_STUDENT_NAME).trim();
            if(exist(list,false)){
                break;
            }
            System.out.println(Constant.messageNameNotMatchWithId);
        } while(true);
        do{
            this.semester = Validation.getString(
                "Enter your semester: ",
                "Must follow fomat just have character and digit",
                Constant.CONDITION_SEMESTER);
            this.courseName = Validation.getString(
                "Enter your course name: ",
                "Must follow fomat: JAVA, .NET, C/C++",
                Constant.CONDITION_COURSE_NAME);
            if(!exist(list,true)){
                break;
            }
            System.out.println(Constant.messageStudentExist);
        } while(true);
    }

    /**
     * Use to display information of student
     */
    public void display() {
        System.out.printf("%10s| %10s| %10s| %10s| ",
                id, studentName, semester, courseName);
        System.out.println();
    }

    /**
     * Use to check student existed in student list
     * 
     * @param list list about information of student take from input
     * @param id   is ID of student take from input
     * @return true if ID existed and same semester or false if ID not exist
     */
    public boolean exist(List<Student> list, String id, String semester, String course) {
        return list.stream().anyMatch(ls -> (
                ls.getId().equalsIgnoreCase(id) &&
                ls.getSemester().equalsIgnoreCase(semester) &&
                ls.getCourseName().equalsIgnoreCase(course)));
    }

    /**
     * Use to check student existed in student list
     * 
     * @param list list about information of student take from input
     * @param id   is ID of student take from input
     * @return true if ID existed and same semester or false if ID not exist
     */
    private boolean exist(List<Student> list, boolean flag) {
        if(flag){
            return list.stream().anyMatch(ls -> (
                ls.getId().equalsIgnoreCase(this.id) &&
                ls.getSemester().equalsIgnoreCase(this.semester) &&
                ls.getCourseName().equalsIgnoreCase(this.course)));
        }
        return list.stream().anyMatch(ls -> (
                ls.getId().equalsIgnoreCase(this.id) &&
                ls.getStudentName().equalsIgnoreCase(this.studentName)));
    }

}