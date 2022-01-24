package org.example.practice.user.controller;

import org.example.practice.user.dto.*;
import org.example.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Validated @RequestBody UserDtoForSave user) {
        userService.add(user);
        return ResponseEntity.ok().body(Map.of("result", "success"));
    }

    @PostMapping("/list")
    public ResponseEntity<?>  getUsers(@Validated @RequestBody UserDtoForListIn dto) {
        List<UserDtoForListOut> users = userService.usersByTerms(dto);
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updUser(@Validated @RequestBody UserDtoForUpd user) {
        userService.update(user);
        return ResponseEntity.ok().body(Map.of("result", "success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id) {
        UserDto userDto = userService.user(id);
        return ResponseEntity.ok().body(userDto);
    }


    /*
     * URL for testing
     */
    @GetMapping("/users")
    public ResponseEntity<?>  getAllUsers() {
        List<UserDtoForListOut> users = userService.users();
        return ResponseEntity.ok().body(users);
    }


}
