
package com.dao;

import com.model.UserModel;
import com.utils.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO() throws SQLException {
        
        try {
            this.connection = DatabaseHelper.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error establishing database connection: " + e.getMessage());
            throw e; 
        }
    }

    public void insertUser(UserModel user) {
        String query = "INSERT INTO user (name, address, nic, age, userName, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getNic());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
    }
}
