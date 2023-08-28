package az.lms.controller;

import az.lms.dto.request.LoginRequest;
import az.lms.dto.request.StudentRequest;
import az.lms.repository.StudentRepository;
import az.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Mehman Osmanov on 26.08.23
 * @project LMS
 */
@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
public class WebController {

   private final StudentService studentService;

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String viewHomePage() {
      return "view";
   }

   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String register(Model model) {
      StudentRequest registerDto = new StudentRequest();
      model.addAttribute("registerDto", registerDto);
      return "register";
   }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(Model model) {
      LoginRequest loginRequest = new LoginRequest();
      model.addAttribute("login", loginRequest);
      return "login";
   }

   @RequestMapping(value = "/save",method = RequestMethod.POST)
   public String saveStudent(@ModelAttribute("student") StudentRequest student) {
      studentService.create(student);
      return "redirect:/web/";
   }
}
