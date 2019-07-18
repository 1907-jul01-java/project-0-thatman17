package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.*;

/**
 * MovieDao
 */
public class Function implements Dao<Holder>, Other<Client> {
    Connection connection;

    @Override
    public void insert(Holder holder) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("insert into clients(username, password, balance) values(?, ?, 0)");
            pStatement.setString(1, holder.getUsername());
            pStatement.setString(2, holder.getPassword());
            pStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    @Override
    public void place(Client client) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("insert into employees(username, password) values(?, ?)");
    		pt.setString(1, client.getUsername());
    		pt.setString(2, client.getPassword());
    		pt.executeUpdate();
    	}catch (SQLException e) {}
    }

    @Override
    public List<Holder> getAll(String validate) {
        Holder holder;
        List<Holder> holders = new ArrayList<>();
        String strQuery = "select * from clients where validate $something";
        try {
        	String query = strQuery.replace("$something", validate);
            PreparedStatement pt = connection.prepareStatement(query);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                holder = new Holder();
                holder.setUsername(resultSet.getString("username"));
                holder.setBalance(resultSet.getInt("balance"));
                holders.add(holder);
            }
        } catch (SQLException e) {

        }
        return holders;
    }
    
    @Override
    public List<Client> getAllClients(String validate) {
        Client client;
        List<Client> clients = new ArrayList<>();
        String strQuery = "select * from clients where validate $something";
        try {
        	String query = strQuery.replace("$something", validate);
            PreparedStatement pt = connection.prepareStatement(query);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setUsername(resultSet.getString("username"));
                client.setPassword(resultSet.getString("password"));
                clients.add(client);
            }
        } catch (SQLException e) {

        }
        return clients;
    }
    
    @Override
    public List<Holder> adminGetAll(){
    	Holder holder;
    	List<Holder> holders = new ArrayList<>();
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultset = statement.executeQuery("select * from clients where validate = 1");
    		while (resultset.next()) {
    			holder = new Holder();
    			holder.setUsername(resultset.getString("username"));
    			holder.setBalance(resultset.getInt("balance"));
    			holders.add(holder);
    		}
    	}catch(SQLException e) {}
    	return holders;
    }
    
    @Override
    public List<Client> getEmployees(){
    	Client client;
    	List<Client> clients = new ArrayList<>();
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultset = statement.executeQuery("select * from employees");
    		while (resultset.next()) {
    			client = new Client();
    			client.setUsername(resultset.getString("username"));
    			client.setPassword(resultset.getString("password"));
    			clients.add(client);
    		}
    	}catch(SQLException e) {}
    	return clients;
    }
    
    @Override
    public List<Client> display(String username){
    	Client holder;
    	List<Client> holders = new ArrayList<>();
    	try {
    		PreparedStatement pt = connection.prepareStatement("select username, password from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		while (resultset.next()) {
    			holder = new Client();
    			holder.setUsername(resultset.getString("username"));
    			holder.setPassword(resultset.getString("password"));
    			holders.add(holder);
    		}
    	}catch (SQLException e) {
    		
    	} return holders;
    }
    
    @Override
    public List<Holder> adminDisplay(String username){
    	Holder holder;
    	List<Holder> holders = new ArrayList<>();
    	try {
    		PreparedStatement pt = connection.prepareStatement("select username, balance from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		while (resultset.next()) {
    			holder = new Holder();
    			holder.setUsername(resultset.getString("username"));
    			holder.setBalance(resultset.getInt("balance"));
    			holders.add(holder);
    		}
    	}catch (SQLException e) {
    		
    	} return holders;
    }
    
    @Override
    public int checkLogin(String username, String password) {
    	int checker = 0;
    	try {
    		PreparedStatement pt = connection.prepareStatement("select username, password, validate from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		String baName = "", baPass = "";
    		int baValid = 0;
    		while (resultset.next()) {
    			baName = resultset.getString("username");
    			baPass = resultset.getString("password");
    			baValid = resultset.getInt("validate");
    		}
    		if (baPass.equals(password) && (baName.equals(username) && (baValid == 2))) {
    			checker = 1;
    		} else if (baPass.equals(password) && (baName.equals(username) && (baValid == 1))){
    			checker = 2;
    		} else if (baPass.equals(password) && (baName.equals(username))) {
    			checker = 3;
    		}
    	} catch (Exception e) {
    	} return checker;
    }
    
    @Override
    public boolean checkAdminLogin(String username, String password, int code) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("select username, password, code from admin where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		String baName ="", baPass = "";
    		int adCode = 0;
    		while (resultset.next()) {
    			baName = resultset.getString("username");
    			baPass = resultset.getString("password");
    			adCode = resultset.getInt("code");
    		}
    		if (baPass.contentEquals(password) && (baName.equals(username) && (adCode == code))){
    			return true;
    		}
    	}catch (Exception e) {}
    	return false;
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
    public void takeMoney(String username, int money) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("update clients set balance = balance - ? where username =?");
    		pt.setInt(1, money);
    		pt.setString(2, username);
    		pt.executeUpdate();
    	}catch (Exception e) {}
    }
    
    @Override
    public boolean checkEmpLogin(String username, String password, String info) {
    	String strQuery = "select username, password from $tableName where username =?";
    	try {
    		String query = strQuery.replace("$tableName", info);
    		PreparedStatement pt = connection.prepareStatement(query);
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		String baName = "", baPass = "";
    		while (resultset.next()) {
    			baName = resultset.getString("username");
    			baPass = resultset.getString("password");
    		}
    		if (baPass.equals(password) && (baName.equals(username))){
    			return true;
    		}
    	}catch (Exception e) {}
    	return false;
    }
    
    @Override
    
    public void approval(String username, int approval) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("update clients set validate = ? where username =?");
    		pt.setString(2, username);
    		if (approval == 1) {
    			pt.setInt(1, 1);
    			pt.executeUpdate();
    		} else if (approval == 2) {
    			pt.setInt(1, 2);
    			pt.executeUpdate();
    		} else {}
    	}catch (Exception e) {}
    }

    @Override
    public void delete(String username) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("delete from employees where username =?");
    		pt.setString(1, username);
    		pt.execute();
    	}catch (Exception e) {}
    }
    
    @Override
    public void bigRed() {
    	try {
    		PreparedStatement pt = connection.prepareStatement("truncate employees, clients");
    		pt.execute();
    	}catch (Exception e) {}
    }
    
    @Override
    public boolean check(String username) {
    	try {
    		PreparedStatement pt = connection.prepareStatement("select username, validate from clients where username =?");
    		pt.setString(1, username);
    		ResultSet resultset = pt.executeQuery();
    		String baName ="";
    		int validate = 0;
    		while (resultset.next()){
    			baName = resultset.getString("username");
    			validate = resultset.getInt("validate");
    		}
    		if (baName.equalsIgnoreCase(username) && validate == 1) {
    			return true;
    		}
    	}catch (Exception e) {}
    	return false;
    }

    public Function(Connection connection) {
        this.connection = connection;
    }
}