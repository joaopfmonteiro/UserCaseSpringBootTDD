package com.example.UserCaseSpirng;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/{requestedId}")
    private ResponseEntity<Users> findById(){
        Users users = new Users(45L,"Joao","joao@gmail.com");
        return ResponseEntity.ok(users);
    }
}
