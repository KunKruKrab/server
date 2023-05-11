package KunKruKrab.schedule.config;

import KunKruKrab.schedule.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Autowired
   private UserDetailsServiceImp userDetailsService;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

        .and()
                .formLogin()
                .defaultSuccessUrl("/home", true)
        .and()
                .logout();

      return http.build();
   }

   @Bean
   public PasswordEncoder encoder() {
       return new BCryptPasswordEncoder(12);
   }
}