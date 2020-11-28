package br.com.senac.vacinas.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.vacinas.model.seletores.SeletorVacina;
import br.com.senac.vacinas.model.seletores.SeletorVacinacao;
import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.model.vo.VacinaVO;
import br.com.senac.vacinas.model.vo.VacinacaoVO;

public class VacinacaoDAO {
	
	public VacinacaoVO inserir(VacinacaoVO vacinacao) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO VACINACAO (IDVACINA, IDPESSOA, DT_VACINACAO, AVALIACAO) " 
					+ " VALUES (?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);
		ResultSet resultado = null;

		try {
			query.setInt(1, vacinacao.getVacina().getIdVacina());
			query.setInt(2, vacinacao.getPessoa().getIdPessoa());
			query.setDate(3, java.sql.Date.valueOf(vacinacao.getDataVacinacao()));
			query.setInt(4, vacinacao.getAvaliacao());
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				resultado = query.getGeneratedKeys();
				if (resultado.first()) {
					int chaveGerada = resultado.getInt(1);
					vacinacao.setIdVacinacao(chaveGerada);
				}						
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar vacina��o.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return vacinacao;
	}
	
	public boolean atualizar(VacinacaoVO vacinacao) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE VACINACAO "
				   + " SET IDVACINA=?, IDPESSOA=?, DT_VACINACAO=?, AVALIACAO=? "
				   + " WHERE IDVACINACAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setInt(1, vacinacao.getVacina().getIdVacina());
			query.setInt(2, vacinacao.getPessoa().getIdPessoa());
			query.setDate(3, java.sql.Date.valueOf(vacinacao.getDataVacinacao()));
			query.setInt(4, vacinacao.getAvaliacao());
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina��o.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		
		String sql = " DELETE FROM VACINACAO "
				   + " WHERE IDVACINACAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina��o.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public VacinacaoVO pesquisarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINACAO "
				   + " WHERE IDVACINACAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		VacinacaoVO vacinacao = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				vacinacao = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinação por id (Id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinacao;
	}
	
	public List<VacinacaoVO> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINACAO ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<VacinacaoVO> vacinacoes = new ArrayList<VacinacaoVO>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				VacinacaoVO vacinacao = this.construirDoResultSet(rs);
				vacinacoes.add(vacinacao);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinações.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinacoes;
		
	}
	
	public VacinacaoVO pesquisarPorIdPessoa(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINACAO "
				   + " WHERE IDPESSOA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		VacinacaoVO vacinacao = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				vacinacao = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinação por id Pessoa. \nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinacao;
	}
	
	
	private VacinacaoVO construirDoResultSet(ResultSet vacinacaoConsultada) throws SQLException {
		VacinacaoVO vacinacao = new VacinacaoVO();
		vacinacao.setIdVacinacao(vacinacaoConsultada.getInt("idvacinacao"));
		vacinacao.setVacina((VacinaVO) vacinacaoConsultada.getObject("idvacina"));
		vacinacao.setPessoa((PessoaVO) vacinacaoConsultada.getObject("idpessoa"));

		Date dataSQL = vacinacaoConsultada.getDate("dt_vacinacao");
		LocalDate dataVacinacao = dataSQL.toLocalDate();
		vacinacao.setDataVacinacao(dataVacinacao);
		
		vacinacao.setAvaliacao(vacinacaoConsultada.getInt("avaliacao"));
		
		return vacinacao;
	}

	public List<VacinacaoVO> listarComSeletor(SeletorVacinacao seletor) {
		String sql = " SELECT * FROM VACINACAO v ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<VacinacaoVO> vacinacoes = new ArrayList<VacinacaoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				VacinacaoVO v = construirDoResultSet(result);
				vacinacoes.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacinacoes;
	}
	
	private String criarFiltros(SeletorVacinacao seletor, String sql) {

		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getIdVacinacao() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.idvacinacao = " + seletor.getIdVacinacao();
			primeiro = false;
		}

		if (seletor.getAvaliacao() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.avaliacao = " + seletor.getAvaliacao();
			primeiro = false;
		}

		if (seletor.getPessoa() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.idpessoa = '" + seletor.getPessoa().getIdPessoa() + "'";
			primeiro = false;
		}
		
		if (seletor.getVacina() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "v.idvacina = " + seletor.getVacina().getIdVacina();
			primeiro = false;
		}
		return sql;

	}

	public boolean atualizarBusca(VacinacaoVO vacinacao) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE VACINACAO "
				   + " SET IDVACINA=?, IDPESSOA=?, AVALIACAO=? "
				   + " WHERE IDVACINACAO=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setInt(1, vacinacao.getVacina().getIdVacina());
			query.setInt(2, vacinacao.getPessoa().getIdPessoa());
			query.setInt(3, vacinacao.getAvaliacao());
			query.setInt(4, vacinacao.getIdVacinacao());
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacinação.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
}
