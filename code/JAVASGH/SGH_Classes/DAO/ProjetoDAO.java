package SGH_Classes.DAO;

import SGH_Classes.Business.Projeto;

public class ProjetoDAO {

	public List<Projeto> getProjetos() {
		throw new UnsupportedOperationException();
	}

	public List<Projeto> getProjetosByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public void addProjeto(Projeto p) {
		throw new UnsupportedOperationException();
	}

	public Projeto getProjeto(int idProjeto) {
		throw new UnsupportedOperationException();
	}

	public void__ setEstadoProjeto(int idProjeto, int estado) {
		throw new UnsupportedOperationException();
	}

	public void remProjeto(int idProjeto) {
		throw new UnsupportedOperationException();
	}

	public void addVoluntarioProjeto(int idProjeto, int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void remVoluntarioProjeto(int idProjeto, int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void setProjeto(Projeto p) {
		throw new UnsupportedOperationException();
	}

	public void__ addTarefaProjeto(int idTarefa, int idProjeto, boolean concluida, LocalDate dataInicial, LocalDate dataFinal) {
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
		} else if (object instanceof ProjetoDAO) {
			ProjetoDAO lProjetoDAOObject = (ProjetoDAO) object;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}