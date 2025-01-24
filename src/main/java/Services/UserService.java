package Services;

import models.User;
import models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public void register(User user) throws EmailExistsException, UsernameExistsException {
        if(repository.existsByEmail(user.getEmail())) {
            throw new EmailExistsException("Email Already Exists");
        }
        if(repository.existsByUsername(user.getUsername())) {
            throw new UsernameExistsException("Username Already Exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        repository.save(user);
    }

    public User findUser(String credential) {
        Optional<User> user = (repository.findByEmail(credential).isEmpty()) ? repository.findByUsername(credential) : repository.findByEmail(credential);
        return user.get();
    }

}
