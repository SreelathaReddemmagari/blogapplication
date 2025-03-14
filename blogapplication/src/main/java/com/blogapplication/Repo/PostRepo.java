package com.blogapplication.Repo;

import com.blogapplication.entities.Categories;
import com.blogapplication.entities.Posts;
import com.blogapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.event.ListDataEvent;
import java.util.List;

public interface PostRepo extends JpaRepository<Posts,Integer> {
    List<Posts> findByUser(User user);
   List<Posts> findByCategories(Categories categories);
}
