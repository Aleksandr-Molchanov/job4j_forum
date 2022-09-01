package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Date;

@Controller
public class PostControl {

    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/formAddPost")
    public String formAddPost(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "addPost";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute Post post) {
        post.setCreated(new Date(System.currentTimeMillis()));
        posts.addPost(post);
        return "redirect:/index";
    }

    @GetMapping("/descriptionPost/{postId}")
    public String descriptionPost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", posts.findById(id));
        return "post";
    }

    @GetMapping("/formEditPost/{postId}")
    public String formEditPost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", posts.findById(id));
        return "editPost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCreated(new Date(System.currentTimeMillis()));
        posts.update(post);
        return "redirect:/index";
    }

    @GetMapping("/deletePost/{postId}")
    public String deletePost(@PathVariable("postId") int id) {
        posts.delete(posts.findById(id).get());
        return "redirect:/index";
    }
}