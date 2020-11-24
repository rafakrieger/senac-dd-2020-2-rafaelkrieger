package br.com.senac.seletores;


public class SeletorVacinacao {
	
	private int idVacinacao;
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
