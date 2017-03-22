package serca.projetoword.service;

import serca.projetoword.model.Relatorio;

public interface GeradorRelatorio {

	public byte[] gerar(Relatorio relatorio) throws Exception;
}
