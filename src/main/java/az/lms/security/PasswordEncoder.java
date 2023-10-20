/*
 *Created by Jaweed.Hajiyev
 *Date:19.08.23
 *TIME:19:33
 *Project name:LMS
 */

package az.lms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String passwordEncode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
