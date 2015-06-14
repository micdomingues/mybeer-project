package br.unicamp.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unicamp.controller.ClienteController;
import br.unicamp.model.Mensagem;
import br.unicamp.model.StatisticBar;

@Path("/clientes")
public class ClienteResource
{
	@GET
	@Path("/bares/favoritos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<StatisticBar> baresFavoritos(@PathParam("id") String id)
	{
		return new ClienteController().baresFavoritos(Integer.parseInt(id));
	}
	
	@GET
	@Path("/mensagens/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Mensagem> carregaMensagens(@PathParam("id") String id)
	{
		return new ClienteController().todasMensagens(Integer.parseInt(id));
	}
}
