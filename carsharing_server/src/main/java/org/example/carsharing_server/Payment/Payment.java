package org.example.carsharing_server.Payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.User.User;

import java.time.LocalDateTime;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int paymentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User payer;

    @JsonIgnore
    @OneToOne(mappedBy = "payment")
    private Booking booking;

    private double amount;

    private LocalDateTime date;

    public Payment(double amount, LocalDateTime date, User payer) {
        this.amount = amount;
        this.date = date;
        this.payer = payer;
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

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
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
