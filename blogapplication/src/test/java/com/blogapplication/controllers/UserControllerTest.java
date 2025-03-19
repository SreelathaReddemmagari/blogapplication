package com.blogapplication.controllers;

import com.blogapplication.controller.UserController;
import com.blogapplication.payload.ApiResponse;
import com.blogapplication.payload.UserDto;
import com.blogapplication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.createUser(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService, times(1)).createUser(any(UserDto.class));
    }

    @Test
    public void testUpdateUser() {
        int userId = 1;
        UserDto userDto = new UserDto();
        when(userService.updateUser(any(UserDto.class), eq(userId))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.updateUser(userDto, userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService, times(1)).updateUser(any(UserDto.class), eq(userId));
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        UserDto userDto = new UserDto();
        when(userService.getUserById(eq(userId))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService, times(1)).getUserById(eq(userId));
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto> userDtos = Arrays.asList(new UserDto(), new UserDto());
        when(userService.getAllUsers()).thenReturn(userDtos);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;

        ResponseEntity<ApiResponse> response = userController.deleteUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("user deleted successfully", response.getBody().getMessage());
        verify(userService, times(1)).deleteUser(eq(userId));
    }
}