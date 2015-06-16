package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.CardapioDAO;
import br.unicamp.model.Cardapio;

public class CardapioController
{
	public Cardapio adicionar(Cardapio cardapio)
	{
		if(CardapioDAO.getInstance().colsultaDisponibilidade(cardapio))
			return CardapioDAO.getInstance().adicionar(cardapio);
		else
			return null;
	}
	
	public int excluir(int codcardapio)
	{
		return CardapioDAO.getInstance().excluir(codcardapio);
	}
	
	public ArrayList<Cardapio> listarTodos()
	{
		return CardapioDAO.getInstance().listarTodos();
	}

}
