package br.com.iffar.controller;

import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.AtendimentoDao;
import br.com.iffar.dao.ColaboradorDao;
import br.com.iffar.dao.PacienteDao;
import br.com.iffar.enums.Turno;
import br.com.iffar.model.Atendimento;
import br.com.iffar.model.Colaborador;
import br.com.iffar.model.Paciente;

@ManagedBean
@ViewScoped
public class AtendimentoController {

	private Atendimento atendimento = new Atendimento();
	private AtendimentoDao dao = new AtendimentoDao();
	private List<Atendimento> atendimentosEncontrados;
	private LocalDate dataProcurada;
	private PacienteDao pDao = new PacienteDao();
	private ColaboradorDao clDao = new ColaboradorDao();

	private int pacienteSelecionadoId;
	private int colaboradorSelecionadoId;

	private Colaborador colaborador;
	private Paciente paciente;

	public void salvar() {
		paciente = pDao.buscarPorId(pacienteSelecionadoId);
		colaborador = clDao.buscarPorId(colaboradorSelecionadoId);
		try {
			atendimento.setColaborador(colaborador);
			atendimento.setPaciente(paciente);
			atendimento.setdata_atendimento(LocalDate.now());
			dao.salvar(atendimento);
			atendimento = new Atendimento();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atendimento salvo com sucesso.", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Atendimento> listaAtendimentos() {
		return dao.listar();
	}

	public void buscarPorData() {
		atendimentosEncontrados = dao.buscarPorData(dataProcurada);
	}

	public List<Atendimento> getAtendimentosEncontrados() {
		return atendimentosEncontrados;
	}

	public void setAtendimentosEncontrados(List<Atendimento> atendimentosEncontrados) {
		this.atendimentosEncontrados = atendimentosEncontrados;
	}

	public List<Turno> getTurnos() {
		return Turno.valuesAsList();
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public LocalDate getDataProcurada() {
		return dataProcurada;
	}

	public void setDataProcurada(LocalDate dataProcurada) {
		this.dataProcurada = dataProcurada;
	}

	public AtendimentoDao getDao() {
		return dao;
	}

	public void setDao(AtendimentoDao dao) {
		this.dao = dao;
	}

	public PacienteDao getpDao() {
		return pDao;
	}

	public void setpDao(PacienteDao pDao) {
		this.pDao = pDao;
	}

	public ColaboradorDao getClDao() {
		return clDao;
	}

	public void setClDao(ColaboradorDao clDao) {
		this.clDao = clDao;
	}

	public int getPacienteSelecionadoId() {
		return pacienteSelecionadoId;
	}

	public void setPacienteSelecionadoId(int pacienteSelecionadoId) {
		this.pacienteSelecionadoId = pacienteSelecionadoId;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public int getColaboradorSelecionadoId() {
		return colaboradorSelecionadoId;
	}

	public void setColaboradorSelecionadoId(int colaboradorSelecionadoId) {
		this.colaboradorSelecionadoId = colaboradorSelecionadoId;
	}

}
