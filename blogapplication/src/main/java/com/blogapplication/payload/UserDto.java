package com.blogapplication.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private int id;
@NotEmpty
    private String name;
@NotEmpty
    private String email;
@NotEmpty
    private String password;
@NonNull
    private int age;
@NotEmpty
    private String gender;
}
