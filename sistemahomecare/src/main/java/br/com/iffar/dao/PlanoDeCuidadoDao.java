package br.com.iffar.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.iffar.model.PlanoDeCuidado;

public class PlanoDeCuidadoDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public void salvar(PlanoDeCuidado plano) {

		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(plano);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new RuntimeException("Erro ao salvar plano", e);
		} finally {
			em.close();

		}
	}
	
	public void excluir(int idPlano) {

		EntityManager em = emf.createEntityManager();
		try {
			PlanoDeCuidado plano = em.find(PlanoDeCuidado.class, idPlano);
			em.getTransaction().begin();
			em.remove(plano);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void editarPlano(PlanoDeCuidado plano) {

		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(plano);
			em.getTransaction().commit();
		} catch (Exception e) {

		}
	}

	public List<PlanoDeCuidado> listar() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<PlanoDeCuidado> query = em.createQuery("SELECT c FROM PlanoDeCuidado c", PlanoDeCuidado.class);
		return query.getResultList();
	}

	public List<PlanoDeCuidado> buscarPorData(LocalDate data) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT c FROM PlanoDeCuidado c WHERE c.dataPlano LIKE :data");
		query.setParameter("data", data);

		@SuppressWarnings("unchecked")
		List<PlanoDeCuidado> resultados = query.getResultList();

		if (resultados.isEmpty()) {
			return null;
		} else {
			return resultados;
		}
	}

}
