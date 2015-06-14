package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.AvaliacaoDAO;
import br.unicamp.model.Avaliacao;

public class AvaliacaoController
{
	public ArrayList<Avaliacao> listarTodos()
	{
		return AvaliacaoDAO.getInstance().listarTodos();
	}

}
