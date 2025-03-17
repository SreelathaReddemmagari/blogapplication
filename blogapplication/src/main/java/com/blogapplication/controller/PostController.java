package com.blogapplication.controller;

import com.blogapplication.payload.ApiResponse;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.service.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ) {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }
    @PutMapping("/user/{userId}/category/{categoryId}/post/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable Integer postId
    ) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
    @GetMapping("/postuser")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts=this.postService.getPostByUser(userId);
        return  new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    @DeleteMapping("post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId ){
        this.postService.deletePost(postId);
        return new ApiResponse("post is successfully deleted",true);
    }
    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = (PostDto) postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(value="sortBy",defaultValue="postId",required = false)String sortBy) {

        PostResponse postResponse=postService.getAllPosts(pageNumber, pageSize,sortBy);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

}


