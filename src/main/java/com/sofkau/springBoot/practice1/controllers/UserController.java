package com.sofkau.springBoot.practice1.controllers;

import com.sofkau.springBoot.practice1.models.UserModel;
import com.sofkau.springBoot.practice1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel>getById(@PathVariable Long userId) {
        try {
            return userService.getById(userId)
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
       try {
           return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/query")
    public ResponseEntity<List<UserModel>> getUserByPriority(@RequestParam("priority") Integer priority) {
        try {
            return new ResponseEntity<>(userService.getByPriority(priority),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable Long userId) {
        try {
            var isDelete = userService.delete(userId);

            if(isDelete) {
               return new ResponseEntity<>(HttpStatus.OK);
           } else {
               return new ResponseEntity(HttpStatus.NOT_FOUND);
           }
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserModel> updateUser(@RequestBody UserModel user, @PathVariable Long userId) {
       try {
           return  new ResponseEntity<>(userService.update(user, userId), HttpStatus.OK);
       } catch (Exception ex) {
           return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
