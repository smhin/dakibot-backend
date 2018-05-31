package com.octo.dakibot.services;

import com.octo.dakibot.entities.Project;
import com.octo.dakibot.entities.User;
import com.octo.dakibot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public Project getProjectByUser(long userId, long projectId) {
        User user = userRepository.findOne(userId);
        return user.getProjects().stream()
                .filter(project -> projectId == project.getId())
                .findFirst()
                .orElse(null);
    }

    public void addProjectToUser(long userId, Project project) {
        User user = userRepository.findOne(userId);
        if(user != null) {
            user.getProjects().add(project);
            userRepository.save(user);
        }
    }

    public void updateUser(long userId, User newUser) {
        User user = userRepository.findOne(userId);
        if(user != null) {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            save(user);
        }
    }

    public User getUserByCredentials(String username, String password) {
        User user = userRepository.findByUsername(username);
        return getPasswordEncoder().encode(password) == user.getPassword() ? user : null;
    }

}
