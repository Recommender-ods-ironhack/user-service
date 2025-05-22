package com.recommender.user_service.service;

import com.recommender.user_service.DTO.UserPatchDTO;
import com.recommender.user_service.exceptions.UserNotFoundException;
import com.recommender.user_service.model.User;
import com.recommender.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long id){
        var user= userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User patchUser(Long id, UserPatchDTO userPatchDTO){
        User existingUser = getUserById(id);

        if(userPatchDTO.getName()!= null){
            existingUser.setName(userPatchDTO.getName());
        }

        if (userPatchDTO.getSizes()!= null){
            existingUser.setSizes(userPatchDTO.getSizes());
        }

        if (userPatchDTO.getStyles()!= null){
            existingUser.setStyles(userPatchDTO.getStyles());
        }

        return userRepository.save(existingUser);
    }

    public User deleteUser(Long id){
        var user= getUserById(id);
        userRepository.deleteById(id);
        return user;
    }
}
