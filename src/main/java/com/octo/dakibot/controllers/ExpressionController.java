package com.octo.dakibot.controllers;

import com.octo.dakibot.entities.Expression;
import com.octo.dakibot.services.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpressionController {

    @Autowired
    private ExpressionService expressionService;

    @GetMapping(value = "/project/{projectId}/context/{contextId}/intent/{intentId}/language/{languageId}/expressions")
    public List<Expression> getExpressionsByIntentAndLanguage(@PathVariable("projectId") Long projectId,
                                                              @PathVariable("contextId") Long contextId,
                                                              @PathVariable("intentId") Long intentId,
                                                              @PathVariable("languageId") Long languageId) {
        return expressionService.getExpressionsByIntentAndLanguage(projectId, contextId, intentId, languageId);
    }

    @GetMapping(value = "/project/{projectId}/context/{contextId}/intent/{intentId}/language/{languageId}/expression/{expressionId}")
    public Expression getExpressionByIntentAndLanguage(@PathVariable("projectId") Long projectId,
                                                       @PathVariable("contextId") Long contextId,
                                                       @PathVariable("intentId") Long intentId,
                                                       @PathVariable("languageId") Long languageId,
                                                       @PathVariable("expressionId") Long expressionId) {
        return expressionService.getExpressionByIntentAndLanguage(projectId, contextId, intentId, languageId, expressionId);
    }

    @PutMapping(value = "/project/{projectId}/context/{contextId}/intent/{intentId}/expression")
    public void addExpressionToIntent(@PathVariable("projectId") Long projectId,
                                      @PathVariable("contextId") Long contextId,
                                      @PathVariable("intentId") Long intentId,
                                      @RequestBody Expression expression) {
        expressionService.addExpressionToIntent(projectId, contextId, intentId, expression);
    }

}
