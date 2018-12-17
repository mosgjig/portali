package com.fifoo.demo.controller;

import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.service.UserService;
import com.fifoo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable("id") long id){
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") long id , @RequestBody UserDto userDto){
        return userService.update(id,userDto);
    }

    @GetMapping("/{username}")
    public UserDto getByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }
}
