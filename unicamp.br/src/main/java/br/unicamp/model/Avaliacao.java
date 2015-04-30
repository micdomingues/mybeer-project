package br.unicamp.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Avaliacao
{
	private int codavaliacao, idcliente, codbar;
	private int preco, qualidade;
	private String comentario;
	private boolean favorito;
	
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
				+ ", qualidade=" + qualidade + ", comentario=" + comentario
				+ ", favorito=" + favorito + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codavaliacao;
		result = prime * result + codbar;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + (favorito ? 1231 : 1237);
		result = prime * result + idcliente;
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
		if (favorito != other.favorito)
			return false;
		if (idcliente != other.idcliente)
			return false;
		if (preco != other.preco)
			return false;
		if (qualidade != other.qualidade)
			return false;
		return true;
	}
}
