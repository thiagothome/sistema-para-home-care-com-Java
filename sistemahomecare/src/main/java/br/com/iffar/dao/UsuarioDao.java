package br.com.iffar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Usuario;

public class UsuarioDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public Usuario buscarPorLogin(String login) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public void salvar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public List<Usuario> listar() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT c FROM Usuario c", Usuario.class);
			return query.getResultList();
		} finally {
			em.close();
		}

	}
	
	public Usuario buscarPorId(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);
			return usuario;

		} finally {
			em.close();
		}

	}

}
