package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.EventoDAO;
import br.unicamp.dao.LoginDAO;
import br.unicamp.model.Evento;
import br.unicamp.model.Login;

public class EventoController
{
	public Evento adicionar(Evento evento)
	{
		return EventoDAO.getInstance().adicionar(evento);
	}
	
	public ArrayList<Evento> listarTodos()
	{
		return EventoDAO.getInstance().listarTodos();
	}

}
