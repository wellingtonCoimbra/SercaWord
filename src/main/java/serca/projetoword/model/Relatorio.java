package serca.projetoword.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Relatorio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	@OneToMany(mappedBy="relatorio")
	private List<ItemAchado> itensCompra;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

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
	
	public List<ItemAchado> getItensOrdenados(){
		List<ItemAchado> itensOrdenados = new ArrayList<>();
		itensOrdenados.addAll(this.getItens());
		Collections.sort(itensOrdenados);
		return itensOrdenados;
	}
	
}
