package SGH_Classes.Business;

public class Doador {
	private Doacao doacoes;

	public int hashCode() {
		int lHashCode = 0;
		if ( this.doacoes != null ) {
			lHashCode += this.doacoes.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Doador) {
			Doador lDoadorObject = (Doador) object;
			boolean lEquals = true;
			lEquals &= ((this.doacoes == lDoadorObject.doacoes)
				|| (this.doacoes != null && this.doacoes.equals(lDoadorObject.doacoes)));
			return lEquals;
		}
		return false;
	}
}