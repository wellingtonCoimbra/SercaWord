package serca.projetoword.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

public class GeradorPdf implements GeradorRelatorio{
	
	public byte[] gerar(Relatorio relatorio) throws IOException{
		
		XWPFDocument doc = new XWPFDocument();
		List<ItemAchado> itensOrdenados = new ArrayList<>();
		itensOrdenados.addAll(relatorio.getItens());
		Collections.sort(itensOrdenados);

		for (ItemAchado item : itensOrdenados) {
			XWPFParagraph p = doc.createParagraph();
			XWPFRun run = p.createRun();
			run.setText(item.getAchado().getTexto());
			run.addBreak();
		}
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		doc.write(output);
		doc.close();
		output.close();
		return output.toByteArray();
	}

}
