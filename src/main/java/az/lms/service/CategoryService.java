/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service;

import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.model.Book;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategory();
    CategoryResponse getCategoryById(Long id);
    void updateCategory(Long id ,CategoryRequest request);
    void deleteCategoryById(Long id);
}