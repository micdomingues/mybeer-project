package br.unicamp.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensagem
{
	private int codmensagem, idfuncionario;
	private String assunto, nomebar, conteudo, data;
	private boolean lida;
	private ArrayList<Integer> ids;
	
	public ArrayList<Integer> getIds() {
		return ids;
	}

	public void setIds(ArrayList<Integer> ids) {
		this.ids = ids;
	}

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
	
	public String getAssunto()
	{
		return assunto;
	}

	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	public String getNomebar()
	{
		return nomebar;
	}

	public void setNomebar(String nomebar)
	{
		this.nomebar = nomebar;
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
	public String toString() {
		return "Mensagem [codmensagem=" + codmensagem + ", idfuncionario="
				+ idfuncionario + ", assunto=" + assunto + ", nomebar="
				+ nomebar + ", conteudo=" + conteudo + ", data=" + data
				+ ", lida=" + lida + ", ids=" + ids + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + codmensagem;
		result = prime * result
				+ ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idfuncionario;
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		result = prime * result + (lida ? 1231 : 1237);
		result = prime * result + ((nomebar == null) ? 0 : nomebar.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
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
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		if (lida != other.lida)
			return false;
		if (nomebar == null) {
			if (other.nomebar != null)
				return false;
		} else if (!nomebar.equals(other.nomebar))
			return false;
		return true;
	}
}
