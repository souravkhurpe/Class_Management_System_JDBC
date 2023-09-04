package com.controller;

import java.util.Scanner;

import com.service.Services;
import com.service.Servicesimp;
import com.service.*;

public class ClassManagementSystem {
	static Scanner sc = new Scanner(System.in);

	static void menu() {
		System.out.println();
		System.out.println("********* WELCOME TO CLASS MANAGEMENT SYSTEM ***********");
		System.out.println();
		System.out.println("******** ENTER YOUR CHOICE ***********");

		System.out.println("1) ADD COURSE");
		System.out.println("2) DISPLAY COURSE");
		System.out.println("3) ADD FACULTY");
		System.out.println("4) DISPLAY FACULTY");
		System.out.println("5) ADD BATCH");
		System.out.println("6) DISPLAY BATCH");
		System.out.println("7) ADD STUDENT");
		System.out.println("8) DISPLAY STUDENT");
		System.out.println("9) Remove Student");
		System.out.println("10) Update Student Info");
		System.out.println("0) TO EXIT APPLICATION");
	}

	public static void main(String[] args) {
		boolean flag = true;
		Services u = new Servicesimp();

		while (flag) {
			menu();
//
			boolean flag2 = true;

			while (flag2) {
//
				int ch = sc.nextInt();
//
				switch (ch) {
				case 1:
					u.addCourse();
					flag2 = false;
					break;
				case 2:
					u.getCourse();
					flag2 = false;
					break;
				case 3:
					u.addFaculty();
					flag2 = false;
					break;
				case 4:
					u.getFaculty();
					flag2 = false;
					break;
				case 5:
					u.addBatch();
					flag2 = false;
					break;
				case 6:
					u.getBatch();
					flag2 = false;
					break;
				case 7:
					u.addStudent();
					flag2 = false;
					break;
				case 8:
					u.getStudent();
					flag2 = false;
					break;
				case 9:
					u.deleteStudent();
					flag2 = false;
					break;
				case 10:
					u.updateStudent();
					flag2 = false;
					break;

				case 0:
					flag = false;
					flag2 = false;

					break;

				default:
					System.out.println("### Enter valid choice ###");
				}

			}

		}
	

		System.out.println();
		System.out.println("******** Thanks for visiting ********");
	}
}
