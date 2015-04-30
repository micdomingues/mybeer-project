package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Login;

/***
 * 
 * Classe responsável por conter os métodos do CRUD
 * 
 * @author Felipe Carvalho <felipe@tbbrain.com.br>
 *
 */
public class LoginDAO extends ConnectionFactory
{
	private static LoginDAO instance;
	
	/***
	 * 
	 * Método responsável por criar uma instancia da classe LoginDAO (Singleton)
	 * 
	 * @return LoginDAO instance
	 * @author Felipe Carvalho <felipe@tbbrain.com.br>
	 * 
	 */
	public static LoginDAO getInstance()
	{
		if(instance == null)
			instance = new LoginDAO();
		return instance;
	}
	
	public Login autenticar(Login login)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM LOGIN WHERE USUARIO = ? AND SENHA = SHA2(?, 256)");
			
			pstmt.setString(1, login.getUsuario());
			pstmt.setString(2, login.getSenha());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				login = new Login();
				
				login.setId(rs.getInt("ID"));
				login.setUsuario(rs.getString("USUARIO"));
				login.setSenha(rs.getString("SENHA"));
			}
			else
				login = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao autenticar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return login;		
	}
	
	public Login adicionar(Login login)
	{
		if(login != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO LOGIN (ID, USUARIO, SENHA, EMAIL) VALUES (?, ?, SHA2(?, 256), ?)";	
				pstmt = conexao.prepareStatement(comando);
				
				pstmt.setInt(1, login.getId());
				pstmt.setString(2, login.getUsuario());
				pstmt.setString(3, login.getSenha());
				pstmt.setString(4, login.getEmail());
				
				res = pstmt.executeUpdate();
				
				if(res <= 0)
	            {
	                login = null;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Login: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			login = null;
		
		return login;
	}
	
	
	/***
	 *
	 * Método responsável por listar todos os logins do banco
	 * 
	 * @return ArrayList<Login> logins
	 * @author Felipe Carvalho <felipe@tbbrain.com.br>
	 * 
	 */
	public ArrayList<Login> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Login> logins = null;
		
		conexao = criarConexao();
		logins = new ArrayList<Login>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM LOGIN ORDER BY USUARIO");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Login login = new Login();
				
				login.setId(rs.getInt("ID"));
				login.setUsuario(rs.getString("USUARIO"));
				login.setSenha(rs.getString("SENHA"));
				
				logins.add(login);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os logins: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return logins;
	}
	

}