package br.com.senac.vacinas.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.senac.vacinas.model.bo.VacinacaoBO;
import br.com.senac.vacinas.model.exception.CamposVaziosException;
import br.com.senac.vacinas.model.exception.DataVaziaException;
import br.com.senac.vacinas.model.seletores.SeletorVacinacao;
import br.com.senac.vacinas.model.vo.VacinacaoVO;
import br.com.senac.vacinas.utils.GeradorPlanilha;

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
	
	private void validarData(LocalDate data) throws DataVaziaException {
		if (data == null || data.isAfter(LocalDate.now())) {
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

	public List<VacinacaoVO> listarVacinacoes(SeletorVacinacao seletor) {
		return bo.listarVacinacoes(seletor);
	}

	public String excluir(VacinacaoVO vacinacao) {
		String mensagem = "";
		boolean excluiu = bo.excluir(vacinacao);
		
		if(excluiu) {
			mensagem = "Aplicação excluída com sucesso!";
		} else {
			mensagem = "Erro ao excluir aplicação";
		}
		
		return mensagem;
	}

	public String atualizarBusca(VacinacaoVO vacinacao) {
		String mensagem = "";
		boolean atualizou = bo.atualizarBusca(vacinacao);			
		
		if (atualizou) {
			mensagem = "Atualizado com sucesso!";	
		} else {
			mensagem = "Nenhum registro selecionado";	
		}
		
		return mensagem;			
	}

	public int contarAplicacoes() {		
		return bo.contarAplicacoes();
	}

	public Double mediaAvaliacao() {		
		return bo.mediaAvaliacao();
	}

	public String gerarPlanilha(List<VacinacaoVO> vacinacoesConsultadas, String caminho, String titulo) {
		GeradorPlanilha geradorExcel = new GeradorPlanilha();
		return geradorExcel.gerarPlanilhaVacinacao(caminho, vacinacoesConsultadas, titulo);
	}
}
