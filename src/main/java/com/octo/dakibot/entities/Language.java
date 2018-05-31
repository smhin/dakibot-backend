package com.octo.dakibot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Language {

    @Id
    @GeneratedValue
    private Long id;
    private String languageCode;
    private String languageLabel;

    /*
    @ManyToMany(fetch= FetchType.LAZY, mappedBy = "languages")
    @JsonIgnoreProperties("languages")
    private List<Project> projects = new ArrayList<>();
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public void setLanguageLabel(String languageLabel) {
        this.languageLabel = languageLabel;
    }
}
