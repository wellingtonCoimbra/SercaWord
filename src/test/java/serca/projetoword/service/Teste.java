package serca.projetoword.service;

import java.io.FileOutputStream;
import java.util.ArrayList;

import serca.projetoword.model.Achado;
import serca.projetoword.model.ItemAchado;
import serca.projetoword.model.Relatorio;

public class Teste {

	public static void main(String[] args) throws Exception {

		Relatorio r = createRelatorio();
		gerarRelatorio(r, new GeradorDocApachePoi(), "ApachePoi_Example.docx");
		gerarRelatorio(r, new GeradorDocDocx4j(), "Docx4j_Example.docx");
		gerarRelatorio(r, new GeradorDocDocx4jToHtml(), "Docx4j_Example.html");

	}

	private static void gerarRelatorio(Relatorio r, GeradorRelatorio geradorDocApachePoi, String fileName)
			throws Exception {
		byte[] gerado = geradorDocApachePoi.gerar(r);
		FileOutputStream output = new FileOutputStream(fileName);
		output.write(gerado);
		output.close();
	}

	private static Relatorio createRelatorio() {
		Relatorio r = new Relatorio();
		Achado a = new Achado();
		a.setTexto("aaaaaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaa aaaaaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaaaaaaaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaaaaaaaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaaaaaaaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaaaaaaaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaa");
		Achado b = new Achado();
		b.setTexto("bbbbbbbbbb bbbbbbb bbbbbbbbbbbbbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb bbbbbbb bbbbbb bbbbbb");
		Achado c = new Achado(); 
		c.setTexto("ccccccccccc cccc cccccccccccccccc ccccccccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc cccccc cccccc cccc");
		Achado d = new Achado();
		d.setTexto("ddddddd dddddddd ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd ddddddddd  ddddddddddd  ");
		Achado e = new Achado();
		e.setTexto("eee eeeee eeeeeeeee eeeeee eeeeeee eeeeeeeee eeeee eeeeee eee eeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee eeeeeeee eeeeee ");
		r.setItens(new ArrayList<>());

		int i = 0;
		for (int j = 0; j < 30; j++) {
			addItem(r, a, i++);
			addItem(r, b, i++);
			addItem(r, c, i++);
			addItem(r, d, i++);
			addItem(r, e, i++);
		}
		return r;
	}

	private static void addItem(Relatorio r, Achado a, int i) {
		ItemAchado item = new ItemAchado();
		item.setAchado(a);
		item.setPosicao(i);
		item.setRelatorio(r);
		r.getItens().add(item);
	}




}

