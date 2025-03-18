package com.blogapplication.serviceImpl;

import com.blogapplication.Repo.CategoryRepo;
import com.blogapplication.Repo.PostRepo;
import com.blogapplication.Repo.UserRepo;
import com.blogapplication.entities.Categories;
import com.blogapplication.entities.Posts;
import com.blogapplication.entities.User;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Categories categories = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("categories", "categoryId", categoryId));
        Posts post = this.modelMapper.map(postDto, Posts.class);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategories(categories);
        Posts newpost = this.postRepo.save(post);
        return this.modelMapper.map(newpost, PostDto.class);


    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Posts post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Posts updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Posts post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));

        Page<Posts> all=this.postRepo.findAll(pageable);
        List<Posts> allPosts=all.getContent();
        PostResponse postResponse=new PostResponse();

       List<PostDto> postDtos=  allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setLastPage(all.isLast());
       //if we want to return all the data detais
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Posts post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
       return  this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
      Categories cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("categories","category_id",categoryId));
      List<Posts> posts=this.postRepo.findByCategories(cat);
      List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<Posts> posts = postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyWord) {
        List<Posts> posts=this.postRepo.findByTitleContaining(keyWord);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
