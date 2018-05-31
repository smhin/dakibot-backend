package com.octo.dakibot.services;

import com.octo.dakibot.entities.*;
import com.octo.dakibot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ExpressionService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContextRepository contextRepository;

    @Autowired
    private IntentRepository intentRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ExpressionRepository expressionRepository;


    public void addExpressionToIntent(Long projectId, Long contextId, Long intentId, Expression expression) {
        Project project = projectRepository.findOne(projectId);
        Context context = contextRepository.findOne(contextId);
        Intent intent = intentRepository.findOne(intentId);
        Language language = languageRepository.findOne(expression.getLanguage().getId());
        if(project.getLanguages().contains(language) && project.getContexts().contains(context) && context.getIntents().contains(intent)) {
            expression.setLanguage(language);
            intent.getExpressions().add(expression);
            intentRepository.save(intent);
        }
    }

    public Expression getExpressionByIntentAndLanguage(Long projectId, Long contextId, Long intentId, Long languageId, Long expressionId) {
        Project project = projectRepository.findOne(projectId);
        Context context = contextRepository.findOne(contextId);
        Intent intent = intentRepository.findOne(intentId);
        Language language = languageRepository.findOne(languageId);
        Expression expression = expressionRepository.findOne(expressionId);
        if(project.getLanguages().contains(language) && project.getContexts().contains(context)
                && context.getIntents().contains(intent)
                && intent.getExpressions().contains(expression)) {
            return expression;
        }
        return null;
    }

    public List<Expression> getExpressionsByIntentAndLanguage(Long projectId, Long contextId, Long intentId, Long languageId) {
        Project project = projectRepository.findOne(projectId);
        Context context = contextRepository.findOne(contextId);
        Intent intent = intentRepository.findOne(intentId);
        Language language = languageRepository.findOne(languageId);
        if(project.getLanguages().contains(language) && project.getContexts().contains(context) && context.getIntents().contains(intent)) {
            return intent.getExpressions().stream()
                                          .filter(expression -> expression.getLanguage().getId() == languageId)
                                          .collect(toList());
        }
        return null;
    }
}
