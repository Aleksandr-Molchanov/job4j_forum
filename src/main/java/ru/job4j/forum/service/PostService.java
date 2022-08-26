package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMem;

import java.util.Collection;

@Service
public class PostService {

    private PostMem postMem;

    public PostService(PostMem postMem) {
        this.postMem = postMem;
    }

    public Collection<Post> getAllPosts() {
        return postMem.findAll();
    }

    public void addPost(Post post) {
        postMem.add(post);
    }

    public Post findById(int id) {
        return postMem.findById(id);
    }

    public void update(Post post) {
        postMem.update(post);
    }
}