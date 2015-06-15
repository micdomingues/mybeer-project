package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.joda.time.DateTime;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Promocao;

public class PromocaoDAO extends ConnectionFactory
{
	private static PromocaoDAO instance;	
	
	public static PromocaoDAO getInstance()
	{
		if(instance == null)
			instance = new PromocaoDAO();
		return instance;
	}
	
	public Promocao adicionar(Promocao promocao)
	{
		if(promocao != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			DateTime dateTime;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO PROMOCAO (CODBAR, IDFUNCIONARIO, DATAABERTURA, DATAINICIO, DATAFIM, NOME, TIPO, DESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setInt(1, promocao.getCodbar());
				pstmt.setInt(2, promocao.getIdfuncionario());
				
				dateTime = new DateTime(sdf.parse(promocao.getDataabertura()));
				pstmt.setString(3, dateTime.toString("YYYY-MM-dd HH:mm:ss"));
				
				dateTime = new DateTime(sdf.parse(promocao.getDatainicio()));
				pstmt.setString(4, dateTime.toString("YYYY-MM-dd HH:mm:ss"));
				
				dateTime = new DateTime(sdf.parse(promocao.getDatafim()));
				pstmt.setString(5, dateTime.toString("YYYY-MM-dd HH:mm:ss"));
				
				pstmt.setString(6, promocao.getNome());
				pstmt.setString(7, promocao.getTipo());
				pstmt.setString(8, promocao.getDescricao());
				
				res = pstmt.executeUpdate();	            
	            rs = pstmt.getGeneratedKeys();
				
				if(res <= 0)
	            {
	                promocao = null;
	            }
				else
				{
					promocao.setCodpromocao(((rs.next())?rs.getInt(1):0));
				}
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao Adicionar Promocao: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			promocao = null;
		
		return promocao;
	}
	
	public ArrayList<Promocao> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Promocao promocao = null;
		ArrayList<Promocao> promocoes = null;
		
		conexao = criarConexao();
		promocoes = new ArrayList<Promocao>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CODPROMOCAO, CODBAR, IDFUNCIONARIO, DATE_FORMAT(DATAABERTURA,'%d/%m/%Y %H:%i') AS DATAABERTURA,"
					+ " DATE_FORMAT(DATAINICIO,'%d/%m/%Y %H:%i') AS DATAINICIO, DATE_FORMAT(DATAFIM,'%d/%m/%Y %H:%i') AS DATAFIM, "
					+ "NOME, TIPO, DESCRICAO FROM PROMOCAO WHERE NOW() < DATAFIM");
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
			System.out.println("Erro ao listar todas as promocoes: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return promocoes;
	}

}
