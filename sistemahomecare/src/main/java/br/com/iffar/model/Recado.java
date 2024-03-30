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
@Table(name = "recado")
public class Recado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recado")
	private int idRecado;

	@Column(name = "mensagem", columnDefinition = "TEXT", nullable = false)
	private String mensagem;
	
	@Column(name = "data_recado")
	private LocalDate dataRecado;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_colaborador")
	private Colaborador colaborador;

	public int getIdRecado() {
		return idRecado;
	}

	public void setIdRecado(int idRecado) {
		this.idRecado = idRecado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDate getDataRecado() {
		return dataRecado;
	}

	public void setDataRecado(LocalDate dataRecado) {
		this.dataRecado = dataRecado;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	
	

}
