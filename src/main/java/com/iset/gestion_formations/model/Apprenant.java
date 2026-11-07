package com.iset.gestion_formations.model;

import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Apprenant extends Personne {

    private String numero;
    private LocalDate datePremiereInscription;
@OneToMany(mappedBy = "apprenant")
private List<Inscription> inscriptions = new ArrayList<>();
    public Apprenant() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDatePremiereInscription() {
        return datePremiereInscription;
    }

    public void setDatePremiereInscription(LocalDate datePremiereInscription) {
        this.datePremiereInscription = datePremiereInscription;
    }
    public List<Inscription> getInscriptions() {
    return inscriptions;
}

public void setInscriptions(List<Inscription> inscriptions) {
    this.inscriptions = inscriptions;
}
}