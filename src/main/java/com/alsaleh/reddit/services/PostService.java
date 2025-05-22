package com.alsaleh.reddit.services;

import com.alsaleh.reddit.dto.PostRequest;
import com.alsaleh.reddit.dto.PostResponse;
import com.alsaleh.reddit.exceptions.SpringRedditException;
import com.alsaleh.reddit.mapper.PostMapper;
import com.alsaleh.reddit.model.Post;
import com.alsaleh.reddit.model.Subreddit;
import com.alsaleh.reddit.model.User;
import com.alsaleh.reddit.repository.PostRepository;
import com.alsaleh.reddit.repository.SubredditRepository;
import com.alsaleh.reddit.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SpringRedditException.SubredditNotFoundException(postRequest.getSubredditName()));
        System.out.println("sammmmmmmservvvvvvvicmmmmme");

        User currentUser = authService.getCurrentUser();

        Post post = postMapper.map(postRequest, subreddit, currentUser);

        // ✅ SAVE the post to the database
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException.PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user).stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SpringRedditException.SubredditNotFoundException(subredditId.toString()));
        return postRepository.findAllBySubreddit(subreddit).stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
