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
				//log in
				menu.title();
				menu.log();
				select = Integer.parseInt(scanner.nextLine());
				switch (select) {
				case 1:
					//client
					int checker = 0;
					menu.title();
					System.out.println("Please enter your username");
					answer1 = scanner.nextLine();
					if (answer1 != null && !answer1.isEmpty()) {
						System.out.println("Please enter your password");
						answer2 = scanner.nextLine();
						checker = (function.checkLogin(answer1, answer2));
						if (checker == 3) {
							System.out.println("Hello, " + answer1 + "! Unfortunately, your account has not yet been approved.");
							System.out.println("Please try again later.");
							System.out.println();
						}else if (checker == 2) {
							int choice = 0;
							menu.title();
							do {
								System.out.println("Welcome, " + answer1 + "!");
								System.out.println("Here are your options!");
								System.out.println();
								System.out.println("1: Check Balance");
								System.out.println("2: Deposit da monies");
								System.out.println("3. Withdraw some monies");
								System.out.println("4. Exit");
								choice = Integer.parseInt(scanner.nextLine());
								switch (choice) {
								case 1:
									//check balance
									System.out.println();
									int balance = function.checkBalance(answer1);
									System.out.println("Your Balance is:\t" + balance);
									System.out.println();
									break;
								case 2:
									//deposit
									int deposit = 0;
									System.out.println("Please enter an amount to deposit:");
									do {
										deposit = scanner.nextInt();
										scanner.nextLine();
										if (deposit < 0 ) {
											System.out.println();
											System.out.println("Please enter a positive whole number.");
										}
									} while (deposit < 0);
									function.addMoney(answer1, deposit);
									if (deposit == 0) {
										System.out.println();
										System.out.println("Your balance has not been changed.");
										System.out.println();
									} else {
										System.out.println();
										System.out.println("Your account has been updated!");
										System.out.println();
									}
									break;
								case 3:
									//withdraw
									int withdraw = 0;
									int noNegative = function.checkBalance(answer1);
									System.out.println("Please enter an amount to withdraw:");
									do {
										withdraw = scanner.nextInt();
										scanner.nextLine();
										if (withdraw < 0) {
											System.out.println();
											System.out.println("Please enter a positive whole number.");
										} else if (withdraw >= noNegative) {
											System.out.println();
											System.out.println("The amount specified exceeds your total balance.");
											System.out.println("Please try again.");
											withdraw = 0;
										}
									} while (withdraw < 0);
									function.takeMoney(answer1, withdraw);
									if (withdraw == 0) {
										System.out.println();
										System.out.println("Your balance has not been changed.");
										System.out.println();
									} else {
										System.out.println();
										System.out.println("Your account has been updated!");
										System.out.println();
									}
									break;
								} 
							} while(choice != 4);
						} else if (checker == 1) {
							System.out.println("Unfortunately, your application has been denied.");
							System.out.println("Probably because the employee reviewing your application decided you were a loser. Goodbye!");
							System.out.println();
						} else if (answer2 != null && !answer2.isEmpty()){
							System.out.println("The information you entered does not match anything stored in our database.");
							System.out.println("Please look over your information and try again.");
							System.out.println();
						} else {
							System.out.println("Sorry, you seem to have forgotten to enter a password. Please try again.");
							System.out.println();
						}
					} else {
						System.out.println("Sorry, you appear to haver forgotten to enter your username. Please try again.");
						System.out.println();
					} 
					break;
				case 2:
					//employee log-in
					menu.title();
					System.out.println("Please enter your username.");
					answer1 = scanner.nextLine();
					if (answer1 != null && !answer1.isEmpty()) {
						System.out.println("Please enter your password");
						answer2 = scanner.nextLine();
						if (function.checkEmpLogin(answer1, answer2, "employees")){
							int choice = 0;
							do {
								System.out.println("Welcome, " + answer1 + "!");
								System.out.println("Please select an option:");
								System.out.println();
								System.out.println("1: View current clients");
								System.out.println("2: Review client applications");
								System.out.println("3: Back");
								choice = Integer.parseInt(scanner.nextLine());
								switch (choice) {
								case 1: 
									System.out.println(function.getAll("= 1"));
									System.out.println("Press Enter to leave Client View.");
									scanner.nextLine();
									break;
								case 2:
									String entry = "";
									int entry2 = 0;
									do {
										System.out.println(function.getAll("is null"));
										System.out.println("Enter in a username to approve or deny a client.");
										System.out.println("Enter in \"Back\" or \"b\" to return.");
										entry = scanner.nextLine();
										if (!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b")){
											System.out.println(function.display(entry));
											System.out.println();
											System.out.println("Options:");
											System.out.println();
											System.out.println("1. Approve");
											System.out.println("2. Deny");
											System.out.println("3. Back");
											entry2 = Integer.parseInt(scanner.nextLine());
											function.approval(entry, entry2);
										}
									} while (!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b"));
								}
							} while (choice != 3);
						} else if (answer2 != null && !answer2.isEmpty()) {
							System.out.println("The information entered does not match an employee in our database.");
							System.out.println("Please try again.");
							}
						else {
							System.out.println("You appear to have forgotten to enter your password. Please try again.");
						}
					} else {
						System.out.println("You appear to have forgotten to enter your username. Please try again.");
						System.out.println();
					}
					break;
				case 3:
					//this is for administrator log-in
					menu.title();
					System.out.println("Please enter your username");
					answer1 = scanner.nextLine();
					if (answer1 != null && !answer1.isEmpty()) {
						System.out.println("Please enter your password");
						answer2 = scanner.nextLine();
						if (answer2 != null && !answer2.isEmpty()) {
							System.out.println("Please enter the super-secret admin code.");
							int code = Integer.parseInt(scanner.nextLine());
							if (function.checkAdminLogin(answer1, answer2, code)) {
								int choice = 0;
								do {
									System.out.println("Welcome " + answer1 + "!");
									System.out.println();
									System.out.println("Here are your options.");
									System.out.println();
									System.out.println("1. Change client balance");
									System.out.println("2. Check employees");
									System.out.println("3. Review client applications");
									System.out.println("4. Create new employee");
									System.out.println("5. Big Red Button");
									System.out.println("6. Return");
									choice = Integer.parseInt(scanner.nextLine());
									switch (choice) {
									case 1:
										//Manipulate client data
										String entry = "";
										int entry2 = 0;
										do {
											System.out.println(function.adminGetAll());
											System.out.println();
											System.out.println("Enter in the username of the client whose data you would like to change.");
											System.out.println("Enter in \"Back\" or \"b\" to return.");
											entry = scanner.nextLine();
											if (!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b")) {
												do {
													System.out.println(function.adminDisplay(entry));
													System.out.println();
													System.out.println("Options:");
													System.out.println();
													System.out.println("1. Deposit");
													System.out.println("2. Withdraw");
													System.out.println("3. Return");
													entry2 = Integer.parseInt(scanner.nextLine());
													switch (entry2) {
													case 1:
														//deposit
														int deposit = 0;
														System.out.println("Please enter an amount to deposit:");
														do {
															deposit = scanner.nextInt();
															scanner.nextLine();
															if (deposit < 0 ) {
																System.out.println();
																System.out.println("Please enter a positive whole number.");
															}
														} while (deposit < 0);
														function.addMoney(entry, deposit);
														if (deposit == 0) {
															System.out.println();
															System.out.println("The balance has not been changed.");
															System.out.println();
														} else {
															System.out.println();
															System.out.println("The account has been updated!");
															System.out.println();
														}
														break;
													case 2:
														//withdraw
														int withdraw = 0;
														int noNegative = function.checkBalance(entry);
														System.out.println("Please enter an amount to withdraw:");
														do {
															withdraw = scanner.nextInt();
															scanner.nextLine();
															if (withdraw < 0) {
																System.out.println();
																System.out.println("Please enter a positive whole number.");
															} else if (withdraw >= noNegative) {
																System.out.println();
																System.out.println("The amount specified exceeds the total balance.");
																System.out.println("Please try again.");
																withdraw = 0;
															}
														} while (withdraw < 0);
														function.takeMoney(entry, withdraw);
														if (withdraw == 0) {
															System.out.println();
															System.out.println("The balance has not been changed.");
															System.out.println();
														} else {
															System.out.println();
															System.out.println("The account has been updated!");
															System.out.println();
														}
														break;
													}
												}while(entry2 != 3);
											}
										}while(!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b"));
										break;
									case 2:
										do {
											System.out.println(function.getEmployees());
											System.out.println();
											System.out.println("Type in an employee's username to delete that employee.");
											System.out.println("Otherwise, type \"Back\" or \"b\" to leave.");
											entry = scanner.nextLine();
											if (!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b")) {
												function.delete(entry);
												System.out.println("Employee deleted!");
											}
										}while(!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b"));
										break;
									case 3: 
										do {
											System.out.println(function.getAll("is null"));
											System.out.println("Enter in a username to approve or deny a client.");
											System.out.println("Enter in \"Back\" or \"b\" to return.");
											entry = scanner.nextLine();
											if (!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b")){
												System.out.println(function.display(entry));
												System.out.println();
												System.out.println("Options:");
												System.out.println();
												System.out.println("1. Approve");
												System.out.println("2. Deny");
												System.out.println("3. Back");
												entry2 = Integer.parseInt(scanner.nextLine());
												function.approval(entry, entry2);
											}
										} while (!entry.equalsIgnoreCase("back") && !entry.equalsIgnoreCase("b"));
										break;
									case 4:
										System.out.println("Please enter a username for the employee.");
										String username = scanner.nextLine();
										if (username != null && !username.isEmpty()) {
											System.out.println("Now enter a password for the employee.");
											String password = scanner.nextLine();
											if (password != null && !password.isEmpty()) {
												function.place(new Client(username, password));
												System.out.println("New Emplyee account created!");
											}else {
												System.out.println("An employee without a password could break the bank.");
												System.out.println("Try again.");
												System.out.println();
											}
										}else {
											System.out.println("Can't make an employee without a username.");
											System.out.println("Try again!");
											System.out.println();
										}
										break;
									}
								}while (choice != 6);
							}else {
								System.out.println("These are not admin credentials.");
								System.out.println("Perhaps you meant to log in somewhere else?");
								System.out.println();
							}
						} else {
							System.out.println("You seem to have forgotten to enter you password.");
							System.out.println("An admin really shouldn't do that.");
							System.out.println();
						}
					}else {
						System.out.println("You seem to have forgotten to enter a username.");
						System.out.println("Please try again.");
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
					System.out.println("Please enter a username.");
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
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number.");
				System.out.println();
			}
		} while(option != 3);
		connectionUtil.close();
	}
}
//This is a test comment to check git control.