package com.estudos.bjj_api.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.bjj_api.entities.Category;
import com.estudos.bjj_api.services.CategoryService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping(value = "/categories")
public class CategoryController {
    private final CategoryService categoryService;
     public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
     @GetMapping("/{id}")
    public ResponseEntity<Category> readById(@PathVariable Long id){
        Optional<Category> category = categoryService.readById(id);
        if(category.isPresent()){
            return ResponseEntity.ok(category.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<Category> categoryList(){
        return categoryService.getAllCategories();
    }
     @PostMapping
    public ResponseEntity<Category> insertCategory(@RequestBody Category category){
       Category savedCategory = categoryService.insertCategory(category);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
     @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable  Long id, @RequestBody Category category){
        Category updateCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.ok(updateCategory);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.readById(id);
        if( category.isPresent()){
            categoryService.delCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();    
    }
    
}