package br.unicamp.resource;

import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

import br.unicamp.controller.ClienteController;
import br.unicamp.controller.MensagemController;

@Path("/clientes")
public class ClienteResource
{
	@GET
	@Path("/bares/favoritos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String baresFavoritos(@PathParam("id") String id)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new ClienteController().baresFavoritos(Integer.parseInt(id)).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@GET
	@Path("/promocoes/favoritos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String promocoesFavoritos(@PathParam("id") String id)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new ClienteController().promocoesFavoritos(Integer.parseInt(id)).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@GET
	@Path("/eventos/favoritos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String eventosFavoritos(@PathParam("id") String id)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new ClienteController().eventosFavoritos(Integer.parseInt(id)).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@GET
	@Path("/avaliacoes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarAvaliacoes(@PathParam("id") String id)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new ClienteController().listarAvaliacoes(Integer.parseInt(id)).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@GET
	@Path("/mensagens/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String carregaMensagens(@PathParam("id") String id)
	{
		StringWriter out = new StringWriter();
		JSONArray list = new JSONArray(new ClienteController().todasMensagens(Integer.parseInt(id)).toArray());
		list.write(out);
				
		return out.toString();
	}
	
	@POST
	@Path("/mensagens/lidas")
	@Produces(MediaType.APPLICATION_JSON)
	public String marcaLidas(JSONObject json)
	{
		String id = null;
		try {
			id = json.getString("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(new MensagemController().marcaLidas(Integer.parseInt(id)))
			return "Sucesso";
		else
			return null;
	}
}
