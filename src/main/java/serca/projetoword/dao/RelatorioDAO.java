package serca.projetoword.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

@Stateless
public class RelatorioDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public RelatorioDAO(){}
	
	public void salvar(Relatorio relatorio){
		em.merge(relatorio);
	}
	
	public List<Relatorio> listar(){
		TypedQuery<Relatorio> query = em.createQuery("Select r from Relatorio r", Relatorio.class);
		List<Relatorio> resultList = query.getResultList();
		System.out.println("RelatorioDAO.listar()" +resultList.size());
		return resultList;
	}

	public void deleteItem(ItemAchado item){
		em.remove(item);
		
	}
}
