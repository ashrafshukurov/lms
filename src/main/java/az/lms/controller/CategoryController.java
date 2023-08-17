/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:21
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @ApiOperation(value = "Create new category", notes = "Category name must be unique")
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@ApiParam(name = "request", value = "Category request object", required = true)
                                              @Valid @RequestBody CategoryRequest request) {
        service.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }

    @ApiOperation(value = "Update category by id", notes = "Update category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Category id not found")
    })
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@Valid @PathVariable Long id, @RequestBody CategoryRequest request) {
        service.updateCategory(id, request);
        return ResponseEntity.ok("Successfully updated");
    }

    @ApiOperation(value = "Get all categories", notes = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All category returned"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {

        return ResponseEntity.ok(service.getAllCategory());
    }

    @ApiOperation(value = "Get category by id", notes = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category returned by id"),
            @ApiResponse(code = 404, message = "Category id not found"),

    })
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @ApiOperation(value = "Delete category by id", notes = "Delete category by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category deleted successfully"),
            @ApiResponse(code = 404, message = "Category id not found"),
            @ApiResponse(code = 409, message = "Could not execute statement")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok("Category with ID " + id + " has been successfully deleted.");
    }
}
