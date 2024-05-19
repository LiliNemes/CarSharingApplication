package org.example.carsharing_server;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.Payment.Payment;
import org.example.carsharing_server.Payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MoneyService {

    @Autowired
    PaymentRepository paymentRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updatePayments() {
        List<Payment> payments = paymentRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for (Payment payment : payments){
            if (payment.getBooking() == null || payment.getBooking().getCar() == null)
                continue;

            if (payment.getBooking().getEnd_time() == null || payment.getBooking().getEnd_time().isAfter(now)){
                payment.setAmount(payment.getAmount() + payment.getBooking().getCar().getPrice());
                payment.setDate(now);
            }
        }
    }
}
