package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.PromocaoDAO;
import br.unicamp.model.Promocao;

public class PromocaoController
{
	public ArrayList<Promocao> listarTodos()
	{
		return PromocaoDAO.getInstance().listarTodos();
	}
	
	public Promocao adicionar(Promocao promocao)
	{
		return PromocaoDAO.getInstance().adicionar(promocao);
	}

}
