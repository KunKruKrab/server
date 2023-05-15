package KunKruKrab.schedule.service;

import KunKruKrab.schedule.model.User;
import KunKruKrab.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
