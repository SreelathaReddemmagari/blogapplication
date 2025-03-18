package com.blogapplication.service;

import com.blogapplication.entities.Posts;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer postId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    //search post
    List<PostDto> searchPost(String keyWord);

}
