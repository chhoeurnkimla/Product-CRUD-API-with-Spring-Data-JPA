package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Category;
import co.istad.springwebmvc.repository.CategoryRepository;
import co.istad.springwebmvc.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void categoryById(Integer id) {

    }

    @Override
    public void deleteCategoryById(Integer id) {
        // check
        if (!categoryRepository.existsById(id)){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category have not been !"
            );
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category have been not found!"
                ));
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);

        return this.findCategoriesById(id);
    }

    @Override
    public void createNewCategory(CategoryRequest request) {
        // check
        if (categoryRepository.existsByName(request.name())){
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already existing !"
            );
        }

        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);

    }

    @Override
    public CategoryResponse findCategoriesByName(String name) {
        return null;
    }

    @Override
    public CategoryResponse findCategoriesById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category have been not found!"
                ));
        return new CategoryResponse(category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryResponse> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category ->
                        new CategoryResponse(category.getName(), category.getDescription()))
                .toList();
    }


}
