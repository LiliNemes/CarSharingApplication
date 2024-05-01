package org.example.carsharing_server.Payment;

import org.example.carsharing_server.Booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.Payment.PaymentSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {this.paymentRepository = paymentRepository;}

    @Override
    public List<Payment> getPayments() {return paymentRepository.findAll();}

    @Override
    public void addNewPayment(Payment payment) {paymentRepository.save(payment);}

    @Override
    public List<Payment> getUsersPayments(String userId) {
        int userIdNum = Integer.parseInt(userId);
        return paymentRepository.findAll(usersPayments(userIdNum));
    }

    @Override
    public List<Payment> getPaymentOfBooking(String bookingId) {
        int bookingIdNum = Integer.parseInt(bookingId);
        return paymentRepository.findAll(bookingsPayments(bookingIdNum));
    }
}
