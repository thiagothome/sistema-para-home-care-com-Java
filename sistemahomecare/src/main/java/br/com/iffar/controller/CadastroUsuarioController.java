package br.com.iffar.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.CadastroUsuarioDao;
import br.com.iffar.dao.ColaboradorDao;
import br.com.iffar.model.Colaborador;
import br.com.iffar.model.Usuario;

@ManagedBean
@RequestScoped
public class CadastroUsuarioController {

	private Usuario usuario = new Usuario();
	private CadastroUsuarioDao dao = new CadastroUsuarioDao();

	private int colaboradorSelecionadoId;
	private Colaborador colaborador = new Colaborador();
	private ColaboradorDao clDao = new ColaboradorDao();

	public void cadastrar() {

		if (!isValidEmail(usuario.getLogin())) {
			FacesContext.getCurrentInstance().addMessage("form:login",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de e-mail inválido.", null));
			return;
		}
		
		if (usuario.getLogin() == null || usuario.getLogin().isEmpty() || usuario.getSenha() == null
				|| usuario.getSenha().isEmpty() || colaboradorSelecionadoId == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Por favor, preencha todos os campos obrigatórios", null));
			return;
		}

		try {
			colaborador = clDao.buscarPorId(colaboradorSelecionadoId);
			usuario.setColaborador(colaborador);
			dao.cadastrar(usuario);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "colaborador salvo com sucesso.", ""));
			usuario = new Usuario();

		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha todos os campos.", ""));
		}

	}

	private boolean isValidEmail(String email) {

		if (email == null || email.isEmpty()) {
			return false;
		}
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		return email.matches(regex);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CadastroUsuarioDao getDao() {
		return dao;
	}

	public void setDao(CadastroUsuarioDao dao) {
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
