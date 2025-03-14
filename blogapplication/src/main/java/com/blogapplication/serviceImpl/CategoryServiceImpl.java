package com.blogapplication.serviceImpl;

import com.blogapplication.Repo.CategoryRepo;
import com.blogapplication.entities.Categories;
import com.blogapplication.exception.ResourceNotFoundException;
import com.blogapplication.payload.CategoryDto;
import com.blogapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Categories cat=this.modelMapper.map(categoryDto,Categories.class);
        Categories addedCategory=this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Categories cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category_id",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Categories updatedcategory=this.categoryRepo.save(cat);

        return this.modelMapper.map(updatedcategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Categories cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("categories","categoryId",categoryId));
        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Categories cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("categories","categoryId",categoryId));

        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Categories> categories=this.categoryRepo.findAll();
        List<CategoryDto> categoryDto=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDto;
    }
}
