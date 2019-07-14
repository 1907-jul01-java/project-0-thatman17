package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Movie;

/**
 * MovieDao
 */
public class MovieDao implements Dao<Movie> {
    Connection connection;

    @Override
    public void insert(Movie movie) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("insert into clients(username, password) values(?, ?)");
            pStatement.setString(1, movie.getUsername());
            pStatement.setString(2, movie.getPassword());
            pStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public List<Movie> getAll() {
        Movie movie;
        List<Movie> movies = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clients");
            while (resultSet.next()) {
                movie = new Movie();
                movie.setUsername(resultSet.getString("username"));
                movie.setPassword(resultSet.getString("password"));
                movies.add(movie);
            }
        } catch (SQLException e) {

        }
        return movies;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public MovieDao(Connection connection) {
        this.connection = connection;
    }
}