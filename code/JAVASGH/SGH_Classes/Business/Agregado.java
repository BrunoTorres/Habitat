package SGH_Classes.Business;

public class Agregado {

	public int hashCode() {
		int lHashCode = 0;
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Agregado) {
			Agregado lAgregadoObject = (Agregado) object;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}