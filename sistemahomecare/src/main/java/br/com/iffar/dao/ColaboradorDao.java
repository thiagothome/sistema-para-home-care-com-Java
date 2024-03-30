package br.com.iffar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Colaborador;

public class ColaboradorDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public void salvar(Colaborador colaborador) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(colaborador);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public List<Colaborador> listar() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Colaborador> query = em.createQuery("SELECT c FROM Colaborador c", Colaborador.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public List<Colaborador> buscarColaboradoresParaRelatorio() {
		List<Colaborador> colaboradores = null;
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			String hql = "SELECT c FROM Colaborador c";
			colaboradores = em.createQuery(hql, Colaborador.class).getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		return colaboradores;
	}

	public List<Colaborador> buscarPorNome(String nome) {
		EntityManager em = emf.createEntityManager();
		try {
			nome.trim();
			Query query = em.createQuery("SELECT c FROM Colaborador c WHERE c.nome_colaborador LIKE :nome");
			query.setParameter("nome", nome + "%");
			@SuppressWarnings("unchecked")
			List<Colaborador> resultados = query.getResultList();
			if (resultados.isEmpty()) {
				return null;
			} else {
				return resultados;
			}
		} finally {
			em.close();
		}
	}

	public Colaborador buscarPorId(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			Colaborador colaborador = em.find(Colaborador.class, id);
			return colaborador;

		} finally {
			em.close();
		}
	}

	public void excluir(int idColaborador) {
		EntityManager em = emf.createEntityManager();
		try {
			Colaborador colaborador = em.find(Colaborador.class, idColaborador);
			em.getTransaction().begin();
			em.remove(colaborador);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void editarcolaborador(Colaborador colaborador) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			if (!em.contains(colaborador)) {
				colaborador = em.merge(colaborador);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao editar colaborador", e);
		} finally {
			em.close();
		}
	}
}
