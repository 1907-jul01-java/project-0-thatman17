    
package com.revature.entities;

import java.util.List;

/**
 * Dao
 */
public interface Dao<E> {
    void insert(E e);

    List<E> getAll(String validate);
    
    List<E> adminDisplay(String username);

    void update();

    void delete(String username);
    
    int checkLogin(String username, String password);
    
    int checkBalance(String username);
    
    void addMoney(String username, int money);
    
    void takeMoney(String username, int money);
    
    boolean checkEmpLogin(String username, String password, String info);
    
    void approval(String username, int approval);
    
    boolean checkAdminLogin(String username, String password, int code);
    
    List<E> adminGetAll();
}