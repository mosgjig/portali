package com.fifoo.demo.controller;

import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.exception.UserFoundException;
import com.fifoo.demo.exception.UserNotFoundException;
import com.fifoo.demo.service.UserService;
import com.fifoo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fifoo.demo.controller.constant.WebDefinition.*;

@RestController
@RequestMapping(USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) throws UserFoundException {
        return userService.create(userDto);
    }

    @DeleteMapping(SLASH + ID)
    public void delete(@PathVariable("id") long id) throws UserNotFoundException {
        userService.delete(id);
    }

    @PutMapping(SLASH + ID)
    public UserDto update(@PathVariable("id") long id , @RequestBody UserDto userDto) throws UserNotFoundException{
        return userService.update(id, userDto);
    }

    @GetMapping(SLASH + USERNAME)
    public UserDto getByUsername(@PathVariable("username") String username) throws UserFoundException{
        return userService.findByUsername(username);
    }

    @GetMapping(SLASH + ID)
    public User findOneUser(@PathVariable ("id") long id) throws UserNotFoundException{
        return userService.findOneUser(id);
    }

}
