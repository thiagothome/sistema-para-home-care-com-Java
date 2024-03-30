package br.com.iffar.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.UsuarioDao;
import br.com.iffar.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {

	private UsuarioDao dao = new UsuarioDao();
	private Usuario usuario = new Usuario();
	private String usuarioLogado;
	public String nome = "thiago";

	public String autenticar() {
		Usuario usuarioEntidade = dao.buscarPorLogin(usuario.getLogin());
		if (usuarioEntidade != null && usuarioEntidade.getSenha().equals(usuario.getSenha())) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado",
					usuarioEntidade);
			this.usuarioLogado = usuarioEntidade.getLogin();
			System.out.println("Usuário logado: " + usuarioEntidade.getColaborador().getNome_colaborador());

			return "pacienteslista.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credenciais inválidas"));
			return "";
		}
	}

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.invalidateSession();
		externalContext.redirect("login.xhtml");
	}

	public UsuarioDao getDao() {
		return dao;
	}

	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
