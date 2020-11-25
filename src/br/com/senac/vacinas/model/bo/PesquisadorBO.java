package br.com.senac.vacinas.model.bo;

import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class PesquisadorBO {

	public PesquisadorVO salvar(PesquisadorVO pesquisador) {
		PesquisadorDAO novoPesquisador = new PesquisadorDAO();			
		return novoPesquisador.inserir(pesquisador);	
	}

	public boolean atualizar(PesquisadorVO pesquisador) {		
		PesquisadorDAO pesqAtualizado = new PesquisadorDAO();
		int idPesquisador = pesqAtualizado.pesquisarPorIdPessoa(pesquisador.getIdPessoa()).getIdPesquisador();
		pesquisador.setIdPesquisador(idPesquisador);
		return pesqAtualizado.atualizar(pesquisador);	
	}

}
