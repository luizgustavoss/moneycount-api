package br.com.votti.api.moneycount.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public class MoedasSuportadas {

	private Set<Moeda> moedasSuportadas = new HashSet<>();
	
	public void adicionarMoeda(Moeda moeda) {
		this.moedasSuportadas.add(moeda);
	}
	
}
