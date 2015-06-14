package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unicamp.controller.BarController;
import br.unicamp.controller.LancamentoController;
import br.unicamp.model.Bar;
import br.unicamp.model.Lancamento;

@Path("/bares")
public class BarResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bar> listarTodos(){
		return new BarController().listarTodos();
	}
	
	@GET
	@Path("{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bar carregaBar(@PathParam("codbar") String codbar)
	{	    
		Bar bar = null;
		
		System.out.println(codbar);
		
    	if(codbar != null && !codbar.isEmpty() && codbar != "")
    	{			
			bar = new BarController().carregar(Integer.parseInt(codbar));			
    	}
	    return bar;
	}
	
	@GET
	@Path("/lancamentos/{idfuncionario}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Lancamento> listarLancamentos(@PathParam("idfuncionario") String idfuncionario){
		return new LancamentoController().listarTodos(Integer.parseInt(idfuncionario));
	}
}
