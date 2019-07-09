package com.revature;
import java.util.Scanner;

public class MenuTest extends StringHolder {

	public static void main(String[] args) {
		StringHolder Menu = new StringHolder();
		Menu.initial();
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		if (answer.equalsIgnoreCase("log in")) {
			System.out.println("This part isn't ready yet, mate!");
		} else if (answer.equalsIgnoreCase("sign in")) {
			System.out.println("1. If signing up for a client account, type \"client\" 2. If signing in for an employee account, type \"employee\".");
			System.out.println("3. If signing up for an administrator account, type \"admin\".\n" + exit);
		}
		else {
			System.out.println(answer);
		}
		scanner.close();
	}

}
class StringHolder{
	static String exit = "3: At any time, type in \"exit\" to exit the application.";
	public void initial(){
		System.out.println("Welcome to Meade Bank!\n1: To sign in, type \"sign in\".\n2: To log in, type \"log in\". \n" + exit);
	}
}