
package com.model;

import com.utils.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentModel {
    public void savePayment(String amount, String reference) throws SQLException {
        try (Connection connection = DatabaseHelper.getConnection()) {
            String sql = "INSERT INTO payment (amount, reference, paymentTime) VALUES (?, ?, NOW())";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(amount));
                statement.setString(2, reference);

                statement.executeUpdate();
            }
        }
    }
}
