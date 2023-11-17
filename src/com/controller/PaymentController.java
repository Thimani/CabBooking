
package com.controller;

import com.model.PaymentModel;
import com.view.Payment;

import java.sql.SQLException;

public class PaymentController {
    private Payment paymentView;
    private PaymentModel paymentModel;

    public PaymentController(Payment paymentView, PaymentModel paymentModel) {
        this.paymentView = paymentView;
        this.paymentModel = paymentModel;
    }

    public void handlePaymentSubmission(String amount, String reference) throws SQLException {
        
        paymentModel.savePayment(amount, reference);
    }
}
