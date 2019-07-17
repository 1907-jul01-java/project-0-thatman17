package com.revature;

class Menu {
	public void title() {
		System.out.println("Welcome to Meade Bank!");
		System.out.println();
	}
	public void initial() {
		System.out.println("Please enter a number so we might assist you.");
		System.out.println("1: Log In");
		System.out.println("2: Sign In");
		System.out.println("3: Exit");
	}
	public void log() {
		System.out.println("Please choose an option.");
		System.out.println("1: Client Log-In");
		System.out.println("2: Employee Log-In");
		System.out.println("3: Admin Log-In");
		System.out.println("4: Exit");
	}
	public void query1() {
		System.out.println("Please enter your username");
	}
	public void query2() {
		System.out.println("Please enter your password");
	}
}
