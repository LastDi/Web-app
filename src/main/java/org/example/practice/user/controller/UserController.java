package org.example.practice.user.controller;

import org.example.practice.user.dto.*;
import org.example.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getOneUser(@PathVariable Long id) {
        return userService.user(id);
    }

    @PostMapping("/save")
    public void saveUser(@Validated @RequestBody UserDtoForSave user) {
        userService.add(user);
    }

    @PostMapping("/list")
    public List<UserDtoForListOut> getUsers(@Validated @RequestBody UserDtoForListIn dto) {
        return userService.usersByTerms(dto);
    }

    @PostMapping("/update")
    public void updUser(@Validated @RequestBody UserDtoForUpd user) {
        userService.update(user);
    }
}
