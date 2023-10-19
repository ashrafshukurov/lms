/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Category;
import az.lms.repository.CategoryRepository;
import az.lms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            log.error("Category name - " + request.getName() + " already exist!!!");
            throw new AlreadyExistsException("Category name-" + request.getName() + "already exist!!!");
        }
        Category category = categoryRepository.save(categoryMapper.requestToModel(request));
        log.info("Created new category \n {}", category);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(categoryMapper.modelToResponse(category));
        }
        log.info("Getting all category.All categories count {}", categoryResponses.size());
        return categoryResponses;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        log.info("Getting category by id" + category.toString());
        return categoryMapper.modelToResponse(category);
    }

    @Override
    public void updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id not found.ID: " + id));
        Category newCategory = categoryMapper.requestToModel(request);
        newCategory.setId(category.getId());
        categoryRepository.save(newCategory);
        log.info("Category successfully updated");
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id not found"));
        categoryRepository.delete(category);
        log.info("Category has been deleted successfully.Deleted category id {}", id);
    }

}
