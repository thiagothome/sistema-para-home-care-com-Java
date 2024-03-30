package br.com.iffar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Profissao;

public class ProfissaoDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public void salvar(Profissao profissao) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(profissao);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public List<Profissao> buscarProfissoes() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Profissao> query = em.createQuery("SELECT p FROM Profissao p", Profissao.class);
		return query.getResultList();
	}

}
