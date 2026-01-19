package com.long1dep.myportfolio.repository;

import com.long1dep.myportfolio.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
