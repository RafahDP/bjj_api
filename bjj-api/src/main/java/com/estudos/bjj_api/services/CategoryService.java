package com.estudos.bjj_api.services;

import org.springframework.stereotype.Service;


import com.estudos.bjj_api.entities.Category;
import com.estudos.bjj_api.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService (CategoryRepository categoryRepository){
        this.categoryRepository= categoryRepository;
    }
    public List<Category>getAllCategories(){
        return categoryRepository.findAll();
    }
     public Category insertCategory(Category category){
        return categoryRepository.save(category);
    }
     public Optional <Category> readById(Long id){
        return categoryRepository.findById(id);
    }
      public void delCategory(Long id){
        categoryRepository.deleteById(id);
    }
     public Category updateCategory(Long id, Category category){
        category.setId(id);
        return categoryRepository.save(category);
    }
}
