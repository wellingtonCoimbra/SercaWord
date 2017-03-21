package serca.projetoword.service;

import java.io.IOException;

import serca.projetoword.model.Relatorio;

public interface GeradorRelatorio {

	public byte[] gerar(Relatorio relatorio) throws IOException;
}
