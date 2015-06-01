package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unicamp.controller.PromocaoController;
import br.unicamp.model.Promocao;

@Path("/promocoes")
public class PromocaoResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Promocao> listarTodos(){
		return new PromocaoController().listarTodos();
	}

}
