package br.com.senac.vacinas.controller;

import java.util.List;

import br.com.senac.vacinas.model.bo.PesquisadorBO;
import br.com.senac.vacinas.model.exception.InstituicaoInvalidaException;
import br.com.senac.vacinas.model.vo.PesquisadorVO;

public class PesquisadorController {
	
	private PesquisadorBO bo = new PesquisadorBO();

	public String salvar(PesquisadorVO pesquisador) {
		String mensagem = "";
		
		try {
			this.validarInstituicao(pesquisador.getInstituicao());			
			pesquisador = bo.salvar(pesquisador);
		} catch (InstituicaoInvalidaException excecao) {
			mensagem = excecao.getMessage();		
		} 
		
		mensagem = "Salvo com sucesso! Id gerado: " + pesquisador.getIdPesquisador();
		
		return mensagem;		
	}

	private void validarInstituicao(String instituicao) throws InstituicaoInvalidaException {
		if (instituicao == null || instituicao.isEmpty()) {
			throw new InstituicaoInvalidaException("Preencher instituição");
		}
		
	}

	public String atualizar(PesquisadorVO pesquisador) {
		String mensagem = "";
		boolean valido = false;
		try {
			this.validarInstituicao(pesquisador.getInstituicao());			
			valido = bo.atualizar(pesquisador);
		} catch (InstituicaoInvalidaException excecao) {
			mensagem = excecao.getMessage();		
		} 
		
		if (valido) {			
			mensagem = "Atualizado com sucesso!";	
		} else {
			mensagem = "Problema ao atualizar";	
		}	
		
		return mensagem;	
		
	}

	public PesquisadorVO pesquisarPorIdPessoa(int idPessoa) {		
		return bo.pesquisarPorIdPessoa(idPessoa);
	}

	public List<PesquisadorVO> pesquisarTodos() {		
		return bo.pesquisarTodos();
	}

}
