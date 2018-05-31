package com.octo.dakibot.controllers;

import com.octo.dakibot.entities.Intent;
import com.octo.dakibot.services.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContextController {

    @Autowired
    private ContextService contextService;

    @GetMapping(value = "/project/{projectId}/context/{contextId}/intents")
    public List<Intent> getIntentsByContext(@PathVariable("projectId") Long projectId,
                                            @PathVariable("contextId") Long contextId) {
        return contextService.getIntentsByContext(projectId, contextId);
    }

    @PutMapping(value = "/project/{projectId}/context/{contextId}/user/{userId}/intent")
    public void addIntentToContext(@PathVariable("projectId") Long projectId,
                                   @PathVariable("contextId") Long contextId,
                                   @PathVariable("userId") Long userId,
                                   @RequestBody Intent intent) {
        contextService.addIntentToContext(projectId, contextId, userId, intent);
    }

}
