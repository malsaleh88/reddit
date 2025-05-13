package com.alsaleh.reddit.repository;

import com.alsaleh.reddit.model.Post;
import com.alsaleh.reddit.model.Subreddit;
import com.alsaleh.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    List<Post> findAllBySubreddit(Subreddit subreddit);
}