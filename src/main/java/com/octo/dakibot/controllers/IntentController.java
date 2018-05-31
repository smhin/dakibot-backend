package com.octo.dakibot.controllers;

import com.octo.dakibot.entities.Context;
import com.octo.dakibot.entities.Intent;
import com.octo.dakibot.entities.Project;
import com.octo.dakibot.repositories.ContextRepository;
import com.octo.dakibot.repositories.IntentRepository;
import com.octo.dakibot.repositories.ProjectRepository;
import com.octo.dakibot.services.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class IntentController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContextRepository contextRepository;

    @Autowired
    private IntentRepository intentRepository;

    @Autowired
    private ContextService contextService;

    @GetMapping(value = "/project/{projectId}/context/{contextId}/intent/{intentId}")
    public Intent getIntentById(@PathVariable("projectId") Long projectId,
                                @PathVariable("contextId") Long contextId,
                                @PathVariable("intentId") Long intentId) {
        Project project = projectRepository.findOne(projectId);
        Context context = contextRepository.findOne(contextId);
        if(project != null && context != null && project.getContexts().contains(context)) {
            return intentRepository.findOne(intentId);
        }
        return null;
    }

    @GetMapping(value = "/project/{projectId}/user/{userId}/intents")
    public List<Intent> getIntents(@PathVariable("projectId") Long projectId, @PathVariable("userId") long userId) {
        Project project = projectRepository.findOne(projectId);
        List<Long> projectUsersIds = project.getUsers().stream().map(user -> user.getId()).collect(Collectors.toList());
        if(project != null && projectUsersIds.contains(userId)) {
            List<Intent> intents = new ArrayList<>();
            project.getContexts().stream().map(context -> intents.addAll(context.getIntents())).collect(Collectors.toList());
            return intents;
        }
        return null;
    }

}
