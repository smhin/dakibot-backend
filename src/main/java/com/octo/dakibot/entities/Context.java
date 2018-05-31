package com.octo.dakibot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contexts")
public class Context {

    @Id
    @GeneratedValue
    private Long id;
    private String contextLabel;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "CONTEXT_INTENT",
            joinColumns = { @JoinColumn(name = "CONTEXT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "INTENT_ID") })
    @JsonIgnoreProperties("contexts")
    private List<Intent> intents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContextLabel() {
        return contextLabel;
    }

    public void setContextLabel(String contextLabel) {
        this.contextLabel = contextLabel;
    }

    public List<Intent> getIntents() {
        return intents;
    }

    public void setIntents(List<Intent> intents) {
        this.intents = intents;
    }
}
