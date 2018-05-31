package com.octo.dakibot.services;

import com.octo.dakibot.entities.Context;
import com.octo.dakibot.entities.Intent;
import com.octo.dakibot.entities.Project;
import com.octo.dakibot.repositories.ContextRepository;
import com.octo.dakibot.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContextService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContextRepository contextRepository;


    public List<Intent> getIntentsByContext(Long projectId, Long contextId) {
        Project project = projectRepository.findOne(projectId);
        Context context = contextRepository.findOne(contextId);
        if(project != null && context != null && project.getContexts().contains(context)) {
            return context.getIntents();
        }
        return null;
    }

    public void addIntentToContext(Long projectId, Long contextId, Long userId, Intent intent) {
        Project project = projectRepository.findOne(projectId);
        Context context = contextRepository.findOne(contextId);
        List<Long> projectUsersIds = project.getUsers().stream().map(user -> user.getId()).collect(Collectors.toList());
        if(project != null && context != null && projectUsersIds.contains(userId) && project.getContexts().contains(context)) {
            context.getIntents().add(intent);
            contextRepository.save(context);
        }
    }
}
