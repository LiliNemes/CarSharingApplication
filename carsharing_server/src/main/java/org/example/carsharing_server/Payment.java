package org.example.carsharing_server;
import java.sql.*;

class PaymentDAO {
    private Connection connection;

    public PaymentDAO() {
        try {
            connection = DriverManager.getConnection("", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Payment {
    private int paymentId;
    private User userId;
    private Booking bookingId;
    private int amount;
    private Date date;
    private String payment_method;

    public Payment(int amount, Date date, String payment_method) {
        this.amount = amount;
        this.date = date;
        this.payment_method = payment_method;
    }


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
