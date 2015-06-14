package br.unicamp.controller;

import br.unicamp.dao.StatisticBarDAO;
import br.unicamp.model.StatisticBar;

public class StatisticBarController
{
	public StatisticBar carregar(int codbar)
	{
		return StatisticBarDAO.getInstance().carregar(codbar);
	}

}
