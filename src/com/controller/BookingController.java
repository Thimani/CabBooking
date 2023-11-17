
package com.controller;

import com.model.BookingModel;
import com.view.Booking;
import java.sql.SQLException;
import java.util.Date;

public class BookingController {
   private Booking bookingView;
    private BookingModel bookingModel;

    public BookingController(Booking bookingView, BookingModel bookingModel) {
        this.bookingView = bookingView;
        this.bookingModel = bookingModel;
    }

    public BookingController() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void handleBookingSubmission(Date bookingDate, Date endDate, String pickUpLocation, String destination) throws SQLException {
        // save the booking
        bookingModel.saveBooking(bookingDate, endDate, pickUpLocation, destination);
    }
}