package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Bar;

public class BarDAO extends ConnectionFactory
{
	private static BarDAO instance;	
	
	public static BarDAO getInstance()
	{
		if(instance == null)
			instance = new BarDAO();
		return instance;
	}
	
	public Bar adicionar(Bar bar)
	{
		if(bar != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO BAR (CNPJ, NOME, NOMEFANTASIA) VALUES (?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, bar.getCnpj());
				pstmt.setString(2, bar.getNome());
				pstmt.setString(3, bar.getNomefantasia());
				
				res = pstmt.executeUpdate();	            
	            rs = pstmt.getGeneratedKeys();
				
				if(res <= 0)
	            {
	                bar = null;
	            }
				else
				{
					bar.setCodbar(((rs.next())?rs.getInt(1):0));
				}
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Bar: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		return bar;
	}
	
	public Bar carregar(int codbar)
	{
		Bar bar = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM BAR WHERE CODBAR = ?");
			
			pstmt.setInt(1, codbar);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				bar = new Bar();
				
				bar.setCodbar(codbar);
				bar.setCnpj(rs.getString("CNPJ"));
				bar.setNome(rs.getString("NOME"));
				bar.setNomefantasia(rs.getString("NOMEFANTASIA"));
			}
			else
				bar = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao Carregar Bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return bar;		
	}
	
	public Bar excluir(Bar bar)
    {
		if(bar != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			
			conexao = criarConexao();
	        try
	        {
	            pstmt = conexao.prepareStatement("DELETE FROM BAR WHERE CODBAR = ?");
	            pstmt.setInt(1, bar.getCodbar());

	            res = pstmt.executeUpdate();
	            
	            if(res <= 0)
	                bar = null;
	        }
	        catch (Exception e) 
			{
				System.out.println("Erro ao Excluir Bar: " + e);
				e.printStackTrace();
			}
			finally
			{
				fecharConexao(conexao, pstmt, null);
			}			
		}
		return bar;
    }
	
	public ArrayList<Bar> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bar bar = null;
		ArrayList<Bar> bares = null;
		
		conexao = criarConexao();
		bares = new ArrayList<Bar>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM BAR");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bar = new Bar();
				
				bar.setCodbar(rs.getInt("CODBAR"));
				bar.setCnpj(rs.getString("CNPJ"));
				bar.setNome(rs.getString("NOME"));
				bar.setNomefantasia(rs.getString("NOMEFANTASIA"));			
				
				bares.add(bar);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os bares: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return bares;
	}

}
