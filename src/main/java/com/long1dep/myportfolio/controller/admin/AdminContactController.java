package com.long1dep.myportfolio.controller.admin;

import com.long1dep.myportfolio.entity.ContactMessage;
import com.long1dep.myportfolio.entity.Subscriber;
import com.long1dep.myportfolio.service.ContactService;
import com.long1dep.myportfolio.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminContactController {
    private final ContactService contactService;
    private final SubscriberService subscriberService;

    @GetMapping("/contacts")
    public List<ContactMessage> contacts() {
        return contactService.getAll();
    }

    @GetMapping("/subscribers")
    public List<Subscriber> subscribers() {
        return subscriberService.getAll();
    }
}
