package org.example.carsharing_server.User.email;

import jakarta.validation.constraints.Email;


public record EmailDetails(
        @Email String recipient,
        String msgBody,
        String subject
) {}
