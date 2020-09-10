package model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LancamentoUsuarioDTO {
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private int idUsuario;
	private String nome;
	private double valor;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	
	public LancamentoUsuarioDTO() {
		super();
	}

	public LancamentoUsuarioDTO(DateTimeFormatter dateFormatter, int idUsuario, String nome, double valor,
			LocalDate dataInicial, LocalDate dataFinal) {
		super();
		this.dateFormatter = dateFormatter;
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.valor = valor;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public DateTimeFormatter getDateFormatter() {
		return dateFormatter;
	}

	public void setDateFormatter(DateTimeFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	public void imprimir() {
		System.out.printf("%3d %-40s %10s \n",
				this.getIdUsuario(),
				this.getNome(),
				this.getValor());
	}
	

}