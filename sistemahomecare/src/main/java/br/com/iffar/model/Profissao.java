package br.com.iffar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profissao")
public class Profissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profissao_id")
	private int idProfissao;

	private String profissao;

	public int getIdProfissao() {
		return idProfissao;
	}

	public void setIdProfissao(int idProfissao) {
		this.idProfissao = idProfissao;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

}
