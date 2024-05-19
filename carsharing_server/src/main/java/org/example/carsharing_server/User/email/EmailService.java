package org.example.carsharing_server.User.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    String sendSimpleMail(EmailDetails detals);
}
