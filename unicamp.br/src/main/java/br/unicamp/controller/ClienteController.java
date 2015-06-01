package br.unicamp.controller;

import br.unicamp.dao.ClienteDAO;
import br.unicamp.model.Cliente;

public class ClienteController
{
	public Cliente adicionar(Cliente cliente)
	{
		return ClienteDAO.getInstance().adicionar(cliente);
	}
	
	public Cliente carregar(Cliente cliente)
	{
		return ClienteDAO.getInstance().carregar(cliente);
	}
}
