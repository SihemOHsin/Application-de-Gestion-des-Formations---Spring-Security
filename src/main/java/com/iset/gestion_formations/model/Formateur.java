package com.iset.gestion_formations.model;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Formateur extends Personne {

    private String grade;
    private String fonction;
    private LocalDate datePremiereFormation;
@OneToMany(mappedBy = "formateur")
private List<SessionFormation> sessions = new ArrayList<>();
    public Formateur() {
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public LocalDate getDatePremiereFormation() {
        return datePremiereFormation;
    }

    public void setDatePremiereFormation(LocalDate datePremiereFormation) {
        this.datePremiereFormation = datePremiereFormation;
    }
    public List<SessionFormation> getSessions() {
    return sessions;
}

public void setSessions(List<SessionFormation> sessions) {
    this.sessions = sessions;
}
}