package com.blogapplication.serviceImpl;

import com.blogapplication.Repo.UserRepo;
import com.blogapplication.entities.User;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.UserDto;
import com.blogapplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto createUser(UserDto userdto) {
        User map=this.modelMapper.map(userdto, User.class);
        map.setPassword(passwordEncoder.encode(userdto.getPassword()));
        User addedUser=this.userRepo.save(map);
        return this.modelMapper.map(addedUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User cat = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)); // Handle not found case;
        cat.setName((userDto.getName()));
        cat.setEmail(userDto.getEmail());
        cat.setPassword(userDto.getPassword());
        cat.setAge(userDto.getAge());
        cat.setGender(userDto.getGender());
        User updateduser=this.userRepo.save(cat);
        return this.modelMapper.map(updateduser,UserDto.class);
    }
    @Override
    public UserDto getUserById(Integer userId) {
        User cat=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));;
        return this.modelMapper.map(cat,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> cats = this.userRepo.findAll(); // Changed 'users' to 'cats'
        List<UserDto> userDtos = cats.stream()
                .map(cat -> this.modelMapper.map(cat, UserDto.class)) // Lambda parameter 'user' to 'cat'
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User cat = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(cat);

    }
}
