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
            PreparedStatement pStatement = connection.prepareStatement("insert into movies(title, year) values(?, ?)");
            pStatement.setString(1, movie.getTitle());
            pStatement.setInt(2, movie.getYear());
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
            ResultSet resultSet = statement.executeQuery("select * from movies");
            while (resultSet.next()) {
                movie = new Movie();
                movie.setTitle(resultSet.getString("title"));
                movie.setYear(resultSet.getInt("year"));
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