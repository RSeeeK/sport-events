package ru.devhack.motomoto.sportevents.db.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PingMeRepository {
    private final EntityManager entityManager;

    public PingMeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Integer ping() {
        return (Integer) entityManager.createNativeQuery("SELECT 1")
                .getSingleResult();
    }
}
