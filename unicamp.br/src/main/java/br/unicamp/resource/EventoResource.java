package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.EventoController;
import br.unicamp.model.Evento;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/eventos")
public class EventoResource
{
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Evento insereLogin(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Evento evento = null;
		
		//TESTAR INTEGRIDADE DO JSON	    
	    
    	if(json != null)
    	{
    		evento =  gson.fromJson(json.toString(), Evento.class);

    		System.out.println(json);
    		System.out.println(evento.toString());
    		
    		//JODA TIME CORRIGIR DATA
		    //Adiciona o Evento no BD a partir do Controller
		    evento = new EventoController().adicionar(evento);
	    }
	    
	    if(evento != null)
	    {	    	
		    return evento;
	    }
	    else
	    {
	    	return null;
	    }
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Evento> listarTodos(){
		return new EventoController().listarTodos();
	}

}
