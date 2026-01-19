package com.long1dep.myportfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscribers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;

    private LocalDateTime subscribedAt = LocalDateTime.now();
}
