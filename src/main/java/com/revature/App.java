package com.revature;

import java.util.Scanner;

import com.revature.entities.*;
import com.revature.util.*;

import com.revature.models.*;

public class App{

	public static void main(String[] args) {
		int option = 0;
		int select;
		String answer1 = new String();
		String answer2 = new String();
		ConnectionUtil connectionUtil = new ConnectionUtil();
        Function function = new Function(connectionUtil.getConnection());
		do {
			try {
			Menu menu = new Menu();
			menu.title();
			menu.initial();
			Scanner scanner = new Scanner(System.in);
			option = Integer.parseInt(scanner.nextLine());
			switch (option) {
			case 1: 
				menu.title();
				menu.log();
				select = Integer.parseInt(scanner.nextLine());
				switch (select) {
				case 1:
					menu.title();
					int c = 0;
					System.out.println("Please enter your username");
					answer1 = scanner.nextLine();
					if (answer1 != null && !answer1.isEmpty()) {
						System.out.println("Please enter your password");
						answer2 = scanner.nextLine();
						if (function.checkLogin(answer1, answer2)) {
							System.out.println("Validated!");
							c++;
						} else if (answer2 != null && !answer2.isEmpty()){
							System.out.println("The information you entered does not match anything stored in our database.");
							System.out.println("Please look over your information and try again.");
							System.out.println();
						} else {
							System.out.println("Sorry, you seem to have forgotten to enter a password. Please try again.");
						}
					} else {
						System.out.println("Sorry, you appear to haver forgotten to enter your username. Please try again.");
						System.out.println();
					} 
					break;
				}
				break;
			case 2:
				//This is for signing in
				menu.title();
				int c = 0;
				do {
					System.out.println("Please enter your username.");
					answer1 = scanner.nextLine();
					if (answer1 != null && !answer1.isEmpty()) {
						System.out.println("Thank you!");
						do {
							System.out.println("Now please enter a password.");
							answer2 = scanner.nextLine();
							if (answer2 != null && !answer2.isEmpty()) {
									System.out.println("Thank you. Your information has been received, and is pending bank approval.");
									function.insert(new Holder(answer1, answer2));
									c++;
							} else {}
						}while (c !=1);
					}else {}
				}while (c != 1);
				break;
			case 3:
				System.out.println("Thank you for banking with us!");
				scanner.close();
				break;
			case 4:
		        function.insert(new Holder("The Dark Knight", "o seven"));
		        System.out.println(function.getAll());
		        
				break;
			}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number.");
				System.out.println();
			}
		} while(option != 3);
		connectionUtil.close();
	}
}