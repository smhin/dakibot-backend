package com.octo.dakibot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    private String projectName;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "PROJECT_LANGUAGE",
            joinColumns = { @JoinColumn(name = "PROJECT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "LANGUAGE_ID") })
    @JsonIgnoreProperties("projects")
    private List<Language> languages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "PROJECT_CONTEXT",
            joinColumns = { @JoinColumn(name = "PROJECT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CONTEXT_ID") })
    @JsonIgnoreProperties("projects")
    private List<Context> contexts = new ArrayList<>();

    @ManyToMany(fetch=FetchType.LAZY, mappedBy = "projects")
    @JsonIgnoreProperties("projects")
    private List<User> users = new ArrayList<>();

    Project() {}

    public Project(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }
}
