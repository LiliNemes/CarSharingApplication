package org.example.carsharing_server.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.carsharing_server.User.email.EmailDetails;
import org.example.carsharing_server.User.email.EmailService;
import org.example.carsharing_server.User.login.LoginRequest;
import org.example.carsharing_server.User.login.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping
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

    @PutMapping
    public void updateUser(@Valid @RequestBody User user, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            userService.updateUser(user, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@Valid @PathVariable @Min(1) Integer userId, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            userService.deleteUser(userId, userDetails);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GetMapping("/car/{licenseplate}")
    public ResponseEntity<String> getCarOwner(@Valid @PathVariable @NotBlank @Size(min = 6, max = 6) String licenseplate) {
        Optional<User> result = userService.getCarOwner(licenseplate);
        if (result.isPresent())
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String res;
            try {
                res = objectMapper.writeValueAsString(result.get());
            } catch (JsonProcessingException e) {
                res = result.get().toString();
            }
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.ok("");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        System.out.println("Registering user");
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getUserType());

        userRepository.save(user);

        String result = emailService.sendSimpleMail(new EmailDetails(user.getEmail(), "Thank you for registering on our site!", "Welcome at our Carsharing service"));
        System.Logger logger = System.getLogger(UserController.class.getName());
        logger.log(System.Logger.Level.INFO, result);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User u = userRepository.findByEmail(loginRequest.getEmail());

        return new ResponseEntity<>(u.toString(), HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok().body("Logout successful");
    }

}






