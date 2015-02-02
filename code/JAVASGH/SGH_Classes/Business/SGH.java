package SGH_Classes.Business;

import SGH_Classes.DAO.FuncionarioDAO;
import SGH_Classes.DAO.CandidaturaDAO;
import SGH_Classes.DAO.MaterialDAO;
import SGH_Classes.DAO.EquipaDAO;
import SGH_Classes.DAO.EventoDAO;
import SGH_Classes.DAO.DoadorDAO;
import SGH_Classes.DAO.ProjetoDAO;
import SGH_Classes.DAO.TarefaDAO;
import SGH_Classes.DAO.DoacaoDAO;
import SGH_Classes.DAO.VoluntarioDAO;

public class SGH {
	private FuncionarioDAO funcionarioDao;
	private CandidaturaDAO candidaturaDao;
	private MaterialDAO materialDao;
	private EquipaDAO equipaDao;
	private EventoDAO eventoDao;
	private DoadorDAO doadorDao;
	private ProjetoDAO projectoDao;
	private TarefaDAO tarefaDao;
	private DoacaoDAO doacaoDao;
	private VoluntarioDAO voluntarioDao;
	private Doador doador;
	private Evento evento;
	private Material material;
	private Projeto projeto;
	private Funcionario funcionario;
	private Doacao doacao;
	private Equipa equipa;
	private Voluntario voluntario;
	private Tarefa tarefa;
	private Candidatura candidatura;

	public List<Doador> getDoadoresByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public Doador getDoador(int idDoador) {
		throw new UnsupportedOperationException();
	}

	public List<Projeto> getProjetos() {
		throw new UnsupportedOperationException();
	}

	public Projeto getProjeto(int idProjeto) {
		throw new UnsupportedOperationException();
	}

	public Evento getEvento(int idEvento) {
		throw new UnsupportedOperationException();
	}

	public void addDoacao(Doador d) {
		throw new UnsupportedOperationException();
	}

	public void remDoador(int idDoador) {
		throw new UnsupportedOperationException();
	}

	public List<Equipa> getEquipasByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public List<Evento> getEventosByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public List<Voluntario> getVoluntariosByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public Voluntario getVoluntario(int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void addVoluntarioEquipa(int idVoluntario, int idEquipa) {
		throw new UnsupportedOperationException();
	}

	public Equipa getEquipa(int idEquipa) {
		throw new UnsupportedOperationException();
	}

	public List<Evento> getEventos() {
		throw new UnsupportedOperationException();
	}

	public void remEquipa(int idEquipa) {
		throw new UnsupportedOperationException();
	}

	public void remEvento(int idEvento) {
		throw new UnsupportedOperationException();
	}

	public List<Projeto> getProjetosByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public void addTarefa(Tarefa t) {
		throw new UnsupportedOperationException();
	}

	public List<Tarefa> getTarefasByName(String nome) {
		throw new UnsupportedOperationException();
	}

	public Tarefa getTarefa(int idTarefa) {
		throw new UnsupportedOperationException();
	}

	public void setTarefa(Tarefa t) {
		this.tarefa = t;
	}

	public void addVoluntarioProjeto(int idProjeto, int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void remTarefa(int idTarefa) {
		throw new UnsupportedOperationException();
	}

	public void remVoluntarioProjeto(int idProjeto, int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void addCandidatura(Candidatura c) {
		throw new UnsupportedOperationException();
	}

	public Candidatura getCandidatura(int nr) {
		throw new UnsupportedOperationException();
	}

	public void setCandidatura(Candidatura c) {
		this.candidatura = c;
	}

	public void setEstadoCandidatura(int nr, int estado) {
		throw new UnsupportedOperationException();
	}

	public void setEstadoOrcamento(int nr, int estado) {
		throw new UnsupportedOperationException();
	}

	public void remCandidatura(int idCandidatura) {
		throw new UnsupportedOperationException();
	}

	public void addProjeto(Projeto p) {
		throw new UnsupportedOperationException();
	}

	public void setEstadoProjeto(int idProjeto, int estado) {
		throw new UnsupportedOperationException();
	}

	public void remProjeto(int idProjeto) {
		throw new UnsupportedOperationException();
	}

	public void addTarefaProjeto(int idTarefa, int idProjeto, boolean concluida, date dataInicial, date dataFinal) {
		throw new UnsupportedOperationException();
	}

	public int autenticaUtilizador(String username, String password) {
		throw new UnsupportedOperationException();
	}

	public void addFuncionario(Funcionario f) {
		throw new UnsupportedOperationException();
	}

	public Funcionario getFuncionario(int idFuncionario) {
		throw new UnsupportedOperationException();
	}

	public void remFuncionario(int idFuncionario) {
		throw new UnsupportedOperationException();
	}

	public boolean existeVoluntario(int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void setVoluntario(Voluntario v) {
		this.voluntario = v;
	}

	public void setProjeto(Projeto p) {
		this.projeto = p;
	}

	public void remVoluntario(int idVoluntario) {
		throw new UnsupportedOperationException();
	}

	public void addDoador(Doador d) {
		throw new UnsupportedOperationException();
	}

	public void setDoador(Doador d) {
		this.doador = d;
	}

	public void addEquipa(Equipa e) {
		throw new UnsupportedOperationException();
	}

	public void setEquipa(Equipa e) {
		this.equipa = e;
	}

	public void addEvento(Evento e) {
		throw new UnsupportedOperationException();
	}

	public void setEvento(Evento e) {
		this.evento = e;
	}

	public void addMaterial(Material m) {
		throw new UnsupportedOperationException();
	}

	public List<Material> getMaterialbyName(String nomeM) {
		throw new UnsupportedOperationException();
	}

	public List<Material> getStock() {
		throw new UnsupportedOperationException();
	}

	public void remStock(int idMaterial, double quant) {
		throw new UnsupportedOperationException();
	}

	public void setMaterial(Material m) {
		this.material = m;
	}

	public void setTarefaProjeto(int idTarefa, int idProjeto, boolean concluida, LocalDate datafinal) {
		throw new UnsupportedOperationException();
	}

	public void addVoluntario(Voluntario v) {
		throw new UnsupportedOperationException();
	}

	public void setVoluntarioAtivo(int idVoluntario, boolean estado) {
		throw new UnsupportedOperationException();
	}

	public List<Funcionario> getFuncionariobyName(String nome) {
		throw new UnsupportedOperationException();
	}

	public void setFuncionario(Funcionario f) {
		this.funcionario = f;
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this.funcionarioDao != null ) {
			lHashCode += this.funcionarioDao.hashCode();
		}
		if ( this.candidaturaDao != null ) {
			lHashCode += this.candidaturaDao.hashCode();
		}
		if ( this.materialDao != null ) {
			lHashCode += this.materialDao.hashCode();
		}
		if ( this.equipaDao != null ) {
			lHashCode += this.equipaDao.hashCode();
		}
		if ( this.eventoDao != null ) {
			lHashCode += this.eventoDao.hashCode();
		}
		if ( this.doadorDao != null ) {
			lHashCode += this.doadorDao.hashCode();
		}
		if ( this.projectoDao != null ) {
			lHashCode += this.projectoDao.hashCode();
		}
		if ( this.tarefaDao != null ) {
			lHashCode += this.tarefaDao.hashCode();
		}
		if ( this.doacaoDao != null ) {
			lHashCode += this.doacaoDao.hashCode();
		}
		if ( this.voluntarioDao != null ) {
			lHashCode += this.voluntarioDao.hashCode();
		}
		if ( this.doador != null ) {
			lHashCode += this.doador.hashCode();
		}
		if ( this.evento != null ) {
			lHashCode += this.evento.hashCode();
		}
		if ( this.material != null ) {
			lHashCode += this.material.hashCode();
		}
		if ( this.projeto != null ) {
			lHashCode += this.projeto.hashCode();
		}
		if ( this.funcionario != null ) {
			lHashCode += this.funcionario.hashCode();
		}
		if ( this.doacao != null ) {
			lHashCode += this.doacao.hashCode();
		}
		if ( this.equipa != null ) {
			lHashCode += this.equipa.hashCode();
		}
		if ( this.voluntario != null ) {
			lHashCode += this.voluntario.hashCode();
		}
		if ( this.tarefa != null ) {
			lHashCode += this.tarefa.hashCode();
		}
		if ( this.candidatura != null ) {
			lHashCode += this.candidatura.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof SGH) {
			SGH lSGHObject = (SGH) object;
			boolean lEquals = true;
			lEquals &= ((this.funcionarioDao == lSGHObject.funcionarioDao)
				|| (this.funcionarioDao != null && this.funcionarioDao.equals(lSGHObject.funcionarioDao)));
			lEquals &= ((this.candidaturaDao == lSGHObject.candidaturaDao)
				|| (this.candidaturaDao != null && this.candidaturaDao.equals(lSGHObject.candidaturaDao)));
			lEquals &= ((this.materialDao == lSGHObject.materialDao)
				|| (this.materialDao != null && this.materialDao.equals(lSGHObject.materialDao)));
			lEquals &= ((this.equipaDao == lSGHObject.equipaDao)
				|| (this.equipaDao != null && this.equipaDao.equals(lSGHObject.equipaDao)));
			lEquals &= ((this.eventoDao == lSGHObject.eventoDao)
				|| (this.eventoDao != null && this.eventoDao.equals(lSGHObject.eventoDao)));
			lEquals &= ((this.doadorDao == lSGHObject.doadorDao)
				|| (this.doadorDao != null && this.doadorDao.equals(lSGHObject.doadorDao)));
			lEquals &= ((this.projectoDao == lSGHObject.projectoDao)
				|| (this.projectoDao != null && this.projectoDao.equals(lSGHObject.projectoDao)));
			lEquals &= ((this.tarefaDao == lSGHObject.tarefaDao)
				|| (this.tarefaDao != null && this.tarefaDao.equals(lSGHObject.tarefaDao)));
			lEquals &= ((this.doacaoDao == lSGHObject.doacaoDao)
				|| (this.doacaoDao != null && this.doacaoDao.equals(lSGHObject.doacaoDao)));
			lEquals &= ((this.voluntarioDao == lSGHObject.voluntarioDao)
				|| (this.voluntarioDao != null && this.voluntarioDao.equals(lSGHObject.voluntarioDao)));
			lEquals &= ((this.doador == lSGHObject.doador)
				|| (this.doador != null && this.doador.equals(lSGHObject.doador)));
			lEquals &= ((this.evento == lSGHObject.evento)
				|| (this.evento != null && this.evento.equals(lSGHObject.evento)));
			lEquals &= ((this.material == lSGHObject.material)
				|| (this.material != null && this.material.equals(lSGHObject.material)));
			lEquals &= ((this.projeto == lSGHObject.projeto)
				|| (this.projeto != null && this.projeto.equals(lSGHObject.projeto)));
			lEquals &= ((this.funcionario == lSGHObject.funcionario)
				|| (this.funcionario != null && this.funcionario.equals(lSGHObject.funcionario)));
			lEquals &= ((this.doacao == lSGHObject.doacao)
				|| (this.doacao != null && this.doacao.equals(lSGHObject.doacao)));
			lEquals &= ((this.equipa == lSGHObject.equipa)
				|| (this.equipa != null && this.equipa.equals(lSGHObject.equipa)));
			lEquals &= ((this.voluntario == lSGHObject.voluntario)
				|| (this.voluntario != null && this.voluntario.equals(lSGHObject.voluntario)));
			lEquals &= ((this.tarefa == lSGHObject.tarefa)
				|| (this.tarefa != null && this.tarefa.equals(lSGHObject.tarefa)));
			lEquals &= ((this.candidatura == lSGHObject.candidatura)
				|| (this.candidatura != null && this.candidatura.equals(lSGHObject.candidatura)));
			return lEquals;
		}
		return false;
	}
}