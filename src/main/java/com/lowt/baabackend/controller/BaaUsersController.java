package com.lowt.baabackend.controller;

import com.lowt.baabackend.entity.BaaUsers;
import com.lowt.baabackend.service.Impl.BaaUsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/baabackend/baaUsers")
public class BaaUsersController {
    @Autowired
    BaaUsersServiceImpl baaUsersService;

    @PostMapping("register")
    public ResponseEntity<BaaUsers> register(@RequestBody BaaUsers baaUsers) {
        try {
            return ResponseEntity.ok(baaUsersService.register(baaUsers));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
