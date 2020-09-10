package br.com.senac.vacinas.model.vo;

import java.time.LocalDate;

public class PesquisadorVO extends PessoaVO{
	
	private int idPesquisador;
	private String instituicao;

	public PesquisadorVO() {
		super();
	}

	public PesquisadorVO(int idPessoa, String nome, LocalDate dataNascimento, String sexo, String cpf, boolean voluntario,
			String instituicao, int idPesquisador) {
		super(idPessoa, nome, dataNascimento, sexo, cpf, voluntario);
		this.instituicao = instituicao;
		this.idPesquisador = idPesquisador;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	

	public int getIdPesquisador() {
		return idPesquisador;
	}

	public void setIdPesquisador(int idPesquisador) {
		this.idPesquisador = idPesquisador;
	}

	@Override
	public String toString() {
		return this.getNome();
	}

}
