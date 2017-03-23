package serca.projetoword.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import serca.projetoword.dao.RelatorioDAO;
import serca.projetoword.model.Relatorio;

@ManagedBean(name="relatorioView")
@ViewScoped
public class RelatorioBean {
	@Inject
	private RelatorioDAO relatorioDao;
	
	private List<Relatorio> relatorios;	
	
	public List<Relatorio> getRelatorios(){
		return relatorioDao.listar();
	}
	
	public String irParaEdicao(Relatorio relatorio){
		System.out.println("Total de itens: "+relatorio.getItens().size());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("relatorio", relatorio);
		return "relatorioEdicao?faces-redirect=true"; 
	}
}
