package serca.projetoword.service;

import java.math.BigInteger;
import java.util.List;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.utils.BufferUtil;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;

public class HeaderBuilder {
	private static ObjectFactory objectFactory = new ObjectFactory();

	public Relationship createHeaderPart(
			WordprocessingMLPackage wordprocessingMLPackage)
					throws Exception {

		HeaderPart headerPart = new HeaderPart();

		Relationship rel =  wordprocessingMLPackage.getMainDocumentPart()
				.addTargetPart(headerPart);

		// After addTargetPart, so image can be added properly
		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, headerPart));
		
		return rel;
	}

	public void createHeaderReference(
			WordprocessingMLPackage wordprocessingMLPackage,
			Relationship relationship )
					throws InvalidFormatException {

		List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();

		SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
		// There is always a section wrapper, but it might not contain a sectPr
		if (sectPr==null ) {
			sectPr = objectFactory.createSectPr();
			wordprocessingMLPackage.getMainDocumentPart().addObject(sectPr);
			sections.get(sections.size() - 1).setSectPr(sectPr);
		}

		HeaderReference headerReference = objectFactory.createHeaderReference();
		headerReference.setId(relationship.getId());
		headerReference.setType(HdrFtrRef.DEFAULT);
		sectPr.getEGHdrFtrReferences().add(headerReference);// add header or
		// footer references

	}

	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage,
			Part sourcePart) throws Exception {

		Hdr hdr = objectFactory.createHdr();

		java.io.InputStream is = this.getClass().getResourceAsStream("/images/brasao_p.png");
//		hdr.getContent().add(
//				newImage(wordprocessingMLPackage,
//						sourcePart, 
//						BufferUtil.getBytesFromInputStream(is), 
//						"filename", "alttext", 1, 2
//						)
//				);
		R r = new R();
		Text t = new Text();

		RPr rpr = new RPr();
		r.setRPr(rpr);

		HpsMeasure sz = new HpsMeasure();
		BigInteger fontSize = BigInteger.valueOf(58);
		sz.setVal(fontSize);
		rpr.setSz(sz);

		PPr paragraphProperties = new PPr();
		Jc justification = new Jc();

		justification.setVal(JcEnumeration.BOTH);
		paragraphProperties.setJc(justification);
		
		t.setValue("Tribunal de Código do Estado");
		r.getContent().add(t);
		hdr.getContent().add(r);
		return hdr;

	}



	//	public static P getP() {
	//		P headerP = objectFactory.createP();
	//		R run1 = objectFactory.createR();
	//		Text text = objectFactory.createText();
	//		text.setValue("123head123");
	//		run1.getRunContent().add(text);
	//		headerP.getParagraphContent().add(run1);
	//		return headerP;
	//	}

	public static P getP() {
		P headerP = objectFactory.createP();
		R run1 = objectFactory.createR();
		Text text = objectFactory.createText();
		text.setValue("Título do Cabecalho");
		run1.getRunContent().add(text);
		headerP.getParagraphContent().add(run1);
		return headerP;
	}
	
	public org.docx4j.wml.P newImage( WordprocessingMLPackage wordMLPackage,
			Part sourcePart,
			byte[] bytes,
			String filenameHint, String altText, 
			int id1, int id2) throws Exception {

		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, 
				sourcePart, bytes);

		Inline inline = imagePart.createImageInline( filenameHint, altText, 
				id1, id2, false);

		// Now add the inline in w:p/w:r/w:drawing
		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
		org.docx4j.wml.P  p = factory.createP();
		org.docx4j.wml.R  run = factory.createR();		
		p.getContent().add(run);        
		org.docx4j.wml.Drawing drawing = factory.createDrawing();		
		run.getContent().add(drawing);		
		drawing.getAnchorOrInline().add(inline);

		return p;

	}	

}
