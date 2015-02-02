package pt.uminho.lei.dss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pt.uminho.lei.dss.model.Tarefa;

public class TarefaDAO extends AbstractDAO {

    private static final String SELECT_TAREFAS = "select idTarefa,Nome,Funcionario from Tarefa";
    private static final String SELECT_TAREFA = "select idTarefa,Nome,Funcionario from Tarefa where idTarefa = ?";
    private static final String SELECT_TAREFA_NOME = "select idTarefa,Nome,Funcionario from Tarefa where Nome = ?";
    
    private static final String SELECT_TAREFAS_NOME = "select idTarefa,Nome,Funcionario from Tarefa where nome LIKE ?";
    //private static final String SELECT_PROJECTOSTAREFA = "select Projecto, Tarefa from ProjectoTarefa where Tarefa = ?";
    //private static final String SELECT_EQUIPATAREFA = "select idTarefa,Nome,Funcionario from EquipaTarefa where Tarefa = ?";

    private static final String INSERT_TAREFA = "INSERT INTO Tarefa (Nome,Funcionario)VALUES(?,?);";
    private static final String INSERT_TAREFAPROJECTO = "INSERT INTO TarefaProjecto (Tarefa,Projecto,Concluida,DataFinal,DataInicial)VALUES(?,?,?,?,?)";
    
    private static final String UPDATE_TAREFA = "update Tarefa set Nome = ?, Funcionario = ? where idTarefa = ?";
    private static final String UPDATE_TAREFAPROJECTO = "update TarefaProjecto set Concluida = ?, DataFinal = ?, DataInicial = ? where Tarefa = ? AND Projecto = ?";

    
    private static final String DELETE_TAREFAPROJECTO = "delete from TarefaProjecto where Tarefa = ? and Projecto = ?";
    private static final String DELETE_TAREFAS = "delete from Tarefa";

    private static final String COUNT_TAREFAS = "select count(*) as n from Tarefa";

    public TarefaDAO() {
    }
    
    public boolean existeTarefa(Connection connection, Tarefa t) throws PersistenceException {
        Tarefa v = getTarefa(connection, t.getId());
        return (v != null);
    }
    
    

    public void addTarefa(Connection connection, Tarefa t) throws PersistenceException {
        String query;
        int autoGeneratedKeys;
        query = INSERT_TAREFA;
        autoGeneratedKeys = Statement.RETURN_GENERATED_KEYS;
        try{
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(query, autoGeneratedKeys);
            
            statement.setString(1, t.getNome());
            statement.setInt(2, t.getFuncionario());
            
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            
            try {
                if(keys.next()) {
                    t.setId(keys.getInt(1));
                }
                else {
                    throw new PersistenceException("Error generating id for Tarefa: " + t);
                }
            }
            finally {
                keys.close();
                statement.close();
                //connection.close();
            }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error saving Voluntario: " + t, ex);
        }
    }

    public List<Tarefa> getTarefasByName(Connection connection, String nome) throws PersistenceException {
        try {   
            ArrayList<Tarefa> tafs = new ArrayList<>();
            
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_TAREFAS_NOME);
            statement.setString(1, nome + "%");
            ResultSet result = statement.executeQuery();
            
            try {
                while(result.next()) {
                    Tarefa t = new Tarefa();
                    t.setId(result.getInt("idTarefa"));
                    t.setNome(result.getString("Nome"));
                    t.setFuncionario(result.getInt("Funcionario"));
                    
                    tafs.add(t);
                    
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return tafs;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL Tarefas", ex);
        }
    
    }

    public Tarefa getTarefa(Connection connection, int idTarefa) throws PersistenceException {
        try {
            Tarefa t;
            
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_TAREFA);
            statement.setLong(1, idTarefa);
            ResultSet result = statement.executeQuery();
            
            try {
                if(result.next()) {
                    t = new Tarefa();
                    t.setId(result.getInt("idTarefa"));
                    t.setNome(result.getString("Nome"));
                    t.setFuncionario(result.getInt("Funcionario"));
                    
                } else {
                    t = null;
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return t;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS", ex);
        }
    }
    
    public Tarefa getTarefaByName(Connection connection, String nTarefa) throws PersistenceException {
        try {
            Tarefa t;
            
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_TAREFA_NOME);
            statement.setString(1, nTarefa);
            ResultSet result = statement.executeQuery();
            
            try {
                if(result.next()) {
                    t = new Tarefa();
                    t.setId(result.getInt("idTarefa"));
                    t.setNome(result.getString("Nome"));
                    t.setFuncionario(result.getInt("Funcionario"));
                    
                } else {
                    t = null;
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return t;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS", ex);
        }
    }

    public void setTarefa(Connection connection, Tarefa t) throws PersistenceException {
        try {
                //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
                PreparedStatement statement = connection.prepareStatement(UPDATE_TAREFA);
                
                try {
                        statement.setString(1, t.getNome());
                        statement.setInt(2, t.getFuncionario());
                        statement.setInt(3, t.getId());
                        
                        statement.executeUpdate();
                    
                }
                finally {
                    statement.close();
                    //connection.close();
                }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL SET VOLUNTARIO", ex);
        }
    }
    
    public void setTarefaProjecto(Connection connection, Tarefa t) throws PersistenceException {
        try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_TAREFAPROJECTO);
                
                try {
                        statement.setInt(1, t.isConcluida()?1:0);
                        statement.setDate(2, java.sql.Date.valueOf(t.getDataFinal()));
                        statement.setDate(3, java.sql.Date.valueOf(t.getDataInicial()));
                        statement.setInt(4, t.getId());
                        statement.setInt(5, t.getProjecto());
                        statement.executeUpdate();
                    
                }
                finally {
                    statement.close();
                }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL SET TAREFAPROJECTO", ex);
        }
    }

    public void remTarefaProjecto(Connection connection, int idTarefa, int idProjecto) throws PersistenceException {
        try {
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(DELETE_TAREFAPROJECTO);
            
            statement.setInt(1, idTarefa);
            statement.setInt(2, idProjecto);
            
            try {
                statement.executeUpdate();
            }
            finally {
                statement.close();
            }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error Deleting TarefaProjecto ");
        }
    }

    public void addTarefaProjeto(Connection connection, Tarefa t) throws PersistenceException {
        String query;
        int autoGeneratedKeys;
        query = INSERT_TAREFAPROJECTO;
        autoGeneratedKeys = Statement.RETURN_GENERATED_KEYS;
        try{
            PreparedStatement statement = connection.prepareStatement(query, autoGeneratedKeys);
            
            statement.setInt(1, t.getId());
            statement.setInt(2, t.getProjecto());
            statement.setBoolean(3, t.isConcluida());
            statement.setDate(4, java.sql.Date.valueOf(t.getDataFinal()));
            LocalDate dataFinal = t.getDataFinal();
            if(dataFinal != null)
                statement.setDate(5, java.sql.Date.valueOf(dataFinal));
            else
                statement.setNull(5, Types.NULL);
            
            
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            
            try {
                if(keys.next()) {
                    t.setId(keys.getInt(1));
                }
                else {
                    //throw new PersistenceException("Error generating id for TarefaProjecto: " + t);
                }
            }
            finally {
                keys.close();
                statement.close();
                //connection.close();
            }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error saving TarefaProjecto: " + t, ex);
        }
    }

    public int hashCode() {
        int lHashCode = 0;
        if (lHashCode == 0) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof TarefaDAO) {
            TarefaDAO lTarefaDAOObject = (TarefaDAO) object;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }
}