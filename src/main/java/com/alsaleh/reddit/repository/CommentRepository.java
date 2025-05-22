package com.alsaleh.reddit.repository;

import com.alsaleh.reddit.model.Comment;
import com.alsaleh.reddit.model.Post;
import com.alsaleh.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
}