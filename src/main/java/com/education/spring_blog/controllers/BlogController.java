package com.education.spring_blog.controllers;

import com.education.spring_blog.models.Post;
import com.education.spring_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController extends PostService {

    @Autowired
    private PostService postService;

    // обрабатывается URl: адрес сайта
    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        postService.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postService.existsId(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postService.findId(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if(!postService.existsId(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postService.findId(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postService.findId(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postService.save(post);
        return "redirect:/blog";
    }


    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postService.findId(id).orElseThrow();
        postService.delete(id);
        return "redirect:/blog";
    }

    @RequestMapping(path = {"/blog/filter"})
    public String getFilterBlog (@RequestParam(required = false) String titleFind, Model model) {
        if(titleFind != null) {
            Iterable<Post> posts = postService.findTitle(titleFind);
            model.addAttribute("posts", posts);
            return "filter";
        }
        return "redirect:/blog";
    }
}
