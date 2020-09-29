package br.com.senac.vacinas.controller;

import br.com.senac.vacinas.model.bo.PessoaBO;
import br.com.senac.vacinas.model.exception.CpfInvalidoException;
import br.com.senac.vacinas.model.exception.NomeInvalidoException;
import br.com.senac.vacinas.model.exception.SexoInvalidoException;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class PessoaController {
	
	private PessoaBO bo = new PessoaBO();

	public String salvar(PessoaVO pessoa) {
		String mensagem = "";
		
		try {
			this.validarCPF(pessoa.getCpf());
			this.validarNome(pessoa.getNome());
			this.validarSexo(pessoa.getSexo());
			pessoa = bo.salvar(pessoa);
		} catch (CpfInvalidoException excecao) {
			mensagem = excecao.getMessage();
		} catch (NomeInvalidoException excecao) {
			mensagem = excecao.getMessage();
		} catch (SexoInvalidoException excecao) {
			mensagem = excecao.getMessage();
		} 
		
		mensagem = "Salvo com sucesso! Id gerado: " + pessoa.getIdPessoa();
		
		return mensagem;
		
	}

	private void validarSexo(String sexo) throws SexoInvalidoException {
		if (sexo == null || sexo.isEmpty()) {
			throw new SexoInvalidoException("Preencher sexo");
		}
		
	}

	private void validarNome(String nome) throws NomeInvalidoException {
		if (nome == null || nome.isEmpty()
				|| nome.length() < 3) {
			throw new NomeInvalidoException("Nome deve possuir ao menos 3 caracteres");
		}
		
		if (nome.indexOf(" ") < 0) {
			throw new NomeInvalidoException("Digite nome e sobrenome");
		}
		
	}

	private void validarCPF(String cpf) throws CpfInvalidoException {
		if(cpf == null || cpf.isEmpty()
				|| cpf.length() != 11) {
			throw new CpfInvalidoException("CPF deve possuir 11 caracteres");
		}
		
		try {
			Integer.parseInt(cpf);
		} catch (NumberFormatException ex) {
			throw new CpfInvalidoException("CPF deve possuir 11 caracteres numéricos");
		}
	}

}
