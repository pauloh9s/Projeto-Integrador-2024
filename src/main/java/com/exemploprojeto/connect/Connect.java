package com.exemploprojeto.connect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {
	
	private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }



}
