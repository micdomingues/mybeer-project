package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
			pstmt = conexao.prepareStatement("SELECT LOGIN.ID, LOGIN.USUARIO, LOGIN.EMAIL, PESSOA.TIPO FROM LOGIN "
					+ "INNER JOIN PESSOA ON (LOGIN.ID = PESSOA.ID) WHERE USUARIO = ? AND SENHA = SHA2(?, 256)");
			
			pstmt.setString(1, login.getUsuario());
			pstmt.setString(2, login.getSenha());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				login = new Login();
				
				login.setId(rs.getInt("LOGIN.ID"));
				login.setUsuario(rs.getString("LOGIN.USUARIO"));
				login.setTipo(rs.getString("PESSOA.TIPO"));
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
				comando = "INSERT INTO LOGIN (USUARIO, SENHA, EMAIL) VALUES (?, SHA2(?, 256), ?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, login.getUsuario());
				pstmt.setString(2, login.getSenha());
				pstmt.setString(3, login.getEmail());
				
				res = pstmt.executeUpdate();	            
	            rs = pstmt.getGeneratedKeys();
				
				if(res <= 0)
	            {
	                login = null;
	            }
				else
				{
					login.setId(((rs.next())?rs.getInt(1):0));
					login.setSenha(null);
				}
			}
			catch (Exception e) 
			{
				login = null;
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
	
	public boolean consultaDisponibilidade(Login login)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM LOGIN WHERE USUARIO = ? OR EMAIL = ?");
			
			pstmt.setString(1, login.getUsuario());
			pstmt.setString(2, login.getEmail());
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
				resultado = true;			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao consultar disponibilidade de login: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return resultado;		
	}
	
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
			pstmt = conexao.prepareStatement("SELECT LOGIN.ID, LOGIN.USUARIO, LOGIN.EMAIL, PESSOA.TIPO FROM LOGIN INNER JOIN PESSOA ON (LOGIN.ID = PESSOA.ID)");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Login login = new Login();
				
				login.setId(rs.getInt("LOGIN.ID"));
				login.setUsuario(rs.getString("LOGIN.USUARIO"));
				login.setTipo(rs.getString("PESSOA.TIPO"));
				
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