package br.com.iffar.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RelatorioView")
public class RelatorioView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String anotacao;
    private Date data_atendimento;
    private String nome_colaborador;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnotacao() {
		return anotacao;
	}
	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
	public Date getData_atendimento() {
		return data_atendimento;
	}
	public void setData_atendimento(Date data_atendimento) {
		this.data_atendimento = data_atendimento;
	}
	public String getNome_colaborador() {
		return nome_colaborador;
	}
	public void setNome_colaborador(String nome_colaborador) {
		this.nome_colaborador = nome_colaborador;
	}
    
    
}
