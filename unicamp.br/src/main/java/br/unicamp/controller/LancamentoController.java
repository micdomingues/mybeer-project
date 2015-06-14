package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.LancamentoDAO;
import br.unicamp.model.Lancamento;

public class LancamentoController
{
	public Lancamento adicionar(Lancamento lancamento)
	{
		return LancamentoDAO.getInstance().adicionar(lancamento);
	}
	
	public ArrayList<Lancamento> listarTodos(int idfuncionario)
	{
		return LancamentoDAO.getInstance().listarTodos(idfuncionario);
	}

}
