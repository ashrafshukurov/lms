/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:34
 *Project name:LMS
 */

package az.lms.service;

import az.lms.dto.request.LibrarianRequest;
import az.lms.dto.response.LibrarianResponse;

import java.util.List;
public interface LibrarianService {
    void createLibrarian(LibrarianRequest request);
    List<LibrarianResponse> getAllLibrarian();
    void deleteLibrarian(Long id);
    LibrarianResponse getLibrarianById(Long id);
    void updateLibrarian(Long id, LibrarianRequest request);
}
