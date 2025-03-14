package com.blogapplication.service;

import com.blogapplication.entities.Posts;
import com.blogapplication.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer postId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    List<Posts> getAllPosts();
    List<Posts> getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    //search post
    List<Posts> searchPost(String keyWord);
}
