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
import br.com.senac.vacinas.model.vo.VacinacaoVO;


public class GeradorPlanilha {


	public String gerarPlanilhaVacinas(String caminhoArquivo, List<VacinaVO> vacinas) {
		XSSFWorkbook planilha = new XSSFWorkbook();

		XSSFSheet aba = planilha.createSheet("Vacinas");

		int linhaAtual = 0;

		String[] nomesColunas = { "#", "País", "Pesquisador", "Estágio", "Data de início" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		criarLinhasVacinas(vacinas, aba, linhaAtual);

		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasVacinas(List<VacinaVO> vacinas, XSSFSheet aba, int posicaoLinhaAtual) {
		for (VacinaVO v : vacinas) {
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			linhaAtual.createCell(0).setCellValue(v.getIdVacina());
			linhaAtual.createCell(1).setCellValue(v.getPesquisador().getNome());
			linhaAtual.createCell(2).setCellValue(v.getPaisOrigem());
			linhaAtual.createCell(3).setCellValue(v.getEstagioPesquisa());
			linhaAtual.createCell(4).setCellValue(v.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			

			posicaoLinhaAtual++;
		}

	}
	
	
	
	
	public String gerarPlanilhaPessoas(String caminhoArquivo, List<PessoaVO> pessoas) {
		XSSFWorkbook planilha = new XSSFWorkbook();

		XSSFSheet aba = planilha.createSheet("Pessoas");

		int linhaAtual = 0;

		String[] nomesColunas = { "#", "Nome", "CPF", "Sexo", "Data de nascimento" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		criarLinhasPessoas(pessoas, aba, linhaAtual);

		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasPessoas(List<PessoaVO> pessoas, XSSFSheet aba, int posicaoLinhaAtual) {
		for (PessoaVO p : pessoas) {
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			linhaAtual.createCell(0).setCellValue(p.getIdPessoa());
			linhaAtual.createCell(1).setCellValue(p.getNome());
			linhaAtual.createCell(2).setCellValue(p.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			linhaAtual.createCell(3).setCellValue(p.getSexo());
			linhaAtual.createCell(4).setCellValue(p.getCpf());
			linhaAtual.createCell(5).setCellValue(p.isVoluntario());
			

			posicaoLinhaAtual++;
		}

	}
	
	public String gerarPlanilhaVacinacao(String caminhoArquivo, List<VacinacaoVO> vacinacao) {
		XSSFWorkbook planilha = new XSSFWorkbook();

		XSSFSheet aba = planilha.createSheet("Pessoas");

		int linhaAtual = 0;

		String[] nomesColunas = { "#", "Vacina", "Pessoa", "Avaliação", "Data" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		criarLinhasVacinacao(vacinacao, aba, linhaAtual);

		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void criarLinhasVacinacao(List<VacinacaoVO> vacinacao, XSSFSheet aba, int posicaoLinhaAtual) {
		for (VacinacaoVO v : vacinacao) {
			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);

			linhaAtual.createCell(0).setCellValue(v.getIdVacinacao());
			linhaAtual.createCell(1).setCellValue(v.getVacina().getIdVacina());
			linhaAtual.createCell(2).setCellValue(v.getPessoa().getNome());
			linhaAtual.createCell(3).setCellValue(v.getDataVacinacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			linhaAtual.createCell(4).setCellValue(v.getAvaliacao());			
			
			
			posicaoLinhaAtual++;
		}

	}
	

	private void criarCabecalho(String[] nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);

		posicaoLinhaAtual++;
		
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