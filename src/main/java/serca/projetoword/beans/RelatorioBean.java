package serca.projetoword.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import serca.projetoword.model.Relatorio;

@ManagedBean(name="relatorioView")
@ViewScoped
public class RelatorioBean {
	private List<Relatorio> relatorios = Arrays.asList(new Relatorio(1, "RELATORIO 1"), 
			new Relatorio(2, "RELATORIO 2"), 
			new Relatorio(3, "RELATORIO 3"), 
			new Relatorio(4, "RELATORIO 4"),
			new Relatorio(5, "RELATORIO 5"));	
	
	public List<Relatorio> getRelatorios(){
		return this.relatorios;
	}
	
	public String irParaEdicao(Relatorio relatorio){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("relatorio", relatorio);
		return "relatorioEdicao?faces-redirect=true"; 
	}
}
