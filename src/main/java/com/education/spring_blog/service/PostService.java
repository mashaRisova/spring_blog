package com.education.spring_blog.service;

import com.education.spring_blog.models.Post;
import com.education.spring_blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Iterable<Post> getAll() {
        return postRepository.findAll();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(long id) {
        postRepository.deleteById(id);
    }

    public Optional<Post> findId(long id) {
        return Optional.of(postRepository.findById(id).orElseThrow());

    }

    public boolean existsId(long id) {
        return postRepository.existsById(id);
    }

    public Iterable<Post> findTitle(String titleFind) {
        return postRepository.findByTitleContains(titleFind);
    }
}
