package SGH_Classes.Business;

public class Candidatura {
	private Agregado agregado;

	public int hashCode() {
		int lHashCode = 0;
		if ( this.agregado != null ) {
			lHashCode += this.agregado.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Candidatura) {
			Candidatura lCandidaturaObject = (Candidatura) object;
			boolean lEquals = true;
			lEquals &= ((this.agregado == lCandidaturaObject.agregado)
				|| (this.agregado != null && this.agregado.equals(lCandidaturaObject.agregado)));
			return lEquals;
		}
		return false;
	}
}