package org.example.carsharing_server.Payment;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PaymentService {
    List<Payment> getPayments();

    void addNewPayment(Payment payment, UserDetails userDetails);

    List<Payment> getUsersPayments(int userId, UserDetails userDetails);
    List<Payment> getPaymentOfBooking(int bookingId);

    void updatePayment(Payment payment);
}
