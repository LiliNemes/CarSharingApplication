package org.example.carsharing_server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class ApplicationController {
    @GetMapping(path = "/")
    public ResponseEntity<String> getHome() {
        return new ResponseEntity<>("Welcome to the Car Sharing Server!", HttpStatus.OK);
    }
}
