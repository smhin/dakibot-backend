package com.octo.dakibot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "expressions")
public class Expression {

    @Id
    @GeneratedValue
    private Long id;
    private String expression;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "EXRPESSION_LANGUAGE",
            joinColumns = { @JoinColumn(name = "EXPRESSION_ID") },
            inverseJoinColumns = { @JoinColumn(name = "LANGUAGE_ID") })
    @JsonIgnoreProperties("expressions")
    private Language language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
