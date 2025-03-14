package com.blogapplication.entities;

import com.blogapplication.payload.CategoryDto;
import com.blogapplication.payload.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title",length = 100,nullable = false)
    private String title;
    @Column(length = 100000)
    private String content;
    private String imageName;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
