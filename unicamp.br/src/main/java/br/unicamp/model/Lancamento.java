package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Lancamento
{
	private int codlancamento, idfuncionario, idcliente;
	private float valor;
	private String nomefuncionario, nomecliente, data;
	
	public int getCodlancamento()
	{
		return codlancamento;
	}
	
	public String getNomefuncionario() {
		return nomefuncionario;
	}

	public void setNomefuncionario(String nomefuncionario) {
		this.nomefuncionario = nomefuncionario;
	}

	public String getNomecliente() {
		return nomecliente;
	}

	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}

	public void setCodlancamento(int codlancamento)
	{
		this.codlancamento = codlancamento;
	}
	
	public int getIdfuncionario()
	{
		return idfuncionario;
	}
	
	public void setIdfuncionario(int idfuncionario)
	{
		this.idfuncionario = idfuncionario;
	}
	
	public int getIdcliente()
	{
		return idcliente;
	}
	
	public void setIdcliente(int idcliente)
	{
		this.idcliente = idcliente;
	}
	
	public float getValor()
	{
		return valor;
	}
	
	public void setValor(float valor)
	{
		this.valor = valor;
	}
	
	public String getData()
	{
		return data;
	}
	
	public void setData(String data)
	{
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codlancamento;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idcliente;
		result = prime * result + idfuncionario;
		result = prime * result
				+ ((nomecliente == null) ? 0 : nomecliente.hashCode());
		result = prime * result
				+ ((nomefuncionario == null) ? 0 : nomefuncionario.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		Lancamento other = (Lancamento) obj;
		if (codlancamento != other.codlancamento)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idcliente != other.idcliente)
			return false;
		if (idfuncionario != other.idfuncionario)
			return false;
		if (nomecliente == null) {
			if (other.nomecliente != null)
				return false;
		} else if (!nomecliente.equals(other.nomecliente))
			return false;
		if (nomefuncionario == null) {
			if (other.nomefuncionario != null)
				return false;
		} else if (!nomefuncionario.equals(other.nomefuncionario))
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [codlancamento=" + codlancamento
				+ ", idfuncionario=" + idfuncionario + ", idcliente="
				+ idcliente + ", valor=" + valor + ", nomefuncionario="
				+ nomefuncionario + ", nomecliente=" + nomecliente + ", data="
				+ data + "]";
	}
}
