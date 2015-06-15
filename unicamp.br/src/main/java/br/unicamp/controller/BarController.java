package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.BarDAO;
import br.unicamp.model.Avaliacao;
import br.unicamp.model.Bar;
import br.unicamp.model.Cardapio;
import br.unicamp.model.Evento;
import br.unicamp.model.Promocao;

public class BarController
{
	public ArrayList<Bar> listarTodos()
	{
		return BarDAO.getInstance().listarTodos();
	}
	
	public Bar adicionar(Bar bar)
	{
		return BarDAO.getInstance().adicionar(bar);
	}
	
	public Bar carregar(int codbar)
	{
		return BarDAO.getInstance().carregar(codbar);
	}
	
	public ArrayList<Promocao> listarPromocoes(int codbar, String tipo)
	{
		return BarDAO.getInstance().listarPromocoes(codbar, tipo);
	}
	
	public ArrayList<Evento> listarEventos(int codbar)
	{
		return BarDAO.getInstance().listarEventos(codbar);
	}

	public ArrayList<Avaliacao> listarAvaliacoes(int codbar)
	{
		return BarDAO.getInstance().listarAvaliacoes(codbar);
	}

	public Cardapio cardapioDia(int codbar)
	{
		return BarDAO.getInstance().cardapioDia(codbar);
	}

	public ArrayList<Cardapio> listarCardapios(int codbar)
	{
		return BarDAO.getInstance().listarCardapios(codbar);
	}
}
