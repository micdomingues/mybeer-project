package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Cliente;
import br.unicamp.model.Evento;
import br.unicamp.model.Funcionario;
import br.unicamp.model.Login;
import br.unicamp.model.Pessoa;

public class PessoaDAO extends ConnectionFactory
{
	private static PessoaDAO instance;
	
	public static PessoaDAO getInstance()
	{
		if(instance == null)
			instance = new PessoaDAO();
		return instance;
	}
	
	public Pessoa adicionar(Pessoa pessoa)
	{
		if(pessoa != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO PESSOA (ID, NOME, SOBRENOME, TIPO) VALUES (?, ?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando);
				
				pstmt.setInt(1, pessoa.getId());
				pstmt.setString(2, pessoa.getNome());
				pstmt.setString(3, pessoa.getSobrenome());
				
				if(pessoa instanceof Cliente)
					pstmt.setString(4, "C");									
				else if(pessoa instanceof Funcionario)
					pstmt.setString(4, "F");				
				
				res = pstmt.executeUpdate();
	            
	            if(res <= 0)
	            	pessoa = null;					
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Pessoa: " + e);
				e.printStackTrace();
			}
			finally
		 	{
 				fecharConexao(conexao, pstmt, null);
	 		}
		}
        else
        	pessoa = null;
		
		return pessoa;				
	}
	
	public Pessoa carregar(int id)
	{
		Pessoa pessoa = null;
		String tipo = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM PESSOA WHERE ID = ?");
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				tipo = rs.getString("TIPO");
				
				if(tipo.equals("C"))
					pessoa = new Cliente();
				else
					pessoa = new Funcionario();
					
				pessoa.setId(rs.getInt("ID"));
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setSobrenome(rs.getString("SOBRENOME"));
				pessoa.setTipo(tipo);
			}
			else
				pessoa = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao Carregar Pessoa: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return pessoa;		
	}
	
	/*
	public ArrayList<Pessoa> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Evento> pessoas = null;
	
		conexao = criarConexao();
		pessoas = new ArrayList<Evento>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM PESSOA");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Evento evento = new Evento();
				
				evento.setCodevento(rs.getInt("CODEVENTO"));
				evento.setCodbar(rs.getInt("CODBAR"));
				evento.setNome(rs.getString("NOME"));
				evento.setDescricao(rs.getString("DESCRICAO"));
				evento.setData(rs.getString("DATA"));
				evento.setLinkevento(rs.getString("LINKEVENTO"));
				evento.setLinkimagem(rs.getString("LINKIMAGEM"));
				
				pessoas.add(evento);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os eventos: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return pessoas;
	}
	*/

}
