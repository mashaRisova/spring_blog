package com.education.spring_blog.repo;

import com.education.spring_blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query
    public Iterable<Post> findByTitleContains(String titleFind);
}
