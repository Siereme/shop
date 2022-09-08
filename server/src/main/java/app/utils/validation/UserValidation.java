package app.utils.validation;

import app.exception.UserAlreadyExistsException;
import app.model.user.User;
import app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserValidation {

    @Autowired
    private UserRepository userRepo;

    public void createUserValidation(User user){
        Map<String, String> messages = new HashMap<>();

        User verifyUserEmail = userRepo.findByEmail(user.getEmail()).orElseGet(User::new);
        if(Objects.equals(verifyUserEmail.getEmail(), user.getEmail())){
            messages.put("email", "Пользователь с таким email уже существует");
        }
        User verifyUserPhone = userRepo.findByPhone(user.getPhone()).orElseGet(User::new);
        if(Objects.equals(verifyUserPhone.getPhone(), user.getPhone())){
            messages.put("phone", "Пользователь с таким телефоном уже существует");
        }

        if(!messages.isEmpty()){
            throw new UserAlreadyExistsException(messages);
        }
    }

}
