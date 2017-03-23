package serca.projetoword.beans;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ReorderEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import serca.projetoword.model.Achado;
import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;
import serca.projetoword.service.GeradorDocApachePoi;
import serca.projetoword.service.GeradorDocDocx4j;
import serca.projetoword.service.GeradorDocDocx4jToHtml;
import serca.projetoword.service.GeradorDocDocx4jToPdf;

@ManagedBean(name="editorRelatorio")
@ViewScoped
public class EditorRelatorioBean implements Serializable{
	private Relatorio relatorio = new Relatorio(1, "Relatorio 1");	
	private List<Achado> achados = Arrays.asList(new Achado(1, "ACHADO 1"), 
			new Achado(2, "ACHADO 2"), 
			new Achado(3, "ACHADO 3"), 
			new Achado(4, "ACHADO 4"), 
			new Achado(5, "ACHADO 5"));
	
	private String texto = "";
	
	private StreamedContent file;
	
	@PostConstruct
	public void init() {
	     Relatorio relatorio = (Relatorio) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("relatorio");
	     this.relatorio = relatorio;
	     if(relatorio == null){
	    	 relatorio = new Relatorio(); 
	     }
	    this.relatorio.setItens(new ArrayList<>());
	}
	
	public Relatorio getRelatorio(){
		return this.relatorio;
	}
	
	public List<Achado> getAchados(){
		return this.achados;
	}
	
	public List<ItemAchado> getItens(){
		return this.relatorio.getItens();
	}
	
	public void onRowReorder(ReorderEvent event) throws Exception {
		this.atualizaTexto();
    }
	
	public String getTexto(){
		return this.texto;
	}
	
	public void addAchado(Achado achado) throws Exception{
		getItens().add(new ItemAchado(this.relatorio, achado));
		this.atualizaTexto();		
	}
	
	public void excluiItem(ItemAchado item) throws Exception{
		this.getItens().remove(item);
		this.atualizaTexto();
	}
	
	private void atualizaTexto() throws Exception{
//		this.texto = "";
//		for(ItemAchado ia : getItens()){
//			this.texto += "<p>" + ia.getAchado().getTexto() + "</p>";
//		}
		this.texto = new String(new GeradorDocDocx4jToHtml().gerar(this.relatorio));
	}
	
	public String salvaRelatorio(){
		return "relatorios?faces-redirect=true";
	}
	
	private StreamedContent downloadContent(byte[] content, String contenType, String fileName){
		try {
			InputStream is = new ByteArrayInputStream(content);
			return new DefaultStreamedContent(is, contenType, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	 'docx'  => "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
//	  'xlsx'  => 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
//	  'word'  => 'application/msword',
//	  'xls'   => 'application/excel',
//	  'pdf'   => 'application/pdf'
//	  'psd'   => 'application/x-photoshop'
	public StreamedContent impressaoDOCDox4j() throws Exception{
		System.out.println("EditorRelatorioBean.impressaoDOCDox4j()");
		byte[] content = new GeradorDocDocx4j().gerar(this.relatorio);
		this.file = downloadContent(content, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Relatorio.docx");
		return this.file;
	}

	public StreamedContent impressaoDOCApachePoi() throws Exception{
		byte[] content = new GeradorDocApachePoi().gerar(this.relatorio);
		this.file = downloadContent(content, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Relatorio.docx");
		return this.file;
	}

	public StreamedContent impressaoPDF() throws Exception{
		byte[] content = new GeradorDocDocx4jToPdf().gerar(this.relatorio);
		this.file = downloadContent(content, "application/pdf", "Relatorio.pdf");
		return this.file;
	}

	
	public StreamedContent getFile(String doc) throws Exception {
		if(doc.equals("docx4j")){
			impressaoDOCDox4j();
		}else if(doc.equals("docPoi")){
			impressaoDOCApachePoi();
		}else if(doc.equals("pdf")){
			impressaoPDF();
		}
			
		if(!relatorio.getItens().isEmpty()){
			return file;
		}
		return null;
			
	}
}

