package serca.projetoword.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.docx4j.jaxb.Context;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import serca.projetoword.model.Relatorio;

public class GeradorPdfXdocReportT implements GeradorRelatorio{

	static org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory(); 

	@Override
	public byte[] gerar(Relatorio relatorio) throws Exception {
		GeradorDocDocx4j docGen = new GeradorDocDocx4j();
		byte[] docx = docGen.gerar(relatorio);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IXDocReport report = XDocReportRegistry.getRegistry().loadReport(new ByteArrayInputStream(docx), TemplateEngineKind.Velocity);
		
		// 2) Create context Java model
		IContext context = report.createContext();
//		Project project = new Project("XDocReport");
//		context.put("project", project);

		// 3) Generate report by merging Java model with the Docx
		// report.process(context, out);
		Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
//		report.convert(context, options, os);
		report.process(context, os);
		
//		ByteArrayInputStream inStream = new ByteArrayInputStream(docx);
//		WordprocessingMLPackage wordMLPackage = Docx4J.load(inStream);
//		Docx4J.toPDF(wordMLPackage, os);

		return os.toByteArray();
	}
//	Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);
//	IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//	InputStream in= new ByteArrayInputStream(docx);
//	converter.convert(in, os, options);

}

