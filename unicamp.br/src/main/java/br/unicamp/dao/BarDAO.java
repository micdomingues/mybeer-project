package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Avaliacao;
import br.unicamp.model.Bar;
import br.unicamp.model.Cardapio;
import br.unicamp.model.Evento;
import br.unicamp.model.Promocao;
import br.unicamp.model.Semana;

public class BarDAO extends ConnectionFactory
{
	private static BarDAO instance;	
	
	public static BarDAO getInstance()
	{
		if(instance == null)
			instance = new BarDAO();
		return instance;
	}
	
	public Bar adicionar(Bar bar)
	{
		if(bar != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO BAR (CNPJ, NOME, NOMEFANTASIA) VALUES (?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, bar.getCnpj());
				pstmt.setString(2, bar.getNome());
				pstmt.setString(3, bar.getNomefantasia());
				
				res = pstmt.executeUpdate();	            
	            rs = pstmt.getGeneratedKeys();
				
				if(res <= 0)
	            {
	                bar = null;
	            }
				else
				{
					bar.setCodbar(((rs.next())?rs.getInt(1):0));
				}
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Bar: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		return bar;
	}
	
	public Bar atualizar(Bar bar)
	{
		if(bar != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "UPDATE BAR SET NOMEFANTASIA = ?, ENDERECO = ?, DESCRICAO = ?, OBSERVACAO = ? WHERE CODBAR = ?";	
				pstmt = conexao.prepareStatement(comando);
				
				pstmt.setString(1, bar.getNomefantasia());
				pstmt.setString(2, bar.getEndereco());
				pstmt.setString(3, bar.getDescricao());
				pstmt.setString(4, bar.getObservacao());
				pstmt.setInt(5, bar.getCodbar());
				
				res = pstmt.executeUpdate();	            
	        
				
				if(res <= 0)
	            {
	                bar = null;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao atualizar Bar: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		return bar;
	}
	
	public Bar carregar(int codbar)
	{
		Bar bar = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM BAR WHERE CODBAR = ?");
			
			pstmt.setInt(1, codbar);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				bar = new Bar();
				
				bar.setCodbar(codbar);
				bar.setCnpj(rs.getString("CNPJ"));
				bar.setNome(rs.getString("NOME"));
				bar.setNomefantasia(rs.getString("NOMEFANTASIA"));
			}
			else
				bar = null;
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao Carregar Bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return bar;		
	}
	
	public Bar excluir(Bar bar)
    {
		if(bar != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			
			conexao = criarConexao();
	        try
	        {
	            pstmt = conexao.prepareStatement("DELETE FROM BAR WHERE CODBAR = ?");
	            pstmt.setInt(1, bar.getCodbar());

	            res = pstmt.executeUpdate();
	            
	            if(res <= 0)
	                bar = null;
	        }
	        catch (Exception e) 
			{
				System.out.println("Erro ao Excluir Bar: " + e);
				e.printStackTrace();
			}
			finally
			{
				fecharConexao(conexao, pstmt, null);
			}			
		}
		return bar;
    }
	
	public Cardapio cardapioDia(int codbar)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cardapio cardapio = null;
		Semana sem = null;
		
		conexao = criarConexao();	
		try
		{
			pstmt = conexao.prepareStatement("SELECT CAR.CODCARDAPIO, CAR.IDFUNCIONARIO, CAR.NOME, CAR.DESCRICAO, "
					+ "DATE_FORMAT(CAR.DATAINICIO,'%d/%m/%Y') AS DATAINICIO, DATE_FORMAT(CAR.DATAFIM,'%d/%m/%Y') AS DATAFIM, "
					+ "CAR.SEG, CAR.TER, CAR.QUA, CAR.QUI, CAR.SEX, CAR.SAB, CAR.DOM FROM CARDAPIO CAR INNER JOIN "
					+ "(FUNCIONARIO FUN INNER JOIN BAR ON (FUN.CODBAR = BAR.CODBAR)) ON (CAR.IDFUNCIONARIO = FUN.ID) "
					+ "WHERE BAR.CODBAR = ? AND ((NOW() BETWEEN CAR.DATAINICIO AND CAR.DATAFIM) OR "
					+ "(IF(DATE_FORMAT(NOW(), '%W') = 0 AND CAR.DOM = 1, TRUE, IF(DATE_FORMAT(NOW(), '%W') = 1 AND "
					+ "CAR.SEG = 1, TRUE, IF(DATE_FORMAT(NOW(), '%W') = 2 AND CAR.TER = 1, TRUE, IF(DATE_FORMAT(NOW(), '%W') = 3 "
					+ "AND CAR.QUA = 1, TRUE, IF(DATE_FORMAT(NOW(), '%W') = 4 AND CAR.QUI = 1, TRUE, IF(DATE_FORMAT(NOW(), '%W') = 5 "
					+ "AND CAR.SEX = 1, TRUE, IF(DATE_FORMAT(NOW(), '%W') = 6 AND CAR.SAB = 1, TRUE, FALSE)))))))))");
			
			pstmt.setInt(1, codbar);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				cardapio = new Cardapio();
				
				cardapio.setCodcardapio(rs.getInt("CODCARDAPIO"));
				cardapio.setNome(rs.getString("NOME"));
				cardapio.setDescricao(rs.getString("DESCRICAO"));
				cardapio.setIdfuncionario(rs.getInt("IDFUNCIONARIO"));
				
				cardapio.setDatainicio(rs.getString("DATAINICIO"));
				cardapio.setDatafim(rs.getString("DATAFIM"));
				
				sem = new Semana();
				
				sem.setSegunda(((rs.getInt("SEG") == 1)?true:false));
				sem.setTerca(((rs.getInt("TER") == 1)?true:false));
				sem.setQuarta(((rs.getInt("QUA") == 1)?true:false));
				sem.setQuinta(((rs.getInt("QUI") == 1)?true:false));
				sem.setSexta(((rs.getInt("SEX") == 1)?true:false));
				sem.setSabado(((rs.getInt("SAB") == 1)?true:false));
				sem.setDomingo(((rs.getInt("DOM") == 1)?true:false));
				
				if(!sem.isEmpty())
					cardapio.setSemana(sem);
			}			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao carregar cardapio do dia: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return cardapio;
	}
	
	public ArrayList<Cardapio> listarCardapios(int codbar)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cardapio> cardapios = null;
		
		conexao = criarConexao();
		cardapios = new ArrayList<Cardapio>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CAR.CODCARDAPIO, CAR.IDFUNCIONARIO, CAR.NOME, CAR.DESCRICAO, "
					+ "DATE_FORMAT(CAR.DATAINICIO,'%d/%m/%Y') AS DATAINICIO, DATE_FORMAT(CAR.DATAFIM,'%d/%m/%Y') AS DATAFIM, "
					+ "CAR.SEG, CAR.TER, CAR.QUA, CAR.QUI, CAR.SEX, CAR.SAB, CAR.DOM FROM CARDAPIO CAR INNER JOIN "
					+ "FUNCIONARIO FUN ON (CAR.IDFUNCIONARIO = FUN.ID) WHERE FUN.CODBAR = ?");
			
			pstmt.setInt(1, codbar);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Cardapio cardapio = new Cardapio();
				
				cardapio.setCodcardapio(rs.getInt("CODCARDAPIO"));
				cardapio.setNome(rs.getString("NOME"));
				cardapio.setDescricao(rs.getString("DESCRICAO"));
				cardapio.setIdfuncionario(rs.getInt("IDFUNCIONARIO"));
				
				cardapio.setDatainicio(rs.getString("DATAINICIO"));
				cardapio.setDatafim(rs.getString("DATAFIM"));
				
				Semana sem = new Semana();
				
				sem.setSegunda(((rs.getInt("SEG") == 1)?true:false));
				sem.setTerca(((rs.getInt("TER") == 1)?true:false));
				sem.setQuarta(((rs.getInt("QUA") == 1)?true:false));
				sem.setQuinta(((rs.getInt("QUI") == 1)?true:false));
				sem.setSexta(((rs.getInt("SEX") == 1)?true:false));
				sem.setSabado(((rs.getInt("SAB") == 1)?true:false));
				sem.setDomingo(((rs.getInt("DOM") == 1)?true:false));
				
				if(!sem.isEmpty())
					cardapio.setSemana(sem);
				
				cardapios.add(cardapio);
			}			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar os cardapios do bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return cardapios;
	}

	public ArrayList<Avaliacao> listarAvaliacoes(int codbar)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Avaliacao avaliacao = null;
		ArrayList<Avaliacao> avaliacoes = null;
		
		conexao = criarConexao();
		avaliacoes = new ArrayList<Avaliacao>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT AV.CODAVALIACAO, AV.IDCLIENTE, CONCAT(PES.NOME, ' ', PES.SOBRENOME) AS NOMECLIENTE, "
					+ "AV.CODBAR, BAR.NOMEFANTASIA, AV.PRECO, AV.QUALIDADE, AV.COMENTARIO, AV.FAVORITO, DATE_FORMAT(AV.`DATA`,'%d/%m/%Y %H:%i') AS DATA FROM "
					+ "AVALIACAO AV INNER JOIN (CLIENTE CLI INNER JOIN PESSOA PES ON (CLI.ID = PES.ID)) ON (AV.IDCLIENTE = CLI.ID) "
					+ "INNER JOIN BAR ON (AV.CODBAR = BAR.CODBAR) WHERE AV.CODBAR = ? ORDER BY AV.`DATA` DESC");
			
			pstmt.setInt(1, codbar);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				avaliacao = new Avaliacao();
				
				avaliacao.setCodavaliacao(rs.getInt("CODAVALIACAO"));
				avaliacao.setIdcliente(rs.getInt("IDCLIENTE"));
				avaliacao.setNomecliente(rs.getString("NOMECLIENTE"));
				avaliacao.setCodbar(rs.getInt("CODBAR"));
				avaliacao.setNomebar(rs.getString("NOMEFANTASIA"));
				avaliacao.setPreco(rs.getInt("PRECO"));
				avaliacao.setQualidade(rs.getInt("QUALIDADE"));
				avaliacao.setComentario(rs.getString("COMENTARIO"));
				avaliacao.setFavorito(((rs.getInt("FAVORITO") == 1)?true:false));
				avaliacao.setData(rs.getString("DATA"));
				
				avaliacoes.add(avaliacao);
			}			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todas os avaliacoes: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return avaliacoes;
	}
	
	public ArrayList<Evento> listarEventos(int codbar)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = null;
		
		conexao = criarConexao();
		eventos = new ArrayList<Evento>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CODEVENTO, CODBAR, NOME, DESCRICAO, "
					+ "DATE_FORMAT( DATA,'%d/%m/%Y %H:%i') AS DATA, LINKEVENTO, LINKIMAGEM FROM EVENTO WHERE CODBAR = ? AND (NOW() < DATA) ORDER BY DATA DESC");
			
			pstmt.setInt(1, codbar);
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
				
				eventos.add(evento);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar os eventos do bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return eventos;
	}
	
	public ArrayList<Promocao> listarPromocoes(int codbar, String tipo)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Promocao promocao = null;
		ArrayList<Promocao> promocoes = null;
		String comando = null;
		
		conexao = criarConexao();
		promocoes = new ArrayList<Promocao>();		
		try
		{
			if(tipo == null)
			{
				comando = "SELECT CODPROMOCAO, CODBAR, IDFUNCIONARIO, DATE_FORMAT(DATAABERTURA,'%d/%m/%Y %H:%i') AS DATAABERTURA,"
							+ " DATE_FORMAT(DATAINICIO,'%d/%m/%Y %H:%i') AS DATAINICIO, DATE_FORMAT(DATAFIM,'%d/%m/%Y %H:%i') AS DATAFIM, "
							+ "NOME, TIPO, DESCRICAO FROM PROMOCAO WHERE (NOW() < DATAFIM) AND CODBAR = ?";
				
			}
			else
			{
				comando = "SELECT CODPROMOCAO, CODBAR, IDFUNCIONARIO, DATE_FORMAT(DATAABERTURA,'%d/%m/%Y %H:%i') AS DATAABERTURA,"
						+ " DATE_FORMAT(DATAINICIO,'%d/%m/%Y %H:%i') AS DATAINICIO, DATE_FORMAT(DATAFIM,'%d/%m/%Y %H:%i') AS DATAFIM, "
						+ "NOME, TIPO, DESCRICAO FROM PROMOCAO WHERE (NOW() < DATAFIM) AND CODBAR = ? AND TIPO = ?";
			}
			
			pstmt = conexao.prepareStatement(comando);
			
			pstmt.setInt(1, codbar);
			if(tipo != null)
				pstmt.setString(2, tipo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				promocao = new Promocao();
				
				promocao.setCodpromocao(rs.getInt("CODPROMOCAO"));
				promocao.setCodbar(rs.getInt("CODBAR"));
				promocao.setIdfuncionario(rs.getInt("IDFUNCIONARIO"));
				promocao.setDataabertura(rs.getString("DATAABERTURA"));
				promocao.setDatainicio(rs.getString("DATAINICIO"));
				promocao.setDatafim(rs.getString("DATAFIM"));
				promocao.setNome(rs.getString("NOME"));
				promocao.setTipo(rs.getString("TIPO"));
				promocao.setDescricao(rs.getString("DESCRICAO"));
				
				promocoes.add(promocao);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar as promocoes do bar: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return promocoes;
	}
	
	public ArrayList<Bar> search(String nomefantasia)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bar bar = null;
		ArrayList<Bar> bares = null;
		
		conexao = criarConexao();
		bares = new ArrayList<Bar>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM BAR WHERE NOMEFANTASIA LIKE CONCAT('%', ?, '%')");
			
			pstmt.setString(1, nomefantasia);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bar = new Bar();
				
				bar.setCodbar(rs.getInt("CODBAR"));
				bar.setCnpj(rs.getString("CNPJ"));
				bar.setNome(rs.getString("NOME"));
				bar.setNomefantasia(rs.getString("NOMEFANTASIA"));
				bar.setEndereco(rs.getString("ENDERECO"));
				bar.setDescricao(rs.getString("DESCRICAO"));
				bar.setObservacao(rs.getString("OBSERVACAO"));
				
				bares.add(bar);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os bares: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return bares;
	}
	
	public ArrayList<Bar> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bar bar = null;
		ArrayList<Bar> bares = null;
		
		conexao = criarConexao();
		bares = new ArrayList<Bar>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT * FROM BAR");
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bar = new Bar();
				
				bar.setCodbar(rs.getInt("CODBAR"));
				bar.setCnpj(rs.getString("CNPJ"));
				bar.setNome(rs.getString("NOME"));
				bar.setNomefantasia(rs.getString("NOMEFANTASIA"));			
				
				bares.add(bar);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os bares: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return bares;
	}
}
