package br.com.iffar.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.iffar.model.Usuario;

public class CadastroUsuarioDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public void cadastrar(Usuario usuario) {

		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();

		}

	}

}
