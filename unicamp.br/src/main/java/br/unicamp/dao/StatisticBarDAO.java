package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.StatisticBar;

public class StatisticBarDAO extends ConnectionFactory
{
	private static StatisticBarDAO instance;
	
	public static StatisticBarDAO getInstance()
	{
		if(instance == null)
			instance = new StatisticBarDAO();
		return instance;
	}
	
	public StatisticBar carregar(int codbar)
	{
		StatisticBar statsbar = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT BAR.CODBAR, BAR.CNPJ, BAR.NOME, BAR.NOMEFANTASIA, BAR.ENDERECO, BAR.DESCRICAO, BAR.OBSERVACAO, AVG(PRECO) AS MPRECO, "
					+ "AVG(QUALIDADE) MQUALIDADE, (SELECT COUNT(*) FROM AVALIACAO AV2 WHERE FAVORITO = 1 AND AV2.CODBAR = AV1.CODBAR) AS FAVORITOS "
					+ "FROM AVALIACAO AV1 INNER JOIN BAR ON (AV1.CODBAR = BAR.CODBAR) WHERE AV1.CODBAR = ?");
			
			pstmt.setInt(1, codbar);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				statsbar = new StatisticBar();
				
				statsbar.setCodbar(codbar);
				statsbar.setCnpj(rs.getString("CNPJ"));
				statsbar.setNome(rs.getString("NOME"));
				statsbar.setNomefantasia(rs.getString("NOMEFANTASIA"));
				statsbar.setEndereco(rs.getString("ENDERECO"));
				statsbar.setDescricao(rs.getString("DESCRICAO"));
				statsbar.setObservacao(rs.getString("OBSERVACAO"));
				
				statsbar.setMediapreco(rs.getFloat("MPRECO"));
				statsbar.setMediaqualidade(rs.getFloat("MQUALIDADE"));
				statsbar.setFavoritos(rs.getInt("FAVORITOS"));
			}
			else
				statsbar = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao carregar estatisticas do Bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return statsbar;		
	}

}
