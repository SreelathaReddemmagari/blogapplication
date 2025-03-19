package com.blogapplication.Entities;

import com.blogapplication.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    public void testUserEntity(){
        User user=new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setAge(23);
        user.setGender("female");


        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(23,user.getAge());
        assertEquals("female",user.getGender());

    }
}
