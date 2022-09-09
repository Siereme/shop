package app.utils.validation;

import app.exception.UserAlreadyExistsException;
import app.model.user.User;
import app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserValidation {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public void verifyUserCreate(User user){
        Map<String, String> messages = new HashMap<>();

        userRepo.findByEmail(user.getEmail())
                .ifPresent(verifyUserEmail -> messages.put("email", "Пользователь с таким email уже существует"));
        userRepo.findByPhone(user.getPhone())
                .ifPresent(verifyUserPhone -> messages.put("phone", "Пользователь с таким телефоном уже существует"));

        if(!messages.isEmpty()){
            throw new UserAlreadyExistsException(messages);
        }
    }

    public void verifyLogin(User user, String email, String password){
        Map<String, String> messages = new HashMap<>();

        if(!Objects.equals(email, user.getEmail())){
            messages.put("email", "Пользователя с таким email не существует");
        }
        else if(!passwordEncoder.matches(password, user.getPassword())){
            messages.put("password", "Неверный пароль");
        }

        if(!messages.isEmpty()){
            throw new UserAlreadyExistsException(messages);
        }
    }

}
