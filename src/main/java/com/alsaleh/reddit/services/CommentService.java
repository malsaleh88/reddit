package com.alsaleh.reddit.services;

import com.alsaleh.reddit.dto.CommentsDto;
import com.alsaleh.reddit.exceptions.SpringRedditException;
import com.alsaleh.reddit.mapper.CommentMapper;
import com.alsaleh.reddit.model.Comment;
import com.alsaleh.reddit.model.NotificationEmail;
import com.alsaleh.reddit.model.Post;
import com.alsaleh.reddit.model.User;
import com.alsaleh.reddit.repository.CommentRepository;
import com.alsaleh.reddit.repository.PostRepository;
import com.alsaleh.reddit.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService {

    private static final @NotBlank(message = "Username is required") String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new SpringRedditException.PostNotFoundException(commentsDto.getPostId().toString()));
        System.out.println("comment7777777");

        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        System.out.println("commentrfjkjgf");

        commentRepository.save(comment);


        System.out.println("comment");
        String message = mailContentBuilder.build(post.getUser().getUsername() +
                " posted a comment on your post." + POST_URL);
        System.out.println("crrrrrromment");

        sendCommentNotification(message, post.getUser());
        System.out.println("crrrrbbbbbbbbrromment");

    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(
                user.getUsername() + " Commented on your post",
                user.getEmail(),
                message
        ));
    }


    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new SpringRedditException.PostNotFoundException(postId.toString()));

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));

        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
