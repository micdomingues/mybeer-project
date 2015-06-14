package br.unicamp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.unicamp.controller.ClienteController;
import br.unicamp.controller.LancamentoController;
import br.unicamp.model.Cliente;
import br.unicamp.model.Lancamento;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/lancamentos")
public class LancamentoResource
{	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lancamento insereLancamento(JSONObject json)
	{
		Gson gson = new GsonBuilder().create();
	    
		Lancamento lancamento = null;
		Cliente cliente = null;
		String cpf = null;
		
		//TESTAR INTEGRIDADE DO JSON
    	if(json != null)
    	{
    		try
    		{
				cpf = json.getString("cpf");
			}
    		catch (JSONException e)
    		{
    			System.out.println("Erro ao obter CPF do json: " + e);
				e.printStackTrace();
			}
    		
    		cliente = new Cliente();
    		cliente.setCpf(cpf);
    		
    		cliente = new ClienteController().consultaCPF(cliente);
    		if(cliente != null)
    		{
    			lancamento =  gson.fromJson(json.toString(), Lancamento.class);
    			//Adiciona o Lancamento no BD a partir do Controller
    			lancamento.setIdcliente(cliente.getId());
		    	lancamento = new LancamentoController().adicionar(lancamento);
    		}		    
	    }
	    
	    return lancamento;
	}

}
