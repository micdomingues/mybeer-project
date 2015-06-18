package br.unicamp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.LoginController;
import br.unicamp.controller.PessoaController;
import br.unicamp.model.Funcionario;
import br.unicamp.model.Login;
import br.unicamp.model.Pessoa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/funcionarios")
public class FuncionarioResource
{
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa insereFuncionarioLogin(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Pessoa pessoa = null;
		Login login = null;
		
		//TESTAR INTEGRIDADE DO JSON	    
		System.out.println(json);
    	if(json != null)
    	{
    		login = gson.fromJson(json.toString(), Login.class);
    		login = new LoginController().adicionar(login);
    		
    		if(login != null && login.getId() != 0)
    		{
    			pessoa =  gson.fromJson(json.toString(), Funcionario.class);
    			pessoa.setId(login.getId());
    			((Funcionario) pessoa).setClasse("C");
    			pessoa = new PessoaController().adicionar(pessoa);
    		}
    	}
    	
    	return (pessoa.getId() != 0)?pessoa:null;
    			    
    }
	

}
