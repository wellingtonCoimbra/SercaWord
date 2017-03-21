package serca.projetoword.model;



public class ItemAchado {
	private int posicao;
	private Achado achado;
	private Relatorio relatorio;
	
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

	
	

}
