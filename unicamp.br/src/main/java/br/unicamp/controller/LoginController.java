package br.unicamp.controller;

import java.util.ArrayList;

import br.unicamp.dao.LoginDAO;
import br.unicamp.model.Login;


/***
 * 
 * Classe responsável por ser o controlador entre o resource e a camada DAO
 * 
 * @author Felipe Carvalho <felipe@tbbrain.com.br>
 *
 */
public class LoginController
{
	public ArrayList<Login> listarTodos()
	{
		return LoginDAO.getInstance().listarTodos();
	}
	
	public Login autenticar(Login login)
	{
		return LoginDAO.getInstance().autenticar(login);
	}
	
	public Login adicionar(Login login)
	{
		return LoginDAO.getInstance().adicionar(login);
	}
	
}