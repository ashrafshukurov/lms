/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.exception.NotFoundException;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.CategoryRepository;
import az.lms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public void createCategory(CategoryRequest request) {
        repository.save(mapper.requestToModel(request));
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = repository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(mapper.modelToResponse(category));
        }
        return categoryResponses;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        return mapper.modelToResponse(category);
    }

    @Override
    public void updateCategory(Long id, CategoryRequest request) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            if (request.getName() != null) {
                category.setName(request.getName());
            }
            if (request.getDescription() != null) {
                category.setDescription(request.getDescription());
            }
            repository.save(category);
        }
    }

    @Override
    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCategory(Long id) {
        List<Book> bookList = new ArrayList<>();
        return null;
    }
}
