package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DespesaVO extends LancamentoVO {
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private String categoria;
	
	public DespesaVO() {
		super();
	}

	public DespesaVO(int id, int idUsuario, String descricao, double valor, LocalDate dataVencimento,
			LocalDate dataPagamento, String categoria) {
		super(id, idUsuario, descricao, valor);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.categoria = categoria;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Código: "+this.getId()+
				"\nUsuário: "+this.getIdUsuario()+
				"\nDescrição: "+this.getDescricao()+
				"\nValor: "+this.getValor()+
				"\nVencimento: " + dataVencimento + 
				"\nData de pagamento: "+dataPagamento + 
				"\nCategoria: " + categoria;
	}

	public void imprimir() {
		System.out.printf("%-5d %-15d %-30s %-10s %-20s %-20s %-20s \n",
				this.getId(),
				this.getIdUsuario(),
				this.getDescricao(),
				this.getValor(),
				this.getDataVencimento().format(dateFormatter),
				validaData(this.getDataPagamento()),
				this.getCategoria());
		
	}

	private Object validaData(LocalDate dataPagamento) {
		String resultado = "";
		if (dataPagamento != null) {
			resultado = dataPagamento.format(dateFormatter).toString();
		}
		return resultado;
	}
	

}
