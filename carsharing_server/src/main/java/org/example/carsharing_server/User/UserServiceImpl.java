package org.example.carsharing_server.User;

import jakarta.transaction.Transactional;
import org.example.carsharing_server.User.login.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.example.carsharing_server.User.UserSpecification.carsOwner;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public List<User> getUsers() {return userRepository.findAll();}

    @Override
    @Transactional
    public void updateUser(User user, UserDetails userDetails) {
        User existingUser = userRepository.findById(user.getUser_id()).orElseThrow(() ->
                new IllegalArgumentException("User Id " + user.getUser_id() + "does not exist"));

        if (!existingUser.getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to update other's account!");
        }

        existingUser.setBalance(user.getBalance());

        if (user.getPassword() != null && !user.getPassword().isBlank())
            existingUser.setPassword(user.getPassword());
    }

    @Override
    public void deleteUser(int userId, UserDetails userDetails) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User id " + userId + " does not exist"));

        if (!user.getEmail().equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("User is trying to delete other's account!");
        }

        userRepository.delete(user);
    }

    @Override
    public Optional<User> getCarOwner(String licensePlate) {
        return userRepository.findOne(carsOwner(licensePlate));
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User id " + userId + " does not exist"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
