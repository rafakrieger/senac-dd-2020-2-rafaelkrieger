package br.com.senac.vacinas.model.bo;

import java.util.List;

import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.exception.CpfRepetidoException;
import br.com.senac.vacinas.model.exception.NomeInvalidoException;
import br.com.senac.vacinas.model.seletores.SeletorPessoa;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class PessoaBO {
	
	PessoaDAO dao = new PessoaDAO();

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
		if (dao.pesquisarPorCpf(cpf) != null) {
			throw new CpfRepetidoException("CPF já cadastrado");
		}
	}

	public List<PessoaVO> listarPessoas(SeletorPessoa seletor) {
		return dao.listarComSeletor(seletor);
	}

	public boolean excluir(PessoaVO pessoa) {
		boolean sucesso = dao.excluir(pessoa.getIdPessoa());
		return sucesso;
	}

	public PessoaVO pesquisarPorId(int id) {		
		return dao.pesquisarPorId(id);
	}

	public boolean atualizarBusca(PessoaVO pessoa) {
		return dao.atualizarBusca(pessoa);	
	}

	public void gerarPlanilha(List<PessoaVO> pessoas, String caminhoEscolhido) {
		// TODO Auto-generated method stub
		
	}

	public PessoaVO pesquisarPorCpf(String cpf) {		
		return dao.pesquisarPorCpf(cpf);
	}

	public List<PessoaVO> pesquisarTodos() {		
		return dao.pesquisarTodos();
	}

	public List<PessoaVO> pesquisarPesquisadores() {
		return dao.pesquisarPesquisadores();
	}

	public List<PessoaVO> pesquisarVoluntarios() {
		return dao.pesquisarVoluntarios();
	}


}
