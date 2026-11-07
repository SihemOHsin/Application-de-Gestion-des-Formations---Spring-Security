package com.iset.gestion_formations.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Inscription {

    @EmbeddedId
    private InscriptionId id;

    private LocalDate dateInscription;

    @ManyToOne
    @MapsId("apprenantId")
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private SessionFormation sessionFormation;

    public Inscription() {
    }

    public Inscription(InscriptionId id,
                       LocalDate dateInscription,
                       Apprenant apprenant,
                       SessionFormation sessionFormation) {
        this.id = id;
        this.dateInscription = dateInscription;
        this.apprenant = apprenant;
        this.sessionFormation = sessionFormation;
    }

    public InscriptionId getId() {
        return id;
    }

    public void setId(InscriptionId id) {
        this.id = id;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public SessionFormation getSessionFormation() {
        return sessionFormation;
    }

    public void setSessionFormation(SessionFormation sessionFormation) {
        this.sessionFormation = sessionFormation;
    }
}
