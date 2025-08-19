package com.exemploprojeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.exemploprojeto.connect.Connect;
import com.exemploprojeto.model.Cliente;

public class ClienteDAO {

	static Connect connect = new Connect();

    public void insert(Cliente cliente) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(cliente);
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

    public void delete(Cliente cliente) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Cliente mergedCliente = em.merge(cliente);
            em.remove(mergedCliente);
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

    public void update(Cliente cliente) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(cliente);
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

    public Cliente selectById(int id) {
        EntityManager em = connect.getEntityManager();
        Cliente cliente = null;
        try {
            cliente = em.find(Cliente.class, id);
        } finally {
            em.close();
        }
        return cliente;
    }

    public List<Cliente> selectAll() {
        EntityManager em = connect.getEntityManager();
        List<Cliente> clientes = null;
        try {
            clientes = em.createQuery("FROM " + Cliente.class.getName(), Cliente.class).getResultList();
        } finally {
            em.close();
        }
        return clientes;
    }
}

