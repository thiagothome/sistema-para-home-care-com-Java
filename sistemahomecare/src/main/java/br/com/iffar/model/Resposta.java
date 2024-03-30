package br.com.iffar.model;

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
@Table(name = "resposta")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resposta")
	private int idResposta;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String mensagem;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_recado")
	private Recado recado;

	public int getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Recado getRecado() {
		return recado;
	}

	public void setRecado(Recado recado) {
		this.recado = recado;
	}

}
