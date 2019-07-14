package com.revature;

import java.util.Scanner;
import com.revature.entities.*;
import com.revature.util.*;
import com.revature.models.*;

public class Main{

	public static void main(String[] args) {
		int option;
		int select;
		String answer1 = new String();
		String answer2 = new String();
		do {
			Menu menu = new Menu();
			menu.title();
			menu.initial();
			Scanner scanner = new Scanner(System.in);
			option = Integer.parseInt(scanner.nextLine());
			switch (option) {
			case 1: 
				menu.title();
				menu.log();
				select = scanner.nextInt();
				switch (select) {
				case 1:
					menu.title();
					int c = 0;
					do {
						System.out.println("Please enter username");
						scanner.nextLine();
						if (answer1 != null && !answer1.isEmpty()) {
							System.out.println("Please enter password");
							answer2 = scanner.nextLine();
							if (answer2 != null) {
								c++;
							} else {
								System.out.println("Please enter password");
							}
						} else {
							System.out.println("Please enter a name");
						} 
					} while (c != 1);
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
									System.out.println("Thank you. Your information has been received, and is pending bank approval. Goodbye!");
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
				ConnectionUtil connectionUtil = new ConnectionUtil();
		        MovieDao movieDao = new MovieDao(connectionUtil.getConnection());
		        movieDao.insert(new Movie("The Dark Knight", 2007));
		        System.out.println(movieDao.getAll());
		        connectionUtil.close();
				break;
			}
		} while(option != 3);
	}
}