package model.bo;

import java.util.ArrayList;

import model.dao.ReceitaDAO;
import model.vo.ReceitaVO;

public class ReceitaBO {
	
	public void cadastrarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		int resultado = receitaDAO.cadastrarReceitaDAO(receitaVO);
			if (resultado == 1 ) {
				System.out.println("\nReceita cadastrada com sucesso");
			} else {
				System.out.println("\nNão foi possível cadastrar a receita");
			}
	}

	public ArrayList<ReceitaVO> consultarReceitasUsuarioBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ArrayList<ReceitaVO> listaReceitasVO = receitaDAO.consultarReceitasUsuarioDAO(receitaVO);
		if (listaReceitasVO.isEmpty()) {
			System.out.println("\nNão existem receitas cadastradas!");
		}
		return listaReceitasVO;
	}

	public ReceitaVO consultarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ReceitaVO receita = receitaDAO.consultarReceitaDAO(receitaVO);
		if (receita == null) {
			System.out.println("\nReceita não encontrada!");
		}
		return receita;
	}		

	public void atualizarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.existeRegistroPorIdReceita(receitaVO.getId())) {
			int resultado = receitaDAO.atualizarReceitaDAO(receitaVO);
			if (resultado == 1) {
				System.out.println("\nReceita atualizada com sucesso");
			} else {
				System.out.println("\nNão foi possível atualizar a receita");
			}
		} else {
			System.out.println("\nReceita não cadastrada!");
		}
	}

	public void excluirReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaDAO.existeRegistroPorIdReceita(receitaVO.getId())) {
			int resultado = receitaDAO.excluirReceitaDAO(receitaVO);
			if (resultado == 1) {
				System.out.println("\nReceita excluída com sucesso");
			} else {
				System.out.println("\nNão foi possível excluir a receita");
			}
		} else {
			System.out.println("\nReceita não existe no banco de dados!");
		}
		
	}
	
}
