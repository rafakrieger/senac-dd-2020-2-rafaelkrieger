package br.com.senac.vacinas.model.bo;

import br.com.senac.vacinas.model.dao.VacinaDAO;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class VacinaBO {

	public VacinaVO salvar(VacinaVO vacina) {
		VacinaDAO novaVacina = new VacinaDAO();
		return novaVacina.inserir(vacina);	
		
	}

}
