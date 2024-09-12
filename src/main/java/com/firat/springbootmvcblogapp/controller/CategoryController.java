package com.firat.springbootmvcblogapp.controller;

import com.firat.springbootmvcblogapp.dto.ApiResponse;
import com.firat.springbootmvcblogapp.dto.CategoryDto;
import com.firat.springbootmvcblogapp.dto.UserDto;
import com.firat.springbootmvcblogapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto createCategory = categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    //update

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                      @PathVariable Integer categoryId){

        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);

        //return ResponseEntity.ok(updatedCategory);
    }

    //delete

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){

        categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>
                (new ApiResponse("Category is deleted!", true), HttpStatus.OK);
    }


    //get

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){

        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    //get all

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {

        return ResponseEntity.ok(categoryService.getCategories());
    }

}










