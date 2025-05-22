package com.alsaleh.reddit.controller;

import com.alsaleh.reddit.dto.CommentsDto;
import com.alsaleh.reddit.repository.PostRepository;
import com.alsaleh.reddit.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments/")
public class CommentsController {

    private final CommentService commentService;
    private final PostRepository post;


    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
       // System.out.println(post.getId());
        //System.out.println(post.getUser()); // This will help confirm it's null
        System.out.println("post.getId()");

        post.findById(commentsDto.getPostId());
        System.out.println(post.findById(commentsDto.getPostId()));

        commentService.save(commentsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(userName));
    }
}
