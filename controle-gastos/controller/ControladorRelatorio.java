package controller;

import java.util.ArrayList;

import model.bo.RelatorioBO;
import model.dto.LancamentoUsuarioDTO;

public class ControladorRelatorio {

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalReceitasUsuarioController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.gerarRelatorioTotalReceitasUsuarioBO();
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalReceitasPeriodoController(
			LancamentoUsuarioDTO lancamentoUsuarioDTO) {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.gerarRelatorioTotalReceitasPeriodoBO(lancamentoUsuarioDTO);
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasUsuarioController() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.gerarRelatorioTotalDespesasUsuarioBO();
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasPeriodoController(
			LancamentoUsuarioDTO lancamentoUsuarioDTO) {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.gerarRelatorioTotalDespesasPeriodoBO(lancamentoUsuarioDTO);
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasAbertas() {
		RelatorioBO relatorioBO = new RelatorioBO();
		return relatorioBO.gerarRelatorioTotalDespesasAbertasBO();
	}

}
