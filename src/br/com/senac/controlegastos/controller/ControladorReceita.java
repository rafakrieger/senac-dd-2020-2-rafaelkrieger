package br.com.senac.controlegastos.controller;

import java.util.ArrayList;

import br.com.senac.controlegastos.model.bo.ReceitaBO;
import br.com.senac.controlegastos.model.vo.ReceitaVO;

public class ControladorReceita {
	
	public void cadastrarReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.cadastrarReceitaBO(receitaVO);	
	}

	public ArrayList<ReceitaVO> consultarReceitasUsuarioController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();				
		return receitaBO.consultarReceitasUsuarioBO(receitaVO);
	}

	public ReceitaVO consultarReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();				
		return receitaBO.consultarReceitaBO(receitaVO);
	}

	public void atualizarReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.atualizarReceitaBO(receitaVO);				
	}

	public void excluirReceitaController(ReceitaVO receitaVO) {
		ReceitaBO receitaBO = new ReceitaBO();
		receitaBO.excluirReceitaBO(receitaVO);		
	}

}
