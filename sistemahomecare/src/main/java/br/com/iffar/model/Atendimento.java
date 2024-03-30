package br.com.iffar.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.iffar.enums.Turno;

@Entity
@Table(name = "atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atendimento")
	private int idAtendimento;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	@Column(nullable = false)
	private String anotacao;

	@Column(name = "data_atendimento")
	private LocalDate data_atendimento;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_colaborador")
	private Colaborador colaborador;
	
	public int getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(int idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public LocalDate getdata_atendimento() {
		return data_atendimento;
	}

	public void setdata_atendimento(LocalDate data_atendimento) {
		this.data_atendimento = data_atendimento;
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

	 public String getTurnoDescricao() {
	        return turno.getDescricao();
	    }
	

}
