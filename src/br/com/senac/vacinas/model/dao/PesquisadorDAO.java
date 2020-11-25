package br.com.senac.vacinas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.vacinas.model.vo.PesquisadorVO;

public class PesquisadorDAO {
	
	public PesquisadorVO inserir(PesquisadorVO pesquisador) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO PESQUISADOR (INSTITUICAO, IDPESSOA, NOME) " 
					+ " VALUES (?, ?, ?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);
		ResultSet resultado = null;

		try {			
			query.setString(1, pesquisador.getInstituicao());
			query.setInt(2, pesquisador.getIdPessoa());
			query.setString(3, pesquisador.getNome());
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				resultado = query.getGeneratedKeys();
				if (resultado.first()) {
					int chaveGerada = resultado.getInt(1);
					pesquisador.setIdPesquisador(chaveGerada);
				}		
				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pesquisador.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return pesquisador;
	}
	
	public boolean atualizar(PesquisadorVO pesquisador) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE PESQUISADOR "
				   + " SET NOME=?, INSTITUICAO=? "					  
				   + " WHERE IDPESQUISADOR=? "; 
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setString(1, pesquisador.getNome());
			query.setString(2, pesquisador.getInstituicao());
			query.setInt(3, pesquisador.getIdPesquisador());
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar pesquisador.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		
		String sql = " DELETE FROM PESQUISADOR "
				   + " WHERE IDPESQUISADOR=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pesquisador.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public PesquisadorVO pesquisarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESQUISADOR "
				   + " WHERE IDPESQUISADOR=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		PesquisadorVO pesquisador = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				pesquisador = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pesquisador por id (Id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pesquisador;
	}
	
	public PesquisadorVO pesquisarPorIdPessoa(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESQUISADOR "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		PesquisadorVO pesquisador = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				pesquisador = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pesquisador por id Pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pesquisador;
	}
	
	public List<PesquisadorVO> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESQUISADOR ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<PesquisadorVO> pesquisadores = new ArrayList<PesquisadorVO>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				PesquisadorVO pesquisador = this.construirDoResultSet(rs);
				pesquisadores.add(pesquisador);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pesquisadores.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pesquisadores;
	}
	
	private PesquisadorVO construirDoResultSet(ResultSet pesquisadorConsultado) throws SQLException {
		PesquisadorVO pesquisador = new PesquisadorVO();
		pesquisador.setIdPesquisador(pesquisadorConsultado.getInt("idpesquisador"));
		pesquisador.setInstituicao(pesquisadorConsultado.getString("instituicao"));
		pesquisador.setIdPessoa(pesquisadorConsultado.getInt("idpessoa"));
		pesquisador.setNome(pesquisadorConsultado.getString("nome"));
		return pesquisador;
	}
	
}
