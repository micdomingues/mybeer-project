package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.EventoDAO;
import br.unicamp.model.Evento;

public class EventoController
{
	public ArrayList<Evento> listarTodos()
	{
		return EventoDAO.getInstance().listarTodos();
	}

}
