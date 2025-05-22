package com.recommender.user_service.service;

import com.recommender.user_service.model.User;
import com.recommender.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(Long id){
        var user= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return user;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User deleteUser(Long id){
        var user= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        userRepository.deleteById(id);
        return user;
    }
}
