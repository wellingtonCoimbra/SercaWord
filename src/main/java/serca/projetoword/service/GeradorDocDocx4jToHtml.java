package serca.projetoword.service;

import java.io.ByteArrayInputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.ConversionFeatures;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.SdtToListSdtTagHandler;
import org.docx4j.convert.out.html.SdtWriter;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import serca.projetoword.model.Relatorio;

public class GeradorDocDocx4jToHtml implements GeradorRelatorio{

	static org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory(); 

	@Override
	public byte[] gerar(Relatorio relatorio) throws Exception {
		GeradorDocDocx4j docGen = new GeradorDocDocx4j();
		byte[] docx = docGen.gerar(relatorio);

		ByteArrayInputStream inStream = new ByteArrayInputStream(docx);
		WordprocessingMLPackage wordMLPackage = Docx4J.load(inStream);

		HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
		htmlSettings.setWmlPackage(wordMLPackage);
		configEstiloNumeracao(htmlSettings);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

		return os.toByteArray();
	}

	private void configEstiloNumeracao(HTMLSettings htmlSettings) {
		boolean nestList = false;
		
		htmlSettings.setUserCSS(css(nestList));
		if (nestList) {
			SdtWriter.registerTagHandler("HTML_ELEMENT", new SdtToListSdtTagHandler());
		} else {
			htmlSettings.getFeatures().remove(ConversionFeatures.PP_HTML_COLLECT_LISTS);
		}
		
	}

	private String css(boolean nestList){

		String userCSS = null;
		if (nestList) {
			// use browser defaults for ol, ul, li
			userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  table, caption, tbody, tfoot, thead, tr, th, td " +
					"{ margin: 0; padding: 0; border: 0;}" +
					"body {line-height: 1;} ";
		} else {
			userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td " +
					"{ margin: 0; padding: 0; border: 0;}" +
					"body {line-height: 1;} ";
		}
		return userCSS;
	}

}

