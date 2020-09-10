package br.com.senac.controlegastos.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.senac.controlegastos.controller.ControladorDespesa;
import br.com.senac.controlegastos.model.vo.DespesaVO;

public class MenuDespesa {
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_CADASTRAR_DESPESA = 1;
	private static final int OPCAO_MENU_CONSULTAR_DESPESA = 2;
	private static final int OPCAO_MENU_ATUALIZAR_DESPESA = 3;
	private static final int OPCAO_MENU_EXCLUIR_DESPESA = 4;
	private static final int OPCAO_MENU_DESPESA_VOLTAR = 5;
	
	private static final int OPCAO_MENU_CONSULTAR_DESPESAS_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_UMA_DESPESA = 2;
	private static final int OPCAO_MENU_CONSULTAR_DESPESAS_VOLTAR = 3;

	public void apresentarMenuDespesa() {		
			int opcao = this.apresentarOpcoesMenu();
			while (opcao != OPCAO_MENU_DESPESA_VOLTAR) {
				switch(opcao) {
				case OPCAO_MENU_CADASTRAR_DESPESA: {
					this.cadastrarDespesa();
					break;
				}
				case OPCAO_MENU_CONSULTAR_DESPESA: {
					this.consultarDespesa();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_DESPESA: {
					this.atualizarDespesa();
					break;
				}
				case OPCAO_MENU_EXCLUIR_DESPESA: {
					this.excluirDespesa();
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
		System.out.println("\nControle de Gastos \n------ Menu Cadastro de Despesas ------");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_DESPESA+" - Cadastrar despesa");
		System.out.println(OPCAO_MENU_CONSULTAR_DESPESA+" - Consultar despesa");
		System.out.println(OPCAO_MENU_ATUALIZAR_DESPESA+" - Atualizar despesa");
		System.out.println(OPCAO_MENU_EXCLUIR_DESPESA+" - Excluir despesa");
		System.out.println(OPCAO_MENU_DESPESA_VOLTAR+" - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void cadastrarDespesa() {
		DespesaVO despesaVO = new DespesaVO();
		System.out.println("\nDigite o código do usuário: ");
		despesaVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
		System.out.println("\nDigite a descrição da despesa: ");
		despesaVO.setDescricao(teclado.nextLine());
		System.out.println("\nDigite o valor da despesa: ");
		despesaVO.setValor(Double.parseDouble(teclado.nextLine()));
		System.out.println("\nDigite a data de vencimento da despesa: ");
		despesaVO.setDataVencimento(LocalDate.parse(teclado.nextLine(), dateFormatter));		
		System.out.println("\nDigite a data de pagamento da despesa: ");
		String dataPagamento = teclado.nextLine();
		if(!dataPagamento.equalsIgnoreCase("")) {
			despesaVO.setDataPagamento(LocalDate.parse(dataPagamento, dateFormatter));
		}		
		System.out.println("\nDigite a categoria da despesa: ");
		despesaVO.setCategoria(teclado.nextLine());
		
		ControladorDespesa controladorDespesa = new ControladorDespesa();
		controladorDespesa.cadastrarDespesaController(despesaVO);
	
	}
	
	private void consultarDespesa() {
		int opcao = this.apresentarOpcoesConsulta();
		ControladorDespesa controladorDespesa = new ControladorDespesa();
		while (opcao != OPCAO_MENU_CONSULTAR_DESPESAS_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CONSULTAR_DESPESAS_USUARIO: {
					opcao = OPCAO_MENU_CONSULTAR_DESPESAS_VOLTAR;
					DespesaVO despesaVO = new DespesaVO();
					System.out.println("\nDigite o código do usuário: ");
					despesaVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
					ArrayList<DespesaVO> listaDespesasVO = controladorDespesa.consultarDespesasUsuarioController(despesaVO);
					System.out.println("\n----- LISTA DE DESPESAS ----- ");
					System.out.printf("\n%-5s %-15s %-30s %-10s %-20s %-20s %-20s \n",
							"ID", "IDUSUARIO", "DESCRIÇÃO", "VALOR", "DATA VENCIMENTO", "DATA PAGAMENTO", "CATEGORIA");
					for (int i=0; i < listaDespesasVO.size(); i++) {
						listaDespesasVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UMA_DESPESA: {
					opcao = OPCAO_MENU_CONSULTAR_DESPESAS_VOLTAR;
					DespesaVO despesaVO = new DespesaVO();
					System.out.println("\nDigite o código da despesa: ");
					despesaVO.setId(Integer.parseInt(teclado.nextLine()));
					DespesaVO despesa = controladorDespesa.consultarDespesaController(despesaVO);
					System.out.println("\n----- RESULTADO DA CONSULTA ----- ");
					System.out.printf("\n%3s %10s %-30s %-10s %-15s %-15s %-15s \n",
							"ID", "IDUSUARIO", "DESCRIÇÃO", "VALOR", "DATA VENCIMENTO", "DATA PAGAMENTO", "CATEGORIA");
					despesa.imprimir();
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
			System.out.println(OPCAO_MENU_CONSULTAR_DESPESAS_USUARIO + " - Consultar todas as despesas do usuário");
			System.out.println(OPCAO_MENU_CONSULTAR_UMA_DESPESA + " - Consultar uma despesa específica");
			System.out.println(OPCAO_MENU_CONSULTAR_DESPESAS_VOLTAR + " - Voltar");
			System.out.println("\nDigite a opção: ");			
			return Integer.parseInt(teclado.nextLine());			
		}
		
		private void atualizarDespesa() {
			DespesaVO despesaVO = new DespesaVO();
			System.out.println("\nDigite o código da despesa: ");
			despesaVO.setId(Integer.parseInt(teclado.nextLine()));
			System.out.println("\nDigite o código do usuário: ");
			despesaVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
			System.out.println("\nDigite a descrição da despesa: ");
			despesaVO.setDescricao(teclado.nextLine());
			System.out.println("\nDigite o valor da despesa: ");
			despesaVO.setValor(Double.parseDouble(teclado.nextLine()));
			System.out.println("\nDigite a data de vencimento da despesa: ");
			despesaVO.setDataVencimento(LocalDate.parse(teclado.nextLine(), dateFormatter));
			System.out.println("\nDigite a data de pagamento da despesa: ");
			String dataPagamento = teclado.nextLine();
			if (!dataPagamento.equalsIgnoreCase("")) {
				despesaVO.setDataPagamento(LocalDate.parse(dataPagamento, dateFormatter));
			}			
			System.out.println("\nDigite a categoria da despesa: ");
			despesaVO.setCategoria(teclado.nextLine());
			
			ControladorDespesa controladorDespesa = new ControladorDespesa();
			controladorDespesa.atualizarDespesaController(despesaVO);
		
	}		
		
		private void excluirDespesa() {
			DespesaVO despesaVO = new DespesaVO();
			System.out.println("\nDigite o código da despesa: ");
			despesaVO.setId(Integer.parseInt(teclado.nextLine()));
			
			ControladorDespesa controladorDespesa = new ControladorDespesa();
			controladorDespesa.excluirDespesaController(despesaVO);
		
	}

}
