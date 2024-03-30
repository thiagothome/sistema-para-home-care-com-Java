package br.com.iffar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.PacienteDao;
import br.com.iffar.model.Paciente;

@ManagedBean
@ViewScoped
public class PacienteController {

	private Paciente paciente = new Paciente();
	private PacienteDao dao = new PacienteDao();
	private String nome_paciente;
	private LocalDate nascimento;

	public void salvar() {

		try {
			dao.salvar(paciente);
			paciente = new Paciente();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente salvo com sucesso.", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Paciente> listaPacientes() {
		List<Paciente> resultado = dao.listar();
		return resultado;
	}

	public void buscarPorNome() {

		paciente = dao.buscarPorNome(nome_paciente);
	}

	public void excluir(Paciente c) {
		try {
			dao.excluir(c.getIdPaciente());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluído com sucesso.", "Error"));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao excluir Paciente", e);
		}
	}

	public void editar() {
		try {
			paciente.setNascimento(nascimento);
			dao.editarPaciente(paciente);
			paciente = new Paciente();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado com sucesso.", "Sucesso"));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao editar Paciente", e);
		}
	}

	public String formatarData(String data) {
		if (data == null || data.isEmpty()) {
			return "";
		}

		LocalDate localDate = LocalDate.parse(data);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return localDate.format(formatter);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente Paciente) {
		this.paciente = Paciente;
	}

	public PacienteDao getDao() {
		return dao;
	}

	public void setDao(PacienteDao dao) {
		this.dao = dao;
	}

	public String getNome_paciente() {
		return nome_paciente;
	}

	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

}
