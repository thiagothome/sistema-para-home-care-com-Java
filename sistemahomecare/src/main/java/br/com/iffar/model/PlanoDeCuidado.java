package br.com.iffar.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plano_de_cuidado")
public class PlanoDeCuidado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plano")
	private int idPlano;

	@Column(name = "data_plano")
	private LocalDate dataPlano;

	@Column(nullable = false)
	private String prescricao;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_colaborador")
	private Colaborador colaborador;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id")
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(int idPlano) {
		this.idPlano = idPlano;
	}

	public LocalDate getDataPlano() {
		return dataPlano;
	}

	public void setDataPlano(LocalDate dataPlano) {
		this.dataPlano = dataPlano;
	}

	public String getPrescricao() {
		return prescricao;
	}

	public void setPrescricao(String prescricao) {
		this.prescricao = prescricao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	
	
	

}
