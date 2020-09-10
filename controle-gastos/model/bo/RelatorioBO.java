package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.LancamentoUsuarioDTO;

public class RelatorioBO {

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalReceitasUsuarioBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = relatorioDAO.gerarRelatorioTotalReceitasUsuarioDAO();
		if (listaLancamentoUsuarioDTO.isEmpty()) {
			System.out.println("A lista de lançamento de receitas está vazia!");
		}
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalReceitasPeriodoBO(
		LancamentoUsuarioDTO lancamentoUsuarioDTO) {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = relatorioDAO.gerarRelatorioTotalReceitasPeriodoDAO(lancamentoUsuarioDTO);
		if (listaLancamentoUsuarioDTO.isEmpty()) {
			System.out.println("A lista de lançamento de receitas por período está vazia!");
		}
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasUsuarioBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = relatorioDAO.gerarRelatorioTotalDespesasUsuarioDAO();
		if (listaLancamentoUsuarioDTO.isEmpty()) {
			System.out.println("A lista de lançamento de despesas está vazia!");
		}
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasPeriodoBO(
			LancamentoUsuarioDTO lancamentoUsuarioDTO) {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = relatorioDAO.gerarRelatorioTotalDespesasPeriodoDAO(lancamentoUsuarioDTO);
		if (listaLancamentoUsuarioDTO.isEmpty()) {
			System.out.println("A lista de lançamento de despesas por período está vazia!");
		}
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasAbertasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = relatorioDAO.gerarRelatorioTotalDespesasAbertasDAO();
		if (listaLancamentoUsuarioDTO.isEmpty()) {
			System.out.println("A lista de lançamento de despesas em aberto está vazia!");
		}
		return listaLancamentoUsuarioDTO;
	}

}
