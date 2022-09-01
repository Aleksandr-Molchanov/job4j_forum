package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.Optional;

@Service
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Iterable<Post> getAllPosts() {
        return repository.findAll();
    }

    public void addPost(Post post) {
        repository.save(post);
    }

    public Optional<Post> findById(int id) {
        return repository.findById(id);
    }

    public void update(Post post) {
        repository.save(post);
    }

    public void delete(Post post) {
        repository.delete(post);
    }
}