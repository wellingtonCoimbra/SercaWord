package serca.projetoword.service;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.Numbering;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.NumPr;
import org.docx4j.wml.PPrBase.NumPr.Ilvl;
import org.docx4j.wml.PPrBase.NumPr.NumId;

import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

public class GeradorDocDocx4j implements GeradorRelatorio{

	@Override
	public byte[] gerar(Relatorio relatorio) throws Exception {
		
		
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
		MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
		
		NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
		wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
		
		ndp.setJaxbElement( (Numbering) XmlUtils.unmarshalString(initialNumbering) );

		for (ItemAchado item : relatorio.getItensOrdenados()) {
			createNumberedParagraph(mdp,1L, 0L, "Subtitle", "Seção");
			P p = mdp.addParagraphOfText(item.getAchado().getTexto());
			
			PPr paragraphProperties = new PPr();
			Jc justification = new Jc();

			justification.setVal(JcEnumeration.BOTH);
			paragraphProperties.setJc(justification);
			p.setPPr(paragraphProperties);
		}
			
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wordMLPackage.save(baos);
		return baos.toByteArray();
	}
	
private static P createNumberedParagraph(MainDocumentPart mdp, long numId, long ilvl, String estilo, String texto) {
		
		P p = mdp.addStyledParagraphOfText(estilo, texto);
	    org.docx4j.wml.PPr ppr = p.getPPr();	    
	    // Create and add <w:numPr>
//	    NumPr numPr =  factory.createPPrBaseNumPr();
	    NumPr numPr =  new NumPr();
	    ppr.setNumPr(numPr);
	    
	    // The <w:ilvl> element
//	    Ilvl ilvlElement = factory.createPPrBaseNumPrIlvl();
	    Ilvl ilvlElement = new Ilvl();
	    numPr.setIlvl(ilvlElement);
	    ilvlElement.setVal(BigInteger.valueOf(ilvl));
	    	    
	    // The <w:numId> element
//	    NumId numIdElement = factory.createPPrBaseNumPrNumId();
	    NumId numIdElement =  new NumId(); 
	    numPr.setNumId(numIdElement);
	    numIdElement.setVal(BigInteger.valueOf(numId));
	    
		return p;
		
	}
	
	static final String paragraphFormatting = 
			  "<w:p>"
				+ "<w:pPr>"
					+ "<w:adjustRightInd/>"
					+ "<w:spacing w:after=\"0\"/>"
					+ "<w:textAlignment w:val=\"right\"/>"
				+ "</w:pPr>"
				+ "<w:r>"
					+ "<w:rPr/>"
					+ "<w:t>hello</w:t>"
				+ "</w:r>"
			+ "</w:p>";
	
	static final String initialNumbering = "<w:numbering xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
		    + "<w:abstractNum w:abstractNumId=\"0\">"
		    + "<w:nsid w:val=\"2DD860C0\"/>"
		    + "<w:multiLevelType w:val=\"multilevel\"/>"
		    + "<w:tmpl w:val=\"0409001D\"/>"
		    + "<w:lvl w:ilvl=\"0\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"decimal\"/>"
		        + "<w:lvlText w:val=\"%1.\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"360\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"1\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"lowerLetter\"/>"
		        + "<w:lvlText w:val=\"%2.\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"720\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"2\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"lowerRoman\"/>"
		        + "<w:lvlText w:val=\"%3.\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"1080\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"3\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"decimal\"/>"
		        + "<w:lvlText w:val=\"(%4)\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"1440\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"4\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"lowerLetter\"/>"
		        + "<w:lvlText w:val=\"(%5)\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"1800\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"5\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"lowerRoman\"/>"
		        + "<w:lvlText w:val=\"(%6)\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"2160\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"6\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"decimal\"/>"
		        + "<w:lvlText w:val=\"%7.\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"2520\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"7\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"lowerLetter\"/>"
		        + "<w:lvlText w:val=\"%8.\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"2880\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		    + "<w:lvl w:ilvl=\"8\">"
		        + "<w:start w:val=\"1\"/>"
		        + "<w:numFmt w:val=\"lowerRoman\"/>"
		        + "<w:lvlText w:val=\"%9.\"/>"
		        + "<w:lvlJc w:val=\"left\"/>"
		        + "<w:pPr>"
		            + "<w:ind w:left=\"3240\" w:hanging=\"360\"/>"
		        + "</w:pPr>"
		    + "</w:lvl>"
		+ "</w:abstractNum>"
		+ "<w:num w:numId=\"1\">"
		    + "<w:abstractNumId w:val=\"0\"/>"
		 + "</w:num>"
		+ "</w:numbering>";

	
	private void setHorizontalAlignment1(P p, JcEnumeration hAlign) {
			Jc align = new Jc();
			align.setVal(hAlign);
			
			PPr pprop = new PPr();
			pprop.setJc(align);
			p.setPPr(pprop);
	}
	
	private void setHorizontalAlignment(P paragraph, JcEnumeration hAlign) {
		if (hAlign != null) {
			PPr pprop;
			if(paragraph.getPPr() == null){
				pprop = new PPr();
			}else{
				pprop = paragraph.getPPr();
			}
			Jc align = new Jc();
			align.setVal(hAlign);
			pprop.setJc(align);
			paragraph.setPPr(pprop);
		}
	}
}