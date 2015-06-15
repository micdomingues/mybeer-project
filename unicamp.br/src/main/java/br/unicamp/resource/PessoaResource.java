package br.unicamp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.PessoaController;
import br.unicamp.model.Cliente;
import br.unicamp.model.Funcionario;
import br.unicamp.model.Pessoa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Path("/pessoas")
public class PessoaResource
{	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa carregaPessoa(@PathParam("id") String id)
	{	    
		Pessoa pessoa = null;	    
	    
    	if(id != null)
    	{			
			pessoa = new PessoaController().carregar(Integer.parseInt(id));			
    	}
	    return pessoa;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa inserePessoa(JSONObject json)
	{
		//Conteiner Usuario (Classe Abstrata) para guardar Aluno ou Professor
		Pessoa pessoa = null;
		Gson gson = new GsonBuilder().create();
		
	    if(json != null)
	    {
	    	String tipo = null;
			try
			{
				tipo = String.valueOf(json.get("tipo"));
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
	    	if(tipo.equals("C"))
	    	{
	    		pessoa = new Cliente();
	    		pessoa =  gson.fromJson(json.toString(), Cliente.class);
	    	}	    		
	    	else if (tipo.equals("F"))
	    	{
	    		pessoa = new Funcionario();
	    		pessoa =  gson.fromJson(json.toString(), Funcionario.class);
	    	}

		    if(pessoa != null)
		    {		    	
			    //Adiciona a Pessoa no BD a partir do Controller
			    pessoa = new PessoaController().adicionar(pessoa);			    
		    }	    	
	    }
	    
	    return ((pessoa.getId() != 0)?pessoa:null);
	}
}
