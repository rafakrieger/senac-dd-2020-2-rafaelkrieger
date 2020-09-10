package br.com.senac.controlegastos.model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReceitaVO extends LancamentoVO {
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private LocalDate dataReceita;

	public ReceitaVO() {
		super();
	}

	public ReceitaVO(int id, int idUsuario, String descricao, double valor, LocalDate dataReceita) {
		super(id, idUsuario, descricao, valor);
		this.dataReceita = dataReceita;
	}

	public LocalDate getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(LocalDate dataReceita) {
		this.dataReceita = dataReceita;
	}

	@Override
	public String toString() {
		return "Código: "+this.getId()+
				"\nUsuário: "+this.getIdUsuario()+
				"\nDescrição: "+this.getDescricao()+
				"\nValor: "+this.getValor()+
				"\nData: " + dataReceita;
	}

	public void imprimir() {
		System.out.printf("%-5d %-15d %-30s %-10s %-15s \n",
				this.getId(),
				this.getIdUsuario(),
				this.getDescricao(),
				this.getValor(),
				this.getDataReceita().format(dateFormatter));		
	}	
	
}
