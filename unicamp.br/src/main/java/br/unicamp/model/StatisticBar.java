package br.unicamp.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatisticBar extends Bar
{
	private float mediapreco, mediaqualidade;
	private int favoritos;
	
	public float getMediapreco()
	{
		return mediapreco;
	}

	public void setMediapreco(float mediapreco)
	{
		this.mediapreco = mediapreco;
	}

	public float getMediaqualidade()
	{
		return mediaqualidade;
	}

	public void setMediaqualidade(float mediaqualidade)
	{
		this.mediaqualidade = mediaqualidade;
	}

	public int getFavoritos() 
	{
		return favoritos;
	}

	public void setFavoritos(int favoritos)
	{
		this.favoritos = favoritos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + favoritos;
		result = prime * result + Float.floatToIntBits(mediapreco);
		result = prime * result + Float.floatToIntBits(mediaqualidade);
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
		StatisticBar other = (StatisticBar) obj;
		if (favoritos != other.favoritos)
			return false;
		if (Float.floatToIntBits(mediapreco) != Float
				.floatToIntBits(other.mediapreco))
			return false;
		if (Float.floatToIntBits(mediaqualidade) != Float
				.floatToIntBits(other.mediaqualidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatisticBar [mediapreco=" + mediapreco + ", mediaqualidade="
				+ mediaqualidade + ", favoritos=" + favoritos + ", codbar="
				+ codbar + ", cnpj=" + cnpj + ", nome=" + nome
				+ ", nomefantasia=" + nomefantasia + ", endereco=" + endereco
				+ ", descricao=" + descricao + ", observacao=" + observacao
				+ "]";
	}
}
