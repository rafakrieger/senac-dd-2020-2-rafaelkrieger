package br.com.senac.vacinas.model.bo;

import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class PessoaBO {

	public PessoaVO salvar(PessoaVO pessoa) {
		PessoaDAO novaPessoa = new PessoaDAO();			
		return novaPessoa.inserir(pessoa);	
	}

}
