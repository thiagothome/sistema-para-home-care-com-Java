package br.com.iffar.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.RespostaDao;
import br.com.iffar.model.Recado;
import br.com.iffar.model.Resposta;

@ManagedBean
@RequestScoped
public class RespostaController {

	@ManagedProperty(value = "#{param.idRecado}")
	private int idRecado;

	private Resposta resposta = new Resposta();
	private RespostaDao dao = new RespostaDao();
	List<Resposta> respostas = new ArrayList<>();

	public void salvar() {
		Recado recado = dao.buscarRecadoPorId(idRecado);
		try {
			resposta.setRecado(recado);
			dao.salvar(resposta);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Recado respondido com sucesso.", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void buscarResposta(Recado recado) {

		respostas = dao.buscarResposta(recado.getIdRecado());

	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public int getIdRecado() {
		return idRecado;
	}

	public void setIdRecado(int idRecado) {
		this.idRecado = idRecado;
	}

	public RespostaDao getDao() {
		return dao;
	}

	public void setDao(RespostaDao dao) {
		this.dao = dao;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

}