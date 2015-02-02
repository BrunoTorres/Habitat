package SGH_Classes.DAO;

import SGH_Classes.Business.Equipa;

public class EquipaDAO {

	public List<Equipa> getEquipasByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public void addVoluntarioEquipa(int idVoluntario, int idEquipa) {
		throw new UnsupportedOperationException();
	}

	public Equipa getEquipa(int idEquipa) {
		throw new UnsupportedOperationException();
	}

	public void remEquipa(int idEquipa) {
		throw new UnsupportedOperationException();
	}

	public void addEquipa(Equipa e) {
		throw new UnsupportedOperationException();
	}

	public void existeEquipa(Equipa e) {
		throw new UnsupportedOperationException();
	}

	public void setEquipa(Equipa e) {
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
		} else if (object instanceof EquipaDAO) {
			EquipaDAO lEquipaDAOObject = (EquipaDAO) object;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}