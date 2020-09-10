package br.com.senac.controlegastos.model.bo;

import java.util.ArrayList;

import br.com.senac.controlegastos.model.dao.DespesaDAO;
import br.com.senac.controlegastos.model.vo.DespesaVO;

public class DespesaBO {

	public void cadastrarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		int resultado = despesaDAO.cadastrarDespesaDAO(despesaVO);
			if (resultado == 1 ) {
				System.out.println("\nDespesa cadastrada com sucesso");
			} else {
				System.out.println("\nNão foi possível cadastrar a despesa");
			}
		}

	public ArrayList<DespesaVO> consultarDespesasUsuarioBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> listaDespesasVO = despesaDAO.consultarDespesasUsuarioDAO(despesaVO);
		if (listaDespesasVO.isEmpty()) {
			System.out.println("\nNão existem despesas cadastradas!");
		}
		return listaDespesasVO;
	}

	public DespesaVO consultarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		DespesaVO despesa = despesaDAO.consultarDespesaDAO(despesaVO);
		if (despesa == null) {
			System.out.println("\nDespesa não encontrada!");
		}
		return despesa;
	}

	public void atualizarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.existeRegistroPorIdDespesa(despesaVO.getId())) {
			int resultado = despesaDAO.atualizarDespesaDAO(despesaVO);
			if (resultado == 1) {
				System.out.println("\nDespesa atualizada com sucesso");
			} else {
				System.out.println("\nNão foi possível atualizar a despesa");
			}
		} else {
			System.out.println("\nDespesa não cadastrada!");
		}
		
	}

	public void excluirDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.existeRegistroPorIdDespesa(despesaVO.getId())) {
			int resultado = despesaDAO.excluirDespesaDAO(despesaVO);
			if (resultado == 1) {
				System.out.println("\nDespesa excluída com sucesso");
			} else {
				System.out.println("\nNão foi possível excluir a despesa");
			}
		} else {
			System.out.println("\nDespesa não existe na base de dados!");
		}
		
	}
		
	}
