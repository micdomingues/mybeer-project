package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			pstmt = conexao.prepareStatement("SELECT * FROM EVENTO ORDER BY CODEVENTO");
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
