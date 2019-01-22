package com.fifoo.demo.converter;

import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static UserDto toDto(User user){

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public static User toUser(UserDto userDto){

        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
