
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
public class Fresher extends Candidate{
    
    private String graduateDate;
    private String graduationRank;
    private String education;

    public Fresher() {
    }

    public Fresher(String id, String firstName, String lastName, String birthDate, 
                    String address, String phone, String email, int candidateType, 
                    String graduated, String graduationRank, String education) {
        super(id, firstName, lastName, birthDate, address, phone, email, candidateType);
        this.graduateDate = graduated;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return super .toString() + " Fresher{" + "graduated=" + graduateDate + ", graduationRank=" + graduationRank + ", education=" + education + '}';
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("|%7s |%7s |%7s", graduateDate,graduationRank,education);
        System.out.println();
    }

    @Override
    public void input(List<Candidate> list, int type) {
        super.input(list,type);
        this.graduateDate = Validation.getString(
                    "Enter your graduation date: ", 
                    "Must follow fomat email", 
                    "Invalid string", 
                    Constant.REGEX_DATE_OF_BIRTH
        ); 

        this.graduationRank = Validation.getString(
                "Enter your graduation rank: ",
                "Must follow: " + Constant.REGEX_GRADUATE_RANK,
                "Invalid string",
                Constant.REGEX_GRADUATE_RANK
        );
        
        this.education = Validation.getString(
                    "Enter your education: ", 
                    "Must follow fomat email", 
                    "Invalid string", 
                    Constant.REGEX_NORMAL
        ); 
    }
    
    
    
    
}