package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Login;
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

}
