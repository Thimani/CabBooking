
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.utils.DatabaseHelper;
import com.model.CabModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CabDAO {
    private Connection connection;

    public CabDAO() throws SQLException {
        connection = DatabaseHelper.getConnection();
    }

    public void insertCab(CabModel cab) throws SQLException {
        String query = "INSERT INTO cab (brand, engineModel, capacity, cabType) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cab.getBrand());
            preparedStatement.setString(2, cab.getEngineModel());
            preparedStatement.setInt(3, cab.getCapacity());
            preparedStatement.setString(4, cab.getCabType());

            preparedStatement.executeUpdate();
        }
    }

     public List<String> getAvailableCabIdsAsString() throws SQLException {
        List<String> availableIds = new ArrayList<>();

       
        String query = "SELECT id FROM cab WHERE isDeleted = 0";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                
                String id = resultSet.getString("id");
                availableIds.add(id);
            }
        }

        return availableIds;
    }
    
}
