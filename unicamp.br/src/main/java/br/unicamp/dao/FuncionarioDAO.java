package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
				pstmt.setString(3, funcionario.getClasse());
				
				res = pstmt.executeUpdate();	            
	     
				if(res <= 0)
	                funcionario = null;
			}
			catch (Exception e) 
			{
				funcionario = null;
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
	
	public ArrayList<Funcionario> listarTodos(int codbar)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> funcionarios = null;
	
		conexao = criarConexao();
		funcionarios = new ArrayList<Funcionario>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT PES.ID, PES.NOME, PES.SOBRENOME, PES.TIPO, FUN.CODBAR, "
					+ "FUN.CLASSE FROM PESSOA PES INNER JOIN FUNCIONARIO FUN ON (PES.ID = FUN.ID) WHERE FUN.CODBAR = ?");
			
			pstmt.setInt(1, codbar);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Funcionario funcionario = new Funcionario();
				
				funcionario.setId(rs.getInt("ID"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setSobrenome(rs.getString("SOBRENOME"));
				funcionario.setTipo(rs.getString("TIPO"));
				funcionario.setCodbar(rs.getInt("CODBAR"));
				funcionario.setClasse(rs.getString("CLASSE"));
				
				funcionarios.add(funcionario);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar os funcionarios do bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return funcionarios;
	}
	
	/*
	public ArrayList<Mensagem> todasMensagens(int idcliente)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Mensagem> mensagens = null;
	
		conexao = criarConexao();
		mensagens = new ArrayList<Mensagem>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CLIMEN.CODCLIMEN, MEN.IDFUNCIONARIO, BAR.NOMEFANTASIA, MEN.ASSUNTO, "
					+ "MEN.CONTEUDO, DATE_FORMAT(DATA,'%d/%m/%Y') AS `DATA`, CLIMEN.LIDA FROM "
					+ "(MENSAGEM MEN INNER JOIN (FUNCIONARIO FUN INNER JOIN BAR ON (FUN.CODBAR = BAR.CODBAR)) ON "
					+ "(MEN.IDFUNCIONARIO = FUN.ID)) INNER JOIN CLIENTE_MENSAGEM CLIMEN ON (MEN.CODMENSAGEM = CLIMEN.CODMENSAGEM) "
					+ "WHERE CLIMEN.IDCLIENTE = ?");
			
			pstmt.setInt(1, idcliente);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Mensagem mensagem = new Mensagem();
				
				mensagem.setCodmensagem(rs.getInt("CODCLIMEN"));
				mensagem.setIdfuncionario(rs.getInt("IDFUNCIONARIO"));
				mensagem.setNomebar(rs.getString("NOMEFANTASIA"));
				mensagem.setAssunto(rs.getString("ASSUNTO"));
				mensagem.setConteudo(rs.getString("CONTEUDO"));
				mensagem.setLida(((rs.getInt("LIDA") == 1)?true:false));
				mensagem.setData(rs.getString("DATA"));

				
				mensagens.add(mensagem);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos as mensagens: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return mensagens;
	}
	*/

}
