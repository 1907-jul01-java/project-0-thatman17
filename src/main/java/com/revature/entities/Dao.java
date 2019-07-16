    
package com.revature.entities;

import java.util.List;

/**
 * Dao
 */
public interface Dao<E> {
    void insert(E e);

    List<E> getAll(String validate);
    

    void update();

    void delete();
    
    int checkLogin(String username, String password);
    
    int checkBalance(String username);
    
    void addMoney(String username, int money);
    
    void takeMoney(String username, int money);
    
    boolean checkEmpLogin(String username, String password, String info);
    
    List<E> display(String username);
}