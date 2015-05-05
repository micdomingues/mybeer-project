package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unicamp.controller.EventoController;
import br.unicamp.model.Evento;

@Path("/evento")
public class EventoResource
{
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Evento> listarTodos(){
		return new EventoController().listarTodos();
	}

}
