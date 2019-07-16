package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Holder;

/**
 * MovieDao
 */
public class Function implements Dao<Holder> {
    Connection connection;

    @Override
    public void insert(Holder holder) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("insert into clients(username, password, validate) values(?, ?, 'f')");
            pStatement.setString(1, holder.getUsername());
            pStatement.setString(2, holder.getPassword());
            pStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public List<Holder> getAll() {
        Holder holder;
        List<Holder> holders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clients");
            while (resultSet.next()) {
                holder = new Holder();
                holder.setUsername(resultSet.getString("username"));
                holder.setPassword(resultSet.getString("password"));
                holders.add(holder);
            }
        } catch (SQLException e) {

        }
        return holders;
    }
    
    @Override
    public int checkLogin(String username, String password) {
    	int checker = 0;
    	try {
    		PreparedStatement pt = connection.prepareStatement("select username, password, validate from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		String baName = "", baPass = "";
    		boolean baValid = false;
    		while (resultset.next()) {
    			baName = resultset.getString("username");
    			baPass = resultset.getString("password");
    			baValid = resultset.getBoolean("validate");
    		}
    		if (baPass.equals(password) && (baName.equals(username) && (baValid == true))) {
    			checker = 2;
    		} else if (baPass.equals(password) && (baName.equals(username))){
    			checker = 1;
    		}
    	} catch (Exception e) {
    	} return checker;
    }

    @Override
    public int checkBalance(String username) {
    	int balance = 0;
    	try {
    		PreparedStatement pt = connection.prepareStatement("select balance from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		while (resultset.next()) {
    			balance = resultset.getInt("balance");
    		}
    	}catch (Exception e) {}
    	return balance;
    }
    @Override
    public void addMoney(String username, int money) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("update clients set balance = balance + ? where username =?");
    		pt.setInt(1, money);
    		pt.setString(2, username);
    		pt.executeUpdate();
    	}catch (Exception e) {}
    }
    
    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public Function(Connection connection) {
        this.connection = connection;
    }
}