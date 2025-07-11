package br.com.ecommerce.ecommerce.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ecommerce.ecommerce.dtos.request.NewUserDTO;
import br.com.ecommerce.ecommerce.dtos.response.UserDTO;
import br.com.ecommerce.ecommerce.models.UserEntity;
import br.com.ecommerce.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> createuser(@RequestBody NewUserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) {
        UserDTO user = userService.getUserResponseById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
