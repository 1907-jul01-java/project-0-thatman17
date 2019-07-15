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
		System.out.println("Please select your designation.");
		System.out.println("1: Client Sign-In");
		System.out.println("2: Employee Sign-In");
		System.out.println("3: Admin Sign-In");
	}
	public void sign() {
		System.out.println("We're so happy you've decided to sign up with us!");
		System.out.println("We just need a bit of information to get started.");
		System.out.println("Please input your first and last name.");
	}
	public void query1() {
		System.out.println("Please enter your username");
	}
	public void query2() {
		System.out.println("Please enter your password");
	}
}
