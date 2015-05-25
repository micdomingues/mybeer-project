package br.unicamp.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.LoginController;
import br.unicamp.controller.PessoaController;



import br.unicamp.criptografy.ResponseClient;
import br.unicamp.model.Cardapio;
import br.unicamp.model.Login;
import br.unicamp.model.Pessoa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/***
 * 
 * Classe responsável por conter os métodos REST de acesso ao webservice
 * 
 * @author Felipe
 *
 */
@Path("/login")
public class LoginResource
{
	// Para Inserir objetos de contexto na classe,
	// ex: ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	/***
	 * 
	 * Método responsável por chamar o Controller
	 * 
	 * 
	 * @return ArrayList<Login>
	 * @author Felipe Carvalho <felipe@tbbrain.com.br>
	 * 
	 */
	private final String REQUEST = "REQUEST"; // nome do campo que ira vir a mensagem criptografada do php
		
	@POST
	@Path("/executeLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Login executeLogin(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Login login = null;
		
		//TESTAR INTEGRIDADE DO JSON	    
	    
    	if(json != null)
    	{
    		login =  gson.fromJson(json.toString(), Login.class);
		
			System.out.println(login.getUsuario() + " " + login.getSenha());
			
			if(login != null)
			{				
				login = new LoginController().autenticar(login);
			}
    	}
	    return login;
	}
	
	/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Login insereLogin(JSONObject json)
	{
		Pessoa pessoa = null;
		Login login = null;
		Gson gson = new GsonBuilder().create();
	    
	    if(flag != null && !flag.equals("") && !flag.isEmpty())
	    {
	    	//Instancia Aluno ou Professor de acordo com o flag
		    if(flag.equals("1"))
		    	pessoa = new Aluno();
		    else
		    	pessoa = new Professor();
		    	 
		    //Adiciona o Usuario no BD a partir do Controller
		    pessoa = new UsuarioController().adicionar(pessoa);
	    	if(pessoa != null)
	    	{
	    		login =  gson.fromJson(request, Login.class);
			    
			    //Adiciona o ID para ser possível o cadastro no BD
			    login.setId(pessoa.getId());
			    //Adiciona no BD via Controller
			    login = new LoginController().adicionar(login);
	    	}
	    }
	    
	    
	    ResponseClient rc = new ResponseClient();
	    if(login != null && pessoa != null)
	    {	    	
		    rc.setRESPONSE(String.valueOf(rc4.encrypt(gson.toJson(login))));
	    }
	    else
	    {
	    	 rc.setRESPONSE("");
	    }
	    
	    String response = rc.parseClassToJSON();
	    return response;
	}
	*/
	
}