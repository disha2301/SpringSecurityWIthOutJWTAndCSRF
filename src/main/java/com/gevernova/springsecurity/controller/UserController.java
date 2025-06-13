package com.gevernova.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    // users with ROLE_USER can access this
    // GET - http://localhost:9090/api/user
    public String userApi(){
        return "Hello User! You can now access the API";
    }

    // users with ROLE_ADMIN can access this
    // GET - http://localhost:9090/api/admin
    @GetMapping("/admin")
    public String adminApi(){
        return "Hello Admin! You can now access the API";
    }

    // anyone can access this without logging in because of permitAll()
    // GET - http://localhost:9090/api/public
    @GetMapping("/public")
    public String publicApi(){
        return "This is a public API";
    }
}
