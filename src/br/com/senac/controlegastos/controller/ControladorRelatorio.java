package br.com.senac.controlegastos.controller;

import java.util.ArrayList;

import br.com.senac.controlegastos.model.bo.RelatorioBO;
import br.com.senac.controlegastos.model.dto.LancamentoUsuarioDTO;

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
