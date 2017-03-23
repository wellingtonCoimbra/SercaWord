package serca.projetoword.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ItemAchadoDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

}
