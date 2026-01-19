package com.long1dep.myportfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();
}
