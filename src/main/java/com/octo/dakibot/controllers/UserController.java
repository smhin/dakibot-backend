package com.octo.dakibot.controllers;

import com.octo.dakibot.entities.Project;
import com.octo.dakibot.entities.User;
import com.octo.dakibot.repositories.UserRepository;
import com.octo.dakibot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/api/users")
    public List<User> getUsers() { return userRepository.findAll(); }

    @PostMapping(value = "/user")
    public void createUser(@RequestBody User user) { userService.save(user); }

    @GetMapping(value = "/api/user/{userId}")
    public User getUserById(@PathVariable("userId") long userId) { return userRepository.findOne(userId); }

    @GetMapping(value = "/api/user/username/{username}/password/{password}")
    public User getUserByCredentials(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userRepository.findByUsername(username);
    }

    @PutMapping(value = "/api/user/{userId}")
    public void updateUser(@PathVariable("userId") long userId, @RequestBody User newUser) {
        userService.updateUser(userId, newUser);
    }

    @PutMapping(value = "/api/user/{userId}/addProject")
    public void addProjectToUser(@PathVariable("userId") long userId, @RequestBody Project project) {
        userService.addProjectToUser(userId, project);
    }

    @GetMapping(value = "/api/user/{userId}/projects")
    public List<Project> getProjectsByUser(@PathVariable("userId") long userId) {
        return userRepository.findOne(userId).getProjects();
    }

    @GetMapping(value = "/api/user/{userId}/project/{projectId}")
    public Project getProjectByUser(@PathVariable("userId") long userId, @PathVariable("projectId") long projectId) {
        return userService.getProjectByUser(userId, projectId);
    }

}
