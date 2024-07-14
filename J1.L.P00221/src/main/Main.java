
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bo.CandidateBO;
import constant.Constant;
import utils.Validation;
/**
 *
 * @author Nhat Anh
 */
public class Main {
    
    public static void main(String[] args){
        CandidateBO candidateBO = new CandidateBO();
        int choice;
        do{
            System.out.println("1. Experience");
            System.out.println("2. Fresher");
            System.out.println("3. Intern");
            System.out.println("4. Search");
            System.out.println("5. Exit");
            choice = Validation.getInt(
                "Enter your choice",
                "Must around 1-5",
                "Invalid number", 
                1,5);
            switch(choice){
                case 1:
                    candidateBO.add(0);
                    break;
                case 2:
                    candidateBO.add(1);
                    break;
                case 3:
                    candidateBO.add(2);
                    break;
                case 4:
                    candidateBO.printListNameCandidate();
                    String name = Validation.getString(
                        "Enter your first name or last name : ", 
                        "Must have(a-zA-Z)", 
                        "Invalid string", 
                        Constant.REGEX_NAME
                    ).toLowerCase();
                    int type = Validation.getInt(
                            "Enter type of cadidate",
                            "Out of range", 
                            "Invalid number", 
                            0, 2);
                    candidateBO.display(candidateBO.search(name, type));                  
                    break;
                case 5:
                    break;
            }

        } while(choice!=5);
    }
}