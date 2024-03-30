package br.com.iffar.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.iffar.dao.ProfissaoDao;
import br.com.iffar.model.Profissao;

@ManagedBean
@RequestScoped
public class ProfissaoController {

	private Profissao profissao = new Profissao();
	private ProfissaoDao dao = new ProfissaoDao();

	private String profissaoSelecionada;
	private List<Profissao> listaProfissoes;
	
	public ProfissaoController() {
		init();
	}

	public void init() {
		listaProfissoes = dao.buscarProfissoes();
	}

	public void salvar() {
		dao.salvar(profissao);
	}

	public void buscarProfissoes() {
		listaProfissoes = dao.buscarProfissoes();
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public ProfissaoDao getDao() {
		return dao;
	}

	public void setDao(ProfissaoDao dao) {
		this.dao = dao;
	}

	

	public String getProfissaoSelecionada() {
		return profissaoSelecionada;
	}

	public void setProfissaoSelecionada(String profissaoSelecionada) {
		this.profissaoSelecionada = profissaoSelecionada;
	}

	public List<Profissao> getListaProfissoes() {
		return listaProfissoes;
	}

	public void setListaProfissoes(List<Profissao> listaProfissoes) {
		this.listaProfissoes = listaProfissoes;
	}

}
