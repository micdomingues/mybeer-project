package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Cliente;
import br.unicamp.model.Mensagem;
import br.unicamp.model.StatisticBar;

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
	
	public Cliente consultaCPF(Cliente cliente)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CLI.ID, CLI.CPF, CLI.SALDOPTS, PES.NOME, PES.SOBRENOME, "
					+ "PES.TIPO FROM CLIENTE CLI INNER JOIN PESSOA PES ON (CLI.ID = PES.ID) "
					+ "WHERE CLI.CPF = ?");
			
			pstmt.setString(1, cliente.getCpf());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				cliente.setId(rs.getInt("ID"));
				cliente.setSaldopts(rs.getFloat("SALDOPTS"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setSobrenome(rs.getString("SOBRENOME"));
				cliente.setTipo(rs.getString("TIPO"));
			}
			else
				cliente = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao carregar Cliente com CPF: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return cliente;		
	}
	
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
	
	public ArrayList<StatisticBar> baresFavoritos(int idcliente)
	{
		StatisticBar statbar = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<StatisticBar> statsbares = null;
		
		conexao = criarConexao();
		statsbares = new ArrayList<StatisticBar>();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT BAR.CODBAR, BAR.CNPJ, BAR.NOME, BAR.NOMEFANTASIA, "
					+ "(SELECT AVG(PRECO) FROM AVALIACAO AV3 WHERE AV3.CODBAR = AV1.CODBAR) AS MPRECO, "
					+ "(SELECT AVG(QUALIDADE) FROM AVALIACAO AV4 WHERE AV4.CODBAR = AV1.CODBAR) AS MQUALIDADE, "
					+ "(SELECT COUNT(*) FROM AVALIACAO AV2 WHERE FAVORITO = 1 AND AV2.CODBAR = AV1.CODBAR) AS FAVORITOS "
					+ "FROM AVALIACAO AV1 INNER JOIN BAR ON (AV1.CODBAR = BAR.CODBAR) WHERE AV1.IDCLIENTE = ?");
			
			pstmt.setInt(1, idcliente);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				statbar = new StatisticBar();
				
				statbar.setCodbar(idcliente);
				statbar.setCnpj(rs.getString("CNPJ"));
				statbar.setNome(rs.getString("NOME"));
				statbar.setNomefantasia(rs.getString("NOMEFANTASIA"));
				
				statbar.setMediapreco(rs.getFloat("MPRECO"));
				statbar.setMediaqualidade(rs.getFloat("MQUALIDADE"));
				statbar.setFavoritos(rs.getInt("FAVORITOS"));
						
				
				statsbares.add(statbar);
			}			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao carregar estatisticas do Bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return statsbares;		
	}
}
