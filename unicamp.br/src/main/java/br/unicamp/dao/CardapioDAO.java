package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.joda.time.DateTime;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Cardapio;
import br.unicamp.model.Semana;

public class CardapioDAO extends ConnectionFactory
{
	private static CardapioDAO instance;
	
	public static CardapioDAO getInstance()
	{
		if(instance == null)
			instance = new CardapioDAO();
		return instance;
	}
	
	public Cardapio adicionar(Cardapio cardapio)
	{
		if(cardapio != null)
		{
			int res;
			boolean semana = false;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			DateTime dateTime = null;
			SimpleDateFormat sdf = null;
			
			conexao = criarConexao();			
			try
			{
				if(cardapio.getDatainicio() != null && cardapio.getDatafim() != null)
				{
					comando = "INSERT INTO CARDAPIO (IDFUNCIONARIO, NOME, DESCRICAO, DATAINICIO, DATAFIM) VALUES (?,?,?,?,?)";
				}
				else
				{
					comando = "INSERT INTO CARDAPIO (IDFUNCIONARIO, NOME, DESCRICAO, SEG, TER, QUA, QUI, SEX, SAB, DOM) VALUES (?,?,?,?,?,?,?,?,?,?)";
					semana = true;
				}
						
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setInt(1, cardapio.getIdfuncionario());
				pstmt.setString(2, cardapio.getNome());
				pstmt.setString(3, cardapio.getDescricao());
				
				if(!semana)
				{
					sdf = new SimpleDateFormat("dd/MM/yyyy");				
					
					dateTime = new DateTime(sdf.parse(cardapio.getDatainicio()));					
					pstmt.setString(4, dateTime.toString("YYYY-MM-dd"));
					
					dateTime = new DateTime(sdf.parse(cardapio.getDatafim()));
					pstmt.setString(5, dateTime.toString("YYYY-MM-dd"));
				}
				else
				{
					Semana sem = cardapio.getSemana();
					pstmt.setInt(4, ((sem.isSegunda())?1:0));
					pstmt.setInt(5, ((sem.isTerca())?1:0));
					pstmt.setInt(6, ((sem.isQuarta())?1:0));
					pstmt.setInt(7, ((sem.isQuinta())?1:0));
					pstmt.setInt(8, ((sem.isSexta())?1:0));
					pstmt.setInt(9, ((sem.isSabado())?1:0));
					pstmt.setInt(10, ((sem.isDomingo())?1:0));
				}				
				
				res = pstmt.executeUpdate();
				
				rs = pstmt.getGeneratedKeys();
		            
				cardapio.setCodcardapio(((rs.next())?rs.getInt(1):0));
				
				if(res <= 0)
	            {
	                cardapio = null;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Cardapio: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			cardapio = null;
		
		return cardapio;
	}
	
	public int excluir(int codcardapio)
    {
		int res;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		
		conexao = criarConexao();
        try
        {
            pstmt = conexao.prepareStatement("DELETE FROM CARDAPIO WHERE CODCARDAPIO = ?");
            pstmt.setInt(1, codcardapio);

            res = pstmt.executeUpdate();
            
            if(res <= 0)
                codcardapio = 0;
        }
        catch (Exception e) 
		{
			System.out.println("Erro ao Excluir Cardapio: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, null);
		}				
		return codcardapio;
    }
	
	public boolean colsultaDisponibilidade(Cardapio cardapio)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String comando = null;
		DateTime dateTime = null;
		SimpleDateFormat sdf = null;
		boolean retorno = false;
		
		conexao = criarConexao();
		
		try
		{
			if(cardapio.getSemana().isEmpty())
			{
				comando = "SELECT CAR.CODCARDAPIO FROM CARDAPIO CAR INNER JOIN FUNCIONARIO FUN ON (CAR.IDFUNCIONARIO = FUN.ID) "
						+ "WHERE FUN.CODBAR = (SELECT CODBAR FROM FUNCIONARIO WHERE ID = ?) AND "
						+ "((? BETWEEN CAR.DATAINICIO AND CAR.DATAFIM) OR (? BETWEEN CAR.DATAINICIO AND CAR.DATAFIM))";
			}
			else
			{
				comando = "SELECT CAR.CODCARDAPIO FROM CARDAPIO CAR INNER JOIN FUNCIONARIO FUN ON (CAR.IDFUNCIONARIO = FUN.ID) WHERE "
						+ "FUN.CODBAR = (SELECT CODBAR FROM FUNCIONARIO WHERE ID = ?) AND "
						+ "((CAR.DOM = ? AND CAR.DOM <> 0) OR (SEG = ? AND CAR.SEG <> 0) OR (TER = ? AND CAR.TER <> 0) OR (QUA = ? AND CAR.QUA <> 0)"
						+ " OR (QUI = ? AND CAR.QUI <> 0) OR (SEX = ? AND CAR.SEX <> 0) OR (SAB = ? AND CAR.SAB <> 0)) ";
				
			}
			
			pstmt = conexao.prepareStatement(comando);
			pstmt.setInt(1, cardapio.getIdfuncionario());
			
			if(cardapio.getSemana().isEmpty())
			{
				sdf = new SimpleDateFormat("dd/MM/yyyy");				
				
				dateTime = new DateTime(sdf.parse(cardapio.getDatainicio()));					
				pstmt.setString(2, dateTime.toString("YYYY-MM-dd"));
				
				dateTime = new DateTime(sdf.parse(cardapio.getDatafim()));					
				pstmt.setString(3, dateTime.toString("YYYY-MM-dd"));
			}
			else
			{
				pstmt.setInt(2, ((cardapio.getSemana().isDomingo())?1:0));
				pstmt.setInt(3, ((cardapio.getSemana().isSegunda())?1:0));
				pstmt.setInt(4, ((cardapio.getSemana().isTerca())?1:0));
				pstmt.setInt(5, ((cardapio.getSemana().isQuarta())?1:0));
				pstmt.setInt(6, ((cardapio.getSemana().isQuinta())?1:0));
				pstmt.setInt(7, ((cardapio.getSemana().isSexta())?1:0));
				pstmt.setInt(8, ((cardapio.getSemana().isSabado())?1:0));				
			}
			
			rs = pstmt.executeQuery();
			
			if(!rs.next())
			{
				retorno = true;
			}
			
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
		return retorno;
	}
	
	public ArrayList<Cardapio> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cardapio> cardapios = null;
		
		conexao = criarConexao();
		cardapios = new ArrayList<Cardapio>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CODCARDAPIO, IDFUNCIONARIO, NOME, DESCRICAO, "
					+ "DATE_FORMAT(DATAINICIO,'%d/%m/%Y') AS DATAINICIO, DATE_FORMAT(DATAFIM,'%d/%m/%Y') AS DATAFIM, "
					+ "SEG, TER, QUA, QUI, SEX, SAB, DOM  FROM CARDAPIO");
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
			System.out.println("Erro ao listar todos os cardapios: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return cardapios;
	}
}
