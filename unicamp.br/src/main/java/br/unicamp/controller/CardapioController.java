package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.CardapioDAO;
import br.unicamp.model.Cardapio;

public class CardapioController
{
	public Cardapio adicionar(Cardapio cardapio)
	{
		return CardapioDAO.getInstance().adicionar(cardapio);
	}
	
	public ArrayList<Cardapio> listarTodos()
	{
		return CardapioDAO.getInstance().listarTodos();
	}

}
