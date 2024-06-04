package org.example.carsharing_server.User;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getUsers();

    @Transactional
    void updateUser(User user, UserDetails userDetails);

    void deleteUser(int userId, UserDetails userDetails);

    Optional<User> getCarOwner(String licensePlate);

    User getUser(int userId);
}
