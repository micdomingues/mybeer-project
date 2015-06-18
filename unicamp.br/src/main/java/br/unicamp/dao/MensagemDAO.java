package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Mensagem;

public class MensagemDAO extends ConnectionFactory
{
	
	private static MensagemDAO instance;
	
	public static MensagemDAO getInstance()
	{
		if(instance == null)
			instance = new MensagemDAO();
		return instance;
	}
	
	public boolean adicionarMensagemCliente(ArrayList<Integer> ids, int codmensagem)
	{
		boolean retorno = false;
		
		if(ids != null)
		{
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{
				comando = "INSERT INTO CLIENTE_MENSAGEM (IDCLIENTE, CODMENSAGEM) VALUES (?, ?)";
				pstmt = conexao.prepareStatement(comando);
				 
				for (Integer temp: ids)
				{
					pstmt.setInt(1, temp);
					pstmt.setInt(2, codmensagem);
					
				    pstmt.addBatch();
				}
				
				int[] res = pstmt.executeBatch();
				
				
				if(res.length != 0)
	            {
	                retorno = true;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Mensagem para os Clientes: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		
		return retorno;
	}
	
	public boolean marcaLidas(int idcliente)
	{
		boolean retorno = false;
		
		if(idcliente != 0)
		{
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{
				comando = "UPDATE CLIENTE_MENSAGEM SET LIDA = 1 WHERE IDCLIENTE = ?";
				pstmt = conexao.prepareStatement(comando);
				 
				pstmt.setInt(1, idcliente);
				
				int res = pstmt.executeUpdate();
				
				if(res > 0)
	            {
	                retorno = true;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao atualizar Mensagens lidas: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		
		return retorno;
		
	}
	
	public Mensagem adicionar(Mensagem mensagem)
	{
		if(mensagem != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO MENSAGEM (IDFUNCIONARIO, ASSUNTO, CONTEUDO) VALUES (?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setInt(1, mensagem.getIdfuncionario());
				pstmt.setString(2, mensagem.getAssunto());
				pstmt.setString(3, mensagem.getConteudo());
				
				res = pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				
				if(res <= 0)
	            {
	                mensagem = null;
	            }
				else
				{
					mensagem.setCodmensagem(((rs.next())?rs.getInt(1):0));
				}
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Mensagem: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			mensagem = null;
		
		return mensagem;
	}
	
	/*
	public ArrayList<Login> listarTodas(int idcliente)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Mensagem> mensagens = null;
		
		conexao = criarConexao();
		mensagens = new ArrayList<Mensagem>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CLIMEN.CODCLIMEN, CONCAT(PES.NOME, ' ', PES.SOBRENOME) "
					+ "AS NOME, MEN.CONTEUDO, MEN.DATA, CLIMEN.LIDA FROM CLIENTE_MENSAGEM CLIMEN INNER JOIN "
					+ "(MENSAGEM MEN INNER JOIN PESSOA PES ON(MEN.IDFUNCIONARIO = PES.ID)) ON "
					+ "(CLIMEN.CODMENSAGEM = MEN.CODMENSAGEM) INNER JOIN CLIENTE CLI ON (CLIMEN.IDCLIENTE = CLI.ID)");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Mensagem mensagem = new Mensagem();
				
				mensagem.setCodmensagem(rs.getInt("CODCLIMEN"));
				mensagem.setConteudo(rs.getString("CONTEUDO"));
				mensagem.setData(rs.getString("DATA"));
				mensagem.s
				
				
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
	*/

}
