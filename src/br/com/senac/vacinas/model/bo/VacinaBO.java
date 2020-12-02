package br.com.senac.vacinas.model.bo;

import java.util.List;

import br.com.senac.vacinas.model.dao.VacinaDAO;
import br.com.senac.vacinas.model.seletores.SeletorVacina;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class VacinaBO {
	
	VacinaDAO dao = new VacinaDAO();

	public VacinaVO salvar(VacinaVO vacina) {
		return dao.inserir(vacina);	
		
	}

	public List<VacinaVO> listarVacinas(SeletorVacina seletor) {
		return dao.listarComSeletor(seletor);
	}

	public boolean excluir(VacinaVO vacina) {
		boolean sucesso = dao.excluir(vacina.getIdVacina());
		return sucesso;
	}

	public boolean atualizarBusca(VacinaVO vacina) {
		return dao.atualizarBusca(vacina);	
	}

	public int contarVacinas() {		
		return dao.contarVacinas();
	}

	public void gerarPlanilha(List<VacinaVO> vacinas, String caminhoEscolhido) {
		// TODO Auto-generated method stub
		
	}

	public List<VacinaVO> pesquisarTodos() {
		return dao.pesquisarTodos();
	}

}
