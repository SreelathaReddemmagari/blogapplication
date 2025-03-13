package com.blogapplication.entities;

import jakarta.persistence.*;


@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name="title")
    private String categoryTitle;
    @Column(name="description")
    private String categoryDescription;

}
