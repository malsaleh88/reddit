package com.alsaleh.reddit.exceptions;


public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String exMessage) {
        super(exMessage);
    }

    public static class PostNotFoundException extends RuntimeException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }

    public class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }

    public static class SubredditNotFoundException extends RuntimeException {
        public SubredditNotFoundException(String message) {
            super(message);
        }
    }

}
