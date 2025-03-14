package com.blogapplication.entities;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name="title")
    private String categoryTitle;
    @Column(name="description")
    private String categoryDescription;
    //@OneToMany(mappedBy = "categories",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
   // private List<Posts> posts=new ArrayList<>();

}
