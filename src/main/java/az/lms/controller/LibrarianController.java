/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:32
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.request.LibrarianRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.LibrarianResponse;
import az.lms.service.LibrarianService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/librarian")
@RequiredArgsConstructor
public class LibrarianController {
    private final LibrarianService service;

    @RolesAllowed("ADMIN")
    @ApiOperation(value = "Create new librarian", notes = "Create new librarian")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created")
    })
    @PostMapping("/")
    public ResponseEntity<String> addLibrarian(@Valid @RequestBody LibrarianRequest request) {
        service.createLibrarian(request);
        return ResponseEntity.ok("Successfully created");
    }

    @RolesAllowed("ADMIN")
    @ApiOperation(value = "Update librarian by id", notes = "Update librarian by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Librarian id not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateLibrarian(@Valid @PathVariable Long id, @RequestBody LibrarianRequest request) {
        service.updateLibrarian(id, request);
        return ResponseEntity.ok("Successfully updated");
    }

    @RolesAllowed("ROLE_ADMIN")
    @ApiOperation(value = "Get all librarian", notes = "Get all librarian")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LibrarianResponse.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<LibrarianResponse>> getAllLibrarian() {
        return ResponseEntity.ok(service.getAllLibrarian());
    }
    @RolesAllowed("ADMIN")
    @ApiOperation(value = "Get  librarian by id", notes = "Get librarian by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AuthorResponse.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LibrarianResponse> getLibrarianById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLibrarianById(id));
    }
    @RolesAllowed("ADMIN")
    @ApiOperation(value = "Delete librarian by id", notes = "Delete librarian by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Librarian id not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibrarian(@PathVariable Long id) {
        service.deleteLibrarian(id);
        return ResponseEntity.ok("Librarian with ID " + id + " has been successfully deleted.");
    }
}

