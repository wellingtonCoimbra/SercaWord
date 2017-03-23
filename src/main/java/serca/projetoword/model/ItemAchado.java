package serca.projetoword.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemAchado implements Comparable<ItemAchado> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int posicao;
	
	@ManyToOne
	private Achado achado;
	
	@ManyToOne
	private Relatorio relatorio;
	
	public ItemAchado(){
		
	}
	
	public ItemAchado(Relatorio relatorio2, Achado achado2) {
		this.relatorio = relatorio2;
		this.achado = achado2;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public Achado getAchado() {
		return achado;
	}

	public void setAchado(Achado achado) {
		this.achado = achado;
	}

	@Override
	public int compareTo(ItemAchado o) {
		return ((Integer)this.posicao).compareTo(o.getPosicao());
	}

	
	

}
