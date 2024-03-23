package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void categoryById(Integer id);
    void deleteCategoryById(Integer id);
    CategoryResponse editCategoryById(Integer id,CategoryRequest request);

    void createNewCategory(CategoryRequest request);
    CategoryResponse findCategoriesByName(String name);
    CategoryResponse findCategoriesById(Integer id);
    List<CategoryResponse> findCategories();

}
