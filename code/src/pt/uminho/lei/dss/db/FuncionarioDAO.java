package pt.uminho.lei.dss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pt.uminho.lei.dss.model.Funcionario;

public final class FuncionarioDAO extends AbstractDAO{
    private static final String SELECT_FUNCIONARIOS = "select idFuncionario, Nome, UserName, Password, Permissao, Activo, Email, DataNascimento, Telefone, Nif, Salario, Rua, Localidade, CodPostal, UltimoLogin from Funcionario";
    private static final String SELECT_FUNCIONARIO = "select idFuncionario, Nome, UserName, Password, Permissao, Activo, Email, DataNascimento, Telefone, Nif, Salario, Rua, Localidade, CodPostal, UltimoLogin from Funcionario where idFuncionario = ?";
    private static final String SELECT_FUNCIONARIOS_NOME = "select idFuncionario, Nome, UserName, Password, Permissao, Activo, Email, DataNascimento, Telefone, Nif, Salario, Rua, Localidade, CodPostal, UltimoLogin from Funcionario where nome LIKE ?";
    private static final String SELECT_FUNCIONARIO_USERNAME = "select idFuncionario, Nome, UserName, Password, Permissao, Activo, Email, DataNascimento, Telefone, Nif, Salario, Rua, Localidade, CodPostal, UltimoLogin from Funcionario where UserName LIKE ?";
    
    private static final String SELECT_PODEREMOVER = "select Funcionario from Projecto where Funcionario = ? UNION select Funcionario from Voluntario where Funcionario = ? UNION select Funcionario from Equipa where Funcionario = ? UNION select Funcionario from Evento where Funcionario = ? UNION select Funcionario from Material where Funcionario = ? UNION select Funcionario from Tarefa where Funcionario = ? UNION select Funcionario from Doador where Funcionario = ? UNION select Funcionario from Doacao where Funcionario = ?";
    //private static final String SELECT_VOLUNTARIOS_FUNCIONARIO = "select idFuncionario,Nome,Profissao,Telefone,DataNascimento,Email,Rua,Localidade,CodPostal,Funcionario,Documento from Funcionario where Funcionario = ?";
    
    private static final String INSERT_FUNCIONARIO = "insert into Funcionario (Nome, UserName, Password, Permissao, Activo, Email, DataNascimento, Telefone, Nif, Salario, Rua, Localidade, CodPostal, UltimoLogin) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_FUNCIONARIO = "update Funcionario set Nome = ?, UserName = ?, Password = ?, Permissao = ?, Activo = ?, Email = ?, DataNascimento = ?, Telefone = ?, Nif = ?, Salario = ?, Rua = ?, Localidade = ?, CodPostal = ?, UltimoLogin = ? where idFuncionario = ?";
    private static final String UPDATE_FUNCIONARIO_ATIVO = "update Funcionario set Activo = ? where idFuncionario = ?";
    
    
    private static final String DELETE_FUNCIONARIO = "delete from Funcionario where idFuncionario = ?";
    private static final String DELETE_FUNCIONARIOS = "delete from Funcionario";
    
    private static final String COUNT_FUNCIONARIOS = "select count(*) as n from Funcionario";
    
    //private final HabitatRepository habitatRepository;

    public FuncionarioDAO() {       
        //this.habitatRepository = RepositoryFactory.getHabitatRepository();
    }
    
    public FuncionarioDAO(String url, String user, String password) {       
        //this.habitatRepository = RepositoryFactory.getHabitatRepository();
    }
    
    public void addFuncionario(Connection connection, Funcionario v) throws PersistenceException {
        String query;
        int autoGeneratedKeys;
        query = INSERT_FUNCIONARIO;
        autoGeneratedKeys = Statement.RETURN_GENERATED_KEYS;
        try{
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(query, autoGeneratedKeys);
            
            statement.setString(1, v.getNome());
            statement.setString(2, v.getUserName());
            statement.setString(3, v.getPassword());
            statement.setInt(4, v.getPermissao());
            statement.setBoolean(5, v.getActivo());
            statement.setString(6, v.getEmail());
            statement.setDate(7, java.sql.Date.valueOf(v.getDataNascimento()));
            statement.setInt(8, v.getTelefone());
            statement.setInt(9, v.getNif());
            statement.setInt(10, v.getSalario());
            statement.setString(11, v.getRua());
            statement.setString(12, v.getLocalidade());
            statement.setString(13, v.getCodPostal());
            statement.setTimestamp(14, java.sql.Timestamp.valueOf(v.getUltimoLoginDataTime()));
            
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            
            try {
                if(keys.next()) {
                    v.setIdFuncionario(keys.getInt(1));
                }
                else {
                    throw new PersistenceException("Error generating id for Funcionario: " + v);
                }
            }
            finally {
                keys.close();
                statement.close();
                //connection.close();
            }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error saving Funcionario: " + v, ex);
        }
 
    }

    public Funcionario getFuncionario(Connection connection, int id) throws PersistenceException {
        try {
            Funcionario v;
            
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_FUNCIONARIO);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            
            try {
                if(result.next()) {
           
                    v = new Funcionario();
                    v.setIdFuncionario(result.getInt("idFuncionario"));
                    v.setNome(result.getString("Nome"));
                    v.setUserName(result.getString("UserName"));
                    v.setPassword(result.getString("Password"));
                    v.setPermissao(result.getInt("Permissao"));
                    v.setActivo(result.getBoolean("Activo"));
                    v.setEmail(result.getString("Email"));
                    v.setDataNascimento(result.getDate("DataNascimento"));
                    v.setTelefone(result.getInt("Telefone"));
                    v.setNif(result.getInt("Nif"));
                    v.setSalario(result.getInt("Salario"));
                    v.setRua(result.getString("Rua"));
                    v.setLocalidade(result.getString("Localidade"));
                    v.setCodPostal(result.getString("CodPostal"));
                    v.setUltimoLogin(result.getTimestamp("UltimoLogin").toLocalDateTime());
                } else {
                    v = null;
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return v;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS", ex);
        }
    }
    
    public boolean existeFuncionario(Connection connection, int id) throws PersistenceException {
        Funcionario v = getFuncionario(connection,id);
        return (v != null);
    }
    
    public void setFuncionario(Connection connection, Funcionario v ) throws PersistenceException {
        try {
            
                //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
                PreparedStatement statement = connection.prepareStatement(UPDATE_FUNCIONARIO);
                
                try {
                    statement.setString(1, v.getNome());
                    statement.setString(2, v.getUserName());
                    statement.setString(3, v.getPassword());
                    statement.setInt(4, v.getPermissao());
                    statement.setBoolean(5, v.getActivo());
                    statement.setString(6, v.getEmail());
                    statement.setDate(7, java.sql.Date.valueOf(v.getDataNascimento()));
                    statement.setInt(8, v.getTelefone());
                    statement.setInt(9, v.getNif());
                    statement.setInt(10, v.getSalario());
                    statement.setString(11, v.getRua());
                    statement.setString(12, v.getLocalidade());
                    statement.setString(13, v.getCodPostal());
                    statement.setTimestamp(14, java.sql.Timestamp.valueOf(v.getUltimoLoginDataTime()));
                    statement.setInt(15,v.getIdFuncionario());
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

    public void setFuncionarioActivo(Connection connection, int idFuncionario, boolean estado) throws PersistenceException {
        try {
            
                //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
                PreparedStatement statement = connection.prepareStatement(UPDATE_FUNCIONARIO_ATIVO);
                
                try {
                    statement.setBoolean(1, estado);
                    statement.setInt(2,idFuncionario);
                    statement.executeUpdate();
                    
                }
                finally {
                    statement.close();
                    //connection.close();
                }
            
        
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL SET VOLUNTARIO ATIVO", ex);
        }
        
    }

    public ArrayList<Funcionario> getFuncionarios(Connection connection) throws PersistenceException {
        try {
            ArrayList<Funcionario> vols = new ArrayList<>();
            
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_FUNCIONARIOS);
            
            try {
                while(result.next()) {
                    Funcionario v = new Funcionario();
                    v.setIdFuncionario(result.getInt("idFuncionario"));
                    v.setNome(result.getString("Nome"));
                    v.setUserName(result.getString("UserName"));
                    v.setPassword(result.getString("Password"));
                    v.setPermissao(result.getInt("Permissao"));
                    v.setActivo(result.getBoolean("Activo"));
                    v.setEmail(result.getString("Email"));
                    v.setDataNascimento(result.getDate("DataNascimento"));
                    v.setTelefone(result.getInt("Telefone"));
                    v.setNif(result.getInt("Nif"));
                    v.setSalario(result.getInt("Salario"));
                    v.setRua(result.getString("Rua"));
                    v.setLocalidade(result.getString("Localidade"));
                    v.setCodPostal(result.getString("CodPostal"));
                    v.setUltimoLogin(result.getTimestamp("UltimoLogin").toLocalDateTime());
                    
                    vols.add(v);
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return vols;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error fetching Funcionarios", ex);
        }
    }
    
    public boolean podeRemoverFuncionario(Connection connection, int idFuncionario) throws PersistenceException{
        try {
            boolean flag = true;
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_PODEREMOVER);
            statement.setLong(1, idFuncionario);
            statement.setLong(2, idFuncionario);
            statement.setLong(3, idFuncionario);
            statement.setLong(4, idFuncionario);
            statement.setLong(5, idFuncionario);
            statement.setLong(6, idFuncionario);
            statement.setLong(7, idFuncionario);
            statement.setLong(8, idFuncionario);
            
            ResultSet result = statement.executeQuery();
            
            try {
                if(result.next()) {
                    flag = false;
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return flag;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS", ex);
        }
    }

    public boolean remFuncionario(Connection connection, int id) throws PersistenceException {
       boolean flag= podeRemoverFuncionario(connection,id);
       if(flag){
        try {
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(DELETE_FUNCIONARIO);
            
            statement.setInt(1, id);
            
            try {
                statement.executeUpdate();
            }
            finally {
                statement.close();
                //connection.close();
            }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error Deleting FUNCIONARIO ");
        }
       }
       return flag;
    }

    public void deleteAll(Connection connection) throws PersistenceException {
        try {
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(DELETE_FUNCIONARIOS);
            
            try {
                statement.executeUpdate();
            }
            finally {
                statement.close();
                //connection.close();
            }
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error deleting FUNCIONARIOS", ex);
        }
    }

    public long count(Connection connection) throws PersistenceException {
        try {
            long count;
            
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(COUNT_FUNCIONARIOS);

            try {
                count = result.getLong("n");
            }
            finally {
                statement.close();
                //connection.close();
            }
            
            return count;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error count VOLUNTARIOS");
        }
    }
    
    public ArrayList<Funcionario> getFuncionariobyName(Connection connection, String nome) throws PersistenceException {
        try {
            ArrayList<Funcionario> vols = new ArrayList<>();
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_FUNCIONARIOS_NOME);
            statement.setString(1, "%"+nome+"%");
            ResultSet result = statement.executeQuery();
            
            try {
                while(result.next()) {
                    Funcionario v = new Funcionario();
                    v.setIdFuncionario(result.getInt("idFuncionario"));
                    v.setNome(result.getString("Nome"));
                    v.setUserName(result.getString("UserName"));
                    v.setPassword(result.getString("Password"));
                    v.setPermissao(result.getInt("Permissao"));
                    v.setActivo(result.getBoolean("Activo"));
                    v.setEmail(result.getString("Email"));
                    v.setDataNascimento(result.getDate("DataNascimento"));
                    v.setTelefone(result.getInt("Telefone"));
                    v.setNif(result.getInt("Nif"));
                    v.setSalario(result.getInt("Salario"));
                    v.setRua(result.getString("Rua"));
                    v.setLocalidade(result.getString("Localidade"));
                    v.setCodPostal(result.getString("CodPostal"));
                    v.setUltimoLogin(result.getTimestamp("UltimoLogin").toLocalDateTime());
                    
                    vols.add(v);
                    
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return vols;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS", ex);
        }
    }
    
    public Funcionario getFuncionarioByUsername(Connection connection, String s) throws PersistenceException{
        try {
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(SELECT_FUNCIONARIO_USERNAME);
            statement.setString(1, "%"+s+"%");
            ResultSet result = statement.executeQuery();
            Funcionario v = new Funcionario();
            try {
                if(result.next()) {
                    v = new Funcionario();
                    v.setIdFuncionario(result.getInt("idFuncionario"));
                    v.setNome(result.getString("Nome"));
                    v.setUserName(result.getString("UserName"));
                    v.setPassword(result.getString("Password"));
                    v.setPermissao(result.getInt("Permissao"));
                    v.setActivo(result.getBoolean("Activo"));
                    v.setEmail(result.getString("Email"));
                    v.setDataNascimento(result.getDate("DataNascimento"));
                    v.setTelefone(result.getInt("Telefone"));
                    v.setNif(result.getInt("Nif"));
                    v.setSalario(result.getInt("Salario"));
                    v.setRua(result.getString("Rua"));
                    v.setLocalidade(result.getString("Localidade"));
                    v.setCodPostal(result.getString("CodPostal"));
                    v.setUltimoLogin(result.getTimestamp("UltimoLogin").toLocalDateTime());
                    
                }
            }
            finally {
                result.close();
                statement.close();
                //connection.close();
            }
            
            return v;
        }
        catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS BY USERNAME", ex);
        }
    }
    
    public Funcionario autenticaUtizador(Connection connection, String user, String password) throws PersistenceException{
        Funcionario f = getFuncionarioByUsername(connection,user);
        if (f != null) {
            if(f.getPassword().equals(password)){
                return f;
            } else {
            return null;
            }
        }
        else return null;
       
    }
}
