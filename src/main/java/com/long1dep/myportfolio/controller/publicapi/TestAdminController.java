package com.long1dep.myportfolio.controller.publicapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/test")
public class TestAdminController {

    @GetMapping
    public String test() {
        return "ADMIN ACCESS OKLA";
    }
}
