package br.com.iffar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Paciente;

public class PacienteDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");

	public void salvar(Paciente Paciente) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(Paciente);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();

		}
	}

	public Paciente buscarPorId(int id) {
		EntityManager em = emf.createEntityManager();
		Paciente paciente = em.find(Paciente.class, id);
		em.close();
		return paciente;
	}

	public List<Paciente> listar() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Paciente> query = em.createQuery("SELECT c FROM Paciente c", Paciente.class);
			return query.getResultList();
		} finally {
			em.close();
		}

	}

	public Paciente buscarPorNome(String nome) {
		EntityManager em = emf.createEntityManager();

		try {
			Query query = em.createQuery("SELECT c FROM Paciente c WHERE c.nome_paciente LIKE :nome");
			query.setParameter("nome", "%" + nome + "%");
			@SuppressWarnings("unchecked")
			List<Paciente> resultados = query.getResultList();
			if (resultados.isEmpty()) {
				return null;
			} else {
				return resultados.get(0);
			}
		} finally {
			em.close();
		}
	}

	public void excluir(int idPaciente) {
		EntityManager em = emf.createEntityManager();
		try {
			Paciente Paciente = em.find(Paciente.class, idPaciente);
			em.getTransaction().begin();
			em.remove(Paciente);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void editarPaciente(Paciente paciente) {
		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();
			em.merge(paciente);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
