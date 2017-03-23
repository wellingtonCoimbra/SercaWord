package serca.projetoword.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import serca.projetoword.model.Achado;
import serca.projetoword.model.Relatorio;

@Stateless
public class AchadoDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public AchadoDAO(){}
	
	public void salvar(Achado achado){
		em.merge(achado);
	}
	
	public List<Achado> listar(){
		TypedQuery<Achado> query = em.createQuery("Select r from Achado r", Achado.class);
		return query.getResultList();
	}
}
