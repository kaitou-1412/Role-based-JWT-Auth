package com.tutorial.jwt.controller;

import com.tutorial.jwt.entity.User;
import com.tutorial.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewOrganizer"})
    public User registerNewOrganizer(@RequestBody User user) {
        return userService.registerNewUser(user, "organizer");
    }

    @PostMapping({"/registerNewParticipant"})
    public User registerNewParticipant(@RequestBody User user) {
        return userService.registerNewUser(user, "participant");
    }

    @GetMapping({"/forOrganizer"})
    @PreAuthorize("hasRole('organizer')")
    public String forAdmin() {
        return "This URL is only accessible to the organizer";
    }

    @GetMapping({"/forParticipant"})
    @PreAuthorize("hasRole('participant')")
    public String forUser() {
        return "This URL is only accessible to the participant";
    }

}
