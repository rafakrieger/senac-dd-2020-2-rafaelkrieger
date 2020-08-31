package br.com.senac.model.vo;

import java.time.LocalDate;

public class VacinacaoVO {
	
	private int idVacinacao;
	private VacinaVO vacina;
	private PessoaVO pessoa;
	private LocalDate dataVacinacao;
	private int avaliacao;
	
	public VacinacaoVO() {
		super();
	}

	public VacinacaoVO(int idVacinacao, VacinaVO vacina, PessoaVO pessoa, LocalDate dataVacinacao, int avaliacao) {
		super();
		this.idVacinacao = idVacinacao;
		this.vacina = vacina;
		this.pessoa = pessoa;
		this.dataVacinacao = dataVacinacao;
		this.avaliacao = avaliacao;
	}

	public int getIdVacinacao() {
		return idVacinacao;
	}

	public void setIdVacinacao(int idVacinacao) {
		this.idVacinacao = idVacinacao;
	}

	public VacinaVO getVacina() {
		return vacina;
	}

	public void setVacina(VacinaVO vacina) {
		this.vacina = vacina;
	}

	public PessoaVO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}

	public LocalDate getDataVacinacao() {
		return dataVacinacao;
	}

	public void setDataVacinacao(LocalDate dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	

}
