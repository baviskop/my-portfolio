package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.ContactRequest;
import com.long1dep.myportfolio.entity.ContactMessage;
import com.long1dep.myportfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactMessageRepository repo;
    private final MailService mailService;

    public void save(ContactRequest r) {
        ContactMessage m = new ContactMessage();
        m.setName(r.getName());
        m.setEmail(r.getEmail());
        m.setSubject(r.getSubject());
        m.setMessage(r.getMessage());

        repo.save(m);

        mailService.sendToAdmin(
                "New contact: " + r.getSubject(),
                "From: " + r.getEmail() + "\n\n" + r.getMessage()
        );
    }

    public List<ContactMessage> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
