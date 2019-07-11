package com.revature;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int option;
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
				break;
			case 2:
				menu.title();
				menu.sign();
				break;
			case 3:
				System.out.println("Thank you for banking with us!");
				scanner.close();
				break;
			}
		} while(option != 3);
	}
}