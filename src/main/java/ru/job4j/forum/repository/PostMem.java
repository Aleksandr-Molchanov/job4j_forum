package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final AtomicInteger size = new AtomicInteger(3);

    public PostMem() {
        posts.put(1, Post.of("Продажа машины.", "Продаю машину ладу 01.", new Date(System.currentTimeMillis())));
        posts.put(2, Post.of("Продажа машины.", "Продаю машину ладу 02.", new Date(System.currentTimeMillis())));
        posts.put(3, Post.of("Продажа машины.", "Продаю машину ладу 03.", new Date(System.currentTimeMillis())));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        int id = size.incrementAndGet();
        post.setId(id);
        posts.put(id, post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }
}
