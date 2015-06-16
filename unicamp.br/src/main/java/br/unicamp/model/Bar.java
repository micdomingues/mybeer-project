package br.unicamp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bar
{
	protected int codbar;
	protected String cnpj;
	protected String nome, nomefantasia;
	protected String endereco, descricao, observacao;
	
	public int getCodbar()
	{
		return codbar;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
	public String toString() {
		return "Bar [codbar=" + codbar + ", cnpj=" + cnpj + ", nome=" + nome
				+ ", nomefantasia=" + nomefantasia + ", endereco=" + endereco
				+ ", descricao=" + descricao + ", observacao=" + observacao
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + codbar;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomefantasia == null) ? 0 : nomefantasia.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
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
		Bar other = (Bar) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (codbar != other.codbar)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
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
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		return true;
	}
}
