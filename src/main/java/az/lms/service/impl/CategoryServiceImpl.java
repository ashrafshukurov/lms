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

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public void create(CategoryRequest request) {
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
    public void updateCategory(CategoryRequest request) {

    }

    @Override
    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCategory(Long id) {
        return null;
    }
}
