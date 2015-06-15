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

import br.unicamp.controller.CardapioController;
import br.unicamp.model.Cardapio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/cardapios")
public class CardapioResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listarTodos()
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new CardapioController().listarTodos().toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cardapio insereLogin(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Cardapio cardapio = null;
		
		//TESTAR INTEGRIDADE DO JSON
    	if(json != null)
    	{
    		cardapio =  gson.fromJson(json.toString(), Cardapio.class);
    		System.out.println(cardapio);
		    //Adiciona o Cardápio no BD a partir do Controller
		    cardapio = new CardapioController().adicionar(cardapio);
	    }
	    System.out.println(cardapio);
	    return cardapio;
	}
}
