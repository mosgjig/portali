package com.fifoo.demo.service;

import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.exception.UserFoundException;
import com.fifoo.demo.exception.UserNotFoundException;
import com.fifoo.demo.model.User;

import java.util.List;

public interface UserService  {

    List<User> getAll();
    UserDto findByUsername(String username) throws UserFoundException;
    UserDto create(UserDto userDto) throws UserFoundException;
    void delete(Long id) throws UserNotFoundException;
    UserDto update(Long id, UserDto userDto) throws UserNotFoundException;
    User findOneUser(Long id) throws UserNotFoundException;
}
