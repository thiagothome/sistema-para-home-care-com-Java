package br.com.iffar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.iffar.model.Recado;
import br.com.iffar.model.Resposta;

public class RespostaDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemahomecare");
	private EntityManager em = emf.createEntityManager();

	public void salvar(Resposta resposta) {
		try {
			em.getTransaction().begin();
			em.merge(resposta);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();

		}

	}

	public List<Resposta> buscarPorNome(String nome) {

		Query query = em.createQuery("SELECT c FROM Resposta c WHERE c.assunto LIKE :nome");
		query.setParameter("nome", "%" + nome + "%");
		@SuppressWarnings("unchecked")
		List<Resposta> resultados = query.getResultList();
		if (resultados.isEmpty()) {
			return null;
		} else {
			return resultados;
		}
	}

	public List<Resposta> buscarResposta(int idRecado) {
		TypedQuery<Resposta> query = em.createQuery("SELECT r FROM Resposta r WHERE r.recado.idRecado = :idRecado",
				Resposta.class);
		query.setParameter("idRecado", idRecado);
		return query.getResultList();
	}

	public Recado buscarRecadoPorId(int idRecado) {
		Recado recado = em.find(Recado.class, idRecado);
		return recado;
	}

}
