package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Funcionario extends Pessoa
{
	private int id;
	private char tipo;
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", tipo=" + tipo + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + tipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id != other.id)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	

}
