package br.com.senac.model.vo;

import java.time.LocalDate;

public class VoluntarioVO extends PessoaVO{
	
	private int idVoluntario;
	private boolean voluntariado;

	public VoluntarioVO() {
		super();
	}

	public VoluntarioVO(int idPessoa, String nome, LocalDate dataNascimento, String sexo, String cpf,
			boolean voluntariado, int idVoluntario) {
		super(idPessoa, nome, dataNascimento, sexo, cpf);
		this.voluntariado = voluntariado;
		this.idVoluntario = idVoluntario;
	}

	public boolean isVoluntariado() {
		return voluntariado;
	}

	public void setVoluntariado(boolean voluntariado) {
		this.voluntariado = voluntariado;
	}

	public int getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(int idVoluntario) {
		this.idVoluntario = idVoluntario;
	}
	

}
