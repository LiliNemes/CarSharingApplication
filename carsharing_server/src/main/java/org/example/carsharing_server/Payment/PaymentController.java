package org.example.carsharing_server.Payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {this.paymentService = paymentService;}

    @GetMapping
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
    public void addNewPayment(@Valid @RequestBody Payment payment, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            paymentService.addNewPayment(payment, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<String> getUsersPayments(@Valid @PathVariable @Min(1) Integer userId, @AuthenticationPrincipal UserDetails userDetails) {
        List<Payment> payments = paymentService.getUsersPayments(userId, userDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(payments);
        } catch (JsonProcessingException e) {
            res = payments.toString();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<String> getPaymentofBooking(@Valid @PathVariable @Min(0) Integer bookingId) {
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
