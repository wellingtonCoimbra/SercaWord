package serca.projetoword.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

public class GeradorPdf implements GeradorRelatorio{
	
	public byte[] gerar(Relatorio relatorio) throws IOException{
		
		GeradorDocApachePoi docGen = new GeradorDocApachePoi();
		byte[] gerar = docGen.gerar(relatorio);
		return gerar;
	}

}
