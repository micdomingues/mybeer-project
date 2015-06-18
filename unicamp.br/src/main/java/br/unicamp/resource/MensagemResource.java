package br.unicamp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.MensagemController;
import br.unicamp.model.Mensagem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/mensagens")
public class MensagemResource
{
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensagem insereLogin(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Mensagem mensagem = null;
		
		//TESTAR INTEGRIDADE DO JSON	    
	    
    	if(json != null)
    	{
    		mensagem =  gson.fromJson(json.toString(), Mensagem.class);

    		System.out.println(json);
    		System.out.println(mensagem.toString());
    		
    		//JODA TIME CORRIGIR DATA
		    //Adiciona o Evento no BD a partir do Controller
		    mensagem = new MensagemController().adicionar(mensagem, mensagem.getIds());
	    }
	    
	    return (mensagem.getCodmensagem() != 0)?mensagem:null;
	}

}
