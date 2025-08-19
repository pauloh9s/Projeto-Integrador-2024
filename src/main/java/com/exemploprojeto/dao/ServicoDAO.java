package com.exemploprojeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.exemploprojeto.connect.Connect;
import com.exemploprojeto.model.Funcionario;
import com.exemploprojeto.model.Servico;

public class ServicoDAO {

	static Connect connect = new Connect();

    public void insert(Servico servico) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(servico);
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

    public void delete(Servico servico) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Servico mergedServico = em.merge(servico);
            em.remove(mergedServico);
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

    public void update(Servico servico) {
        EntityManager em = connect.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(servico);
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

    public Servico selectById(int id) {
        EntityManager em = connect.getEntityManager();
        Servico servico = null;
        try {
            servico = em.find(Servico.class, id);
        } finally {
            em.close();
        }
        return servico;
    }


	 public List<Servico> selectAll() {
	        EntityManager em = connect.getEntityManager();
	        List<Servico> servicos = null;
	        try {
	            servicos = em.createQuery("FROM " +	Servico.class.getName(), Servico.class).getResultList();
	        } finally {
	            em.close();
	        }
	        return servicos;
	    }
	 
	  public List<Servico> findServicosPendentes() {
	        EntityManager em = connect.getEntityManager();
			TypedQuery<Servico> query = em.createQuery("SELECT s FROM Servico s WHERE s.status = :status", Servico.class);
	        query.setParameter("status", "Pendente");
	        return query.getResultList();
	    }
	  
	  public List<Servico> findServicosPendentesFuncionarios(Funcionario funcionario) {
	        EntityManager em = connect.getEntityManager();
			TypedQuery<Servico> query = em.createQuery("SELECT s FROM Servico s WHERE s.status = 'Pendente' AND s.funcionario = :funcionario", Servico.class);
	        query.setParameter("funcionario", funcionario);
	        return query.getResultList();
	    }
	        
}

	

