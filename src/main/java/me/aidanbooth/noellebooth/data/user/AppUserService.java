package me.aidanbooth.noellebooth.data.user;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void createUser(String username, String rawPassword, String role) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        userRepository.save(new AppUser(username, encodedPassword, role));
    }
}
