package br.unicamp.resource;

import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

import br.unicamp.controller.BarController;
import br.unicamp.controller.ClienteController;
import br.unicamp.controller.FuncionarioController;
import br.unicamp.controller.LancamentoController;
import br.unicamp.controller.StatisticBarController;
import br.unicamp.model.Bar;
import br.unicamp.model.Cardapio;
import br.unicamp.model.StatisticBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/bares")
public class BarResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos()
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarTodos().toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@GET
	@Path("/search/{nomefantasia}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos(@PathParam("nomefantasia") String nomefantasia)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().search(nomefantasia).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Bar atualizaBar(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Bar bar = null;
		
		//TESTAR INTEGRIDADE DO JSON
    	if(json != null)
    	{
    		bar =  gson.fromJson(json.toString(), Bar.class);
    		
		    //Adiciona o Cardápio no BD a partir do Controller
		    bar = new BarController().atualizar(bar);
	    }
	    System.out.println(bar);
	    return bar;		
	}
	
	@GET
	@Path("{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public StatisticBar carregaBar(@PathParam("codbar") String codbar)
	{	    
		StatisticBar bar = null;
		
    	if(codbar != null && !codbar.isEmpty() && codbar != "")
    	{			
			bar = new StatisticBarController().carregar(Integer.parseInt(codbar));			
    	}
	    return bar;
	}
	
	@GET
	@Path("/lancamentos/{idfuncionario}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarLancamentos(@PathParam("idfuncionario") String idfuncionario)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new LancamentoController().listarTodos(Integer.parseInt(idfuncionario)).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@GET
	@Path("/promocoes/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPromocoes(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarPromocoes(Integer.parseInt(codbar), null).toArray());
		list.write(out);
				
		return out.toString();		
	}
	
	@GET
	@Path("/funcionarios/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarFuncionarios(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new FuncionarioController().listarTodos(Integer.parseInt(codbar)).toArray());
		list.write(out);
				
		return out.toString();		
	}
	
	@GET
	@Path("/promocoes/gerais/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarPromocoesGerais(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarPromocoes(Integer.parseInt(codbar), "G").toArray());
		list.write(out);
				
		return out.toString();	
	}
	
	@GET
	@Path("/eventos/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarEventos(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarEventos(Integer.parseInt(codbar)).toArray());
		list.write(out);
		
		return out.toString(); 
	}
	
	@GET
	@Path("/clientes/favoritos/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarClientesFavoritos(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new ClienteController().todosFavoritos(Integer.parseInt(codbar)).toArray());
		list.write(out);
		
		return out.toString(); 
	}
	
	@GET
	@Path("/avaliacoes/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarAvaliacoes(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarAvaliacoes(Integer.parseInt(codbar)).toArray());
		list.write(out);
		
		return out.toString();
	}
	
	@GET
	@Path("/cardapios/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarCardapios(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarCardapios(Integer.parseInt(codbar)).toArray());
		list.write(out);
		
		return out.toString();
	}
	
	@GET
	@Path("/mensagens/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarMensagens(@PathParam("codbar") String codbar)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new BarController().listarMensagens(Integer.parseInt(codbar)).toArray());
		list.write(out);
		
		return out.toString();
	}
	
	@GET
	@Path("/cardapiododia/{codbar}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cardapio cardapioDia(@PathParam("codbar") String codbar)
	{
		return new BarController().cardapioDia(Integer.parseInt(codbar));
	}
	
	
}
