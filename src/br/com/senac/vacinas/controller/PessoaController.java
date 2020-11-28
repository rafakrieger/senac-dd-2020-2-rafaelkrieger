package br.com.senac.vacinas.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.senac.vacinas.model.bo.PessoaBO;
import br.com.senac.vacinas.model.exception.CpfInvalidoException;
import br.com.senac.vacinas.model.exception.CpfRepetidoException;
import br.com.senac.vacinas.model.exception.DataVaziaException;
import br.com.senac.vacinas.model.exception.NomeInvalidoException;
import br.com.senac.vacinas.model.exception.SexoInvalidoException;
import br.com.senac.vacinas.model.seletores.SeletorPessoa;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class PessoaController {
	
	private PessoaBO bo = new PessoaBO();

	public String salvar(PessoaVO pessoa) {
		String mensagem = "";
		boolean valido = true;
		
		try {
			this.validarCPF(pessoa.getCpf());			
		} catch (CpfInvalidoException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		try {
			this.validarNome(pessoa.getNome());
		} catch (NomeInvalidoException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		try {
			this.validarSexo(pessoa.getSexo());				
		} catch (SexoInvalidoException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		} 
		
		try {
			this.validarData(pessoa.getDataNascimento());
		} catch (DataVaziaException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		try {
			bo.conferirCpf(pessoa.getCpf());
		} catch (CpfRepetidoException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		if (valido) {
			bo.salvar(pessoa);
			mensagem = "Salvo com sucesso! Id gerado: " + pessoa.getIdPessoa();		
		}		
		
		return mensagem;		
	}
	
	public String atualizar(PessoaVO pessoa) {
		String mensagem = "";
		boolean valido = bo.atualizar(pessoa);
		
		try {
			this.validarCPF(pessoa.getCpf());
		} catch (CpfInvalidoException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		}
		
		try {
			this.validarNome(pessoa.getNome());
		} catch (NomeInvalidoException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		}
		
		try {
			this.validarSexo(pessoa.getSexo());				
		} catch (SexoInvalidoException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		} 
		
		try {
			this.validarData(pessoa.getDataNascimento());
			} catch (DataVaziaException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		}		
		
		if (valido) {			
				mensagem = "Atualizado com sucesso!";	
			} else {
				mensagem = "Problema ao atualizar";	
			}	
		
		return mensagem;		
	}

	private void validarData(LocalDate dataNascimento) throws DataVaziaException {
		if (dataNascimento == null) {
			throw new DataVaziaException("Data inválida");
		}
	}


	private void validarSexo(String sexo) throws SexoInvalidoException {
		if (sexo == null) {
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
	}

	public List<PessoaVO> listarPessoas(SeletorPessoa seletor) {		
		return bo.listarPessoas(seletor);
	}

	public String excluir(PessoaVO pessoa) {		
		String mensagem = "";
		boolean excluiu = bo.excluir(pessoa);
		
		if(excluiu) {
			mensagem = "Registro excluído com sucesso!";
		} else {
			mensagem = "Erro ao excluir";
		}
		
		return mensagem;

	}

	public PessoaVO pesquisarPorId(int id) {		
		return bo.pesquisarPorId(id);
	}

	public String atualizarBusca(PessoaVO pessoa) {
		String mensagem = "";
		boolean valido = bo.atualizarBusca(pessoa);
		
		try {
			this.validarCPF(pessoa.getCpf());
		} catch (CpfInvalidoException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		}
		
		try {
			this.validarNome(pessoa.getNome());
		} catch (NomeInvalidoException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		}
		
		try {
			this.validarSexo(pessoa.getSexo());				
		} catch (SexoInvalidoException excecao) {			
			mensagem = excecao.getMessage();
			valido = false;
		} 	
		
		if (valido) {			
				mensagem = "Atualizado com sucesso!";	
			} else {
				mensagem = "Problema ao atualizar";	
			}	
		
		return mensagem;		
	}


}
