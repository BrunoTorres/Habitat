package SGH_Classes.Business;

public class Voluntario {
	private Equipa equipas;

	public int hashCode() {
		int lHashCode = 0;
		if ( this.equipas != null ) {
			lHashCode += this.equipas.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Voluntario) {
			Voluntario lVoluntarioObject = (Voluntario) object;
			boolean lEquals = true;
			lEquals &= ((this.equipas == lVoluntarioObject.equipas)
				|| (this.equipas != null && this.equipas.equals(lVoluntarioObject.equipas)));
			return lEquals;
		}
		return false;
	}
}