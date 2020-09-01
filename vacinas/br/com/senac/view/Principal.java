package br.com.senac.view;

import java.time.LocalDate;

import br.com.senac.model.dao.PessoaDAO;
import br.com.senac.model.vo.PessoaVO;

public class Principal {


	public static void main(String[] args) {
		
		PessoaVO teste1 = new PessoaVO();
		teste1.setNome("Rafael");
		teste1.setCpf("03710188970");
		teste1.setDataNascimento(LocalDate.parse("1984-01-21"));
		teste1.setSexo("M");
		teste1.setVoluntario(false);
		
		PessoaDAO testeDAO = new PessoaDAO();
		testeDAO.inserir(teste1);
	
	}

}
