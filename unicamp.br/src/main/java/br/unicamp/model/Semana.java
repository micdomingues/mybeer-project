package br.unicamp.model;

public class Semana
{
	private boolean segunda, terca, quarta, quinta, sexta, sabado, domingo;

	public boolean isSegunda() {
		return segunda;
	}

	public void setSegunda(boolean segunda) {
		this.segunda = segunda;
	}

	public boolean isTerca() {
		return terca;
	}

	public void setTerca(boolean terca) {
		this.terca = terca;
	}

	public boolean isQuarta() {
		return quarta;
	}

	public void setQuarta(boolean quarta) {
		this.quarta = quarta;
	}

	public boolean isQuinta() {
		return quinta;
	}

	public void setQuinta(boolean quinta) {
		this.quinta = quinta;
	}

	public boolean isSexta() {
		return sexta;
	}

	public void setSexta(boolean sexta) {
		this.sexta = sexta;
	}

	public boolean isSabado() {
		return sabado;
	}

	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}

	public boolean isDomingo() {
		return domingo;
	}

	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}

	@Override
	public String toString() {
		return "Semana [segunda=" + segunda + ", terca=" + terca + ", quarta="
				+ quarta + ", quinta=" + quinta + ", sexta=" + sexta
				+ ", sabado=" + sabado + ", domingo=" + domingo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (domingo ? 1231 : 1237);
		result = prime * result + (quarta ? 1231 : 1237);
		result = prime * result + (quinta ? 1231 : 1237);
		result = prime * result + (sabado ? 1231 : 1237);
		result = prime * result + (segunda ? 1231 : 1237);
		result = prime * result + (sexta ? 1231 : 1237);
		result = prime * result + (terca ? 1231 : 1237);
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
		Semana other = (Semana) obj;
		if (domingo != other.domingo)
			return false;
		if (quarta != other.quarta)
			return false;
		if (quinta != other.quinta)
			return false;
		if (sabado != other.sabado)
			return false;
		if (segunda != other.segunda)
			return false;
		if (sexta != other.sexta)
			return false;
		if (terca != other.terca)
			return false;
		return true;
	}
	
	public boolean isEmpty()
	{
		if(isSegunda())
			return false;
		if(isTerca())
			return false;
		if(isQuarta())
			return false;
		if(isQuinta())
			return false;
		if(isSexta())
			return false;
		if(isSabado())
			return false;
		if(isDomingo())
			return false;
		
		return true;
	}	
}
