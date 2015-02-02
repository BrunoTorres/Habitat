package pt.uminho.lei.dss.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import pt.uminho.lei.dss.model.Doador;

public class DoadorDAO extends AbstractDAO {

    private static final String SELECT_DOADORES = "select idDoador, Nome, Empresa, Email, Nif, Telefone, Rua, Localidade, CodPostal, ResponsavelEmpresa, ContactoResponsavel, Obs, Funcionario, Actividade from Doador";
    private static final String SELECT_DOADOR = "select idDoador, Nome, Empresa, Email, Nif, Telefone, Rua, Localidade, CodPostal, ResponsavelEmpresa, ContactoResponsavel, Obs, Funcionario, Actividade from Doador where idDoador = ?";
    private static final String SELECT_DOADORES_NOME = "select idDoador, Nome, Empresa, Email, Nif, Telefone, Rua, Localidade, CodPostal, ResponsavelEmpresa, ContactoResponsavel, Obs, Funcionario, Actividade from Doador where nome LIKE ?";
    //private static final String SELECT_DOADOR_USERNAME = "select idDoador, Nome, UserName, Password, Permissao, Activo, Email, DataNascimento, Telefone, Nif, Salario, Rua, Localidade, CodPostal, UltimoLogin from Doador where UserName LIKE ?";
    private static final String SELECT_ATIVIDADES = "select idActividade, Descricao from Actividade";
    private static final String SELECT_ATIVIDADE = "select Descricao from Actividade where idActividade = ?";

    //private static final String SELECT_VOLUNTARIOS_DOADOR = "select idDoador,Nome,Profissao,Telefone,DataNascimento,Email,Rua,Localidade,CodPostal,Doador,Documento from Doador where Doador = ?";
    private static final String INSERT_DOADOR = "insert into Doador (Nome, Empresa, Email, Nif, Telefone, Rua, Localidade, CodPostal, ResponsavelEmpresa, ContactoResponsavel, Obs, Funcionario, Actividade) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_DOADOR = "update Doador set Nome = ?, Empresa = ?, Email = ?, Nif = ?, Telefone = ?, Rua = ?, Localidade = ?, CodPostal = ?, ResponsavelEmpresa = ?, ContactoResponsavel = ?, Obs = ?, Funcionario = ?, Actividade = ? where idDoador = ?";
    private static final String INSERT_ACTIVIDADE = "insert into Actividade (Descricao) values (?)";
    private static final String DELETE_DOADOR = "delete from Doador where idDoador = ?";
    private static final String DELETE_DOADORES = "delete from Doador";

    private static final String COUNT_DOADORES = "select count(*) as n from Doador";

    private DoacaoDAO doacaodao;

    public DoadorDAO() {
    }

    public List<Doador> getDoadoresByName(Connection connection, String nome) throws PersistenceException {
        try {
            ArrayList<Doador> doadores = new ArrayList<>();
            TreeMap<String, Integer> ativs = (TreeMap<String, Integer>) this.getAtividades(connection);
            PreparedStatement statement = connection.prepareStatement(SELECT_DOADORES_NOME);
            statement.setString(1, "%" + nome + "%");
            ResultSet result = statement.executeQuery();

            try {
                while (result.next()) {
                    Doador d = new Doador();

                    d.setIdDoador(result.getInt("idDoador"));
                    d.setNome(result.getString("Nome"));
                    d.setEmpresa(result.getInt("Empresa"));
                    String n = result.getString("Nome");
                    String[] nn = n.split(" ");
                    d.setFirstName(nn[0]);
                    d.setLastName((nn.length > 1) ? nn[nn.length - 1] : "");
                    d.setEmail(result.getString("Email"));
                    d.setNif(result.getInt("Nif"));
                    d.setTelefone(result.getInt("Telefone"));
                    d.setRua(result.getString("Rua"));
                    d.setLocalidade(result.getString("Localidade"));
                    d.setCodPostal(result.getString("CodPostal"));
                    d.setResponsavelEmpresa(result.getString("ResponsavelEmpresa"));
                    d.setContactoResponsavel(result.getInt("ContactoResponsavel"));
                    d.setObs(result.getString("Obs"));
                    d.setFuncionario(result.getInt("Funcionario"));
                    int ativid = result.getInt("Actividade");
                    Iterator<String> it = ativs.keySet().iterator();
                    boolean exit = false;
                    String at;
                    while (it.hasNext() && !exit) {
                        at = it.next();
                        if (ativs.get(at) == ativid) {
                            d.setActividade(at);
                            exit = true;
                        }
                    }

                    doadores.add(d);

                }
            } finally {
                result.close();
                statement.close();
            }

            return doadores;
        } catch (SQLException ex) {
            throw new PersistenceException("Error SQL DOADORESBYNAME", ex);
        }
    }

    public Doador getDoador(Connection connection, int idDoador) throws PersistenceException {
        try {
            Doador d;
            TreeMap<String, Integer> ativs = (TreeMap<String, Integer>) this.getAtividades(connection);
            PreparedStatement statement = connection.prepareStatement(SELECT_DOADOR);
            statement.setLong(1, idDoador);
            ResultSet result = statement.executeQuery();

            try {
                if (result.next()) {
                    d = new Doador();
                    d.setIdDoador(result.getInt("idDoador"));
                    String n = result.getString("Nome");
                    d.setNome(n);
                    String[] nn = n.split(" ");
                    d.setFirstName(nn[0]);
                    d.setLastName((nn.length > 1) ? nn[nn.length - 1] : "");
                    d.setEmpresa(result.getInt("Empresa"));
                    d.setEmail(result.getString("Email"));
                    d.setNif(result.getInt("Nif"));
                    d.setTelefone(result.getInt("Telefone"));
                    d.setRua(result.getString("Rua"));
                    d.setLocalidade(result.getString("Localidade"));
                    d.setCodPostal(result.getString("CodPostal"));
                    d.setResponsavelEmpresa(result.getString("ResponsavelEmpresa"));
                    d.setContactoResponsavel(result.getInt("ContactoResponsavel"));
                    d.setObs(result.getString("Obs"));
                    d.setFuncionario(result.getInt("Funcionario"));
                    int ativid = result.getInt("Actividade");
                    Iterator<String> it = ativs.keySet().iterator();
                    boolean exit = false;
                    String at;
                    while (it.hasNext() && !exit) {
                        at = it.next();
                        if (ativs.get(at) == ativid) {
                            d.setActividade(at);
                            exit = true;
                        }
                    }
                    doacaodao = new DoacaoDAO();
                    d.setDoacoes(doacaodao.getDoacoes(connection, idDoador));
                } else {
                    d = null;
                }
            } finally {
                result.close();
                statement.close();
            }

            return d;
        } catch (SQLException ex) {
            throw new PersistenceException("Error SQL VOLUNTARIOS", ex);
        }
    }

    public boolean remDoador(Connection connection, int idDoador) throws PersistenceException {
        boolean flag = true;
        try {
            if (!doacaodao.existeDoacaoDoador(connection, idDoador)) {
                PreparedStatement statement = connection.prepareStatement(DELETE_DOADOR);

                statement.setInt(1, idDoador);

                try {
                    statement.executeUpdate();
                } finally {
                    statement.close();
                }
            } else {
                flag = false;
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error Deleting FUNCIONARIO ");
        }
        return flag;
    }

    public void addDoador(Connection connection, Doador d) throws PersistenceException {
        String query;
        TreeMap<String, Integer> ativs = (TreeMap<String, Integer>) this.getAtividades(connection);
        int autoGeneratedKeys;
        query = INSERT_DOADOR;
        autoGeneratedKeys = Statement.RETURN_GENERATED_KEYS;
        try {
            PreparedStatement statement = connection.prepareStatement(query, autoGeneratedKeys);

            statement.setString(1, d.getNome());
            statement.setInt(2, d.getEmpresa());
            statement.setString(3, d.getEmail());
            statement.setInt(4, d.getNif());
            statement.setInt(5, d.getTelefone());
            statement.setString(6, d.getRua());
            statement.setString(7, d.getLocalidade());
            statement.setString(8, d.getCodPostal());
            statement.setString(9, d.getResponsavelEmpresa());
            statement.setInt(10, d.getContactoResponsavel());
            statement.setString(11, d.getObs());
            statement.setInt(12, d.getFuncionario());
            statement.setInt(13, ativs.get(d.getAtividade()));

            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();

            try {
                if (keys.next()) {
                    d.setIdDoador(keys.getInt(1));
                } else {
                    throw new PersistenceException("Error generating id for Doador: " + d);
                }
            } finally {
                keys.close();
                statement.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error saving Doador: " + d, ex);
        }
    }

    public boolean existeDoador(Connection connection, Doador d) throws PersistenceException {
        return (getDoador(connection, d.getIdDoador()) != null);
    }

    public void setDoador(Connection connection, Doador d) throws PersistenceException {
        try {
            TreeMap<String, Integer> ativs = (TreeMap<String, Integer>) this.getAtividades(connection);
            PreparedStatement statement = connection.prepareStatement(UPDATE_DOADOR);
            connection.setAutoCommit(true);

            try {
                statement.setString(1, d.getNome());
                statement.setInt(2, d.getEmpresa());
                statement.setString(3, d.getEmail());
                statement.setInt(4, d.getNif());
                statement.setInt(5, d.getTelefone());
                statement.setString(6, d.getRua());
                statement.setString(7, d.getLocalidade());
                statement.setString(8, d.getCodPostal());
                if (d.getEmpresa() == 1) {
                    statement.setString(9, d.getResponsavelEmpresa());
                    statement.setInt(10, d.getContactoResponsavel());
                } else {
                    statement.setNull(9, Types.NULL);
                    statement.setNull(10, Types.NULL);
                }

                statement.setString(11, d.getObs());
                statement.setInt(12, d.getFuncionario());
                statement.setInt(13, ativs.get(d.getAtividade()));
                statement.setInt(14, d.getIdDoador());

                int rows = statement.executeUpdate();

            } finally {
                statement.close();
            }

        } catch (SQLException ex) {
            throw new PersistenceException("Error SQL SET VOLUNTARIO", ex);
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
        } else if (object instanceof DoadorDAO) {
            DoadorDAO lDoadorDAOObject = (DoadorDAO) object;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }

    // ADICIONAR
    public List<Doador> getDoadores(Connection connection) throws PersistenceException {
        try {
            ArrayList<Doador> doadores = new ArrayList<>();
            TreeMap<String, Integer> ativs = (TreeMap<String, Integer>) this.getAtividades(connection);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_DOADORES);

            try {
                while (result.next()) {
                    Doador d = new Doador();
                    d.setIdDoador(result.getInt("idDoador"));
                    String n = result.getString("Nome");
                    d.setNome(n);
                    String[] nn = n.split(" ");
                    d.setFirstName(nn[0]);
                    d.setLastName((nn.length > 1) ? nn[nn.length - 1] : "");
                    d.setEmpresa(result.getInt("Empresa"));
                    d.setEmail(result.getString("Email"));
                    d.setNif(result.getInt("Nif"));
                    d.setTelefone(result.getInt("Telefone"));
                    d.setRua(result.getString("Rua"));
                    d.setLocalidade(result.getString("Localidade"));
                    d.setCodPostal(result.getString("CodPostal"));
                    d.setResponsavelEmpresa(result.getString("ResponsavelEmpresa"));
                    d.setContactoResponsavel(result.getInt("ContactoResponsavel"));
                    d.setObs(result.getString("Obs"));
                    d.setFuncionario(result.getInt("Funcionario"));

                    int ativid = result.getInt("Actividade");
                    Iterator<String> it = ativs.keySet().iterator();
                    boolean exit = false;
                    String at;
                    while (it.hasNext() && !exit) {
                        at = it.next();
                        if (ativs.get(at) == ativid) {
                            d.setActividade(at);
                            exit = true;
                        }
                    }
                    doadores.add(d);
                }
            } finally {
                result.close();
                statement.close();
            }

            return doadores;
        } catch (SQLException ex) {
            throw new PersistenceException("Error fetching Funcionarios", ex);
        }
    }

    public Map<String, Integer> getAtividades(Connection connection) throws PersistenceException {
        try {
            TreeMap<String, Integer> ativs = new TreeMap<>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ATIVIDADES);
            try {
                while (result.next()) {
                    ativs.put(result.getString("Descricao"), result.getInt("idActividade"));
                }
            } finally {
                result.close();
                statement.close();
            }

            return ativs;
        } catch (SQLException ex) {
            throw new PersistenceException("Error fetching atividades", ex);
        }
    }

    private String getAtividade(Connection connection, int idAtividade) throws PersistenceException {
        String query = SELECT_ATIVIDADE;
        String atividade = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            try {
                statement.setString(1, String.valueOf(idAtividade));

                if (rs.next()) {
                    atividade = rs.getString("Descricao");
                }
            } finally {
                statement.close();
                rs.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error fetching atividade: " + idAtividade, ex);
        }
        return atividade;
    }
}
