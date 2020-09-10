package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public void cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeRegistroPorCpf(usuarioVO.getCpf())) {
			System.out.println("\nUsuário já cadastrado!");
		} else {
			int resultado = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
			if (resultado == 1 ) {
				System.out.println("\nUsuário cadastrado com sucesso");
			} else {
				System.out.println("\nNão foi possível cadastrar o usuário");
			}
		}
	}
	
	public ArrayList<UsuarioVO> consultarTodosUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> listaUsuariosVO = usuarioDAO.consultarTodosUsuariosDAO();
		if (listaUsuariosVO.isEmpty()) {
			System.out.println("\nNão existem usuários cadastrados!");
		}
		return listaUsuariosVO;
	}

	public UsuarioVO consultarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioVO usuario = usuarioDAO.consultarUsuarioDAO(usuarioVO);
		if (usuario == null) {
			System.out.println("\nUsuário não encontrado!");
		}
		return usuario;
	}

	public void atualizarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeRegistroPorIdUsuario(usuarioVO.getIdUsuario())) {
			int resultado = usuarioDAO.atualizarUsuarioDAO(usuarioVO);
			if (resultado == 1) {
				System.out.println("\nUsuário atualizado com sucesso");
			} else {
				System.out.println("\nNão foi possível atualizar o usuário");
			}
		} else {
			System.out.println("\nUsuário não cadastrado!");
		}
		
	}

	public void excluirUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.existeRegistroPorIdUsuario(usuarioVO.getIdUsuario())) {
			int resultado = usuarioDAO.excluirUsuarioDAO(usuarioVO);
			if (resultado == 1) {
				System.out.println("\nUsuário excluído com sucesso");
			} else {
				System.out.println("\nNão foi possível excluir o usuário");
			}
		} else {
			System.out.println("\nUsuário não existe!");
		}
		
	}
	

}
