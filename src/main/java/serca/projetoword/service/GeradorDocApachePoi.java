package serca.projetoword.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.exolab.castor.dsml.ImportDescriptor.Policy;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

public class GeradorDocApachePoi implements GeradorRelatorio{
	
	public byte[] gerar(Relatorio relatorio) throws IOException{
		
		List<ItemAchado> itensOrdenados = new ArrayList<>();
		itensOrdenados.addAll(relatorio.getItens());
		Collections.sort(itensOrdenados);
		
		XWPFDocument doc = new XWPFDocument();
		XWPFHeaderFooterPolicy headerPolicy = doc.createHeaderFooterPolicy();
//		XWPFHeaderFooterPolicy headerPolicy = doc.getHeaderFooterPolicy();
		XWPFHeader header = headerPolicy.createHeader(STHdrFtr.DEFAULT);
		XWPFRun runHeader = header.getParagraphArray(0).createRun();
		runHeader.setText("--------------------- Cabecalho ---------------------");
		
		
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
