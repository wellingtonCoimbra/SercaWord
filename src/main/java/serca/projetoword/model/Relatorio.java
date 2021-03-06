package serca.projetoword.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Relatorio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	@OneToMany(mappedBy="relatorio", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ItemAchado> itens;
	
//	@Temporal(TemporalType.DATE)
	@Transient
	private Date data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
