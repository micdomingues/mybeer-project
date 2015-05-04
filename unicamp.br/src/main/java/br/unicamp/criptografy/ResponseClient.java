package br.unicamp.criptografy;
import com.google.gson.Gson;

public class ResponseClient 
{
	private String RESPONSE = "RESPONSE";

	public String getRESPONSE() 
	{
		return RESPONSE;
	}

	public void setRESPONSE(String RESPONSE) 
	{
		this.RESPONSE = RESPONSE;
	}
	
	public String parseClassToJSON()
	{
		String res = "";
		Gson json = new Gson();
		res = json.toJson(this);
		return res;
	}
	
}
