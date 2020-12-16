package br.com.senac.vacinas.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.vacinas.model.seletores.SeletorPessoa;
import br.com.senac.vacinas.model.vo.PessoaVO;


public class PessoaDAO {
		
	public PessoaVO inserir(PessoaVO novaPessoa) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO PESSOA (NOME, DT_NASCIMENTO, SEXO, CPF, VOLUNTARIO) " 
					+ " VALUES (?,?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);
		ResultSet resultado = null;

		try {
			query.setString(1, novaPessoa.getNome().toUpperCase());
			query.setDate(2, java.sql.Date.valueOf(novaPessoa.getDataNascimento()));
			query.setString(3, novaPessoa.getSexo().toUpperCase());
			query.setString(4, novaPessoa.getCpf());
			query.setBoolean(5, novaPessoa.isVoluntario());
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				resultado = query.getGeneratedKeys();
				if (resultado.first()) {
					int chaveGerada = resultado.getInt(1);
					novaPessoa.setIdPessoa(chaveGerada);
				}				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return novaPessoa;
	}
		
	
	public boolean atualizar(PessoaVO pessoa) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE PESSOA "
				   + " SET NOME=?, DT_NASCIMENTO=?, SEXO=?, CPF=?, VOLUNTARIO=? "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setString(1, pessoa.getNome());
			query.setDate(2, java.sql.Date.valueOf(pessoa.getDataNascimento()));
			query.setString(3, pessoa.getSexo());
			query.setString(4, pessoa.getCpf());
			query.setBoolean(5, pessoa.isVoluntario());
			query.setInt(6, pessoa.getIdPessoa());
			
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
	
	public boolean atualizarBusca(PessoaVO pessoa) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE PESSOA "
				   + " SET NOME=?, SEXO=?, CPF=? "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setString(1, pessoa.getNome());			
			query.setString(2, pessoa.getSexo());
			query.setString(3, pessoa.getCpf());			
			query.setInt(4, pessoa.getIdPessoa());
			
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
	
	public PessoaVO pesquisarPorCpf(String cpf) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA "
				   + " WHERE CPF=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		PessoaVO pessoa = null;
		try {
			query.setString(1, cpf);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				pessoa = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por CPF.\nCausa: " + e.getMessage());
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
	
	public List<PessoaVO> pesquisarVoluntarios(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA WHERE VOLUNTARIO = TRUE ";
		
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
	
	
	public List<PessoaVO> pesquisarPesquisadores(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESQUISADOR ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<PessoaVO> pessoas = new ArrayList<PessoaVO>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				PessoaVO pessoa = this.construirPesquisador(rs);
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
		
		Date dataSQL = pessoaConsultada.getDate("dt_nascimento");
		LocalDate dataNascimento = dataSQL.toLocalDate();
		pessoa.setDataNascimento(dataNascimento);

		pessoa.setSexo(pessoaConsultada.getString("sexo"));
		pessoa.setCpf(pessoaConsultada.getString("cpf"));
		pessoa.setVoluntario(pessoaConsultada.getBoolean("voluntario"));
		
		return pessoa;
	}
	
	private PessoaVO construirPesquisador(ResultSet pessoaConsultada) throws SQLException {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setIdPessoa(pessoaConsultada.getInt("idpessoa"));
		pessoa.setNome(pessoaConsultada.getString("nome"));		
		
		return pessoa;
	}


	public List<PessoaVO> listarComSeletor(SeletorPessoa seletor) {
		String sql = " SELECT * FROM PESSOA p ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<PessoaVO> pessoas = new ArrayList<PessoaVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				PessoaVO p = construirDoResultSet(result);
				pessoas.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoas;
	}
	
	private String criarFiltros(SeletorPessoa seletor, String sql) {

		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getIdPessoa() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.idpessoa = " + seletor.getIdPessoa();
			primeiro = false;
		}

		if ((seletor.getNome() != null) && (seletor.getNome().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.nome LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		if ((seletor.getCpf() != null) && (seletor.getCpf().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.cpf = '" + seletor.getCpf() + "'";
			primeiro = false;
		}
		
		if ((seletor.getSexo() != null) && (seletor.getSexo().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.sexo = '" + seletor.getSexo() + "'";
			primeiro = false;
		}
		return sql;

	}


	public PessoaVO pesquisarPorNome(String nome) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA "
				   + " WHERE NOME=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		PessoaVO pessoa = null;
		try {
			query.setString(1, nome);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				pessoa = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por nome.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pessoa;
	}




}
