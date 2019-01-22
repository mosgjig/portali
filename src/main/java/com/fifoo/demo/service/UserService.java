package com.fifoo.demo.service;

import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.exception.UserFoundException;
import com.fifoo.demo.exception.UserNotFoundException;
import com.fifoo.demo.model.User;

import java.util.List;

public interface UserService  {

    public List<User> getAll();
    public UserDto findByUsername(String username) throws UserFoundException;
    public UserDto create(UserDto userDto) throws UserFoundException;
    public void delete(Long id) throws UserNotFoundException;
    public UserDto update(Long id, UserDto userDto) throws UserNotFoundException;
    public UserDto findOneUser(Long id) throws UserNotFoundException;
}
