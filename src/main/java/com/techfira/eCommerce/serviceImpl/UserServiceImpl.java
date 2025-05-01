package com.techfira.eCommerce.serviceImpl;



import java.util.List;
import java.util.stream.Collectors;

import com.techfira.eCommerce.dto.UserDto;
import com.techfira.eCommerce.entity.User;
import com.techfira.eCommerce.exceptions.ResourceNotFoundException;
import com.techfira.eCommerce.repository.UserRepository;
import com.techfira.eCommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User  savedUser =this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());


        User updatedUser=	this.userRepo.save(user);
        UserDto userDto1 =this.userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos	=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user= 	this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;

    }

    public UserDto userToDto(User user) {
        //Conversion between Objects of one Model to another model through model mapper
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;


    }

}

