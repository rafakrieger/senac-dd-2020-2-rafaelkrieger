package br.com.senac.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.senac.model.vo.PessoaVO;

public class PessoaDAO {
	
	public PessoaVO inserir(PessoaVO novaPessoa) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO PESSOA (NOME, DT_NASCIMENTO, SEXO, CPF) " 
					+ " VALUES (?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			query.setString(1, novaPessoa.getNome());
			query.setObject(2, novaPessoa.getDataNascimento());
			query.setString(3, novaPessoa.getSexo());
			query.setString(4, novaPessoa.getCpf());
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt(1);
				
				novaPessoa.setIdPessoa(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return novaPessoa;
	}
		
	
	public boolean atualizar(PessoaVO pessoa) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE PESSOA "
				   + " SET NOME=?, DT_NASCIMENTO=?, SEXO=?, CPF=? "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setString(1, pessoa.getNome());
			query.setObject(2, pessoa.getDataNascimento());
			query.setString(3, pessoa.getSexo());
			query.setString(4, pessoa.getCpf());
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		
		String sql = " DELETE FROM PESSOA "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public PessoaVO pesquisarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		PessoaVO pessoa = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				pessoa = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por id (Id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pessoa;
	}
	
	public List<PessoaVO> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<PessoaVO> pessoas = new ArrayList<PessoaVO>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				PessoaVO pessoa = this.construirDoResultSet(rs);
				pessoas.add(pessoa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoas.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pessoas;
	}
	
	private PessoaVO construirDoResultSet(ResultSet pessoaConsultada) throws SQLException {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setIdPessoa(pessoaConsultada.getInt("idpessoa"));
		pessoa.setNome(pessoaConsultada.getString("nome"));
		pessoa.setDataNascimento((LocalDate) pessoaConsultada.getObject("dt_nascimento"));
		pessoa.setSexo(pessoaConsultada.getString("sexo"));
		pessoa.setCpf(pessoaConsultada.getString("cpf"));
		
		return pessoa;
	}

}
