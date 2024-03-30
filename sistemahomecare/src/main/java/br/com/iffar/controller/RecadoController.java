package br.com.iffar.controller;

import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.ColaboradorDao;
import br.com.iffar.dao.RecadoDao;
import br.com.iffar.model.Colaborador;
import br.com.iffar.model.Recado;

@ManagedBean
@RequestScoped
public class RecadoController {

	private RecadoDao dao = new RecadoDao();
	private Recado recado = new Recado();
	private int colaboradorSelecionadoId;
	private Colaborador colaborador = new Colaborador();
	private ColaboradorDao clDao = new ColaboradorDao();

	public void salvar() {
		colaborador = clDao.buscarPorId(colaboradorSelecionadoId);

		try {
			recado.setColaborador(colaborador);
			recado.setDataRecado(LocalDate.now());
			dao.salvar(recado);
			recado = new Recado();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Recado salvo com sucesso.", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<Recado> listaRecados() {
		return dao.listar();
	}

	public void excluir(Recado r) {
		try {
			dao.excluir(r.getIdRecado());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluído com sucesso.", "Error"));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao excluir recado", e);
		}

	}

	public Recado getRecado() {
		return recado;
	}

	public void setRecado(Recado recado) {
		this.recado = recado;
	}

	public RecadoDao getDao() {
		return dao;
	}

	public void setDao(RecadoDao dao) {
		this.dao = dao;
	}

	public int getColaboradorSelecionadoId() {
		return colaboradorSelecionadoId;
	}

	public void setColaboradorSelecionadoId(int colaboradorSelecionadoId) {
		this.colaboradorSelecionadoId = colaboradorSelecionadoId;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public ColaboradorDao getClDao() {
		return clDao;
	}

	public void setClDao(ColaboradorDao clDao) {
		this.clDao = clDao;
	}

}
