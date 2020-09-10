package br.com.senac.controlegastos.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.senac.controlegastos.controller.ControladorReceita;
import br.com.senac.controlegastos.model.vo.ReceitaVO;

public class MenuReceita {
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_CADASTRAR_RECEITA = 1;
	private static final int OPCAO_MENU_CONSULTAR_RECEITA = 2;
	private static final int OPCAO_MENU_ATUALIZAR_RECEITA = 3;
	private static final int OPCAO_MENU_EXCLUIR_RECEITA = 4;
	private static final int OPCAO_MENU_RECEITA_VOLTAR = 5;
	
	private static final int OPCAO_MENU_CONSULTAR_RECEITAS_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_UMA_RECEITA = 2;
	private static final int OPCAO_MENU_CONSULTAR_RECEITAS_VOLTAR = 3;

	public void apresentarMenuReceita() {		
			int opcao = this.apresentarOpcoesMenu();
			while (opcao != OPCAO_MENU_RECEITA_VOLTAR) {
				switch(opcao) {
				case OPCAO_MENU_CADASTRAR_RECEITA: {
					this.cadastrarReceita();
					break;
				}
				case OPCAO_MENU_CONSULTAR_RECEITA: {
					this.consultarReceita();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_RECEITA: {
					this.atualizarReceita();
					break;
				}
				case OPCAO_MENU_EXCLUIR_RECEITA: {
					this.excluirReceita();
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
		System.out.println("\nControle de Gastos \n------ Menu Cadastro de Receitas ------");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_RECEITA+" - Cadastrar receita");
		System.out.println(OPCAO_MENU_CONSULTAR_RECEITA+" - Consultar receita");
		System.out.println(OPCAO_MENU_ATUALIZAR_RECEITA+" - Atualizar receita");
		System.out.println(OPCAO_MENU_EXCLUIR_RECEITA+" - Excluir receita");
		System.out.println(OPCAO_MENU_RECEITA_VOLTAR+" - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarReceita() {
		ReceitaVO receitaVO = new ReceitaVO();
		System.out.println("\nDigite o código do usuário: ");
		receitaVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
		System.out.println("\nDigite a descrição da receita: ");
		receitaVO.setDescricao(teclado.nextLine());
		System.out.println("\nDigite o valor da receita: ");
		receitaVO.setValor(Double.parseDouble(teclado.nextLine()));
		System.out.println("\nDigite a data da receita: ");
		receitaVO.setDataReceita(LocalDate.parse(teclado.nextLine(), dateFormatter));	
		
		ControladorReceita controladorReceita = new ControladorReceita();
		controladorReceita.cadastrarReceitaController(receitaVO);
	
	}
	
	private void consultarReceita() {
		int opcao = this.apresentarOpcoesConsulta();
		ControladorReceita controladorReceita = new ControladorReceita();
		while (opcao != OPCAO_MENU_CONSULTAR_RECEITAS_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CONSULTAR_RECEITAS_USUARIO: {
					opcao = OPCAO_MENU_CONSULTAR_RECEITAS_VOLTAR;
					ReceitaVO receitaVO = new ReceitaVO();
					System.out.println("\nDigite o código do usuário: ");
					receitaVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
					ArrayList<ReceitaVO> listaReceitasVO = controladorReceita.consultarReceitasUsuarioController(receitaVO);
					System.out.println("\n----- LISTA DE RECEITAS ----- ");
					System.out.printf("\n%-5s %-15s %-30s %-10s %-15s \n",
							"ID", "IDUSUARIO", "DESCRIÇÃO", "VALOR", "DATA");
					for (int i=0; i < listaReceitasVO.size(); i++) {
						listaReceitasVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UMA_RECEITA: {
					opcao = OPCAO_MENU_CONSULTAR_RECEITAS_VOLTAR;
					ReceitaVO receitaVO = new ReceitaVO();
					System.out.println("\nDigite o código da receita: ");
					receitaVO.setId(Integer.parseInt(teclado.nextLine()));
					ReceitaVO receita = controladorReceita.consultarReceitaController(receitaVO);
					System.out.println("\n----- RESULTADO DA CONSULTA ----- ");
					System.out.printf("\n%3s %10s %-30s %-10s %-15s \n",
							"ID", "IDUSUARIO", "DESCRIÇÃO", "VALOR", "DATA");
					receita.imprimir();
					break;				
				}
				default: {
					System.out.println("\nOpção inválida!");
					opcao = this.apresentarOpcoesConsulta();
				}
			}
		}
		
	}

	private int apresentarOpcoesConsulta() {
		System.out.println("\nInforme o tipo de consulta: ");
		System.out.println(OPCAO_MENU_CONSULTAR_RECEITAS_USUARIO + " - Consultar todas as receitas do usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_UMA_RECEITA + " - Consultar uma receita específica");
		System.out.println(OPCAO_MENU_CONSULTAR_RECEITAS_VOLTAR + " - Voltar");
		System.out.println("\nDigite a opção: ");			
		return Integer.parseInt(teclado.nextLine());			
	}

	private void atualizarReceita() {
		ReceitaVO receitaVO = new ReceitaVO();
		System.out.println("\nDigite o código da receita: ");
		receitaVO.setId(Integer.parseInt(teclado.nextLine()));
		System.out.println("\nDigite o código do usuário: ");
		receitaVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
		System.out.println("\nDigite a descrição da receita: ");
		receitaVO.setDescricao(teclado.nextLine());
		System.out.println("\nDigite o valor da receita: ");
		receitaVO.setValor(Double.parseDouble(teclado.nextLine()));
		System.out.println("\nDigite a data da receita: ");
		receitaVO.setDataReceita(LocalDate.parse(teclado.nextLine(), dateFormatter));			
		
		ControladorReceita controladorReceita = new ControladorReceita();
		controladorReceita.atualizarReceitaController(receitaVO);
	
}

	
	private void excluirReceita() {
		ReceitaVO receitaVO = new ReceitaVO();
		System.out.println("\nDigite o código da receita: ");
		receitaVO.setId(Integer.parseInt(teclado.nextLine()));
		
		ControladorReceita controladorReceita = new ControladorReceita();
		controladorReceita.excluirReceitaController(receitaVO);
	
	}

}
