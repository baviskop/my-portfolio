package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.entity.Subscriber;
import com.long1dep.myportfolio.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscriberRepository repo;
    private final MailService mailService;

    public void subscribe(String email) {
        if(repo.existsByEmail(email)) {
            return;
        }

        Subscriber s = new Subscriber();
        s.setEmail(email);
        repo.save(s);

        mailService.sendToAdmin(
                "New subscriber",
                "Email: " + email
        );
    }

    public List<Subscriber> getAll() {
        return repo.findAll();
    }
}
