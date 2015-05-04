package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Cardapio;

public class CardapioDAO extends ConnectionFactory
{
	private static CardapioDAO instance;
	
	public static CardapioDAO getInstance()
	{
		if(instance == null)
			instance = new CardapioDAO();
		return instance;
	}
	
	public Cardapio adicionar(Cardapio cardapio)
	{
		if(cardapio != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO CARDAPIO (IDFUNCIONARIO, SEMANAINICIO, SEMANAFIM, DATAINICIO, DATAFIM, NOME, DESCRICAO) VALUES (?,?,?,?,?,?,?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setInt(1, cardapio.getIdfuncionario());
				pstmt.setString(2, cardapio.getSemanainicio());
				pstmt.setString(3, cardapio.getSemanafim());				
				pstmt.setString(4, cardapio.getDatainicio());
				pstmt.setString(5, cardapio.getDatafim());
				pstmt.setString(6, cardapio.getNome());
				pstmt.setString(7, cardapio.getDescricao());
				
				res = pstmt.executeUpdate();
				
				rs = pstmt.getGeneratedKeys();
		            
				cardapio.setCodcardapio(((rs.next())?rs.getInt(1):0));
				
				if(res <= 0)
	            {
	                cardapio = null;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Cardapio: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			cardapio = null;
		
		return cardapio;
	}
	
	public ArrayList<Cardapio> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cardapio> cardapios = null;
		
		conexao = criarConexao();
		cardapios = new ArrayList<Cardapio>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM CARDAPIO");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Cardapio cardapio = new Cardapio();
				
				cardapio.setCodcardapio(rs.getInt("CODCARDAPIO"));
				cardapio.setNome(rs.getString("NOME"));
				cardapio.setDescricao(rs.getString("DESCRICAO"));
				cardapio.setIdfuncionario(rs.getInt("IDFUNCIONARIO"));
				
				cardapio.setDatainicio(rs.getString("DATAINICIO"));
				cardapio.setDatafim(rs.getString("DATAFIM"));
				
				cardapio.setSemanainicio(rs.getString("SEMANAINICIO"));
				cardapio.setSemanafim(rs.getString("SEMANAFIM"));
				
				cardapios.add(cardapio);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os cardapios: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return cardapios;
	}
}
