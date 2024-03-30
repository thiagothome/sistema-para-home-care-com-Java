package br.com.iffar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Recado;

public class RecadoDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public void salvar(Recado recado) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(recado);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();

		}
	}

	public List<Recado> listar() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Recado> query = em.createQuery("SELECT r FROM Recado r", Recado.class);
			return query.getResultList();
		} finally {
			em.close();
		}

	}

	public void excluir(int idRecado) {
		EntityManager em = emf.createEntityManager();
		try {
			Recado recado = em.find(Recado.class, idRecado);
			em.getTransaction().begin();
			em.remove(recado);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}

	}

}
