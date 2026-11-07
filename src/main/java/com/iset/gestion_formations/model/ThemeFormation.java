package com.iset.gestion_formations.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ThemeFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;

    private String motsCles;

    @OneToMany(mappedBy = "themeFormation")
    private List<SessionFormation> sessions = new ArrayList<>();

    public ThemeFormation() {
    }

    public ThemeFormation(Long id, String theme, String motsCles) {
        this.id = id;
        this.theme = theme;
        this.motsCles = motsCles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(String motsCles) {
        this.motsCles = motsCles;
    }

    public List<SessionFormation> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionFormation> sessions) {
        this.sessions = sessions;
    }
}
