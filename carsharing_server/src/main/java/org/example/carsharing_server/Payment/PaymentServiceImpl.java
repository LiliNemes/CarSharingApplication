package org.example.carsharing_server.Payment;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.Payment.PaymentSpecification.bookingsPayments;
import static org.example.carsharing_server.Payment.PaymentSpecification.usersPayments;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, UserService userService) {this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    @Override
    public List<Payment> getPayments() {return paymentRepository.findAll();}

    @Override
    public void addNewPayment(Payment payment, UserDetails userDetails) {
        if (!userService.getUser(payment.getPayer().getUser_id()).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's payments!");
        }
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getUsersPayments(int userId, UserDetails userDetails) {
        if (!userService.getUser(userId).getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to access other's payments!");
        }
        return paymentRepository.findAll(usersPayments(userId));
    }

    @Override
    public List<Payment> getPaymentOfBooking(int bookingId) {
        return paymentRepository.findAll(bookingsPayments(bookingId));
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        Payment old = paymentRepository.findById(payment.getPaymentID()).orElseThrow(() ->
                new IllegalArgumentException("Payment Id " + payment.getPaymentID() + "does not exist"));

        if (payment.getBooking() != null)
            old.setBooking(payment.getBooking());
    }
}
