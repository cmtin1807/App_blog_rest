package com.example.app_blog.controller;


import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import com.example.app_blog.service.blog.IBlogService;
import com.example.app_blog.service.category.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private final ICategoryService categoryService;
    private final IBlogService blogService;
    public CategoryRestController(ICategoryService categoryService, IBlogService blogService) {
        this.categoryService = categoryService;
        this.blogService = blogService;
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/view-category/{id}")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Blog> blogs = (List<Blog>) blogService.findAllByCategory(categoryOptional.get());
        return ResponseEntity.ok(blogs);
    }
}
