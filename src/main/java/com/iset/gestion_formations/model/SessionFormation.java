package com.iset.gestion_formations.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class SessionFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private ThemeFormation themeFormation;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
@OneToMany(mappedBy = "sessionFormation")
private List<Inscription> inscriptions = new ArrayList<>();
    public SessionFormation() {
    }

    public SessionFormation(Long id, String description,
                            LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ThemeFormation getThemeFormation() {
        return themeFormation;
    }

    public void setThemeFormation(ThemeFormation themeFormation) {
        this.themeFormation = themeFormation;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
    public List<Inscription> getInscriptions() {
    return inscriptions;
}

public void setInscriptions(List<Inscription> inscriptions) {
    this.inscriptions = inscriptions;
}
}
