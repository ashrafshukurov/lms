/*
 *Created by Jaweed.Hajiyev
 *Date:20.08.23
 *TIME:11:35
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.exception.NotFoundException;
import az.lms.model.Author;
import az.lms.model.Librarian;
import az.lms.model.Student;
import az.lms.repository.AuthorRepository;
import az.lms.repository.LibrarianRepository;
import az.lms.repository.StudentRepository;
import az.lms.security.UserPricnipal.LibrarianPrincipal;
import az.lms.security.UserPricnipal.StudentPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
   private final StudentRepository studentRepository;
   private final LibrarianRepository librarianRepository;

   @Override
   public UserDetails loadUserByUsername(String email) throws NotFoundException {
      Optional<Student> studentOptional = studentRepository.findByEmail(email);
      Optional<Librarian> librarianOptional = librarianRepository.findByEmail(email);
      Set<GrantedAuthority> authorities = new HashSet<>();
      if (studentOptional.isPresent()) {
         Student student = studentOptional.get();
         StudentPrincipal studentPrincipal = new StudentPrincipal();
         studentPrincipal.setEmail(student.getEmail());
         studentPrincipal.setPassword(student.getPassword());
         authorities.add(new SimpleGrantedAuthority("ROLE_" + student.getRoleType().name()));
         studentPrincipal.setAuthorities(authorities);
         return studentPrincipal;
      }
      if (librarianOptional.isPresent()) {
         Librarian librarian = librarianOptional.get();
         LibrarianPrincipal librarianPrincipal = new LibrarianPrincipal();
         librarianPrincipal.setPassword(librarian.getPassword());
         librarianPrincipal.setEmail(librarian.getEmail());
         authorities.add(new SimpleGrantedAuthority("ROLE_" + librarian.getRoleType().name()));
         librarianPrincipal.setAuthorities(authorities);
         log.info(librarianPrincipal.getAuthorities().stream().findFirst().toString());
         return librarianPrincipal;
      } else {
         throw new NotFoundException( email+  "  not found");
      }
   }
}
