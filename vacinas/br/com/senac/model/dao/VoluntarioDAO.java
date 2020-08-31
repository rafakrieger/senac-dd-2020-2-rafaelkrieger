package br.com.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.model.vo.VoluntarioVO;

public class VoluntarioDAO {
	
	public VoluntarioVO inserir(VoluntarioVO voluntario) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO VOLUNTARIO (IDPESSOA, VOLUNTARIADO) " 
					+ " VALUES (?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			query.setInt(1, voluntario.getIdPessoa());
			query.setBoolean(2, true);
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt("ID");
				
				voluntario.setIdVoluntario(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar voluntário.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return voluntario;
	}
	
	public boolean atualizar(VoluntarioVO voluntario) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE VOLUNTARIO "
				   + " SET IDPESSOA=?, VOLUNTARIADO=? "
				   + " WHERE IDVOLUNTARIO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setInt(1, voluntario.getIdPessoa());
			query.setBoolean(2, voluntario.isVoluntariado());
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar voluntário.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
		
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		
		String sql = " DELETE FROM VOLUNTARIO "
				   + " WHERE IDVOLUNTARIO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir voluntário.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public VoluntarioVO pesquisarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VOLUNTARIO "
				   + " WHERE IDVOLUNTARIO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		VoluntarioVO voluntario = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				voluntario = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar volntário por id (Id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return voluntario;
	}
	
	public List<VoluntarioVO> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VOLUNTARIO ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<VoluntarioVO> voluntarios = new ArrayList<VoluntarioVO>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				VoluntarioVO voluntario = this.construirDoResultSet(rs);
				voluntarios.add(voluntario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar voluntários.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return voluntarios;
	}
	
	private VoluntarioVO construirDoResultSet(ResultSet voluntarioConsultado) throws SQLException {
		VoluntarioVO voluntario = new VoluntarioVO();
		voluntario.setIdVoluntario(voluntarioConsultado.getInt("idvoluntario"));
		voluntario.setIdPessoa(voluntarioConsultado.getInt("idpessoa"));
		voluntario.setVoluntariado(voluntarioConsultado.getBoolean("voluntariado"));
		voluntario.setNome(voluntarioConsultado.getString("nome"));
		voluntario.setDataNascimento((LocalDate) voluntarioConsultado.getObject("dt_nascimento"));
		voluntario.setSexo(voluntarioConsultado.getString("sexo"));
		voluntario.setCpf(voluntarioConsultado.getString("cpf"));
		
		return voluntario;
	}
	
}
