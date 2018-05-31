package com.octo.dakibot.services;

import com.octo.dakibot.entities.Context;
import com.octo.dakibot.entities.Language;
import com.octo.dakibot.entities.Project;
import com.octo.dakibot.entities.User;
import com.octo.dakibot.repositories.LanguageRepository;
import com.octo.dakibot.repositories.ProjectRepository;
import com.octo.dakibot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private UserRepository userRepository;

    public void addUserToProject(long projectId, long userId) {
        Project project = projectRepository.findOne(projectId);
        User user = userRepository.findOne(userId);
        if(project != null && user != null && !project.getUsers().contains(user)) {
            user.getProjects().add(project);
            userRepository.save(user);
        }
    }

    public void addLanguageToProject(Long userId, Long projectId, Long languageId) {
        Project project = projectRepository.findOne(projectId);
        Language language = languageRepository.findOne(languageId);
        User user = userRepository.findOne(userId);
        if(project != null && language != null && project.getUsers().contains(user) && !project.getLanguages().contains(language)) {
            project.getLanguages().add(language);
            projectRepository.save(project);
        }
    }

    public void addContextToProject(Long projectId, Long userId, Context context) {
        Project project = projectRepository.findOne(projectId);
        User user = userRepository.findOne(userId);
        if(project != null && user != null && project.getUsers().contains(user)) {
            project.getContexts().add(context);
            projectRepository.save(project);
        }
    }

    public Project getProjectById(long projectId, long userId) {
        Project project = projectRepository.findOne(projectId);
        User user = userRepository.findOne(userId);
        return project.getUsers().contains(user) ? project : null;
    }
}
