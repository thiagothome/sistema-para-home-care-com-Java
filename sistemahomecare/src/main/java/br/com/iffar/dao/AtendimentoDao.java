package br.com.iffar.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Atendimento;

public class AtendimentoDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");
	EntityManager em = emf.createEntityManager();

	
	
	public void salvar(Atendimento atendimento) {

		try {
			em.getTransaction().begin();
			em.merge(atendimento);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();

		}

	}

	public List<Atendimento> listar() {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Atendimento> query = em.createQuery("SELECT c FROM Atendimento c", Atendimento.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public List<Atendimento> obterAtendimentosDoBanco() {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Atendimento> query = em.createQuery("SELECT a FROM Atendimento a", Atendimento.class);
			return query.getResultList();
		} finally {
			em.close();
			emf.close();
		}
	}

	public List<Atendimento> buscarPorData(LocalDate data) {
		EntityManager em = emf.createEntityManager();

		try {
			Query query = em.createQuery("SELECT c FROM Atendimento c WHERE c.data_atendimento LIKE :data");
			query.setParameter("data", data);
			@SuppressWarnings("unchecked")
			List<Atendimento> resultados = query.getResultList();

			if (resultados.isEmpty()) {
				return null;
			} else {
				return resultados;
			}
		} finally {
			em.close();
		}
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
