package com.recommender.user_service.controller;

import com.recommender.user_service.DTO.UserPatchDTO;
import com.recommender.user_service.exceptions.UserNotFoundException;
import com.recommender.user_service.model.User;
import com.recommender.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User foundUser = userService.getUserById(id);
            return ResponseEntity.ok(foundUser);
        } catch (UserNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody User user){
        var savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable Long id, @RequestBody UserPatchDTO userDTO){
        try {
            User foundUser = userService.patchUser(id, userDTO);
            return ResponseEntity.ok(foundUser);
        } catch (UserNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            User deletedUser = userService.deleteUser(id);
            String message = "Deleted user: " + deletedUser.getName();
            return ResponseEntity.ok(message);
        } catch (UserNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

}
