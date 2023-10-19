/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:36
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.LibrarianRequest;
import az.lms.dto.response.LibrarianResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.LibrarianMapper;
import az.lms.model.Librarian;
import az.lms.repository.LibrarianRepository;
import az.lms.security.PasswordCoderConfig;
import az.lms.service.LibrarianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LibrarianServiceImpl implements LibrarianService {
    private final LibrarianRepository repository;
    private final LibrarianMapper mapper;
    private final PasswordCoderConfig passwordCoderConfig;

    @Override
    public void createLibrarian(LibrarianRequest request) {
        if (!repository.existsByEmail(request.getEmail())) {
            Librarian librarian = mapper.requestToModel(request);
            librarian.setPassword(passwordCoderConfig.passwordEncode(request.getPassword()));
            repository.save(librarian);
            log.info("Created new librarian \n" + librarian);
        } else {
            log.error("Librarian already exist!!!");
            throw new AlreadyExistsException("Librarian already exist!!!");
        }
    }

    @Override
    public List<LibrarianResponse> getAllLibrarian() {
        List<Librarian> librarians = repository.findAll();
        List<LibrarianResponse> responses = new ArrayList<>();
        for (var librarian : librarians) {
            responses.add(mapper.modelToResponse(librarian));
        }
        log.info("Getting all librarian.All librarian`s count {}", responses.size());
        return responses;
    }

    @Override
    public void deleteLibrarian(Long id) {
        Librarian librarian = repository.findById(id).orElseThrow(() -> new NotFoundException("Librarian not found"));
        repository.delete(librarian);
        log.info("Librarian has been deleted successfully.Deleted librarian id {}", id);
    }

    @Override
    public LibrarianResponse getLibrarianById(Long id) {
        Librarian librarian = repository.findById(id).orElseThrow(()
                -> new NotFoundException("Librarian not found!"));
        log.info("Getting librarian by id" + librarian.toString());
        return mapper.modelToResponse(librarian);
    }

    @Override
    public void updateLibrarian(Long id, LibrarianRequest request) {
        Librarian librarian = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Librarian not found"));
        Librarian newLibrarian = mapper.requestToModel(request);
        newLibrarian.setId(librarian.getId());
        newLibrarian.setEmail(librarian.getEmail());
        newLibrarian.setPassword(passwordCoderConfig.passwordEncode(request.getPassword()));
        repository.save(newLibrarian);
        log.info("Librarian updated successfully");
    }
}
