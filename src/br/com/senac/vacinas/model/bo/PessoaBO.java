package br.com.senac.vacinas.model.bo;

import java.util.List;

import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.exception.CpfRepetidoException;
import br.com.senac.vacinas.model.exception.NomeInvalidoException;
import br.com.senac.vacinas.model.seletores.SeletorPessoa;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class PessoaBO {

	public PessoaVO salvar(PessoaVO pessoa) {
		PessoaDAO novaPessoa = new PessoaDAO();	
		return novaPessoa.inserir(pessoa);
		}
	
	public boolean atualizar(PessoaVO pessoa) {		
		PessoaDAO pessoaAtualizada = new PessoaDAO();
		int idPessoa = pessoaAtualizada.pesquisarPorCpf(pessoa.getCpf()).getIdPessoa();
		pessoa.setIdPessoa(idPessoa);
		return pessoaAtualizada.atualizar(pessoa);	
	}

	public void conferirCpf(String cpf) throws CpfRepetidoException {
		PessoaDAO dao = new PessoaDAO();	
		if (dao.pesquisarPorCpf(cpf) != null) {
			throw new CpfRepetidoException("CPF já cadastrado");
		}
	}

	public List<PessoaVO> listarPessoas(SeletorPessoa seletor) {
		PessoaDAO dao = new PessoaDAO();	
		return dao.listarComSeletor(seletor);
	}

	public boolean excluir(PessoaVO pessoa) {
		PessoaDAO dao = new PessoaDAO();
		boolean sucesso = dao.excluir(pessoa.getIdPessoa());
		return sucesso;
	}



}
