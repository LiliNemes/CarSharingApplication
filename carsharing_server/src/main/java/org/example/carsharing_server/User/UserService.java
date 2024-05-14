package org.example.carsharing_server.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface UserService {
    List<User> getUsers();

    void addNewUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);

    List<User> getCarOwner(String licensePlate);
}
