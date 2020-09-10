package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.LancamentoUsuarioDTO;

public class RelatorioDAO {

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalReceitasUsuarioDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;		
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = new ArrayList<LancamentoUsuarioDTO>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, SUM(RECEITA.VALOR) "
			+" FROM USUARIO INNER JOIN RECEITA ON USUARIO.IDUSUARIO = RECEITA.IDUSUARIO "
			+" GROUP BY USUARIO.IDUSUARIO ";
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				LancamentoUsuarioDTO lancamentoUsuario = new LancamentoUsuarioDTO();
				lancamentoUsuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				lancamentoUsuario.setNome(resultado.getString(2));
				lancamentoUsuario.setValor(Double.parseDouble(resultado.getString(3)));
				listaLancamentoUsuarioDTO.add(lancamentoUsuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de relatório de receitas de usuários");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalReceitasPeriodoDAO(
		LancamentoUsuarioDTO lancamentoUsuarioDTO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;		
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = new ArrayList<LancamentoUsuarioDTO>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, SUM(RECEITA.VALOR) "
			+" FROM USUARIO INNER JOIN RECEITA ON USUARIO.IDUSUARIO = RECEITA.IDUSUARIO "
			+" WHERE RECEITA.DATARECEITA >= '"+lancamentoUsuarioDTO.getDataInicial()+"'"
			+" AND RECEITA.DATARECEITA <= '"+lancamentoUsuarioDTO.getDataFinal()+"'"
			+" GROUP BY USUARIO.IDUSUARIO, USUARIO.NOME";
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				LancamentoUsuarioDTO lancamentoUsuario = new LancamentoUsuarioDTO();
				lancamentoUsuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				lancamentoUsuario.setNome(resultado.getString(2));
				lancamentoUsuario.setValor(Double.parseDouble(resultado.getString(3)));
				listaLancamentoUsuarioDTO.add(lancamentoUsuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de relatório de receitas por período");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasUsuarioDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;		
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = new ArrayList<LancamentoUsuarioDTO>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, SUM(DESPESA.VALOR) "
			+" FROM USUARIO INNER JOIN DESPESA ON USUARIO.IDUSUARIO = DESPESA.IDUSUARIO "
			+" GROUP BY USUARIO.IDUSUARIO, USUARIO.NOME ";
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				LancamentoUsuarioDTO lancamentoUsuario = new LancamentoUsuarioDTO();
				lancamentoUsuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				lancamentoUsuario.setNome(resultado.getString(2));
				lancamentoUsuario.setValor(Double.parseDouble(resultado.getString(3)));
				listaLancamentoUsuarioDTO.add(lancamentoUsuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de relatório de despesas de usuários");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasPeriodoDAO(
		LancamentoUsuarioDTO lancamentoUsuarioDTO) {		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;		
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = new ArrayList<LancamentoUsuarioDTO>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, SUM(DESPESA.VALOR) "
			+" FROM USUARIO INNER JOIN DESPESA ON USUARIO.IDUSUARIO = DESPESA.IDUSUARIO "
			+" WHERE DESPESA.DATAPAGAMENTO >= '"+lancamentoUsuarioDTO.getDataInicial()+"'"
			+" AND DESPESA.DATAPAGAMENTO <= '"+lancamentoUsuarioDTO.getDataFinal()+"'"
			+" GROUP BY USUARIO.IDUSUARIO, USUARIO.NOME";
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				LancamentoUsuarioDTO lancamentoUsuario = new LancamentoUsuarioDTO();
				lancamentoUsuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				lancamentoUsuario.setNome(resultado.getString(2));
				lancamentoUsuario.setValor(Double.parseDouble(resultado.getString(3)));
				listaLancamentoUsuarioDTO.add(lancamentoUsuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de relatório de despesas por período");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return listaLancamentoUsuarioDTO;
	}

	public ArrayList<LancamentoUsuarioDTO> gerarRelatorioTotalDespesasAbertasDAO() {			
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;		
		ArrayList<LancamentoUsuarioDTO> listaLancamentoUsuarioDTO = new ArrayList<LancamentoUsuarioDTO>();
		String query = "SELECT USUARIO.IDUSUARIO, USUARIO.NOME, SUM(DESPESA.VALOR) "
			+" FROM USUARIO INNER JOIN DESPESA ON USUARIO.IDUSUARIO = DESPESA.IDUSUARIO "
			+" WHERE DESPESA.DATAPAGAMENTO IS NULL "
			+" GROUP BY USUARIO.IDUSUARIO, USUARIO.NOME ";
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				LancamentoUsuarioDTO lancamentoUsuario = new LancamentoUsuarioDTO();
				lancamentoUsuario.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				lancamentoUsuario.setNome(resultado.getString(2));
				lancamentoUsuario.setValor(Double.parseDouble(resultado.getString(3)));
				listaLancamentoUsuarioDTO.add(lancamentoUsuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de relatório de despesas em aberto");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return listaLancamentoUsuarioDTO;
	}

}
