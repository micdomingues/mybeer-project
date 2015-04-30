package br.unicamp.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import br.unicamp.controller.LoginController;
import br.unicamp.controller.PessoaController;



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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String executeLogin(@FormParam("usuario") String usuario, @FormParam("senha") String senha)
	{
		Login
	    //pegar no bd se existe o usuario q esta sendo requisitado
	    Login loginRes = new LoginController().autenticar(login);
	    
	    ResponseClient rc = new ResponseClient();
	    if(loginRes != null)
	    {	    	
		    rc.setRESPONSE(String.valueOf(rc4.encrypt(gson.toJson(loginRes))));
	    }
	    else
	    {
	    	 rc.setRESPONSE("");
	    }
	    
	    String response = rc.parseClassToJSON();
	    return response;
	}
	
	@POST
	@Path("/insereLogin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String insereLogin(@FormParam(REQUEST) String request, @FormParam("FLAG") String flag)
	{
		//Conteiner Usuario (Classe Abstrata) para guardar Aluno ou Professor
		Usuario usuario = null;
		Login login = null;
		Gson gson = new GsonBuilder().create();
	    RC4 rc4 = new RC4();
	    
	    request = rc4.decrypt(request);
	    
	    if(flag != null && !flag.equals("") && !flag.isEmpty())
	    {
	    	//Instancia Aluno ou Professor de acordo com o flag
		    if(flag.equals("1"))
		    	usuario = new Aluno();
		    else
		    	usuario = new Professor();
		    	 
		    //Adiciona o Usuario no BD a partir do Controller
		    usuario = new UsuarioController().adicionar(usuario);
	    	if(usuario != null)
	    	{
	    		login =  gson.fromJson(request, Login.class);
			    
			    //Adiciona o ID para ser possível o cadastro no BD
			    login.setId(usuario.getId());
			    //Adiciona no BD via Controller
			    login = new LoginController().adicionar(login);
	    	}
	    }
	    
	    
	    ResponseClient rc = new ResponseClient();
	    if(login != null && usuario != null)
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
	
}