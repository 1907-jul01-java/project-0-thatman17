    
package com.revature.entities;

import java.util.List;

/**
 * Dao
 */
public interface Dao<E> {
    void insert(E e);

    List<E> getAll();
    

    void update();

    void delete();
    
    int checkLogin(String username, String password);
    
    int checkBalance(String username);
    
    void addMoney(String username, int money);
}