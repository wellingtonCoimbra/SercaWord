package serca.projetoword.service;

import java.io.ByteArrayInputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import serca.projetoword.model.Relatorio;

public class GeradorDocDocx4jToPdf implements GeradorRelatorio{

	static org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory(); 

	@Override
	public byte[] gerar(Relatorio relatorio) throws Exception {
		GeradorDocDocx4j docGen = new GeradorDocDocx4j();
		byte[] docx = docGen.gerar(relatorio);

		ByteArrayInputStream inStream = new ByteArrayInputStream(docx);
		WordprocessingMLPackage wordMLPackage = Docx4J.load(inStream);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Docx4J.toPDF(wordMLPackage, os);

		return os.toByteArray();
	}

}

