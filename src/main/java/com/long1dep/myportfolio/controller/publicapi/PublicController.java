package com.long1dep.myportfolio.controller.publicapi;

import com.long1dep.myportfolio.dto.request.ContactRequest;
import com.long1dep.myportfolio.dto.request.SubscribeRequest;
import com.long1dep.myportfolio.dto.response.MessageResponse;
import com.long1dep.myportfolio.dto.response.SearchResponse;
import com.long1dep.myportfolio.service.ContactService;
import com.long1dep.myportfolio.service.SearchService;
import com.long1dep.myportfolio.service.SubscriberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {
    private final ContactService contactService;
    private final SubscriberService subscriberService;
    private final SearchService searchService;

    @PostMapping("/contact")
    public ResponseEntity<MessageResponse> contact(
            @Valid @RequestBody ContactRequest r
            ) {
        contactService.save(r);
        return ResponseEntity.ok(new MessageResponse("Sent successfully"));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<MessageResponse> subscribe(
            @Valid @RequestBody SubscribeRequest r
            ) {
        subscriberService.subscribe(r.getEmail());
        return ResponseEntity.ok(new MessageResponse("Subscribed"));
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestParam("q") String q) {
        return ResponseEntity.ok(searchService.search(q));
    }
}
