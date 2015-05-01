package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cardapio
{
	private int codcardapio, idfuncionario;
	private String semanainicio, semanafim;
	private String datainicio, datafim, descricao;

	public int getCodcardapio() {
		return codcardapio;
	}

	public void setCodcardapio(int codcardapio) {
		this.codcardapio = codcardapio;
	}

	public int getIdfuncionario() {
		return idfuncionario;
	}

	public void setIdfuncionario(int idfuncionario) {
		this.idfuncionario = idfuncionario;
	}

	public String getSemanainicio() {
		return semanainicio;
	}

	public void setSemanainicio(String semanainicio) {
		this.semanainicio = semanainicio;
	}

	public String getSemanafim() {
		return semanafim;
	}

	public void setSemanafim(String semanafim) {
		this.semanafim = semanafim;
	}

	public String getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(String datainicio) {
		this.datainicio = datainicio;
	}

	public String getDatafim() {
		return datafim;
	}

	public void setDatafim(String datafim) {
		this.datafim = datafim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString()
	{
		return "Cardapio [codcardapio=" + codcardapio + ", idfuncionario="
				+ idfuncionario + ", semanainicio=" + semanainicio
				+ ", semanafim=" + semanafim + ", datainicio=" + datainicio
				+ ", datafim=" + datafim + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codcardapio;
		result = prime * result + ((datafim == null) ? 0 : datafim.hashCode());
		result = prime * result
				+ ((datainicio == null) ? 0 : datainicio.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idfuncionario;
		result = prime * result
				+ ((semanafim == null) ? 0 : semanafim.hashCode());
		result = prime * result
				+ ((semanainicio == null) ? 0 : semanainicio.hashCode());
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
		Cardapio other = (Cardapio) obj;
		if (codcardapio != other.codcardapio)
			return false;
		if (datafim == null) {
			if (other.datafim != null)
				return false;
		} else if (!datafim.equals(other.datafim))
			return false;
		if (datainicio == null) {
			if (other.datainicio != null)
				return false;
		} else if (!datainicio.equals(other.datainicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idfuncionario != other.idfuncionario)
			return false;
		if (semanafim == null) {
			if (other.semanafim != null)
				return false;
		} else if (!semanafim.equals(other.semanafim))
			return false;
		if (semanainicio == null) {
			if (other.semanainicio != null)
				return false;
		} else if (!semanainicio.equals(other.semanainicio))
			return false;
		return true;
	}	
}
