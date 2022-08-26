package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserMem;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private UserMem userMem;

    public UserService(UserMem userMem) {
        this.userMem = userMem;
    }

    public Collection<User> getAllUsers() {
        return userMem.findAll();
    }

    public void addUser(User user) {
        userMem.add(user);
    }

    public User findById(int id) {
        return userMem.findById(id);
    }

    public void update(User user) {
        userMem.update(user);
    }

    public Optional<User> findByUsername(String username) {
        return userMem.findByUsername(username);
    }
}