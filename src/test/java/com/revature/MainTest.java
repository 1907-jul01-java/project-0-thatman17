package com.revature;

import java.util.Scanner;

public class MainTest {

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
			option = scanner.nextInt();
			switch (option) {
			case 1: 
				menu.title();
				menu.log();
				select = scanner.nextInt();
				switch (select) {
				case 1:
					menu.title();
					menu.query1();
					answer1 = scanner.nextLine();
					break;
				}
				break;
			case 2:
				menu.title();
				menu.sign();
				select = scanner.nextInt();
				break;
			case 3:
				System.out.println("Thank you for banking with us!");
				scanner.close();
				break;
			}
		} while(option != 3);
	}
}