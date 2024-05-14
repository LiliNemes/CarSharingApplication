package org.example.carsharing_server.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.carsharing_server.User.UserSpecification.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public List<User> getUsers() {return userRepository.findAll();}

    @Override
    public void addNewUser(User user) {userRepository.save(user);}

    @Override
    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getUser_id()).orElseThrow(() ->
                new IllegalArgumentException("User Id " + user.getUser_id() + "does not exist"));
        existingUser.setUser_id(user.getUser_id());
        existingUser.setBalance(user.getBalance());
        existingUser.setBookings(user.getBookings());
        existingUser.setCars(user.getCars());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPayments(user.getPayments());
        existingUser.setType(user.getType());
        existingUser.setReviews(user.getReviews());
        existingUser.setPhone_number(user.getPhone_number());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User id " + userId + " does not exist"));
        userRepository.delete(user);
    }

    @Override
    public List<User> getCarOwner(String licensPlate) {
        return userRepository.findAll(carsOwner(licensPlate));
    }
}
