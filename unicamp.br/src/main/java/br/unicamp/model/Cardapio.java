package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cardapio
{
	private int codcardapio, idfuncionario;
	private Semana semana;
	private String datainicio, datafim, nome, descricao;
	
	public Cardapio()
	{
		semana = null;
	}

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

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Cardapio [codcardapio=" + codcardapio + ", idfuncionario="
				+ idfuncionario + ", semana=" + semana + ", datainicio="
				+ datainicio + ", datafim=" + datafim + ", nome=" + nome
				+ ", descricao=" + descricao + "]";
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((semana == null) ? 0 : semana.hashCode());
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (semana == null) {
			if (other.semana != null)
				return false;
		} else if (!semana.equals(other.semana))
			return false;
		return true;
	}	
}
