package br.com.senac.vacinas.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.senac.vacinas.model.bo.VacinaBO;
import br.com.senac.vacinas.model.exception.CamposVaziosException;
import br.com.senac.vacinas.model.exception.DataVaziaException;
import br.com.senac.vacinas.model.exception.PaisInvalidoException;
import br.com.senac.vacinas.model.seletores.SeletorVacina;
import br.com.senac.vacinas.model.vo.VacinaVO;
import br.com.senac.vacinas.utils.GeradorPlanilha;




public class VacinaController {
	
	public static final String TIPO_RELATORIO_XLS = "xls";
	public static final String TIPO_RELATORIO_PDF = "pdf";
	
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
			if (vacina.getEstagioPesquisa() < 1
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

		public List<VacinaVO> listarVacinas(SeletorVacina seletor) {
			return bo.listarVacinas(seletor);
		}

		public String excluir(VacinaVO vacina) {			
			String mensagem = "";
			boolean excluiu = bo.excluir(vacina);
			
			if(excluiu) {
				mensagem = "Vacina excluída com sucesso!";
			} else {
				mensagem = "Erro ao excluir vacina";
			}
			
			return mensagem;

		}

		public String atualizarBusca(VacinaVO vacina) {
			String mensagem = "";
			boolean atualizou = bo.atualizarBusca(vacina);
			
			try {
				this.validarPais(vacina.getPaisOrigem());
			} catch (PaisInvalidoException excecao) {
				atualizou = false;
				mensagem = excecao.getMessage();				
			} 
			
			try {
				this.validarCampos(vacina);		
			} catch (CamposVaziosException excecao) {
				atualizou = false;
				mensagem = excecao.getMessage();
			}
			
			
			if (atualizou) {
				mensagem = "Atualizado com sucesso!";	
			}				
			
			return mensagem;		
		}

		public int contarVacinas() {			
			return bo.contarVacinas();
		}
		
		public void gerarRelatorio(List<VacinaVO> vacinas, String caminhoEscolhido, String tipoRelatorio) {
			if (tipoRelatorio.equals(TIPO_RELATORIO_XLS)) {
				bo.gerarPlanilha(vacinas, caminhoEscolhido);
			}
		}

		public List<VacinaVO> listarProdutos(SeletorVacina seletor) {
			return bo.listarVacinas(seletor);
		}
		
		public String gerarPlanilha(List<VacinaVO> vacinasConsultadas, String caminho) {
			GeradorPlanilha geradorExcel = new GeradorPlanilha();
			return geradorExcel.gerarPlanilhaVacinas(caminho, vacinasConsultadas);
		}
		
}
