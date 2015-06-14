package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.ClienteDAO;
import br.unicamp.model.Cliente;
import br.unicamp.model.Mensagem;
import br.unicamp.model.StatisticBar;

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
	
	public Cliente consultaCPF(Cliente cliente)
	{
		return ClienteDAO.getInstance().consultaCPF(cliente);
	}
	
	public ArrayList<StatisticBar> baresFavoritos(int idcliente)
	{
		return ClienteDAO.getInstance().baresFavoritos(idcliente);
	}
	
	public ArrayList<Mensagem> todasMensagens(int idcliente)
	{
		return ClienteDAO.getInstance().todasMensagens(idcliente);
	}
}
