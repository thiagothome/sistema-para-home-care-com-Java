package br.com.iffar.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private int idPaciente;

	@Column(nullable = false)
	private String nome_paciente;

	@Column(name = "nascimento", nullable = false)
	private LocalDate nascimento;

	@Column(name = "patologia")
	private String patologia;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Paciente() {
		this.endereco = new Endereco();
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	
	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome_paciente() {
		return nome_paciente;
	}

	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}

	
}
