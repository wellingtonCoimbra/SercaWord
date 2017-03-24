package serca.projetoword.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

public class GeradorDocApachePoi implements GeradorRelatorio{

	public byte[] gerar(Relatorio relatorio) throws IOException{
		
		XWPFDocument doc = new XWPFDocument();
		XWPFHeaderFooterPolicy headerPolicy = doc.createHeaderFooterPolicy();
		XWPFHeader header = headerPolicy.createHeader(STHdrFtr.DEFAULT);
		XWPFRun runHeader = header.getParagraphArray(0).createRun();
		runHeader.setText("--------------------- Cabecalho ---------------------");

		/** Preparando numeracao */
		CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
		cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));

		///* Bullet list
		//  CTLvl cTLvl = cTAbstractNum.addNewLvl();
		//  cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
		//  cTLvl.addNewLvlText().setVal("•");
		//*/

		///* Decimal list
		CTLvl cTLvl = cTAbstractNum.addNewLvl();
		cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
		cTLvl.addNewLvlText().setVal("%1.");
		cTLvl.addNewStart().setVal(BigInteger.valueOf(1));
		//*/

		XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
		XWPFNumbering numbering = doc.createNumbering();
		BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
		BigInteger numID = numbering.addNum(abstractNumID);
		/** Fim numeracao */

		for (ItemAchado item : relatorio.getItensOrdenados()) {

			XWPFParagraph p = doc.createParagraph();
			p.setNumID(numID);
			p.createRun().setText(item.getAchado().getNome());;

			p = doc.createParagraph();
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
