
package com.model;

import com.utils.DatabaseHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingModel {
    public void saveBooking(Date bookingDate, Date endDate, String pickUpLocation, String destination) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "INSERT INTO booking (bookingDate, endDate, location, destination, createdTime) VALUES (?, ?, ?, ?, NOW())";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, dateFormat.format(bookingDate));
                statement.setString(2, dateFormat.format(endDate));
                statement.setString(3, pickUpLocation);
                statement.setString(4, destination);

                statement.executeUpdate();
            }
        }
    }
}
