package br.unicamp.resource;

import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

import br.unicamp.controller.PromocaoController;
import br.unicamp.model.Promocao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/promocoes")
public class PromocaoResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos()
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new PromocaoController().listarTodos().toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Promocao inserePromocao(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Promocao promocao = null;
		
		//TESTAR INTEGRIDADE DO JSON	    
	    
    	if(json != null)
    	{
    		promocao =  gson.fromJson(json.toString(), Promocao.class);

    		System.out.println(json);
    		
		    //Adiciona o Evento no BD a partir do Controller
		    promocao = new PromocaoController().adicionar(promocao);
	    }
	    
	    return (promocao.getCodpromocao() != 0)?promocao:null;
	}

}
