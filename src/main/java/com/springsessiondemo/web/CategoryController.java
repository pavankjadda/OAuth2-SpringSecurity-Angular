package com.springsessiondemo.web;

import com.springsessiondemo.model.Category;
import com.springsessiondemo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/category")
public class CategoryController
{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "/list")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Optional<Category> findCategoryById(@PathVariable Long id)
    {
        return categoryRepository.findById(id);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("#oauth2.hasScope('write')")
    public Category createNewCategory(@RequestBody Category category)
    {
        return categoryRepository.saveAndFlush(category);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("#oauth2.hasScope('write')")
    public Category updateCategory(@RequestBody Category category)
    {
        return categoryRepository.saveAndFlush(category);
    }

    @PostMapping(path = "/createmultiplecategories")
    public List<Category> createNewCategoriesInBatch(@RequestBody List<Category> categories)
    {
        return categoryRepository.saveAll(categories);
    }


}
