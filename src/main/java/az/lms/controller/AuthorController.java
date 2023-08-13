/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:21
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.AuthorRequest;
import az.lms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;
    @PostMapping("/add")
    public void addAuthor(AuthorRequest request) {
        service.createAuthor(request);
    }
}
