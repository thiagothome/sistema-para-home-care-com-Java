package br.com.iffar.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.ColaboradorDao;
import br.com.iffar.model.Colaborador;

@ManagedBean
@ViewScoped
public class ColaboradorController {

	private Colaborador colaborador = new Colaborador();
	private ColaboradorDao dao = new ColaboradorDao();
	private String nomeProcurado;
	private List<Colaborador> colaboradoresEncontrados;
	private String profissaoSelecionada;
	
	public boolean isEnfermeiroLogado() {
        // Recupere a profissão do colaborador logado (você pode adaptar conforme a sua lógica de autenticação)
        String profissaoColaboradorLogado = colaborador.getProfissao().trim().toLowerCase();

        // Verifique se a profissão é "ENFERMEIRO"
        return "enfermeiro".equals(profissaoColaboradorLogado);
    }

	public void salvar() {
		try {
			colaborador.setProfissao(profissaoSelecionada);
			dao.salvar(colaborador);
			colaborador = new Colaborador();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "colaborador salvo com sucesso.", ""));
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha todos os campos.", ""));
		}

	}

	public List<Colaborador> listaColaboradores() {
		List<Colaborador> resultado = dao.listar();
		return resultado;
	}

	public void buscarPorNome() {
		colaboradoresEncontrados = dao.buscarPorNome(nomeProcurado);
	}

	public void excluir(Colaborador c) {
		try {
			dao.excluir(c.getidColaborador());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluído com sucesso.", "Error"));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao excluir colaborador", e);
		}
	}

	public void editar() {

		try {
			dao.editarcolaborador(colaborador);
			colaborador = new Colaborador();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado com sucesso.", "Sucesso"));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao editar colaborador", e);
		}
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public ColaboradorDao getDao() {
		return dao;
	}

	public void setDao(ColaboradorDao dao) {
		this.dao = dao;
	}

	public List<Colaborador> getColaboradoresEncontrados() {
		return colaboradoresEncontrados;
	}

	public void setColaboradoresEncontrados(List<Colaborador> colaboradoresEncontrados) {
		this.colaboradoresEncontrados = colaboradoresEncontrados;
	}

	public String getNomeProcurado() {
		return nomeProcurado;
	}

	public void setNomeProcurado(String nomeProcurado) {
		this.nomeProcurado = nomeProcurado;
	}

	public String getProfissaoSelecionada() {
		return profissaoSelecionada;
	}

	public void setProfissaoSelecionada(String profissaoSelecionada) {
		this.profissaoSelecionada = profissaoSelecionada;
	}

	
}