package br.com.senac.vacinas.model.seletores;

import java.time.LocalDate;

public class SeletorVacina {
	
	private int idVacina;
	private String pesquisador;
	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDate dataInicio;
	
	private int limite;
	private int pagina;
	
	public SeletorVacina() {
		super();
		this.limite = 0;
		this.pagina = -1;
	}
	
	public boolean temFiltro() {
		if(this.idVacina > 0) {
			return true;
		}
		
		if((this.pesquisador != null) && (this.pesquisador.trim().length() > 0)) {
			return true;
		}
		
		if((this.paisOrigem != null) && (this.paisOrigem.trim().length() > 0)) {
			return true;
		}
		
		if(this.estagioPesquisa > 0) {
			return true;
		}
		
		if(this.dataInicio != null) {
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
	
	

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(String pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
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
