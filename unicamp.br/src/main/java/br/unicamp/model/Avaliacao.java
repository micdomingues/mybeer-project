package br.unicamp.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Avaliacao
{
	private int codavaliacao, idcliente, codbar;
	private int preco, qualidade;
	private String nomecliente, nomebar, comentario, data;
	private boolean favorito;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNomecliente() {
		return nomecliente;
	}
	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}
	public String getNomebar() {
		return nomebar;
	}
	public void setNomebar(String nomebar) {
		this.nomebar = nomebar;
	}
	public int getCodavaliacao() {
		return codavaliacao;
	}
	public void setCodavaliacao(int codavaliacao) {
		this.codavaliacao = codavaliacao;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getCodbar() {
		return codbar;
	}
	public void setCodbar(int codbar) {
		this.codbar = codbar;
	}
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		this.preco = preco;
	}
	public int getQualidade() {
		return qualidade;
	}
	public void setQualidade(int qualidade) {
		this.qualidade = qualidade;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	
	@Override
	public String toString() {
		return "Avaliacao [codavaliacao=" + codavaliacao + ", idcliente="
				+ idcliente + ", codbar=" + codbar + ", preco=" + preco
				+ ", qualidade=" + qualidade + ", nomecliente=" + nomecliente
				+ ", nomebar=" + nomebar + ", comentario=" + comentario
				+ ", data=" + data + ", favorito=" + favorito + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codavaliacao;
		result = prime * result + codbar;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (favorito ? 1231 : 1237);
		result = prime * result + idcliente;
		result = prime * result + ((nomebar == null) ? 0 : nomebar.hashCode());
		result = prime * result
				+ ((nomecliente == null) ? 0 : nomecliente.hashCode());
		result = prime * result + preco;
		result = prime * result + qualidade;
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
		Avaliacao other = (Avaliacao) obj;
		if (codavaliacao != other.codavaliacao)
			return false;
		if (codbar != other.codbar)
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (favorito != other.favorito)
			return false;
		if (idcliente != other.idcliente)
			return false;
		if (nomebar == null) {
			if (other.nomebar != null)
				return false;
		} else if (!nomebar.equals(other.nomebar))
			return false;
		if (nomecliente == null) {
			if (other.nomecliente != null)
				return false;
		} else if (!nomecliente.equals(other.nomecliente))
			return false;
		if (preco != other.preco)
			return false;
		if (qualidade != other.qualidade)
			return false;
		return true;
	}
}
