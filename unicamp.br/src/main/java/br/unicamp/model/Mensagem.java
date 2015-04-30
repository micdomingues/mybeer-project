package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensagem
{
	private int codmensagem, idfuncionario;
	private String conteudo, data;
	private boolean lida;
	
	public int getCodmensagem()
	{
		return codmensagem;
	}
	
	public void setCodmensagem(int codmensagem)
	{
		this.codmensagem = codmensagem;
	}
	
	public int getIdfuncionario()
	{
		return idfuncionario;
	}
	
	public void setIdfuncionario(int idfuncionario)
	{
		this.idfuncionario = idfuncionario;
	}
	
	public String getConteudo()
	{
		return conteudo;
	}
	
	public void setConteudo(String conteudo)
	{
		this.conteudo = conteudo;
	}
	
	public String getData()
	{
		return data;
	}
	
	public void setData(String data)
	{
		this.data = data;
	}
	
	public boolean isLida()
	{
		return lida;
	}
	
	public void setLida(boolean lida)
	{
		this.lida = lida;
	}
	
	@Override
	public String toString()
	{
		return "Mensagem [codmensagem=" + codmensagem + ", idfuncionario="
				+ idfuncionario + ", conteudo=" + conteudo + ", data=" + data
				+ ", lida=" + lida + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + codmensagem;
		result = prime * result
				+ ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idfuncionario;
		result = prime * result + (lida ? 1231 : 1237);
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (codmensagem != other.codmensagem)
			return false;
		if (conteudo == null) {
			if (other.conteudo != null)
				return false;
		} else if (!conteudo.equals(other.conteudo))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idfuncionario != other.idfuncionario)
			return false;
		if (lida != other.lida)
			return false;
		return true;
	}
}
