package br.unicamp.controller;

import br.unicamp.dao.FuncionarioDAO;
import br.unicamp.model.Funcionario;

public class FuncionarioController
{
	public Funcionario adicionar(Funcionario cliente)
	{
		return FuncionarioDAO.getInstance().adicionar(cliente);
	}
	
	public Funcionario carregar(Funcionario cliente)
	{
		return FuncionarioDAO.getInstance().carregar(cliente);
	}
}
