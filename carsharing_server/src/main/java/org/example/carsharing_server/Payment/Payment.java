package org.example.carsharing_server.Payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import jakarta.persistence.*;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.User.User;

import java.sql.Date;

/*
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
*/
@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int paymentID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User payer;
    @OneToOne(mappedBy = "payment")
    private Booking booking;
    private int amount;
    private Date date;

    public Payment(int amount, Date date, User payer, Booking booking) {
        this.amount = amount;
        this.date = date;
        this.payer = payer;
        this.booking = booking;
    }

    public Payment() {

    }


    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentId) {
        this.paymentID = paymentId;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBookingId(Booking booking) {
        this.booking = booking;
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

    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate6Module()
                .enable(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS)
                .enable(Hibernate6Module.Feature.WRITE_MISSING_ENTITIES_AS_NULL));
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Object not mappable");
        }
    }
}
