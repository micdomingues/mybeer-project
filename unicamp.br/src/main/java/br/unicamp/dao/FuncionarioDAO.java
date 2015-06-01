package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Funcionario;

public class FuncionarioDAO extends ConnectionFactory
{
	private static FuncionarioDAO instance;	
	
	public static FuncionarioDAO getInstance()
	{
		if(instance == null)
			instance = new FuncionarioDAO();
		return instance;
	}
	
	public Funcionario adicionar(Funcionario funcionario)
	{
		
		if(funcionario != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO FUNCIONARIO (ID, CODBAR, CLASSE) VALUES (?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando);
				
				pstmt.setInt(1, funcionario.getId());
				pstmt.setInt(2, funcionario.getCodbar());
				pstmt.setString(3, "" + funcionario.getClasse());
				
				res = pstmt.executeUpdate();	            
	     
				if(res <= 0)
	                funcionario = null;
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Aluno: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, null);
	 		}
		}
		else
			funcionario = null;
		
		return funcionario;
	}
	
	public Funcionario carregar(Funcionario funcionario)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM FUNCIONARIO WHERE ID = ?");
			
			pstmt.setInt(1, funcionario.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				funcionario.setCodbar(rs.getInt("CODBAR"));
				funcionario.setClasse(rs.getString("CLASSE"));
			}
			else
				funcionario = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao Carregar Funcionario: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return funcionario;		
	}

}
