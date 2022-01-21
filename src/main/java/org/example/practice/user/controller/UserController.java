package org.example.practice.user.controller;

import org.example.practice.user.dto.*;
import org.example.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private Map<String, String> result;
    private Map<String, String> error;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.result = new HashMap<>(1);
        this.error = new HashMap<>(1);
        this.result.put("result", "success");
        this.error.put("error", "");
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDtoForSave user) {
        try {
            userService.add(user);
        } catch (Exception e) { // детализировать Exception
            System.out.println("CHECK");
            error.replace("error", "User not saved");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/list")
    public ResponseEntity<?>  getUsers(@RequestBody UserDtoForListIn dto) {
        if (dto.getFirstName() == null || dto.getOfficeId() == null || dto.getPosition() == null) {
            error.replace("error", "Incorrect data");
            return ResponseEntity.badRequest().body(error);
        }
        List<UserDtoForListOut> users = userService.usersByTerms(dto);
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updUser(@RequestBody UserDtoForUpd user) {
        try {
            userService.update(user);
        } catch (IllegalArgumentException e) {
            error.replace("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id) {
        UserDto userDto;
        try {
            userDto = userService.user(id);
        } catch (IllegalArgumentException e) {
            error.replace("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /*
     * URL for testing
     */
    @GetMapping("/test")
    public ResponseEntity<?> get(){
        try {
            throw new IllegalArgumentException("User not found");
        } catch (IllegalArgumentException e) {
            error.replace("error", e.getMessage());
        }
        return ResponseEntity.badRequest().body(error);
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
