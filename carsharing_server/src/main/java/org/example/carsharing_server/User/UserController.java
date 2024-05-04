package org.example.carsharing_server.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        List<User> users = userService.getUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            res = users.toString();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        try {
            userService.addNewUser(user);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/users/{licenseplate}")
    public ResponseEntity<String> getCarOwner(@PathVariable String licenseplate) {
        List<User> users = userService.getCarOwner(licenseplate);
        ObjectMapper objectMapper = new ObjectMapper();
        String res;
        try {
            res = objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            res = users.toString();
        }
        return ResponseEntity.ok(res);
    }

}






