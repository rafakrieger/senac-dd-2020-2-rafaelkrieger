package br.com.senac.vacinas.controller;

import br.com.senac.vacinas.model.bo.VacinaBO;
import br.com.senac.vacinas.model.exception.CamposVaziosException;
import br.com.senac.vacinas.model.exception.PaisInvalidoException;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class VacinaController {
	
	private VacinaBO bo = new VacinaBO();

	public String salvar(VacinaVO vacina) {		
			String mensagem = "";
			
			try {
				this.validarPais(vacina.getPaisOrigem());
				this.validarCampos(vacina);				
				vacina = bo.salvar(vacina);
			} catch (PaisInvalidoException excecao) {
				mensagem = excecao.getMessage();
			} catch (CamposVaziosException excecao) {
				mensagem = excecao.getMessage();			
			} 
			
			mensagem = "Salvo com sucesso! Id gerado: " + vacina.getIdVacina();
			
			return mensagem;
			
		}

		private void validarCampos(VacinaVO vacina) throws CamposVaziosException {
			if (vacina.getEstagioPesquisa() > 0
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
