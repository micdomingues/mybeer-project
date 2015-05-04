package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Promocao
{
	private int codpromocao, codbar, idfuncionario;
	private String dataabertura, datainicio, datafim;
	private char tipo;
	private String descricao;
	
	public int getCodpromocao() {
		return codpromocao;
	}
	public void setCodpromocao(int codpromocao) {
		this.codpromocao = codpromocao;
	}
	public int getCodbar() {
		return codbar;
	}
	public void setCodbar(int codbar) {
		this.codbar = codbar;
	}
	public int getIdfuncionario() {
		return idfuncionario;
	}
	public void setIdfuncionario(int idfuncionario) {
		this.idfuncionario = idfuncionario;
	}
	public String getDataabertura() {
		return dataabertura;
	}
	public void setDataabertura(String dataabertura) {
		this.dataabertura = dataabertura;
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
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
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
		return "Promocao [codpromocao=" + codpromocao + ", codbar=" + codbar
				+ ", idfuncionario=" + idfuncionario + ", dataabertura="
				+ dataabertura + ", datainicio=" + datainicio + ", datafim="
				+ datafim + ", tipo=" + tipo + ", descricao=" + descricao + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + codbar;
		result = prime * result + codpromocao;
		result = prime * result
				+ ((dataabertura == null) ? 0 : dataabertura.hashCode());
		result = prime * result + ((datafim == null) ? 0 : datafim.hashCode());
		result = prime * result
				+ ((datainicio == null) ? 0 : datainicio.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idfuncionario;
		result = prime * result + tipo;
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
		Promocao other = (Promocao) obj;
		if (codbar != other.codbar)
			return false;
		if (codpromocao != other.codpromocao)
			return false;
		if (dataabertura == null) {
			if (other.dataabertura != null)
				return false;
		} else if (!dataabertura.equals(other.dataabertura))
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
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
