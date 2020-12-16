package br.com.senac.vacinas.model.vo;

import java.time.LocalDate;

public class VacinaVO {
	
	private int idVacina;
	private PesquisadorVO pesquisador;
	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDate dataInicio;
	
	public VacinaVO() {
		super();
	}

	public VacinaVO(int idVacina, PesquisadorVO pesquisador, String paisOrigem, int estagioPesquisa,
			LocalDate dataInicio) {
		super();
		this.idVacina = idVacina;
		this.pesquisador = pesquisador;
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicio = dataInicio;
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public PesquisadorVO getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(PesquisadorVO pesquisador) {
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

	@Override
	public String toString() {
		return "" + idVacina + " - "+ paisOrigem + " - ESTÁGIO "+ estagioPesquisa;
	}

	

}
