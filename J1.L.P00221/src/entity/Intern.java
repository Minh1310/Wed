
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
public class Intern extends Candidate {
    
    private String majors;
    private String semester;
    private String universityName;

    public Intern() {
    }

    public Intern(String id, String firstName, String lastName, 
                    String birthDate, String address, String phone, 
                    String email, int candidateType, String majors, String semester, 
                    String universityName) {
        super(id, firstName, lastName, birthDate, address, phone, email, candidateType);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return super.toString() +" Intern{" + "majors=" + majors 
                + ", semester=" + semester + ", universityName=" 
                + universityName + '}';
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("|%10s |%10s |%10s",majors,semester,universityName);
        System.out.println();
    }

    @Override
    public void input(List<Candidate> list, int type) {
        super.input(list,type);
        this.majors = Validation.getString(
                    "Enter your majoy: ", 
                    "Must follow fomat", 
                    "Invalid string", 
                    Constant.REGEX_NAME
        ); 
        this.semester = Validation.getString(
                    "Enter your semester: ", 
                    "Must follow fomat", 
                    "Invalid string", 
                    Constant.REGEX_NORMAL
        ); 
        this.universityName = Validation.getString(
                    "Enter your university name: ", 
                    "Must follow fomat", 
                    "Invalid string", 
                    Constant.REGEX_NAME
        );
    }
}