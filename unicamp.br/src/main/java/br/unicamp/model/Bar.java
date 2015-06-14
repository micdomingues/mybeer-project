package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bar
{
	protected int codbar;
	protected String cnpj;
	protected String nome, nomefantasia;
	
	public int getCodbar()
	{
		return codbar;
	}
	public void setCodbar(int codbar)
	{
		this.codbar = codbar;
	}
	public String getCnpj()
	{
		return cnpj;
	}
	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getNomefantasia()
	{
		return nomefantasia;
	}
	public void setNomefantasia(String nomefantasia)
	{
		this.nomefantasia = nomefantasia;
	}
	
	@Override
	public String toString()
	{
		return "Bar [codbar=" + codbar + ", cnpj=" + cnpj + ", nome=" + nome
				+ ", nomefantasia=" + nomefantasia + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + codbar;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomefantasia == null) ? 0 : nomefantasia.hashCode());
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
		Bar other = (Bar) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (codbar != other.codbar)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomefantasia == null) {
			if (other.nomefantasia != null)
				return false;
		} else if (!nomefantasia.equals(other.nomefantasia))
			return false;
		return true;
	}
}
