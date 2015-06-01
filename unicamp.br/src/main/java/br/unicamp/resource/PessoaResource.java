package br.unicamp.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unicamp.controller.PessoaController;
import br.unicamp.model.Pessoa;


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
			System.out.println(id);
			
			pessoa = new PessoaController().carregar(Integer.parseInt(id));			
    	}
	    return pessoa;
	}
}
