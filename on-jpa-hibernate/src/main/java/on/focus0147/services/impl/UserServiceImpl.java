package on.focus0147.services.impl;

import on.focus0147.entities.User;
import on.focus0147.repositories.UserRepository;
import on.focus0147.services.PasswordService;
import on.focus0147.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordService passwordService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public boolean checkPassword(String login, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(login);
        if(optionalUser.isEmpty()){
            return false;
        }
        User user = optionalUser.get();
        return passwordService.checkEquals(password, user.getEncryptedPassword());
    }
}
