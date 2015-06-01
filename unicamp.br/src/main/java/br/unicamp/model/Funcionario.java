package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Funcionario extends Pessoa
{
	private int codbar;
	private String classe;
	
	public int getCodbar() {
		return codbar;
	}

	public void setCodbar(int codbar) {
		this.codbar = codbar;
	}
	
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	@Override
	public String toString() {
		return "Funcionario [codbar=" + codbar + ", classe=" + classe + ", id="
				+ id + ", nome=" + nome + ", sobrenome=" + sobrenome
				+ ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + codbar;
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
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (codbar != other.codbar)
			return false;
		return true;
	}
}
