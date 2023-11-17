
package com.model;

import com.utils.DatabaseHelper;
import static com.utils.DatabaseHelper.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CabModel {
    private String brand;
    private String engineModel;
    private int capacity;
    private String cabType;

    private Connection connection;
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getCabType() {
        return cabType;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }
    
    public CabModel() throws SQLException {
        this.connection = DatabaseHelper.getConnection(); // connection 
    }
    
    public ResultSet getCabData() throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); 
            String query = "SELECT * FROM cab WHERE isDeleted = 0";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
        
        throw e;
    }
    }
    
    private void closeResources(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    public void deleteCab(String cabId) throws SQLException {
        
    String query = "UPDATE cab SET isDeleted = 1 WHERE id = ?";
    try (Connection conn = getConnection(); 
         PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, cabId);
        statement.executeUpdate();
    }
    }
    
    public Cab getCabById(String cabId) throws SQLException {
        

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); 
            String query = "SELECT * FROM cab WHERE id = ? AND isDeleted = 0";
            pst = conn.prepareStatement(query);
            pst.setString(1, cabId);
            rs = pst.executeQuery();

            if (rs.next()) {
                Cab cab = new Cab();
                cab.setBrand(rs.getString("brand"));
                cab.setEngineModel(rs.getString("engineModel"));
                cab.setCapacity(rs.getInt("capacity"));
                cab.setCabType(rs.getString("cabType"));
                return cab;
            } else {
                return null; 
            }
        } finally {
            closeResources(conn, pst, rs);
        }
    }
    
    public void updateCab(Cab cab) throws SQLException {
        String query = "UPDATE cab SET brand = ?, engineModel = ?, capacity = ?, cabType = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cab.getBrand());
            preparedStatement.setString(2, cab.getEngineModel());
            preparedStatement.setInt(3, cab.getCapacity());
            preparedStatement.setString(4, cab.getCabType());
            preparedStatement.setString(5, cab.getId());

            preparedStatement.executeUpdate();
        }
    }


}
