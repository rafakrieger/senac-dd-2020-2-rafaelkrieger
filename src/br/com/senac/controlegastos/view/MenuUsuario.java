package br.com.senac.controlegastos.view;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.senac.controlegastos.controller.ControladorUsuario;
import br.com.senac.controlegastos.model.vo.UsuarioVO;

public class MenuUsuario {
	
	Scanner teclado = new Scanner(System.in);
	
	private static final int OPCAO_MENU_CADASTRAR_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO = 3;
	private static final int OPCAO_MENU_EXCLUIR_USUARIO = 4;
	private static final int OPCAO_MENU_USUARIO_VOLTAR = 5;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_USUARIOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_USUARIO = 2;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR = 3;
	
	public void apresentarMenuUsuario() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_USUARIO_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_USUARIO: {
					this.cadastrarUsuario();
					break;
				}
				case OPCAO_MENU_CONSULTAR_USUARIO: {
					this.consultarUsuario();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_USUARIO: {
					this.atualizarUsuario();
					break;
				}
				case OPCAO_MENU_EXCLUIR_USUARIO: {
					this.excluirUsuario();
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
		System.out.println("\nControle de Gastos \n------ Menu Cadastro de Usuários ------");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_USUARIO+" - Cadastrar usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO+" - Consultar usuário");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO+" - Atualizar usuário");
		System.out.println(OPCAO_MENU_EXCLUIR_USUARIO+" - Excluir usuário");
		System.out.println(OPCAO_MENU_USUARIO_VOLTAR+" - Voltar");
		System.out.print("\nDigite a opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.println("\nDigite o nome do usuário: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.println("\nDigite o CPF do usuário: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.println("\nDigite o telefone do usuário: ");
		usuarioVO.setTelefone(teclado.nextLine());
		System.out.println("\nDigite o login do usuário: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.println("\nDigite a senha do usuário: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		controladorUsuario.cadastrarUsuarioController(usuarioVO);
	}

	private void consultarUsuario() {
		int opcao = this.apresentarOpcoesConsulta();
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		while (opcao != OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CONSULTAR_TODOS_USUARIOS: {
					opcao = OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR;
					ArrayList<UsuarioVO> listaUsuariosVO = controladorUsuario.consultarTodosUsuariosController();
					System.out.println("\n----- USUÁRIOS CADASTRADOS ----- ");
					System.out.printf("\n%-6s %-31s %-16s %-15s \n", "ID", "NOME", "CPF", "TELEFONE");
					for (int i=0; i < listaUsuariosVO.size(); i++) {
						listaUsuariosVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UM_USUARIO: {
					opcao = OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR;
					UsuarioVO usuarioVO = new UsuarioVO();
					System.out.println("\nDigite o código do usuário: ");
					usuarioVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
					UsuarioVO usuario = controladorUsuario.consultarUsuarioController(usuarioVO);
					System.out.println("\n----- DADOS DO USUÁRIO ----- ");
					System.out.printf("\n%-6s %-31s %-16s %-15s \n", "ID", "NOME", "CPF", "TELEFONE");
					usuario.imprimir();
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
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_USUARIOS + " - Consultar todos os usuários");
		System.out.println(OPCAO_MENU_CONSULTAR_UM_USUARIO + " - Consultar usuário específico");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR + " - Voltar");
		System.out.println("\nDigite a opção: ");			
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void atualizarUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.println("\nDigite o código do usuário: ");
		usuarioVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
		System.out.println("\nDigite o nome do usuário: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.println("\nDigite o CPF do usuário: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.println("\nDigite o telefone do usuário: ");
		usuarioVO.setTelefone(teclado.nextLine());
		System.out.println("\nDigite o login do usuário: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.println("\nDigite a senha do usuário: ");
		usuarioVO.setSenha(teclado.nextLine());		
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		controladorUsuario.atualizarUsuarioController(usuarioVO);
	}

	private void excluirUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.println("\nDigite o código do usuário: ");
		usuarioVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		controladorUsuario.excluirUsuarioController(usuarioVO);
	}

}
