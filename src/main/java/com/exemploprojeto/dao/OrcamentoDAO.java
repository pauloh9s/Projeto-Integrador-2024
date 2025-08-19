package com.exemploprojeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.exemploprojeto.connect.Connect;
import com.exemploprojeto.model.Orcamento;
import com.exemploprojeto.model.Servico;

public class OrcamentoDAO {

	static Connect connect = new Connect();

    public void insert(Orcamento orcamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(orcamento);
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

    public void delete(Orcamento orcamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Orcamento mergedOrcamento = em.merge(orcamento);
            em.remove(mergedOrcamento);
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

    public void update(Orcamento orcamento) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(orcamento);
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

    public Orcamento selectById(int id) {
        EntityManager em = connect.getEntityManager();
        Orcamento orcamento = null;
        try {
        	orcamento = em.find(Orcamento.class, id);
        } finally {
            em.close();
        }
        return orcamento;
    }


	 public List<Orcamento> selectAll() {
	        EntityManager em = connect.getEntityManager();
	        List<Orcamento> orcamentos = null;
	        try {
	        	orcamentos = em.createQuery("FROM " + Orcamento.class.getName(), Orcamento.class).getResultList();
	        } finally {
	            em.close();
	        }
	        return orcamentos ;
	    }
	 
	 public List<Orcamento> findByServico(Servico servico) {
		  	EntityManager em = connect.getEntityManager();
		    List<Orcamento> orcamentos = null;
		    try {
		        TypedQuery<Orcamento> query = em.createQuery("from Orcamento where servico = :servico", Orcamento.class);
		        query.setParameter("servico", servico);
		        orcamentos = query.getResultList();
		    } finally {
		    	em.close();
		    }
		    return orcamentos;
		}
	 
	 public Orcamento FindOrcamentoExistente(Servico servico) {
		 	EntityManager em = connect.getEntityManager();
		    TypedQuery<Orcamento> query = em.createQuery(
		        "SELECT o FROM Orcamento o WHERE o.servico = :servico", Orcamento.class);
		    query.setParameter("servico", servico);
		    List<Orcamento> result = query.getResultList();
		    return result.isEmpty() ? null : result.get(0);
		}
	
}
