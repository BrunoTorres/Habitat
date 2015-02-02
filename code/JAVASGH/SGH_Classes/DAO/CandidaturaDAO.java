package SGH_Classes.DAO;

import SGH_Classes.Business.Candidatura;

public class CandidaturaDAO {

	public void addCandidatura(Candidatura c) {
		throw new UnsupportedOperationException();
	}

	public Candidatura getCandidatura(int nr) {
		throw new UnsupportedOperationException();
	}

	public void setCadidatura(Candidatura c) {
		throw new UnsupportedOperationException();
	}

	public void setEstadoCandidatura(int nr, int estado) {
		throw new UnsupportedOperationException();
	}

	public void__ setEstadoOrcamento(int nr, int estado) {
		throw new UnsupportedOperationException();
	}

	public void__ remCandidatura(int nr) {
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
		} else if (object instanceof CandidaturaDAO) {
			CandidaturaDAO lCandidaturaDAOObject = (CandidaturaDAO) object;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}