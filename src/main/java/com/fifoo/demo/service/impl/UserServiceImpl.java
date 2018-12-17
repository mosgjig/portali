package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.UserToDto;
import com.fifoo.demo.dto.UserDto;
import com.fifoo.demo.exception.UserFoundException;
import com.fifoo.demo.exception.UserNotFoundException;
import com.fifoo.demo.model.User;
import com.fifoo.demo.repository.UserRepository;
import com.fifoo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;

    @Autowired
    private final UserToDto userToDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserToDto userToDto){
        this.userRepository = userRepository;
        this.userToDto = userToDto;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDto findByUsername(String username) {

        Optional <User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UserFoundException("This user exist.");
        }
        else{
            return userToDto.toDto(user.get());
        }
    }

    @Override
    public UserDto create(UserDto userDto) {
       Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
       if(optionalUser.isPresent()){
           throw new UserFoundException("This username already exists.");
       }
       else{
           userRepository.save(UserToDto.toUser(userDto));
       }
        return userDto;
    }

    @Override
    public UserDto delete(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User newUser = userOptional.get();
            userRepository.delete(newUser);
        }
        else
        {
            throw new UserNotFoundException("This user doesnt exist.");
        }
        return null;
    }

    @Override
    public UserDto update(long id, UserDto userDto) {
        User newUser = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            newUser = optionalUser.get();
        } else{
            throw new UserNotFoundException("You cannot update a User that doesnt exist.");

        }
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setLastname(userDto.getLastname());
        newUser.setUsername(userDto.getUsername());

          userRepository.save(newUser);

            return null;
    }
}
