package KunKruKrab.schedule.service;

import KunKruKrab.schedule.model.User;
import KunKruKrab.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username)
                                          throws UsernameNotFoundException {

       User user = userRepository.findByEmail(username);

       if (user == null) {
           throw new UsernameNotFoundException("Could not find user");
       }

       return new org.springframework.security.core.userdetails.User(
               user.getEmail(), user.getPassword(),
               new ArrayList<>());
   }
}
