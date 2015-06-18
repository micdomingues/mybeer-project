package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.MensagemDAO;
import br.unicamp.model.Mensagem;

public class MensagemController
{
	public Mensagem adicionar(Mensagem mensagem, ArrayList<Integer> ids)
	{
		if(MensagemDAO.getInstance().adicionar(mensagem) != null)
		{
			if(MensagemDAO.getInstance().adicionarMensagemCliente(ids, mensagem.getCodmensagem()))
				return mensagem;
			else
				return null;
		}
		else
			return null;
	}
	
	public boolean marcaLidas(int idcliente)
	{
		return MensagemDAO.getInstance().marcaLidas(idcliente);
	}

}
