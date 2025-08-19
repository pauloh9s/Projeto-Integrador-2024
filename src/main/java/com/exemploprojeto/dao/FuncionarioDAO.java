package com.exemploprojeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.exemploprojeto.connect.Connect;
import com.exemploprojeto.model.Funcionario;

public class FuncionarioDAO {

	static Connect connect = new Connect();

    public void insert(Funcionario funcionario) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(funcionario);
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

    public void delete(Funcionario funcionario) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Funcionario mergedFuncionario = em.merge(funcionario);
            em.remove(mergedFuncionario);
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

    public void update(Funcionario funcionario) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(funcionario);
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

    public Funcionario selectById(int id) {
        EntityManager em = connect.getEntityManager();
        Funcionario funcionario = null;
        try {
            funcionario = em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
        return funcionario;
    }


	 public List<Funcionario> selectAll() {
	        EntityManager em = connect.getEntityManager();
	        List<Funcionario> funcionarios = null;
	        try {
	            funcionarios = em.createQuery("FROM " + Funcionario.class.getName(), Funcionario.class).getResultList();
	        } finally {
	            em.close();
	        }
	        return funcionarios ;
	    }
	 public Funcionario findByName(String nameUser) {
		 	EntityManager em = connect.getEntityManager();
		    TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.nome = :nameUser", Funcionario.class);
		    query.setParameter("nameUser", nameUser);
		    return query.getSingleResult();
	 }
}


