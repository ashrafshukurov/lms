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
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.CategoryRepository;
import az.lms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public void createCategory(CategoryRequest request) {
        if (repository.existsByName(request.getName())) {
            log.error("Category name - " + request.getName() + " already exist!!!");
            throw new AlreadyExistsException("Category name-" + request.getName() + "already exist!!!");
        }

        Category category = repository.save(mapper.requestToModel(request));
        log.info("Created new category \n {}", category);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = repository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(mapper.modelToResponse(category));
        }
        log.info("Getting all category.All categories count {}", categoryResponses.size());
        return categoryResponses;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        log.info("Getting category by id" + category.toString());
        return mapper.modelToResponse(category);
    }

    @Override
    public void updateCategory(Long id, CategoryRequest request) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            if (request.getName() != null) {
                category.setName(request.getName());
                log.info("Category name updated");
            }
            if (request.getDescription() != null) {
                category.setDescription(request.getDescription());
                log.info("Category description updated");
            }
            repository.save(category);
            log.info("Category successfully updated");
        } else{
            log.error("Category is not present");
            throw new NotFoundException("Category id not found.ID: " + id);
        }
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("Category has been deleted successfully.Deleted category id {}", id);
            log.error("Category id not found");
        } else {
            log.error("Category id not found");
           throw new NotFoundException("Category id not found");
        }
    }

    @Override
    public List<Book> getBooksByCategory(Long id) {

        return null;
    }
}
