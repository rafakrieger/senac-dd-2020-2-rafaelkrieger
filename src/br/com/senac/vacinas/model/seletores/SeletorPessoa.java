package br.com.senac.vacinas.model.seletores;

public class SeletorPessoa {
	
	private int idPessoa;
	private String nome;
	private String sexo;
	private String cpf;
	
	private int limite;
	private int pagina;
	
	public SeletorPessoa() {
		super();
		this.limite = 0;
		this.pagina = -1;
	}
	
	public boolean temFiltro() {
		if(this.idPessoa > 0) {
			return true;
		}
		
		if((this.nome != null) && (this.nome.trim().length() > 0)) {
			return true;
		}
		
		if((this.sexo != null) && (this.sexo.trim().length() > 0)) {
			return true;
		}
		
		if((this.cpf != null) && (this.cpf.trim().length() > 0)) {
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
	
	

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
