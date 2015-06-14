package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente extends Pessoa
{
	private String cpf;
	private Float saldopts;

	public Float getSaldopts() {
		return saldopts;
	}

	public void setSaldopts(Float saldopts) {
		this.saldopts = saldopts;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((saldopts == null) ? 0 : saldopts.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (saldopts == null) {
			if (other.saldopts != null)
				return false;
		} else if (!saldopts.equals(other.saldopts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", saldopts=" + saldopts + ", id=" + id
				+ ", nome=" + nome + ", sobrenome=" + sobrenome + ", tipo="
				+ tipo + "]";
	}
	
	
	
}
