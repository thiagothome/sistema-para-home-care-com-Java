package br.com.iffar.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.iffar.dao.ColaboradorDao;
import br.com.iffar.dao.PacienteDao;
import br.com.iffar.dao.PlanoDeCuidadoDao;
import br.com.iffar.dao.UsuarioDao;
import br.com.iffar.model.Colaborador;
import br.com.iffar.model.Paciente;
import br.com.iffar.model.PlanoDeCuidado;
import br.com.iffar.model.Usuario;

@ManagedBean
@RequestScoped
public class PlanoDeCuidadoController {

	private PlanoDeCuidadoDao dao = new PlanoDeCuidadoDao();
	private PlanoDeCuidado plano = new PlanoDeCuidado();
	private List<PlanoDeCuidado> planosEncontrados;
	private LocalDate dataProcurada;
	private PacienteDao pDao = new PacienteDao();
	private PlanoDeCuidadoDao plDao = new PlanoDeCuidadoDao();
	private ColaboradorDao clDao = new ColaboradorDao();
	private UsuarioDao uDao = new UsuarioDao();

	private int pacienteSelecionadoId;
	private int usuarioSelecionadoId;
	private int colaboradorSelecionadoId;
	private Colaborador colaborador;
	private Paciente paciente;
	private Usuario usuario;

	private Usuario usuarioLogado;
	private List<Usuario> colaboradores;

	public PlanoDeCuidadoController() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		colaboradores = new ArrayList<>();
		colaboradores.add(usuarioLogado);
		System.out.println(usuarioLogado.getColaborador().getNome_colaborador());
	}

	public void salvar() {
		paciente = pDao.buscarPorId(pacienteSelecionadoId);
		colaborador = clDao.buscarPorId(colaboradorSelecionadoId);
		try {
			plano.setColaborador(colaborador);
			plano.setPaciente(paciente);
			plano.setDataPlano(LocalDate.now());
			dao.salvar(plano);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Plano de cuidado salvo com sucesso.", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PlanoDeCuidado> listaPlanos() {
		List<PlanoDeCuidado> resultado = plDao.listar();
		return resultado;
	}

	public List<Colaborador> listaColaboradores() {
		List<Colaborador> resultado = clDao.listar();
		return resultado;
	}

	public void buscarPorData() {
		planosEncontrados = dao.buscarPorData(dataProcurada);
	}

	public PlanoDeCuidado getPlano() {
		return plano;
	}

	public void setPlano(PlanoDeCuidado plano) {
		this.plano = plano;
	}

	public List<PlanoDeCuidado> getPlanosEncontrados() {
		return planosEncontrados;
	}

	public void setPlanosEncontrados(List<PlanoDeCuidado> planosEncontrados) {
		this.planosEncontrados = planosEncontrados;
	}

	public LocalDate getDataProcurada() {
		return dataProcurada;
	}

	public void setDataProcurada(LocalDate dataProcurada) {
		this.dataProcurada = dataProcurada;
	}

	public PlanoDeCuidadoDao getDao() {
		return dao;
	}

	public void setDao(PlanoDeCuidadoDao dao) {
		this.dao = dao;
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

	public int getPacienteSelecionadoId() {
		return pacienteSelecionadoId;
	}

	public void setPacienteSelecionadoId(int pacienteSelecionadoId) {
		this.pacienteSelecionadoId = pacienteSelecionadoId;
	}

	public PacienteDao getpDao() {
		return pDao;
	}

	public void setpDao(PacienteDao pDao) {
		this.pDao = pDao;
	}

	public PlanoDeCuidadoDao getPlDao() {
		return plDao;
	}

	public void setPlDao(PlanoDeCuidadoDao plDao) {
		this.plDao = plDao;
	}

	public int getColaboradorSelecionadoId() {
		return colaboradorSelecionadoId;
	}

	public void setColaboradorSelecionadoId(int colaboradorSelecionadoId) {
		this.colaboradorSelecionadoId = colaboradorSelecionadoId;
	}

	public ColaboradorDao getClDao() {
		return clDao;
	}

	public void setClDao(ColaboradorDao clDao) {
		this.clDao = clDao;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Usuario> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Usuario> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public int getUsuarioSelecionadoId() {
		return usuarioSelecionadoId;
	}

	public void setUsuarioSelecionadoId(int usuarioSelecionadoId) {
		this.usuarioSelecionadoId = usuarioSelecionadoId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getuDao() {
		return uDao;
	}

	public void setuDao(UsuarioDao uDao) {
		this.uDao = uDao;
	}

}
