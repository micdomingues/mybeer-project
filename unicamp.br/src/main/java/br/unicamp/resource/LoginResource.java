package br.unicamp.resource;


import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

import br.unicamp.controller.ClienteController;
import br.unicamp.controller.LoginController;
import br.unicamp.model.Cliente;
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
	public String listarTodos()
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new LoginController().listarTodos().toArray());
		list.write(out);
				
		return out.toString();
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
			if(login != null)
			{				
				login = new LoginController().autenticar(login);
			}
    	}
	    return login;
	}
	/*
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Login insereLogin(JSONObject json)
	{
		Login login = null;
		String cpf = null;
		Cliente cliente = null;
		Gson gson = new GsonBuilder().create();
		
		//TESTAR INTEGRIDADE DO JSON	    
    	if(json != null)
    	{	    	
	    	login =  gson.fromJson(json.toString(), Login.class);
	    	
	    	try {
				cpf = json.getString("cpf");
			} catch (JSONException e) {
				System.out.println("Erro ao obter CPF do json: " + e);
				e.printStackTrace();
			}
	    	cliente = new Cliente();
	    	cliente.setCpf(cpf);
	    	cliente = new ClienteController().consultaCPF(cliente);
	    	
	    	if(cliente == null)
	    	{
	    		cliente = new Cliente();
	    		//Adiciona no BD via Controller
			    login = new LoginController().adicionar(login);	    		
	    	}		       	
	    }
	   
	    return ((login.getId() != 0)?login:null);
	}
	*/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insereLogin(JSONObject json)
	{
		Login login = null;
		String cpf = null;
		Cliente cliente = null;
		Gson gson = new GsonBuilder().create();
		
		String retorno = null;
		//TESTAR INTEGRIDADE DO JSON	    
    	if(json != null)
    	{	    	
	    	login =  gson.fromJson(json.toString(), Login.class);
	    	
	    	try {
				cpf = json.getString("cpf");
			} catch (JSONException e) {
				retorno = "Erro ao obter CPF do json.";
				System.out.println("Erro ao obter CPF do json: " + e);
				e.printStackTrace();
			}
	    	cliente = new Cliente();
	    	cliente.setCpf(cpf);
	    	cliente = new ClienteController().consultaCPF(cliente);
	    	
	    	if(cliente == null)
	    	{
	    		cliente = new Cliente();
	    		//Adiciona no BD via Controller
			    login = new LoginController().adicionar(login);
			    if(login.getId() == 0)
			    	retorno = "Usuário ou Email já cadastrado";
	    	}
	    	else
	    		retorno = "CPF já cadastrado!";
	    	if(login.getId() != 0)
	    		retorno = String.valueOf(login.getId());
	    }
	   
	    return retorno;
	}
	
}