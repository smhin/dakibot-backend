package com.octo.dakibot.controllers;

import com.octo.dakibot.entities.Context;
import com.octo.dakibot.entities.Language;
import com.octo.dakibot.entities.Project;
import com.octo.dakibot.repositories.ProjectRepository;
import com.octo.dakibot.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/projects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping(value = "/project/{projectId}/user/{userId}")
    public Project getProjectById(@PathVariable("projectId") long projectId, @PathVariable("userId") long userId) {
        return projectService.getProjectById(projectId, userId); }

    @PutMapping(value = "/project/{projectId}/user/{userId}")
    public void addUserToProject(@PathVariable("projectId") long projectId, @PathVariable("userId") long userId) {
        projectService.addUserToProject(projectId, userId);
    }

    @PutMapping(value = "/user/{userId}/project/{projectId}/language/{languageId}")
    public void addLanguageToProject(@PathVariable("userId") Long userId,
                                     @PathVariable("projectId") Long projectId,
                                     @PathVariable("languageId") Long languageId) {
        projectService.addLanguageToProject(userId, projectId, languageId);
    }

    @GetMapping(value = "/project/{projectId}/languages")
    public List<Language> getLanguagesByProject(@PathVariable("projectId") Long projectId) {
        return projectRepository.findOne(projectId).getLanguages();
    }

    @PutMapping(value = "/project/{projectId}/user/{userId}/context")
    public void addContextToProject(@PathVariable("projectId") Long projectId,
                                    @PathVariable("userId") Long userId,
                                    @RequestBody Context context) {
        projectService.addContextToProject(projectId, userId, context);
    }

    @GetMapping(value = "/project/{projectId}/contexts")
    public List<Context> getContextsByProject(@PathVariable("projectId") Long projectId) {
        return projectRepository.findOne(projectId).getContexts();
    }
}
