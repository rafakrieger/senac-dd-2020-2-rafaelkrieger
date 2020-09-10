package br.com.senac.vacinas.view;

import java.time.LocalDate;

import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.dao.PessoaDAO;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;

public class Principal {


	public static void main(String[] args) {
		
		PessoaVO teste1 = new PessoaVO();
		teste1.setNome("Rafael");
		teste1.setCpf("03710188970");
		teste1.setDataNascimento(LocalDate.parse("1984-01-21"));
		teste1.setSexo("M");
		teste1.setVoluntario(false);
		
		PessoaVO teste2 = new PessoaVO();
		teste2.setNome("Daniel");
		teste2.setCpf("06693142928");
		teste2.setDataNascimento(LocalDate.parse("2017-02-15"));
		teste2.setSexo("M");
		teste2.setVoluntario(true);
		
		PessoaVO teste3 = new PessoaVO();
		teste3.setNome("Jessica");
		teste3.setCpf("00693142928");
		teste3.setDataNascimento(LocalDate.parse("1981-01-27"));
		teste3.setSexo("F");
		teste3.setVoluntario(false);
		
		PessoaDAO testeDAO = new PessoaDAO();
		testeDAO.inserir(teste3);
		
		PesquisadorVO pesquisador1 = new PesquisadorVO();
		pesquisador1.setIdPessoa(1);
		pesquisador1.setInstituicao("UFSC");
		
		PesquisadorDAO pesquisador = new PesquisadorDAO();		
		pesquisador.inserir(pesquisador1);
		
	}

}
