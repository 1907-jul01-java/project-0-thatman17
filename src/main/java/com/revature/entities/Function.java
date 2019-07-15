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
    public boolean checkLogin(String username, String password) {
    	PreparedStatement pt = null;
    	try {
    		pt = connection.prepareStatement("select username, password from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		String baName = "", baPass = "";
    		while (resultset.next()) {
    			baName = resultset.getString("username");
    			baPass = resultset.getString("password");
    		}
    		if (baPass.equals(password) && (baName.equals(username))) {
    			return true;
    		} else {
    		}
    	} catch (Exception e) {
    	} return false;
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