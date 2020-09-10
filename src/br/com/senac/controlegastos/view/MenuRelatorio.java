package br.com.senac.controlegastos.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.senac.controlegastos.controller.ControladorRelatorio;
import br.com.senac.controlegastos.model.dto.LancamentoUsuarioDTO;

public class MenuRelatorio {
	
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_RELATORIO_TOTAL_RECEITAS_USUARIO = 1;
	private static final int OPCAO_MENU_RELATORIO_TOTAL_RECEITAS_USUARIO_PERIODO = 2;
	private static final int OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_USUARIO = 3;
	private static final int OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_USUARIO_PERIODO = 4;
	private static final int OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_ABERTAS_PERIODO = 5;
	private static final int OPCAO_MENU_RELATORIO_SAIR = 6;

	public void apresentarMenuRelatorio() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_RELATORIO_SAIR) {
			switch(opcao) {
				case OPCAO_MENU_RELATORIO_TOTAL_RECEITAS_USUARIO: {
					this.gerarRelatorioTotalReceitasUsuario();
					break;
				}
				case OPCAO_MENU_RELATORIO_TOTAL_RECEITAS_USUARIO_PERIODO: {
					this.gerarRelatorioTotalReceitasUsuarioPeriodo();
					break;
				}
				case OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_USUARIO: {
					this.gerarRelatorioTotalDespesasUsuario();
					break;
				}
				case OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_USUARIO_PERIODO: {
					this.gerarRelatorioTotalDespesasUsuarioPeriodo();
					break;
				}
				case OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_ABERTAS_PERIODO: {
					this.gerarRelatorioTotalDespesasAbertasPeriodo();
					break;
				}
				default: {
					System.out.println("\nOpção inválida");
				}
			}				
				opcao = this.apresentarOpcoesMenu();
		}		
		
	}
	
	private int apresentarOpcoesMenu() {
		System.out.println("\nControle de Gastos \n------ Menu Relatório ------");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_RELATORIO_TOTAL_RECEITAS_USUARIO+" - Total de receitas dos usuários");
		System.out.println(OPCAO_MENU_RELATORIO_TOTAL_RECEITAS_USUARIO_PERIODO+" - Total de receitas dos usuários por período");		
		System.out.println(OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_USUARIO+" - Total de despesas dos usuários");
		System.out.println(OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_USUARIO_PERIODO+" - Total de despesas dos usuários por período");
		System.out.println(OPCAO_MENU_RELATORIO_TOTAL_DESPESAS_ABERTAS_PERIODO+" - Total de despesas em aberto dos usuários");
		System.out.println(OPCAO_MENU_RELATORIO_SAIR+" - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void gerarRelatorioTotalReceitasUsuario() {
		ControladorRelatorio controladorRelatorio = new ControladorRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuariosDTO = controladorRelatorio.gerarRelatorioTotalReceitasUsuarioController();
		
		System.out.println("\n----- RELATÓRIO: TOTAL DE RECEITAS DOS USUÁRIOS ----- ");
		System.out.printf("%3s %-40s %10s \n", "ID", "NOME", "TOTAL");
		for (int i=0; i<listaLancamentoUsuariosDTO.size(); i++) {
			listaLancamentoUsuariosDTO.get(i).imprimir();
		}
		
	}
	
	private void gerarRelatorioTotalReceitasUsuarioPeriodo() {
		LancamentoUsuarioDTO lancamentoUsuarioDTO = new LancamentoUsuarioDTO();
		System.out.println("\nDigite a data inicial: ");
		lancamentoUsuarioDTO.setDataInicial(LocalDate.parse(teclado.nextLine(), dateFormatter));
		System.out.println("\nDigite a data final: ");
		lancamentoUsuarioDTO.setDataFinal(LocalDate.parse(teclado.nextLine(), dateFormatter));
		
		ControladorRelatorio controladorRelatorio = new ControladorRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuariosDTO = controladorRelatorio.gerarRelatorioTotalReceitasPeriodoController(lancamentoUsuarioDTO);
		
		System.out.println("\n----- RELATÓRIO: TOTAL DE RECEITAS DOS USUÁRIOS ----- ");
		System.out.printf("%3s %-40s %10s \n", "ID", "NOME", "TOTAL");
		for (int i=0; i<listaLancamentoUsuariosDTO.size(); i++) {
			listaLancamentoUsuariosDTO.get(i).imprimir();
		}
		
	}
	
	private void gerarRelatorioTotalDespesasUsuario() {
		ControladorRelatorio controladorRelatorio = new ControladorRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuariosDTO = controladorRelatorio.gerarRelatorioTotalDespesasUsuarioController();
		
		System.out.println("\n----- RELATÓRIO: TOTAL DE DESPESAS DOS USUÁRIOS ----- ");
		System.out.printf("%3s %-40s %10s \n", "ID", "NOME", "TOTAL");
		for (int i=0; i<listaLancamentoUsuariosDTO.size(); i++) {
			listaLancamentoUsuariosDTO.get(i).imprimir();
		}		
	}
	
	private void gerarRelatorioTotalDespesasUsuarioPeriodo() {
		LancamentoUsuarioDTO lancamentoUsuarioDTO = new LancamentoUsuarioDTO();
		System.out.println("\nDigite a data inicial: ");
		lancamentoUsuarioDTO.setDataInicial(LocalDate.parse(teclado.nextLine(), dateFormatter));
		System.out.println("\nDigite a data final: ");
		lancamentoUsuarioDTO.setDataFinal(LocalDate.parse(teclado.nextLine(), dateFormatter));
		
		ControladorRelatorio controladorRelatorio = new ControladorRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuariosDTO = controladorRelatorio.gerarRelatorioTotalDespesasPeriodoController(lancamentoUsuarioDTO);
		
		System.out.println("\n----- RELATÓRIO: TOTAL DE DESPESAS DOS USUÁRIOS ----- ");
		System.out.printf("%3s %-40s %10s \n", "ID", "NOME", "TOTAL");
		for (int i=0; i<listaLancamentoUsuariosDTO.size(); i++) {
			listaLancamentoUsuariosDTO.get(i).imprimir();
		}
		
	}

	private void gerarRelatorioTotalDespesasAbertasPeriodo() {
		ControladorRelatorio controladorRelatorio = new ControladorRelatorio();
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuariosDTO = controladorRelatorio.gerarRelatorioTotalDespesasAbertas();
		
		System.out.println("\n----- RELATÓRIO: TOTAL DE DESPESAS EM ABERTO DOS USUÁRIOS ----- ");
		System.out.printf("%3s %-40s %10s \n", "ID", "NOME", "TOTAL");
		for (int i=0; i<listaLancamentoUsuariosDTO.size(); i++) {
			listaLancamentoUsuariosDTO.get(i).imprimir();
		}				
	}	
}
