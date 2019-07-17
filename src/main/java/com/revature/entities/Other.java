package com.revature.entities;

import java.util.List;

public interface Other<C> {
	
	List<C> display(String username);
	
	List<C> getEmployees();
	
	void place(C c);
	
	void bigRed();
	
	boolean check(String username);
}
