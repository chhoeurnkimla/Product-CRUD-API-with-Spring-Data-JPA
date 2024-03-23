package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.CategoryRepository;
import co.istad.springwebmvc.service.CategoryService;
import co.istad.springwebmvc.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void  deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    CategoryResponse editCategoryById(@PathVariable Integer id,@Valid @RequestBody CategoryRequest request){
       return categoryService.editCategoryById(id,request);
    }

    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest request) {
        categoryService.createNewCategory(request);

    }

    @GetMapping("/{id}")
    CategoryResponse findCategoryById(@PathVariable Integer id){
        return categoryService.findCategoriesById(id);

    }


    @GetMapping()
    List<CategoryResponse> findCategories ( ){
        return categoryService.findCategories();
    }

}
