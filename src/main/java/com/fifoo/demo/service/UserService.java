package com.fifoo.demo.service;

import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.model.User;

import java.util.List;

public interface UserService  {

    List<User> getAll();

    UserDto findByUsername(String username);

    UserDto create(UserDto userDto);

    UserDto delete(long id);

    UserDto update(long id, UserDto userDto);
}
