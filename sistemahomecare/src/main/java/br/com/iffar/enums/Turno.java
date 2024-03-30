package br.com.iffar.enums;

import java.util.Arrays;
import java.util.List;

public enum Turno {
	MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite");

	private String descricao;

	private Turno(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<Turno> valuesAsList() {
		return Arrays.asList(Turno.values());
	}
}
