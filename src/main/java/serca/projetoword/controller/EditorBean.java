package serca.projetoword.controller;

import java.util.ArrayList;

import javax.annotation.ManagedBean;

import serca.projetoword.model.*;



@ManagedBean
public class EditorBean {
	private String texto;
	private String nome;
	private Editor editor;
	
	private ArrayList<Editor> editores;
	public  EditorBean() {
		editores = new ArrayList<>();
		editor = new Editor();
	}
	
	
	public void salva(){
		try {
			editores.add(editor);
		} catch (Exception e) {
			
		}
		editores.add(editor);
		System.out.println("\n\n\n\n" + texto + "\n\n\n\n");
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getNome(){
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Editor getEditor() {
		return editor;
	}
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	public ArrayList<Editor> getEditores() {
		return editores;
	}
	public void setEditores(ArrayList<Editor> editores) {
		this.editores = editores;
	}
	

}
