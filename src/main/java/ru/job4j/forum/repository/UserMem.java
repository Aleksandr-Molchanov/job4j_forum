package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMem {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(1);

    public UserMem() {
        users.put(1, User.of("user123", "123"));
    }

    public Collection<User> findAll() {
        return users.values();
    }

    public void add(User user) {
        int id = size.incrementAndGet();
        user.setId(id);
        users.put(id, user);
    }

    public User findById(int id) {
        return users.get(id);
    }

    public void update(User user) {
        users.put(user.getId(), user);
    }

    public Optional<User> findByUsername(String userName) {
        Optional<User> rsl = Optional.empty();
        for (User user : users.values()) {
            if (user.getUsername().equals(userName)) {
                rsl = Optional.of(user);
                break;
            }
        }
        return rsl;
    }
}