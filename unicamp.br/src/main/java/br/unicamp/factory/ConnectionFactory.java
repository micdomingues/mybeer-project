package br.unicamp.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/***
 * 
 * Classe responsável por conter os métodos criar e fechar o banco de dados.
 * 
 * @author Felipe Carvalho <felipe@tbbrain.com.br>
 *
 */
public class ConnectionFactory
{
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String SERVIDOR = "mybeer.mysql.uhserver.com";
	private static final String BD = "mybeer";
	private static final String PORTA = "3306";
	private static final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BD;
	private static final String USUARIO = "mybeer";
	private static final String SENHA = "mb762*";
	
	
	/**
	 * 
	 * Método responável por criar uma conexão com o banco
	 * 
	 * @author Felipe Carvalho <felipe@tbbrain.com.br>
	 *
	 */
	public Connection criarConexao()
	{
		Connection conexao = null;
		
		try
		{
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);	
		}
		catch (Exception e)
		{
			System.out.println("Erro ao criar conexão com o banco: " + URL);
			e.printStackTrace();
		}
		return conexao;
	}
	
	/**
	 * 
	 * Método responsável por fechar uma conexão com o banco
	 * 
	 * @author Felipe Carvalho <felipe@tbbrain.com.br>
	 *
	 */
	public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet rs)
	{
		try
		{
			if(conexao != null)
			{
				conexao.close();
			}
			if(pstmt != null)
			{
				pstmt.close();
			}
			if(rs != null)
			{
				rs.close();
			}			
		}
		catch (Exception e)
		{
			System.out.println("Erro ao fechar conexão com o banco: " + URL);
			e.printStackTrace();
		}
		
	}
	
}