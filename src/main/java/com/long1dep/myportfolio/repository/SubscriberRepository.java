package com.long1dep.myportfolio.repository;

import com.long1dep.myportfolio.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    boolean existsByEmail(String email);
}
