package com.savindu.fullstackbackend.controller;

import com.savindu.fullstackbackend.exceptions.UserNotFoundException;
import com.savindu.fullstackbackend.model.User;
import com.savindu.fullstackbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepo.save(newUser);
    }

    @GetMapping("/users")
    List<User> newUser(){
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepo.findById(id)
                .orElseThrow(()->
                    new UserNotFoundException(id)
        );
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable long id){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            user.get().setName(newUser.getName());
            user.get().setUsername(newUser.getUsername());
            user.get().setEmail(newUser.getEmail());
            return userRepo.save(user.get());
        }
        throw new UserNotFoundException(id);


    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return id +" Deleted!";
    }

}
