package br.com.senac.vacinas.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.model.vo.VacinaVO;


public class GeradorPlanilha {

	/**
	 * Gera uma planilha Excel (formato .xlsx) a partir de uma lista de produtos
	 * 
	 * @param caminhoArquivo onde a planilha serÃ¡ salva
	 * @param produtos       a lista de produtos
	 * 
	 * @return uma mensagem informando ao usuÃ¡rio o que ocorreu.
	 */
	public String gerarPlanilhaVacinas(String caminhoArquivo, List<VacinaVO> vacinas) {
		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet aba = planilha.createSheet("Vacinas");

		int linhaAtual = 0;

		// Criar o cabeÃ§alho (header)
		String[] nomesColunas = { "#", "Pesquisador", "País de origem", "Estágio", "Data de início" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		// Preencher as linhas com os produtos
		criarLinhasVacinas(vacinas, aba, linhaAtual);

		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasVacinas(List<VacinaVO> vacinas, XSSFSheet aba, int posicaoLinhaAtual) {
		for (VacinaVO v : vacinas) {
			// criar uma nova linha na planilha
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			// Preencher as cÃ©lulas com os atributos do Produto p
			linhaAtual.createCell(0).setCellValue(v.getIdVacina());
			linhaAtual.createCell(1).setCellValue(v.getPesquisador().getNome());
			linhaAtual.createCell(2).setCellValue(v.getPaisOrigem());
			linhaAtual.createCell(3).setCellValue(v.getEstagioPesquisa());
			linhaAtual.createCell(4).setCellValue(v.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			// Converter para Date
			// linhaAtual.createCell(4).setCellValue(new Date(p.get));

			posicaoLinhaAtual++;
		}

	}
	
	
	
	
	////////////////////////////// Gerador para Pessoas //////////////
	
	
	
	public String gerarPlanilhaPessoas(String caminhoArquivo, List<PessoaVO> pessoas) {
		// Criar a planilha (Workbook)
		XSSFWorkbook planilha = new XSSFWorkbook();

		// Criar uma aba (Sheet)
		XSSFSheet aba = planilha.createSheet("Pessoas");

		int linhaAtual = 0;

		// Criar o cabeÃ§alho (header)
		String[] nomesColunas = { "#", "Nome", "Data Nascimento", "Sexo", "cpf", "Voluntário" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		// Preencher as linhas com os produtos
		criarLinhasPessoas(pessoas, aba, linhaAtual);

		// Salvar o arquivo gerado no disco
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasPessoas(List<PessoaVO> pessoas, XSSFSheet aba, int posicaoLinhaAtual) {
		for (PessoaVO p : pessoas) {
			// criar uma nova linha na planilha
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			// Preencher as cÃ©lulas com os atributos do Produto p
			linhaAtual.createCell(0).setCellValue(p.getIdPessoa());
			linhaAtual.createCell(1).setCellValue(p.getNome());
			linhaAtual.createCell(2).setCellValue(p.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			linhaAtual.createCell(3).setCellValue(p.getSexo());
			linhaAtual.createCell(4).setCellValue(p.getCpf());
			linhaAtual.createCell(5).setCellValue(p.isVoluntario());
			
			
			// Converter para Date
			// linhaAtual.createCell(4).setCellValue(new Date(p.get));

			posicaoLinhaAtual++;
		}

	}
	
	////// Final gerador para pessoas /////////////////

	private void criarCabecalho(String[] nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);

		posicaoLinhaAtual++;
		// Para mudar o estilo:
		// https://stackoverflow.com/questions/43467253/setting-style-in-apache-poi
		for (int i = 0; i < nomesColunas.length; i++) {
			Cell novaCelula = linhaAtual.createCell(i);
			novaCelula.setCellValue(nomesColunas[i]);
		}
	}

	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo, String extensao) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			// TODO lanÃ§ar exceÃ§Ãµes de negÃ³cio (para poder capturar as causas no controller
			// ou tela)
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}