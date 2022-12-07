package com.devpredator.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devpredator.demo.entities.UserEntity;
import com.devpredator.demo.repositories.UserRepository;
import com.devpredator.demo.requests.RegisterRequest;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    public String registerUser(RegisterRequest request) {
        String message = "";

        try {
            UserEntity exist = userRepo.findByUsername(request.getUsername()).orElse(null);
            if (exist == null) {
                if (request.getPassword().equals(request.getConfirmPassword())) {
                    UserEntity entity = new UserEntity(request.getUsername(), encoder.encode(request.getPassword()));
                    userRepo.save(entity);
                    message = "El usuario " + entity.getUsername() + " se ha creado exitosamente";
                } else {
                    message = "Las contraseñas no coinciden, intente de nuevo";
                }
            } else {
                message = "El nombre de usuario ya existe";
            }
        } catch (Exception e) {
            message = "Ha ocurrido un error al registrar el usuario";
            log.error("error al registrar el usuario", e);
        }

        return message;
    }
}