package SGH_Classes.DAO;

import SGH_Classes.Business.Funcionario;

public class FuncionarioDAO {

	public int autenticaUtilizador(String username, String password) {
		throw new UnsupportedOperationException();
	}

	public boolean existeFuncionario(Funcionario f) {
		throw new UnsupportedOperationException();
	}

	public void addFuncionario(Funcionario f) {
		throw new UnsupportedOperationException();
	}

	public Funcionario getFuncionario(int idFuncionario) {
		throw new UnsupportedOperationException();
	}

	public Funcionario remFuncionario(int idFuncionario) {
		throw new UnsupportedOperationException();
	}

	public List<Funcionario> getFuncionariobyName(String nome) {
		throw new UnsupportedOperationException();
	}

	public void setFuncionario(Funcionario f) {
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
		} else if (object instanceof FuncionarioDAO) {
			FuncionarioDAO lFuncionarioDAOObject = (FuncionarioDAO) object;
			boolean lEquals = true;
			return lEquals;
		}
		return false;
	}
}