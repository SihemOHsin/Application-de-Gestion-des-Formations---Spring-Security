package com.iset.gestion_formations.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InscriptionId implements Serializable {

    private Long apprenantId;
    private Long sessionId;

    public InscriptionId() {
    }

    public InscriptionId(Long apprenantId, Long sessionId) {
        this.apprenantId = apprenantId;
        this.sessionId = sessionId;
    }

    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscriptionId)) return false;

        InscriptionId that = (InscriptionId) o;

        return Objects.equals(apprenantId, that.apprenantId)
                && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenantId, sessionId);
    }
}