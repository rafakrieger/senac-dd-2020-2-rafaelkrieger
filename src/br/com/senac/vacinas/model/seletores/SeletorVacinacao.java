package br.com.senac.vacinas.model.seletores;

import java.time.LocalDate;

import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class SeletorVacinacao {
	
	private int idVacinacao;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int avaliacao;
	
	private int limite;
	private int pagina;
	
	public SeletorVacinacao() {
		super();
		this.limite = 0;
		this.pagina = -1;
	}
	
	public boolean temFiltro() {
		
		if(this.idVacinacao > 0) {
			return true;
		}
		
		if(this.avaliacao > 0) {
			return true;
		}
		
		if(this.dataInicio != null) {
			return true;
		}
		
		if(this.dataFim != null) {
			return true;
		}		
		return false;
	}	
	
	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}
	
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	public int getIdVacinacao() {
		return idVacinacao;
	}

	public void setIdVacinacao(int idVacinacao) {
		this.idVacinacao = idVacinacao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}	

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	

}
