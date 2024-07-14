package main;

import bo.StudentBO;
import constant.Constant;
import utils.Validation;

public class Main {
	public static void main(String[] args) {
		StudentBO studentBO = new StudentBO();
		System.out.println("----------------");

		int choice;
		do {
			System.out.println("1. Create");
			System.out.println("2. Find and Sort");
			System.out.println("3. Update/Delete");
			System.out.println("4. Report");
			System.out.println("5. Exit");
			choice = Validation.getInt("Enter your choice:", "Your choice must be 1 to 5!", "Number is not valid!", 1,
					5);
			switch (choice) {
			case 1:
				System.out.println("1");
				int count = 0;
				int choice1 = 0;
				do {
					System.out.println(studentBO.add() ? "success" : "fail");
					count++;
					if (count == 1) {
						System.out.println("Do you want continue add?");
						System.out.println("1. Continue");
						System.out.println("2. Exit");
						choice1 = Validation.getInt(
							"Enter your choice: ", 
							"Out of range", 
							"Invalid", 
							1, 2);
						if(choice1 == 1){
							count = 0;
						}
					}
				} while (choice1 != 2);
				studentBO.display();
				break;
			case 2:
				String text = Validation.getString(
					"Enter text: ", 
					"Must follow fomat: Minh",
					Constant.CONDITION_STUDENT_NAME);
				studentBO.display(studentBO.searchName(text));
				break;
			case 3:
				System.out.println("1. Update");
				System.out.println("2. Delete");
				int choice3 = Validation.getInt(
					"Enter your choice:", 
					"Your choice must be 1 to 2!", 
					"Invalid number", 
					1, 2);
				String id = Validation.getString(
						"Enter your ID: ", 
						"Must follow fomat: HE171754",
						Constant.CONDITION_ID);
				int k = studentBO.indexChange(id);
				if(k==-1){
					choice3 = -1;
					System.out.println(Constant.NOT_EXIST_ID);
				}
				switch (choice3) {
					case 1:
						do{
							System.out.println("1. update name");
							System.out.println("2. update semester");
							System.out.println("3. Update course");
							System.out.println("4. exit");
							int choose = Validation.getInt(
								"Enter your choice", 
								"Out of range", 
								"Invalid number", 
								1, 4);
							if(choose == 4){
								break;
							}
							String infor = "";
							switch (choose) {
								case 1:
									infor = Validation.getString(
											"Enter update name",
											"String not valid",
											Constant.CONDITION_STUDENT_NAME);
									studentBO.update(id,infor);
									break;
								case 2:
									infor = Validation.getString(
											"Enter update semester",
											"String not valid",
											Constant.CONDITION_SEMESTER);
											System.out.println(studentBO.update(1,k,infor) 
												? "Update success" : "Dont exist id");
									break;
								case 3:
									infor = Validation.getString(
											"Enter update course",
											"String not valid",
											Constant.CONDITION_COURSE_NAME);
											System.out.println(studentBO.update(2,k,infor) 
											? "Update success" : "Dont exist id");
									break;
							}
						} while (true);
						studentBO.display();
						break;
					case 2:
						System.out.println(studentBO.remove(id) ? 
							"Remove success" : "Remove fail because dont have this id");
						studentBO.display();
						break;
					}
					break;
			case 4:
				System.out.println("4");
				studentBO.report();
				break;
			case 5:
				System.out.println("5");
				break;
			}
		} while (choice != 5);

	}
}

