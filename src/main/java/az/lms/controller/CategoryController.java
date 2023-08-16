/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:21
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.CategoryResponse;
import az.lms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryRequest request) {
        service.createCategory(request);
        return ResponseEntity.ok("Successfully added");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@Valid @PathVariable Long id, @RequestBody CategoryRequest request) {
        service.updateCategory(id, request);
        return ResponseEntity.ok("Successfully updated");
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return ResponseEntity.ok(service.getAllCategory());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok("Category with ID " + id + " has been successfully deleted.");
    }
}
