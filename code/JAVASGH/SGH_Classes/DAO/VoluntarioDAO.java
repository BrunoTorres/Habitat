package SGH_Classes.DAO;

import SGH_Classes.Business.Voluntario;

public class VoluntarioDAO {

	public List<Voluntario> getVoluntariosByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public Voluntario getVoluntario(int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public boolean existeVoluntario(int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void setVoluntario(Voluntario v) {
		throw new UnsupportedOperationException();
	}

	public void remVoluntario(int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void addVoluntario(Voluntario v) {
		throw new UnsupportedOperationException();
	}

	public boolean existeVoluntario(Voluntario v) {
		throw new UnsupportedOperationException();
	}

	public void setVoluntarioAtivo(int idVoluntario, boolean estado) {
		throw new UnsupportedOperationException();
	}

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
		} else if (object instanceof VoluntarioDAO) {
			VoluntarioDAO lVoluntarioDAOObject = (VoluntarioDAO) object;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}