package org.example.carsharing_server.Payment;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
interface PaymentService {
    List<Payment> getPayments();

    void addNewPayment(Payment payment);

    List<Payment> getUsersPayments(String userId);
    List<Payment> getPaymentOfBooking(String bookingId);
}
