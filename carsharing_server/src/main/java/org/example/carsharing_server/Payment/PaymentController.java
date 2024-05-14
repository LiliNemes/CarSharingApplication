package org.example.carsharing_server.Payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {this.paymentService = paymentService;}

    @GetMapping("/payments")
    public ResponseEntity<String> getPayment() {
        List<Payment> payments = paymentService.getPayments();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(payments);
        } catch (JsonProcessingException e) {
            res = payments.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public void addNewPayment(@RequestBody Payment payment) {
        try {
            paymentService.addNewPayment(payment);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    @GetMapping("/payment/{userId}")
    public ResponseEntity<String> getUsersPayments(@PathVariable String userId) {
        List<Payment> payments = paymentService.getUsersPayments(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(payments);
        } catch (JsonProcessingException e) {
            res = payments.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/payment/{bookingId}")
    public ResponseEntity<String> getPaymentofBooking(@PathVariable String bookingId) {
        List<Payment> payments = paymentService.getPaymentOfBooking(bookingId);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(payments);
        } catch (JsonProcessingException e) {
            res = payments.toString();
        }
        return ResponseEntity.ok(res);
    }
}
