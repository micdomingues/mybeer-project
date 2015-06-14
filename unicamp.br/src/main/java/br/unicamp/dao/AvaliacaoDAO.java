package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Avaliacao;

public class AvaliacaoDAO extends ConnectionFactory
{
	private static AvaliacaoDAO instance;	
	
	public static AvaliacaoDAO getInstance()
	{
		if(instance == null)
			instance = new AvaliacaoDAO();
		return instance;
	}
	
	public ArrayList<Avaliacao> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Avaliacao avaliacao = null;
		ArrayList<Avaliacao> avaliacoes = null;
		
		conexao = criarConexao();
		avaliacoes = new ArrayList<Avaliacao>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM AVALIACAO");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				avaliacao = new Avaliacao();
				
				avaliacao.setCodavaliacao(rs.getInt("CODAVALIACAO"));
				avaliacao.setIdcliente(rs.getInt("IDCLIENTE"));
				avaliacao.setCodbar(rs.getInt("CODBAR"));
				avaliacao.setPreco(rs.getInt("PRECO"));
				avaliacao.setQualidade(rs.getInt("QUALIDADE"));
				avaliacao.setComentario(rs.getString("COMENTARIO"));
				avaliacao.setFavorito(((rs.getInt("FAVORITO") == 1)?true:false));				
				
				avaliacoes.add(avaliacao);
			}			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todas os avaliacoes: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return avaliacoes;
	}

}
