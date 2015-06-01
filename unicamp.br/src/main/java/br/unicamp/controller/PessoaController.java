package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.LoginDAO;
import br.unicamp.dao.PessoaDAO;
import br.unicamp.model.Cliente;
import br.unicamp.model.Funcionario;
import br.unicamp.model.Login;
import br.unicamp.model.Pessoa;

public class PessoaController
{
	/*
	public ArrayList<Pessoa> listarTodos()
	{
		return PessoaDAO.getInstance().listarTodos();
	}
	*/
	public Pessoa adicionar(Pessoa pessoa)
	{
		if(PessoaDAO.getInstance().adicionar(pessoa) != null)
		{
			if(pessoa instanceof Cliente)
				return new ClienteController().adicionar((Cliente) pessoa);
			else if(pessoa instanceof Funcionario)
				return new FuncionarioController().adicionar((Funcionario) pessoa);
			else
				return null;			
		}
		else
			return null;		
	}
	
	public Pessoa carregar(int id)
	{
		Pessoa pessoa = PessoaDAO.getInstance().carregar(id);
		if(pessoa != null)
		{
			if(pessoa instanceof Cliente)
				return new ClienteController().carregar((Cliente) pessoa);
			else if(pessoa instanceof Funcionario)
				return new FuncionarioController().carregar((Funcionario) pessoa);
			else
				return null;			
		}
		else
			return null;		
	}

}
