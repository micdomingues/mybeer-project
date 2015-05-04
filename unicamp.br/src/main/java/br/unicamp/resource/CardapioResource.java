package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.CardapioController;
import br.unicamp.model.Cardapio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/cardapio")
public class CardapioResource
{
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cardapio> listarTodos(){
		return new CardapioController().listarTodos();
	}
	
	@POST
	@Path("/insereCardapio")
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

    		System.out.println(json);
    		System.out.println(cardapio.toString());
		    
		    //Adiciona o Cardápio no BD a partir do Controller
		    cardapio = new CardapioController().adicionar(cardapio);
	    }
	    
	    if(cardapio != null)
	    {	    	
		    return cardapio;
	    }
	    else
	    {
	    	return null;
	    }
	}
}
