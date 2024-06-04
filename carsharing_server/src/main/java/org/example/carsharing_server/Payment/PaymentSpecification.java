package org.example.carsharing_server.Payment;

import jakarta.persistence.criteria.Join;
import org.example.carsharing_server.Booking.Booking;
import org.example.carsharing_server.User.User;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpecification {
    static Specification<Payment> usersPayments(int userID) {
        return (root, query, builder) -> {
            Join<User, Payment> usersPayment = root.join("user");
            return builder.equal(usersPayment.get("userID"), userID);
        };
    }

    static Specification<Payment> bookingsPayments(int bookingID) {
        return (root, query, builder) -> {
            Join<Booking, Payment> bookingPayments = root.join("booking");
            return builder.equal(bookingPayments.get("bookingID"), bookingID);
        };
    }
}
