package br.com.senac.vacinas.model.bo;

import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.vo.PesquisadorVO;

public class PesquisadorBO {

	public PesquisadorVO salvar(PesquisadorVO pesquisador) {
		PesquisadorDAO novoPesquisador = new PesquisadorDAO();			
		return novoPesquisador.inserir(pesquisador);	
	}

}
