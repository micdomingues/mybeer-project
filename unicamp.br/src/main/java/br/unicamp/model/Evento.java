package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Evento
{
	private int codevento, codbar;
	private String nome, nomebar, descricao, data;
	
	public String getNomebar(){
		return nomebar;
	}

	public void setNomebar(String nomebar) {
		this.nomebar = nomebar;
	}

	private String linkevento, linkimagem;
	
	public int getCodevento() {
		return codevento;
	}

	public void setCodevento(int codevento) {
		this.codevento = codevento;
	}

	public int getCodbar() {
		return codbar;
	}

	public void setCodbar(int codbar) {
		this.codbar = codbar;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLinkevento() {
		return linkevento;
	}

	public void setLinkevento(String linkevento) {
		this.linkevento = linkevento;
	}

	public String getLinkimagem() {
		return linkimagem;
	}

	public void setLinkimagem(String linkimagem) {
		this.linkimagem = linkimagem;
	}

	@Override
	public String toString() {
		return "Evento [codevento=" + codevento + ", codbar=" + codbar
				+ ", nome=" + nome + ", nomebar=" + nomebar + ", descricao="
				+ descricao + ", data=" + data + ", linkevento=" + linkevento
				+ ", linkimagem=" + linkimagem + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codbar;
		result = prime * result + codevento;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((linkevento == null) ? 0 : linkevento.hashCode());
		result = prime * result
				+ ((linkimagem == null) ? 0 : linkimagem.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Evento other = (Evento) obj;
		if (codbar != other.codbar)
			return false;
		if (codevento != other.codevento)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (linkevento == null) {
			if (other.linkevento != null)
				return false;
		} else if (!linkevento.equals(other.linkevento))
			return false;
		if (linkimagem == null) {
			if (other.linkimagem != null)
				return false;
		} else if (!linkimagem.equals(other.linkimagem))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomebar == null) {
			if (other.nomebar != null)
				return false;
		} else if (!nomebar.equals(other.nomebar))
			return false;
		return true;
	}
}
