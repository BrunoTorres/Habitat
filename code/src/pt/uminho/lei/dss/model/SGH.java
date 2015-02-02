package pt.uminho.lei.dss.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.uminho.lei.dss.db.CandidaturaDAO;
import pt.uminho.lei.dss.db.DoacaoDAO;
import pt.uminho.lei.dss.db.DoadorDAO;
import pt.uminho.lei.dss.db.EquipaDAO;
import pt.uminho.lei.dss.db.EventoDAO;
import pt.uminho.lei.dss.db.FuncionarioDAO;
import pt.uminho.lei.dss.db.MaterialDAO;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.db.ProjectoDAO;
import pt.uminho.lei.dss.db.TarefaDAO;
import pt.uminho.lei.dss.db.VoluntarioDAO;

public class SGH {

    private FuncionarioDAO funcionarioDao;
    private CandidaturaDAO candidaturaDao;
    private MaterialDAO materialDao;
    private EquipaDAO equipaDao;
    private EventoDAO eventoDao;
    private DoadorDAO doadorDao;
    private ProjectoDAO projectoDao;
    private TarefaDAO tarefaDao;
    private DoacaoDAO doacaoDao;
    private VoluntarioDAO voluntarioDao;
    private Connection c;
    private Funcionario f;

    /**
     *
     */
    public SGH() {
        this.funcionarioDao = new FuncionarioDAO();
        this.candidaturaDao = new CandidaturaDAO();
        this.materialDao = new MaterialDAO();
        this.equipaDao = new EquipaDAO();
        this.eventoDao = new EventoDAO();
        this.doadorDao = new DoadorDAO();
        this.projectoDao = new ProjectoDAO();
        this.tarefaDao = new TarefaDAO();
        this.doacaoDao = new DoacaoDAO();
        this.voluntarioDao = new VoluntarioDAO();
        try {
            this.c = doacaoDao.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SGH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devolve lista dos doadores que contenham o nome introduzido
     *
     * @param nome Nome do doador a pesquisar
     * @return Lista de Doadores
     * @throws PersistenceException
     */
    public List<Doador> getDoadoresByName(String nome) throws PersistenceException {
        return doadorDao.getDoadoresByName(c, nome);

    }

    /**
     * Devolve um doador com o id introduzido
     *
     * @param idDoador id do doador que se pretende pesquisar
     * @return doador com o id introduzido
     * @throws PersistenceException
     */
    public Doador getDoador(int idDoador) throws PersistenceException {
        return this.doadorDao.getDoador(c, idDoador);
    }

    /**
     * Devolve lista dos doadores que registados no sistema
     *
     * @return Lista de doadores
     * @throws PersistenceException
     */
    public List<Doador> getDoadores() throws PersistenceException {
        return this.doadorDao.getDoadores(c);
    }

    /**
     * Devolve lista dos projetos registados no sistema
     *
     * @return Lista de projetos
     * @throws PersistenceException
     */
    public List<Projecto> getProjetos() throws PersistenceException {
        return this.projectoDao.getProjectos(c);

    }

    
    /**
     * Devolve um projeto com o id introduzido
     *
     * @param idProjeto id do projeto que se pretende pesquisar
     * @return projeto com o id introduzido
     * @throws PersistenceException
     */
    public Projecto getProjeto(int idProjeto) throws PersistenceException {
        return this.projectoDao.getProjecto(c, idProjeto);
    }

    
    /**
     * Devolve um evento com o id introduzido
     *
     * @param idEvento id do evento que se pretende pesquisar
     * @return evento com o id introduzido
     * @throws PersistenceException
     */
    public Evento getEvento(int idEvento) throws PersistenceException {
        return eventoDao.getEvento(c, idEvento);
    }

    
    /**
     * Adiciona uma nova doação ao sistemna
     *
     * @param d Doação que se pretende adicionar
     * @throws PersistenceException
     */
    public void addDoacao(Doacao d) throws PersistenceException {
        this.doacaoDao.addDoacao(c, d);
    }

    
    /**
     * Remove um doador com o id introduzido
     *
     * @param idDoador id do doador a remover
     * @return true caso o doador seja removido com sucesso, false caso contrário
     * @throws PersistenceException
     */
    public boolean remDoador(int idDoador) throws PersistenceException {
        return doadorDao.remDoador(c, idDoador);
    }

    
    /**
     * Devolve uma lista das equipas cujo nome contenha o nome introduzido
     *
     * @param nome Nome das equipas a pesquisar
     * @return Lista das equipas cujo nome contem o nome introduzido
     * @throws PersistenceException
     */
    public List<Equipa> getEquipasByName(String nome) throws PersistenceException {
        return equipaDao.getEquipasByName(c, nome);
    }

    /**
     * Devolve uma lista dos eventos cujo nome contenha o nome introduzido
     *
     * @param nome Nome dos eventos a pesquisar
     * @return Lista dos eventos cujo nome contem o nome introduzido
     * @throws PersistenceException
     */
    public List<Evento> getEventosByName(String nome) throws PersistenceException {
        return this.eventoDao.getEventosByName(c, nome);
    }

    /**
     * Devolve uma lista dos voluntários cujo nome contenha o nome introduzido
     *
     * @param nome Nome dos voluntários a pesquisar
     * @return Lista dos funcionários cujo nome contem o nome introduzido
     * @throws PersistenceException
     */
    public List<Voluntario> getVoluntariosByName(String nome) throws PersistenceException {
        return this.voluntarioDao.getVoluntariosByName(c, nome);
    }

    /**
     * Devolve uma lista dos voluntários com os voluntários registados no sistema
     *
     * @return Lista de todos os voluntários do sistema
     * @throws PersistenceException
     */
    public List<Voluntario> getVoluntarios() throws PersistenceException {
        return voluntarioDao.getVoluntarios(c);
    }

    /**
     * Devolve um voluntário com o id correspondente ao id introduzido
     *
     * @param idVoluntario  id do voluntário a pesquisar
     * @return Voluntário cujo id corresponde ao id introduzido
     * @throws PersistenceException
     */
    public Voluntario getVoluntario(int idVoluntario) throws PersistenceException {
        return voluntarioDao.getVoluntario(c, idVoluntario);
    }

    /**
     * Associa um voluntário com id igual a idVoluntario a uma equipa com id igual a idEquipa
     *
     * @param idVoluntario id do voluntário a ser associado à equipa
     * @param idEquipa id da equipa à qual o voluntário será associado
     * @throws PersistenceException
     */
    public void addVoluntarioEquipa(int idVoluntario, int idEquipa) throws PersistenceException {
        equipaDao.addVoluntarioEquipa(c, idVoluntario, idEquipa);
    }

    /**
     * Devolve uma equipa com o id correspondente ao id introduzido
     *
     * @param idEquipa  id da equipa a pesquisar
     * @return Equipa cujo id corresponde ao id introduzido
     * @throws PersistenceException
     */
    public Equipa getEquipa(int idEquipa) throws PersistenceException {
        return equipaDao.getEquipa(c, idEquipa);
    }

    /**
     * Devolve lista dos eventos registados no sistema
     *
     * @return Lista de eventos
     * @throws PersistenceException
     */
    public List<Evento> getEventos() throws PersistenceException {
        return this.eventoDao.getEventos(c);
    }

    /**
     * Remove uma equipa com id igual a idEquipa
     *
     * @param idEquipa id da equipa a ser removida
     * @throws PersistenceException
     */
    public void remEquipa(int idEquipa) throws PersistenceException {
        equipaDao.remEquipa(c, idEquipa);
    }

    /**
     * Remove uma equip evento com id igual a idEquipavento
     *
     * @param idEvento id do evento a ser removida
     * @return true se evento for removido com sucesso, false caso contrário
     */
    public boolean remEvento(int idEvento) {
        return eventoDao.remEvento(c, idEvento);
    }

    /**
     * Devolve lista de projetos do sistema com um dado nome
     * 
     * @param nome nome do projeto a pesquisar
     * @return lista de projetos
     * @throws PersistenceException
     */
    public List<Projecto> getProjetosByName(String nome) throws PersistenceException {
        return projectoDao.getProjectosByName(c, nome);
    }

    /**
     * Adiciona uma tarefa ao sistema
     * 
     * @param t tarefa a adicionar
     * @throws PersistenceException
     */
    public void addTarefa(Tarefa t) throws PersistenceException {
        tarefaDao.addTarefa(c, t);
    }

    /**
     * Devolve lista de tarefas do sistema com um dado nome
     * @param nome nome da tarefa a pesquisar
     * @return lista de tarefas
     * @throws PersistenceException
     */
    public List<Tarefa> getTarefasByName(String nome) throws PersistenceException {
        return tarefaDao.getTarefasByName(c, nome);
    }

    /**
     * Devolve uma tarefa com o id correspondente ao id introduzido
     *
     * @param idTarefa id da tarefa a pesquisar
     * @return Tarefa cujo id corresponde ao id introduzido
     * @throws PersistenceException
     */
    public Tarefa getTarefa(int idTarefa) throws PersistenceException {
        return tarefaDao.getTarefa(c, idTarefa);
    }

    /**
     * Devolve a tarefa que se quer pesquisar
     * @param nTarefa nome da tarefa
     * @return Tarefa
     * @throws PersistenceException
     */
    public Tarefa getTarefaByName(String nTarefa) throws PersistenceException {
        return tarefaDao.getTarefaByName(c, nTarefa);
    }

    /**
     * Altera uma dada tarefa
     * @param t tarefa a altera
     * @throws PersistenceException
     */
    public void setTarefa(Tarefa t) throws PersistenceException {
        tarefaDao.setTarefa(c, t);
    }

   
    /**
     * Altera uma dada tarefa associada a um projeto
     * @param t tarefa a alterar
     * @throws PersistenceException
     */
    public void setTarefaProjecto(Tarefa t) throws PersistenceException {
        tarefaDao.setTarefaProjecto(c, t);
    }

    /**
     * Adiciona um dado voluntário a um dado projeto
     * @param idProjeto id do projeto que se vai associar o voluntario
     * @param idVoluntario ido do voluntário que se vai associar ao projeto
     * @throws PersistenceException
     */
    public void addVoluntarioProjeto(int idProjeto, int idVoluntario) throws PersistenceException {
        projectoDao.addVoluntarioProjecto(c, idProjeto, idVoluntario);
    }

   
    
    /**
     * Remove uma tarefa com id igual a idTarefa de um projeto com id igual a idProjecto
     *
     * @param idTarefa id da equipa a ser removida do projeto
     * @param idProjecto id do projeto ao qual a tarefa pertencia
     * @throws PersistenceException
     */
    public void remTarefaProjecto(int idTarefa, int idProjecto) throws PersistenceException {
        tarefaDao.remTarefaProjecto(c, idTarefa, idProjecto);
    }

    /**
     * Remove um voluntário com id igual a idVoluntario de um projeto com id igual a idProjeto
     *
     * @param idVoluntario id da equipa a ser removida do projeto
     * @param idProjeto id do projeto ao qual a tarefa pertencia
     * @throws PersistenceException
     */
    public void remVoluntarioProjeto(int idProjeto, int idVoluntario) throws PersistenceException {
        projectoDao.remVoluntarioProjecto(c, idProjeto, idVoluntario);
    }

    /**
     * Adiciona uma candidatura ao sistema
     * @param cand candidatura que se vai adicinar
     * @throws PersistenceException
     */
    public void addCandidatura(Candidatura cand) throws PersistenceException {
        candidaturaDao.addCandidatura(c, cand);
    }
    

    /**
     * Adiciona um agregado
     * @param a Agregado a adicionar
     * @throws PersistenceException
     */
    public void addAgregado(Agregado a) throws PersistenceException, SQLException {
        candidaturaDao.addAgregado(c, a);
    }

    /**
     * Altera agregado 
     * @param a agregado a alterar
     * @throws PersistenceException
     */
    public void setAgregado(Agregado a) throws PersistenceException {
        candidaturaDao.setAgregado(c, a);
    }

    /**
     * Remove uma tarefa com id igual a idTarefa de um projeto com id igual a idProjecto
     *
     * @param nif nif do elemento do agregado a ser removido
     * @throws PersistenceException
     */
    public void remAgregado(int nif) throws PersistenceException {
        candidaturaDao.remAgregado(c, nif);
    }

    /**
     * Devolve todas as candidaturas que não tem projetos associados
     * @return lista dos numeros das candidaturas
     * @throws PersistenceException
     */
    public List<Integer> getCandidaturasSemProjeto() throws PersistenceException {
        return candidaturaDao.getCandidaturasSemProjeto(c);
    }

    /**
     * Devolve os voluntarios associados a uma equipa
     * @param idEquipa
     * @return lista de voluntarios
     * @throws PersistenceException
     */
    public ArrayList<Voluntario> getEquipaVoluntarios(int idEquipa) throws PersistenceException {
        return equipaDao.getEquipaVoluntarios(c, idEquipa);
    }

    /**
     * Devolve o id do país com o nome igual ao nome introduzido
     *
     * @param nome  no do país a pesquisar
     * @return id do país cujo nome corresponde ao nome introduzido
     * @throws PersistenceException
     */
    public int getPaisId(String nome) throws PersistenceException {
        return equipaDao.getPaisId(c, nome);
    }

    /**
     * Devolve lista dos nomes dos países registados no sistema
     *
     * @return Lista de países
     * @throws PersistenceException
     */
    public ArrayList<String> getPaises() throws PersistenceException {
        return equipaDao.getPaises(c);
    }

    /**
     * Devolve o id da equipa a que o voluntario pertence no momento
     * @param idvol ido do voluntario
     * @return id da equipa
     * @throws PersistenceException
     */
    public int getEquipaActiva(int idvol) throws PersistenceException {
        return equipaDao.getEquipaActiva(c, idvol);

    }

    /**
     * Devolve lista das equipas do voluntario
     * @param idVoluntario ido do voluntario
     * @return lista de equipas
     * @throws PersistenceException
     */
    public ArrayList<Equipa> getEquipaVoluntario(int idVoluntario) throws PersistenceException {
        return voluntarioDao.getEquipaVoluntario(c, idVoluntario);
    }

    /**
     * Torna inativa uma equipa do voluntario.
     * @param idvol id do voluntario
     * @throws PersistenceException
     */
    public void desativaEquipaActiva(int idvol) throws PersistenceException {
        equipaDao.desativaEquipaActiva(c, idvol);
    }

    /**
     * Devolve o documento de uma candidatura
     * @param idCandidatura nr da candidatura
     * @return  documento
     * @throws PersistenceException
     */
    public InputStream getCandidaturaDocumento(int idCandidatura) throws PersistenceException {
        return candidaturaDao.getCandidaturaDocumento(c, idCandidatura);
    }

    /**
     * Devolve a existencia de um documento numa candidatura
     * @param idCandidatura id da candidatura
     * @return verdadeiro se existe, falso o contrario
     * @throws PersistenceException
     */
    public boolean existeCandidaturaDocumento(int idCandidatura) throws PersistenceException {
        return candidaturaDao.existeCandidatura(c, idCandidatura);
    }
    

    /**
     * Devolve uma candidatura com o número correspondente ao número introduzido
     *
     * @param nr  número da candidatura a pesquisar
     * @return candidatura cujo número corresponde ao número introduzido
     * @throws PersistenceException
     */
    public Candidatura getCandidatura(int nr) throws PersistenceException {
        return candidaturaDao.getCandidatura(c, nr);
    }

    /**
     * Devolve todas as cadidaturas no sitema
     * @return lista de candidaturas
     * @throws PersistenceException
     */
    public List<Candidatura> getCandidaturas() throws PersistenceException {
        return candidaturaDao.getCandidaturas(c);
    }

    /**
     * Altera uma candidatura
     * @param cand  candidatura a alterar
     * @throws PersistenceException
     */
    public void setCandidatura(Candidatura cand) throws PersistenceException {
        candidaturaDao.setCandidatura(c, cand);
    }

    /**
     * Alterar o estado de uma candidatura
     * @param nr numero da candidatura
     * @param estado novo estado 
     * @throws PersistenceException
     */
    public void setEstadoCandidatura(int nr, int estado) throws PersistenceException {
        candidaturaDao.setEstadoCandidatura(c, nr, estado);
    }

    /**
     * Alterar orcaamento
     * @param nr numero da candidatura
     * @param estado novo orcamento
     * @throws PersistenceException
     */
    public void setEstadoOrcamento(int nr, int estado) throws PersistenceException {
        candidaturaDao.setEstadoOrcamento(c, nr, estado);
    }

    /**
     * Remove uma candidatura com id igual a idCandidatura
     *
     * @param idCandidatura id da candidatura a ser removida
     * @return true se a candidatura for removida com sucesso, false caso contrário
     * @throws PersistenceException
     */
    public boolean remCandidatura(int idCandidatura) throws PersistenceException {
        return candidaturaDao.remCandidatura(c, idCandidatura);
    }

    /**
     * Adiciona um projeto ao sistema
     * @param p projeto a adicionar
     * @throws PersistenceException
     */
    public void addProjeto(Projecto p) throws PersistenceException {
        projectoDao.addProjecto(c, p);
    }

    /**
     * Alterar estado do projeto
     * @param idProjeto ido do projeto a alterar
     * @param estado novo estado do projeto
     * @throws PersistenceException
     */
    public void setEstadoProjeto(int idProjeto, int estado) throws PersistenceException {
        projectoDao.setEstadoProjecto(c, idProjeto, estado);
    }

    /**
     * Remove projeto com id igual a idProjeto
     *
     * @param idProjeto id do projeto a ser removido
     * @return true se o projeto for removidocom sucesso, false caso contrário
     * @throws PersistenceException
     */
    public boolean remProjeto(int idProjeto) throws PersistenceException {
        return projectoDao.remProjecto(c, idProjeto);
    }

    /**
     * Adiciona uma tarefa a um projeto
     * @param idTarefa id da tarefa a adicionar
     * @param idProjeto id do projeto a adicionar
     * @param concluida estado da tarefa
     * @param dataInicial data inicial da tarefa
     * @param dataFinal data final da tarefa
     * @throws PersistenceException
     */
    public void addTarefaProjeto(int idTarefa, int idProjeto, boolean concluida, LocalDate dataInicial, LocalDate dataFinal) throws PersistenceException {
        Tarefa t;
        t = tarefaDao.getTarefa(c, idTarefa);
        t.setProjecto(idProjeto);
        t.setConcluida(concluida);
        t.setDataFinal(dataFinal);
        t.setDataInicial(dataInicial);
        tarefaDao.addTarefaProjeto(c, t);
    }

    /**
     * Autentica utilizador
     * @param username username do utilizador
     * @param password password do utilizador
     * @return Devolve um funcionario
     * @throws PersistenceException
     */
    public Funcionario autenticaUtilizador(String username, String password) throws PersistenceException {
        return this.funcionarioDao.autenticaUtizador(c, username, password);
    }

    /**
     * Adiciona um novo funcionario
     * @param f funcionario  adicionar
     * @throws PersistenceException
     */
    public void addFuncionario(Funcionario f) throws PersistenceException {
        this.funcionarioDao.addFuncionario(c, f);
    }

    /**
     * Remove funcionário com id igual a idProjetoFuncionario
     *
     * @param idFuncionario  id do funcionário a ser removido
     * @return true se o funcionário for removido com sucesso, false caso contrário
     * @throws PersistenceException
     */
    public boolean remFuncionario(int idFuncionario) throws PersistenceException {
        return this.funcionarioDao.remFuncionario(c, idFuncionario);

    }

    /**
     * Devolve o documento de um voluntario
     * @param idVountario ido do voluntario
     * @return documento
     * @throws PersistenceException
     */
    public InputStream getVoluntarioDocumento(int idVountario) throws PersistenceException {
        return this.voluntarioDao.getVoluntarioDocumento(c, idVountario);
    }

    /**
     * Devolve a existencia ou nao de um documento de um dado voluntario
     * @param idVoluntario id do voluntario
     * @return devolve verdade se existe documento
     * @throws PersistenceException
     */
    public boolean existeVoluntarioDocumento(int idVoluntario) throws PersistenceException {
        return this.voluntarioDao.existeVoluntarioDocumento(c, idVoluntario);
    }

    /**
     * Devolve um funcionário com o id correspondente ao id introduzido
     *
     * @param idFuncionario  id do funcionário a pesquisar
     * @return Funcionário cujo id corresponde ao id introduzido
     * @throws PersistenceException
     */
    public Funcionario getFuncionario(int idFuncionario) throws PersistenceException {
        return this.funcionarioDao.getFuncionario(c, idFuncionario);
    }

    /**
     * Devolve a existencia ou nao de um dado voluntario
     * @param idVoluntario id do voluntario
     * @return verdadeiro se existe voluntario
     * @throws PersistenceException
     */
    public boolean existeVoluntario(int idVoluntario) throws PersistenceException {
        return this.voluntarioDao.existeVoluntario(c, idVoluntario);
    }

    /**
     * Alterar voluntario 
     * @param v voluntario a alterar
     * @throws PersistenceException
     */
    public void setVoluntario(Voluntario v) throws PersistenceException {
        this.voluntarioDao.setVoluntario(c, v);
    }

    /**
     * Alterar projeto
     * @param p projeto a alterar
     * @throws PersistenceException
     */
    public void setProjeto(Projecto p) throws PersistenceException {
        projectoDao.setProjecto(c, p);
    }

    /**
     * Remove voluntário com id igual a idVoluntario
     *
     * @param idVoluntario id do voluntário a ser removido
     * @throws PersistenceException
     */
    public void remVoluntario(int idVoluntario) throws PersistenceException {
        this.voluntarioDao.remVoluntario(c, idVoluntario);
    }

    /**
     * Adiciona doador ao sistema
     * @param d doador a adicionar
     * @throws PersistenceException
     */
    public void addDoador(Doador d) throws PersistenceException {
        doadorDao.addDoador(c, d);
    }

    /**
     * Devolve lista dos funcionários registados no sistema
     *
     * @return Lista de funcionários
     * @throws PersistenceException
     */
    public List<Funcionario> getFuncionarios() throws PersistenceException {
        return this.funcionarioDao.getFuncionarios(c);
    }

    /**
     * Alterar doador
     * @param d doador a alterar
     * @throws PersistenceException
     */
    public void setDoador(Doador d) throws PersistenceException {
        doadorDao.setDoador(c, d);
    }

    /**
     * Adiciona equipa
     * @param e equipa a adicionar
     * @throws PersistenceException
     */
    public void addEquipa(Equipa e) throws PersistenceException {
        equipaDao.addEquipa(c, e);
    }

    /**
     * Altera equipa
     * @param e equipa a alterar
     * @throws PersistenceException
     */
    public void setEquipa(Equipa e) throws PersistenceException {
        equipaDao.setEquipa(c, e);
    }

    /**
     * Adiciona evento
     * @param e evento a adicionar
     * @throws PersistenceException
     */
    public void addEvento(Evento e) throws PersistenceException {
        eventoDao.addEvento(c, e);
    }

    /**
     * Altera evento
     * @param e evento a alterar
     * @throws PersistenceException
     */
    public void setEvento(Evento e) throws PersistenceException {
        eventoDao.setEvento(c, e);
    }

    /**
     * Adiciona material
     * @param m material a adicionar
     * @throws PersistenceException
     */
    public void addMaterial(Material m) throws PersistenceException {
        materialDao.addMaterial(m);
    }

    /**
     * Devolve o material com um dado nome
     * @param nomeM nome do material a pesquisar
     * @return lista de material
     * @throws PersistenceException
     */
    public List<Material> getMaterialbyName(String nomeM) throws PersistenceException {
        return materialDao.getMaterialbyName(c, nomeM);
    }

    /**
     * Devolve material indiponivel
     * @param nomeM nome do material a pequisar
     * @return lista de material
     * @throws PersistenceException
     */
    public List<Material> getMaterialbyNameSem(String nomeM) throws PersistenceException {
        return materialDao.getMaterialbyNameSem(c, nomeM);
    }

    /**
     * Devolve lista dos materiais em stock
     *
     * @return Lista de materiais em stock
     * @throws PersistenceException
     */
    public List<Material> getStock() throws PersistenceException {
        return materialDao.getStock(c);
    }

    /**
     * Remove uma quantidade de um dado material em stock
     *
     * @param idMaterial id do material do qual vai ser removido stock
     * @param quant quantidade de material a remover
     * @throws PersistenceException
     */
    public void remStock(int idMaterial, int quant) throws PersistenceException {
        materialDao.remStock(c, idMaterial, quant);
    }

    /**
     * Alterar material
     * @param m material a alterar
     * @throws PersistenceException
     */
    public void setMaterial(Material m) throws PersistenceException {
        materialDao.setMaterial(c, m);
    }

    /**
     * Associa tarefa a projeto
     * @param idTarefa id da tarefa
     * @param idProjeto id do projeto
     * @param concluida estado da tarefa
     * @param datafinal data final da tarefa
     */
    public void setTarefaProjeto(int idTarefa, int idProjeto, boolean concluida, LocalDate datafinal) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adiciona voluntario
     * @param v voluntario a adicionar
     * @throws PersistenceException
     */
    public void addVoluntario(Voluntario v) throws PersistenceException {
        this.voluntarioDao.addVoluntario(c, v);
    }

    /**
     * Altera voluntario
     * @param idVoluntario ido do voluntario
     * @param estado estado do voluntario
     */
    public void setVoluntarioAtivo(int idVoluntario, boolean estado) {
        throw new UnsupportedOperationException();
    }

    /**
     * Devolve funcionarios com um dado nome
     * @param nome nome dos funcionarios a pesquisar 
     * @return lista de funcionarios
     * @throws PersistenceException
     */
    public List<Funcionario> getFuncionariobyName(String nome) throws PersistenceException {
        return this.funcionarioDao.getFuncionariobyName(c, nome);
    }

    /**
     * Altear funcionario
     * @param f funcionario a alterar
     * @throws PersistenceException
     */
    public void setFuncionario(Funcionario f) throws PersistenceException {
        funcionarioDao.setFuncionario(c, f);
    }

    
    /**
     * Devolve as tarefas de um projeto
     * @param idProjecto ido do projeto
     * @return lista de tarefas
     * @throws PersistenceException
     */
    public ArrayList<Tarefa> getTarefasProjecto(int idProjecto) throws PersistenceException {
        return projectoDao.getTarefasProjecto(c, idProjecto);
    }

    
    /**
     * Devolve lista dos materiais registados no sistema sem stock
     *
     * @return Lista de materiais sem stock
     * @throws PersistenceException
     */
    public List<Material> getSemStock() throws PersistenceException {
        return materialDao.getSemStock(c);
    }

    /**
     * Devolve lista das atividades registados no sistema
     *
     * @return Lista de atividades
     * @throws PersistenceException
     */
    public Set<String> getAtividades() throws PersistenceException {
        return doadorDao.getAtividades(c).keySet();
    }

    public int hashCode() {
        int lHashCode = 0;
        if (this.funcionarioDao != null) {
            lHashCode += this.funcionarioDao.hashCode();
        }
        if (this.candidaturaDao != null) {
            lHashCode += this.candidaturaDao.hashCode();
        }
        if (this.materialDao != null) {
            lHashCode += this.materialDao.hashCode();
        }
        if (this.equipaDao != null) {
            lHashCode += this.equipaDao.hashCode();
        }
        if (this.eventoDao != null) {
            lHashCode += this.eventoDao.hashCode();
        }
        if (this.doadorDao != null) {
            lHashCode += this.doadorDao.hashCode();
        }
        if (this.projectoDao != null) {
            lHashCode += this.projectoDao.hashCode();
        }
        if (this.tarefaDao != null) {
            lHashCode += this.tarefaDao.hashCode();
        }
        if (this.doacaoDao != null) {
            lHashCode += this.doacaoDao.hashCode();
        }
        if (this.voluntarioDao != null) {
            lHashCode += this.voluntarioDao.hashCode();
        }

        if (lHashCode == 0) {
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
            return lEquals;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public int closeConnection() {
        try {
            this.c.close();
            return 0;
        } catch (SQLException ex) {
            return 1;
        }
    }

    /**
     * Devolve a existencia ou nao de material
     * @param nomeMat nome do material
     * @return verdadeiro se existir no sistema
     * @throws PersistenceException
     */
    public boolean existeMaterial(String nomeMat) throws PersistenceException {
        return this.materialDao.existeMaterial(this.c, nomeMat);
    }

    /**
     * Devolve o funcionário atualmente ligado ao sistema
     *
     * @return Funcionário atualmente ligado ao sistema
     */
    public Funcionario getF() {
        return f;
    }

    /**
     * Altera funcionario da classe
     * @param f funcionario
     */
    public void setF(Funcionario f) {
        this.f = f;
    }

    /**
     * Devolve a conexão ativa à base de dados
     *
     * @return Voluntários cujo id corresponde ao id introduzido
     */
    private Connection getC() {
        return c;
    }

    /**
     * Altera coneccao
     * @param c nova coneccao
     */
    private void setC(Connection c) {
        this.c = c;
    }

    /**
     * Devolve o nome do material com o id correspondente ao id introduzido
     *
     * @param material  id do material a pesquisar
     * @return nome do material cujo id corresponde ao id introduzido
     * @throws PersistenceException
     */
    public String getNomeMaterial(int material) throws PersistenceException {
        return this.materialDao.getMaterial(c, material).getNomeProperty().get();
    }
    
    /**
     * Adiciona o material m à lista de materiais usados no projeto com id idProjeto
     *
     * @param idProjeto  id do projeto
     * @param m material a associar ao projeto
     * @throws PersistenceException
     */
    public void addMaterialProjeto(int idProjeto, Material m) throws PersistenceException{
        this.projectoDao.addMaterialProjeto(c, idProjeto, m);
    }
    
    /**
     * Remove o material com id idMateriak da lista de materiais usados no projeto com id idProjeto
     *
     * @param idProjeto  id do projeto
     * @param idMaterial material a retirar do projeto
     * @throws PersistenceException
     */
    public void remMaterialProjeto(int idProjeto, int idMaterial) throws PersistenceException{
        this.projectoDao.remMaterialProjeto(c, idProjeto, idMaterial);
    }
    
    /**
     * Devolve a lista dos materiais utilizados no projeto com id idProjeto
     *
     * @param idProjeto  id do material a pesquisar
     * @return lista dos materiais utilizados no projeto
     * @throws PersistenceException
     */
    public List<Material> getMateriaisProjeto(int idProjeto) throws PersistenceException{
        return this.projectoDao.getMateriaisProjeto(c, idProjeto);
    }

}
