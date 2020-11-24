package br.com.senac.vacinas.controller;

import java.time.LocalDate;

import br.com.senac.vacinas.model.bo.VacinacaoBO;
import br.com.senac.vacinas.model.exception.CamposVaziosException;
import br.com.senac.vacinas.model.exception.DataVaziaException;
import br.com.senac.vacinas.model.vo.VacinacaoVO;

public class VacinacaoController {
	
	private VacinacaoBO bo = new VacinacaoBO();

	public String salvar(VacinacaoVO vacinacao) {
		String mensagem = "";
		boolean valido = true;
			
		try {
			this.validarCampos(vacinacao);		
		} catch (CamposVaziosException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		
		try {
			this.validarData(vacinacao.getDataVacinacao());
		} catch (DataVaziaException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		if (valido) {
			mensagem = bo.salvar(vacinacao);
		}		
		
		return mensagem;			
	}
	
	private void validarData(LocalDate dataInicio) throws DataVaziaException {
		if (dataInicio == null) {
			throw new DataVaziaException("Data inválida");
		}
}

	private void validarCampos(VacinacaoVO vacinacao) throws CamposVaziosException {
		if (vacinacao.getPessoa() == null
				|| vacinacao.getVacina() == null 
				|| vacinacao.getAvaliacao() < 1
				|| vacinacao.getDataVacinacao() == null) {
			throw new CamposVaziosException("Preencher todos os campos");
		}
		
}

}
