package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.CardapioController;
import br.unicamp.model.Cardapio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/cardapios")
public class CardapioResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cardapio> listarTodos(){
		return new CardapioController().listarTodos();
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
    		
		    //Adiciona o Cardápio no BD a partir do Controller
		    cardapio = new CardapioController().adicionar(cardapio);
	    }
	    
	    return cardapio;
	}
}
