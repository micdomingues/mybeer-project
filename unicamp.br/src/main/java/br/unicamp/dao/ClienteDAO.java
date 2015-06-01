package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Cliente;

public class ClienteDAO extends ConnectionFactory
{
	private static ClienteDAO instance;	
	
	public static ClienteDAO getInstance()
	{
		if(instance == null)
			instance = new ClienteDAO();
		return instance;
	}
	
	public Cliente adicionar(Cliente cliente)
	{
		
		if(cliente != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO CLIENTE (ID, CPF) VALUES (?, ?)";	
				pstmt = conexao.prepareStatement(comando);
				
				pstmt.setInt(1, cliente.getId());
				pstmt.setString(2, cliente.getCpf());
				
				res = pstmt.executeUpdate();	            
	     
				if(res <= 0)
	                cliente = null;
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Cliente: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, null);
	 		}
		}
		else
			cliente = null;
		
		return cliente;
	}
	
	public Cliente carregar(Cliente cliente)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM CLIENTE WHERE ID = ?");
			
			pstmt.setInt(1, cliente.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				cliente.setCpf(rs.getString("CPF"));				
			}
			else
				cliente = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao Carregar Cliente: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return cliente;		
	}
}
