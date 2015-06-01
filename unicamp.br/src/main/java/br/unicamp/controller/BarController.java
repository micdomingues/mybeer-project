package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.BarDAO;
import br.unicamp.model.Bar;

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

}
