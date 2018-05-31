package com.octo.dakibot.controllers;

import com.octo.dakibot.entities.Language;
import com.octo.dakibot.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping(value = "/languages")
    public List<Language> getLanguages() { return languageRepository.findAll(); }

    @GetMapping(value = "/language/{id}")
    public Language getLanguages(@PathVariable("id") Long id) { return languageRepository.findOne(id); }

    @PostMapping(value = "/language")
    public void createLanguage(@RequestBody Language language) { languageRepository.save(language); }

}
