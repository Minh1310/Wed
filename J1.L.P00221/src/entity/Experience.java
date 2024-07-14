
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
public class Experience extends Candidate  {
    
    private int yearOfExp;
    private String proSkill;

    public Experience() {
    }

    public Experience(
        String id, String firstName, 
        String lastName, String birthDate, 
        String address, String phone, String email, 
        int candidateType, int yearOfExp, String proSkill) {
        super(id, firstName, lastName, birthDate, address, phone, email, candidateType);
        this.yearOfExp = yearOfExp;
        this.proSkill = proSkill;
    }
    
    public int getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(int yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public String toString() {
        return super.toString() + "Experience{" + "yearOfExp=" + yearOfExp 
                + ", proSkill=" + proSkill + '}';
    }

    @Override
    public void input(List<Candidate> list, int type) {
        super.input(list,type);
        this.yearOfExp = Validation.getInt(
                    "Enter year of experience: ", 
                    "It's invalid experience", 
                    "Invalid number", 
                    0,100
        );
        this.proSkill = Validation.getString(
                    "Enter professional skill: ", 
                    "Must follow format", 
                    "Invalid string", 
                    Constant.REGEX_NORMAL
        );
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("|%3s |%5s",yearOfExp,proSkill);
        System.out.println();
    }

    
    
    
    
    
    
}