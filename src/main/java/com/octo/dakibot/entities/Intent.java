package com.octo.dakibot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "intents")
public class Intent {

    @Id
    @GeneratedValue
    private Long id;
    private String intentLabel;
    private String intentDescription;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "INTENT_EXPRESSION",
            joinColumns = { @JoinColumn(name = "INTENT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "EXPRESSION_ID") })
    @JsonIgnoreProperties("intents")
    private List<Expression> expressions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntentLabel() {
        return intentLabel;
    }

    public void setIntentLabel(String intentLabel) {
        this.intentLabel = intentLabel;
    }

    public String getIntentDescription() {
        return intentDescription;
    }

    public void setIntentDescription(String intentDescription) {
        this.intentDescription = intentDescription;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
