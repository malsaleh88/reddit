package com.alsaleh.reddit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Instant expiryDate;
}