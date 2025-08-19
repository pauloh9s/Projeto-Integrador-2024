package com.exemploprojeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.exemploprojeto.connect.Connect;
import com.exemploprojeto.model.CategoriaEquipamento;

public class CategoriaEquipamentoDAO {

    static Connect connect = new Connect();

    public void insert(CategoriaEquipamento categoriaEquipamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(categoriaEquipamento);
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

    public void delete(CategoriaEquipamento categoriaEquipamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CategoriaEquipamento mergedCategoriaEquipamento = em.merge(categoriaEquipamento);
            em.remove(mergedCategoriaEquipamento);
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

    public void update(CategoriaEquipamento categoriaEquipamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(categoriaEquipamento);
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

    public CategoriaEquipamento selectById(int id) {
        EntityManager em = connect.getEntityManager();
        CategoriaEquipamento categoriaEquipamento = null;
        try {
            categoriaEquipamento = em.find(CategoriaEquipamento.class, id);
        } finally {
            em.close();
        }
        return categoriaEquipamento;
    }

    public List<CategoriaEquipamento> selectAll() {
        EntityManager em = connect.getEntityManager();
        List<CategoriaEquipamento> categoriasEquipamento = null;
        try {
            categoriasEquipamento = em.createQuery("FROM " + CategoriaEquipamento.class.getName(), CategoriaEquipamento.class).getResultList();
        } finally {
            em.close();
        }
        return categoriasEquipamento;
    }
}
