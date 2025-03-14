package com.blogapplication.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private int age;
    @Column
    private String gender;
    //@OneToMany(mappedBy = "user",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    //private List<Posts> posts=new ArrayList<>();
}
