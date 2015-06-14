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
	
	public Cardapio excluir(Cardapio cardapio)
    {
		if(cardapio != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			
			conexao = criarConexao();
	        try
	        {
	            pstmt = conexao.prepareStatement("DELETE FROM CARDAPIO WHERE CODCARDAPIO = ?");
	            pstmt.setInt(1, cardapio.getCodcardapio());

	            res = pstmt.executeUpdate();
	            
	            if(res <= 0)
	                cardapio = null;
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
		}		
		return cardapio;
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
