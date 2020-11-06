package br.com.senac.vacinas.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.dao.PesquisadorDAO;
import br.com.senac.vacinas.model.vo.VacinaVO;

public class VacinaDAO {
	
	public VacinaVO inserir(VacinaVO novaVacina) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO VACINA (IDPESQUISADOR, PAIS_ORIGEM, ESTAGIO_PESQUISA, DT_INICIO) " 
					+ " VALUES (?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);
		ResultSet resultado = null;

		try {
			query.setInt(1, novaVacina.getPesquisador().getIdPesquisador());
			query.setString(2, novaVacina.getPaisOrigem());
			query.setInt(3, novaVacina.getEstagioPesquisa());
			query.setDate(4, java.sql.Date.valueOf(novaVacina.getDataInicio()));
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				resultado = query.getGeneratedKeys();
				if (resultado.first()) {
					int chaveGerada = resultado.getInt(1);
					novaVacina.setIdVacina(chaveGerada);
				}						
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar vacina.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return novaVacina;
	}
	
	public boolean atualizar(VacinaVO vacina) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE VACINA "
				   + " SET IDPESQUISADOR=?, PAIS_ORIGEM=?, ESTAGIO_PESQUISA=?, DT_INICIO=? "
				   + " WHERE IDVACINA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setInt(1, vacina.getPesquisador().getIdPesquisador());
			query.setString(2, vacina.getPaisOrigem());
			query.setInt(3, vacina.getEstagioPesquisa());
			query.setDate(4, java.sql.Date.valueOf(vacina.getDataInicio()));
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		
		String sql = " DELETE FROM VACINA "
				   + " WHERE IDVACINA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public VacinaVO pesquisarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA "
				   + " WHERE IDVACINA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		VacinaVO vacina = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				vacina = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacina por id (Id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacina;
	}
	
	public List<VacinaVO> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<VacinaVO> vacinas = new ArrayList<VacinaVO>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				VacinaVO vacina = this.construirDoResultSet(rs);
				vacinas.add(vacina);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinas.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinas;
	}
	
	public List<VacinaVO> pesquisarVacinasPorEstagio(int estagioPesquisa) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA "
				   + " WHERE ESTAGIO_PESQUISA=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<VacinaVO> vacinas = new ArrayList<VacinaVO>();
		try {
			query.setInt(1, estagioPesquisa);
			
			ResultSet rs = query.executeQuery();
			while(rs.next()) {
				VacinaVO vacina = this.construirDoResultSet(rs);
				vacinas.add(vacina);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinas do estágio: " + estagioPesquisa + ".\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinas;
	}
	
	private VacinaVO construirDoResultSet(ResultSet vacinaConsultada) throws SQLException {
		VacinaVO vacina = new VacinaVO();
		vacina.setIdVacina(vacinaConsultada.getInt("idvacina"));
		PesquisadorDAO pesquisadorDAO = new PesquisadorDAO();
		vacina.setPesquisador(pesquisadorDAO.pesquisarPorId(vacinaConsultada.getInt("idpesquisador")));
		vacina.setPaisOrigem(vacinaConsultada.getString("pais_origem"));
		vacina.setEstagioPesquisa(vacinaConsultada.getInt("estagio_pesquisa"));
		vacina.setDataInicio(vacinaConsultada.getDate("dt_inicio").toLocalDate());
		
		return vacina;
	}

	

}
