package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.joda.time.DateTime;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Evento;
import br.unicamp.model.Login;

public class EventoDAO extends ConnectionFactory
{
	private static EventoDAO instance;
	
	public static EventoDAO getInstance()
	{
		if(instance == null)
			instance = new EventoDAO();
		return instance;
	}
	
	public Evento adicionar(Evento evento)
	{
		if(evento != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO EVENTO (CODBAR, NOME, DESCRICAO, DATA, LINKEVENTO, LINKIMAGEM) VALUES (?, ?, ?, ?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando);
				
				pstmt.setInt(1, evento.getCodbar());
				pstmt.setString(2, evento.getNome());
				pstmt.setString(3, evento.getDescricao());
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");				
				DateTime dateTime = new DateTime(sdf.parse(evento.getData()));
				
				pstmt.setString(4, dateTime.toString("YYYY-MM-dd HH:mm:ss"));
				
				pstmt.setString(5, evento.getLinkevento());
				pstmt.setString(6, evento.getLinkevento());
				
				res = pstmt.executeUpdate();
				
				if(res <= 0)
	            {
	                evento = null;
	            }
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar Evento: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			evento = null;
		
		return evento;
	}
	
	public ArrayList<Evento> listarTodos()
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = null;
		
		conexao = criarConexao();
		eventos = new ArrayList<Evento>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT CODEVENTO, CODBAR, NOME, DESCRICAO, DATE_FORMAT( DATA,'%d/%m/%Y %H:%i') AS DATA, LINKEVENTO, LINKIMAGEM FROM EVENTO ORDER BY CODEVENTO");
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
			System.out.println("Erro ao listar todos os eventos: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return eventos;
	}

}
