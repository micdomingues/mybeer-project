package br.unicamp.resource;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.LoginController;
import br.unicamp.model.Login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/***
 * 
 * Classe responsável por conter os métodos REST de acesso ao webservice
 * 
 * @author Felipe
 *
 */
@Path("/logins")
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Login> listarTodos(){
		return new LoginController().listarTodos();
	}
	
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
	@Path("/insereLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insereLogin(JSONObject json)
	{
		//Conteiner Usuario (Classe Abstrata) para guardar Aluno ou Professor
		Pessoa pessoa = null;
		Login login = null;
		Gson gson = new GsonBuilder().create();
		
		//TESTAR INTEGRIDADE DO JSON	    
    	if(json != null)
    	{	    	
	    	login =  gson.fromJson(json.toString(), Login.class);
	    	
		    //Adiciona no BD via Controller
		    login = new LoginController().adicionar(login);
		    
		    if(login != null)
		    {
		    	//Instancia Aluno ou Professor de acordo com o flag
			    if(login.getTipo() == 'F')
			    	pessoa = new Funcionario();			    	
			    else
			    	pessoa = new Cliente();		    	
			   
			    //Adiciona o ID gerado no login
			    pessoa.setId(login.getId());		    	
			    //Adiciona a Pessoa no BD a partir do Controller
			    pessoa = new PessoaController().adicionar(pessoa);		    	
		    }	    	
	    }
	    
	    ResponseClient rc = new ResponseClient();
	    if(login != null && pessoa != null)
	    {
	    	login.setType(Integer.parseInt(flag));
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