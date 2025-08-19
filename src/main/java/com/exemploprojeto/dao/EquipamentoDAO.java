package com.exemploprojeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.exemploprojeto.connect.Connect;
import com.exemploprojeto.model.Equipamento;

public class EquipamentoDAO {

    static Connect connect = new Connect();

    public void insert(Equipamento equipamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(equipamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Equipamento equipamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Equipamento mergedEquipamento = em.merge(equipamento);
            em.remove(mergedEquipamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Equipamento equipamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(equipamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Equipamento selectById(int id) {
        EntityManager em = connect.getEntityManager();
        Equipamento equipamento = null;
        try {
            equipamento = em.find(Equipamento.class, id);
        } finally {
            em.close();
        }
        return equipamento;
    }

    public List<Equipamento> selectAll() {
        EntityManager em = connect.getEntityManager();
        List<Equipamento> equipamentos = null;
        try {
            equipamentos = em.createQuery("FROM " + Equipamento.class.getName(), Equipamento.class).getResultList();
        } finally {
            em.close();
        }
        return equipamentos;
    }
}
