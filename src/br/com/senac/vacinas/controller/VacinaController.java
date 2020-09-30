package br.com.senac.vacinas.controller;

import java.time.LocalDate;

import br.com.senac.vacinas.model.bo.VacinaBO;
import br.com.senac.vacinas.model.exception.CamposVaziosException;
import br.com.senac.vacinas.model.exception.DataVaziaException;
import br.com.senac.vacinas.model.exception.PaisInvalidoException;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class VacinaController {
	
	private VacinaBO bo = new VacinaBO();

	public String salvar(VacinaVO vacina) {		
			String mensagem = "";
			boolean valido = true;
			
			try {
				this.validarPais(vacina.getPaisOrigem());
			} catch (PaisInvalidoException excecao) {
				valido = false;
				mensagem = excecao.getMessage();				
			} 
			
			try {
				this.validarCampos(vacina);		
			} catch (CamposVaziosException excecao) {
				valido = false;
				mensagem = excecao.getMessage();
			}
			
			
			try {
				this.validarData(vacina.getDataInicio());
			} catch (DataVaziaException excecao) {
				valido = false;
				mensagem = excecao.getMessage();
			}
			
			if (valido) {
				vacina = bo.salvar(vacina);
				mensagem = "Salvo com sucesso! Id gerado: " + vacina.getIdVacina();	
			}	
			
			
			
			return mensagem;			
		}

		private void validarData(LocalDate dataInicio) throws DataVaziaException {
			if (dataInicio == null) {
				throw new DataVaziaException("Data inválida");
			}
	}

		private void validarCampos(VacinaVO vacina) throws CamposVaziosException {
			if (vacina.getEstagioPesquisa() > 1
					|| vacina.getDataInicio() == null 
					|| vacina.getPesquisador() == null) {
				throw new CamposVaziosException("Preencher todos os campos");
			}
			
	}

		private void validarPais(String paisOrigem) throws PaisInvalidoException {
			if (paisOrigem == null || paisOrigem.isEmpty()
					|| paisOrigem.length() < 3) {
				throw new PaisInvalidoException("Nome deve possuir ao menos 3 caracteres");
			}
		
	}
		
}
