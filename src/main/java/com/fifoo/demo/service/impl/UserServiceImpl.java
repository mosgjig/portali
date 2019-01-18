package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.UserConverter;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDto findByUsername(String username) throws UserFoundException{
        Optional <User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UserFoundException("This user exist.");
        }
        else{
            return userConverter.toDto(user.get());
        }
    }

    @Override
    public UserDto create(UserDto userDto) throws UserFoundException{
        User user;
       Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
       if(optionalUser.isPresent()){
           throw new UserFoundException("This username already exists.");
       }
       else{
           user = userRepository.save(UserConverter.toUser(userDto));
       }
        return UserConverter.toDto(user);
    }

    @Override
    public void delete(Long id) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User newUser = userOptional.get();
            userRepository.delete(newUser);
        }
        else
        {
            throw new UserNotFoundException("This user doesnt exist.");
        }
    }

    @Override
    public UserDto update(Long id, UserDto userDto) throws UserNotFoundException {
        User foundUser = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            foundUser = optionalUser.get();
        } else{
            throw new UserNotFoundException("You cannot update a User that doesnt exist.");
        }
        foundUser.setName(userDto.getName());
        foundUser.setEmail(userDto.getEmail());
        foundUser.setPassword(userDto.getPassword());
        foundUser.setLastname(userDto.getLastname());
        foundUser.setUsername(userDto.getUsername());

        User user = userRepository.save(foundUser);
        return UserConverter.toDto(user);
    }

    public User findOneUser(Long id) throws UserNotFoundException{
        Optional<User> user  = userRepository.findById(id);
         if(user.isPresent()){
             User userReturned = user.get();
             return userReturned;
         }
         else{
             throw new UserNotFoundException("User with id : " +id+ " its not found.");
         }
    }
}
