package serca.projetoword.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

/*
 * Comentario
 */
@Entity
public class Relatorio {
	private String nome;
	private Date data;
	private int id;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private List<ItemAchado> itens;

	public List<ItemAchado> getItens() {
		return itens;
	}

	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public void setItens(List<ItemAchado> itens) {
		this.itens = itens;
	}
	
}
