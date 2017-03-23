package serca.projetoword.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ReorderEvent;

import serca.projetoword.model.Achado;
import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

@ManagedBean(name="editorRelatorio")
@ViewScoped
public class EditorRelatorioBean implements Serializable{
	private Relatorio relatorio = new Relatorio(1, "Relatorio 1");	
	private List<Achado> achados = Arrays.asList(new Achado(1, "ACHADO 1"), 
			new Achado(2, "ACHADO 2"), 
			new Achado(3, "ACHADO 3"), 
			new Achado(4, "ACHADO 4"), 
			new Achado(5, "ACHADO 5"));
	private List<ItemAchado> itens = new ArrayList<ItemAchado>();
	
	private String texto = "";
	
	@PostConstruct
	public void init() {
	     Relatorio relatorio = (Relatorio) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("relatorio");
	     this.relatorio = relatorio;
	}
	
	public Relatorio getRelatorio(){
		return this.relatorio;
	}
	
	public List<Achado> getAchados(){
		return this.achados;
	}
	
	public List<ItemAchado> getItens(){
		return this.itens;
	}
	
	public void onRowReorder(ReorderEvent event) {
		this.atualizaTexto();
    }
	
	public String getTexto(){
		return this.texto;
	}
	
	public void addAchado(Achado achado){
		itens.add(new ItemAchado(this.relatorio, achado));
		this.atualizaTexto();		
	}
	
	public void excluiItem(ItemAchado item){
		this.itens.remove(item);
		this.atualizaTexto();
	}
	
	private void atualizaTexto(){
		this.texto = "";
		for(ItemAchado ia : itens){
			this.texto += "<p>" + ia.getAchado().getTexto() + "</p>";
		}
	}
	
	public String salvaRelatorio(){
		return "relatorios?faces-redirect=true";
	}
	
}
