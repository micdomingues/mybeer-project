package br.unicamp.resource;

import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import br.unicamp.controller.PromocaoController;

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

}
