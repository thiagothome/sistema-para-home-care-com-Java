package br.com.iffar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_colaborador")
	private int idColaborador;

	@Column(name = "nome_colaborador")
	private String nome_colaborador;

	@OneToMany(mappedBy = "colaborador")
	private List<Atendimento> atendimentos;


	@Column(nullable = false)
	private String profissao;

	@Column(nullable = false)
	private String registro;

	public int getidColaborador() {
		return idColaborador;
	}

	public void setidColaborador(int idColaborador) {
		this.idColaborador = idColaborador;
	}

	
	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public int getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(int idColaborador) {
		this.idColaborador = idColaborador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNome_colaborador() {
		return nome_colaborador;
	}

	public void setNome_colaborador(String nome_colaborador) {
		this.nome_colaborador = nome_colaborador;
	}
	
}
