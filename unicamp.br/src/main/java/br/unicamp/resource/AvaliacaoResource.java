package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unicamp.controller.AvaliacaoController;
import br.unicamp.model.Avaliacao;

@Path("/avaliacoes")
public class AvaliacaoResource
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Avaliacao> listarTodos(){
		return new AvaliacaoController().listarTodos();
	}

}
