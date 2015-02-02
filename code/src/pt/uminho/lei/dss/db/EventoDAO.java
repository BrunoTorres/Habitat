package pt.uminho.lei.dss.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import javafx.beans.property.SimpleStringProperty;
import pt.uminho.lei.dss.model.Doador;
import pt.uminho.lei.dss.model.Evento;

public class EventoDAO extends AbstractDAO {

    private static final String SELECT_EVENTOS_NOME = "select idEvento, Nome, Data, Organizador, Total, Notas, Funcionario from Evento where Nome LIKE ?";

    private static final String SELECT_EVENTOS = "select idEvento, Nome, Data, Organizador, Total, Notas, Funcionario from Evento";
    private static final String SELECT_EVENTO = "select idEvento, Nome, Data, Organizador, Total, Notas, Funcionario from Evento WHERE idEvento = ?";
    private static final String INSERT_EVENTO = "insert into Evento (Nome, Data, Organizador, Total, Notas, Funcionario) values (?,?,?,0,?,?)";
    private static final String UPDATE_EVENTO = "update Evento set Nome = ?, Data = ?, Organizador = ?, Notas = ?, Funcionario = ? where idEvento = ?";
    private static final String UPDATE_TOTAL = "update Evento set Total = Total + ? where idEvento = ?";
    private static final String DELETE_EVENTO = "delete from Evento where idEvento = ?";

    public List<Evento> getEventosByName(Connection connection, String nome) throws PersistenceException {
        try {
            ArrayList<Evento> eventos = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(SELECT_EVENTOS_NOME);
            statement.setString(1, "%" + nome + "%");
            ResultSet rs = statement.executeQuery();

            try {
                while (rs.next()) {
                    Evento e = new Evento();

                    e.setIdEvento(rs.getInt("idEvento"));
                    e.setNome(new SimpleStringProperty(rs.getString("Nome")));
                    e.setData(rs.getDate("Data").toLocalDate());
                    e.setOrganizador(rs.getString("Organizador"));
                    e.setTotal(rs.getDouble("Total"));
                    e.setNotas(rs.getString("Notas"));
                    e.setFuncionario(rs.getInt("Funcionario"));

                    eventos.add(e);

                }
            } finally {
                rs.close();
                statement.close();
            }

            return eventos;
        } catch (SQLException ex) {
            throw new PersistenceException("Error SQL DOADORESBYNAME", ex);
        }

    }

    public Evento getEvento(Connection connection, int idEvento) throws PersistenceException {
        try {
            Evento e = new Evento();
            PreparedStatement statement = connection.prepareStatement(SELECT_EVENTO);
            statement.setInt(1, idEvento);
            ResultSet rs = statement.executeQuery();

            try {
                while (rs.next()) {
                    e.setIdEvento(rs.getInt("idEvento"));
                    e.setNome(new SimpleStringProperty(rs.getString("Nome")));
                    e.setData(rs.getDate("Data").toLocalDate());
                    e.setOrganizador(rs.getString("Organizador"));
                    e.setTotal(rs.getDouble("Total"));
                    e.setNotas(rs.getString("Notas"));
                    e.setFuncionario(rs.getInt("Funcionario"));
                }
            } finally {
                rs.close();
                statement.close();
            }
            return e;
        } catch (SQLException ex) {
            throw new PersistenceException("Error fetching Eventos", ex);
        }
    }

    public void setEvento(Connection connection, Evento e) throws PersistenceException {
        try {

            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(UPDATE_EVENTO);
            try {
                statement.setString(1, e.getNome().get());
                statement.setDate(2, java.sql.Date.valueOf(e.getData()));
                statement.setString(3, e.getOrganizador());
                statement.setString(4, e.getNotas());
                statement.setInt(5, e.getFuncionario());
                statement.setInt(6, e.getIdEvento());

                statement.executeUpdate();

            } finally {
                statement.close();
                //connection.close();
            }

        } catch (SQLException ex) {
            throw new PersistenceException("Error SQL SET CANDIDATURA", ex);
        }

    }

    public void addTotal(Connection connection, int idEvento, double quant) throws PersistenceException, SQLException {
        PreparedStatement st = connection.prepareStatement(UPDATE_TOTAL);
        try {
            st.setDouble(1, quant);
            st.setInt(2, idEvento);

            int rowsChanged = st.executeUpdate();
        } finally {
            st.close();
        }
    }

    public List<Evento> getEventos(Connection connection) throws PersistenceException {
        try {
            ArrayList<Evento> events = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_EVENTOS);

            try {
                while (rs.next()) {
                    Evento e = new Evento();
                    e.setIdEvento(rs.getInt("idEvento"));
                    e.setNome(new SimpleStringProperty(rs.getString("Nome")));
                    e.setData(rs.getDate("Data").toLocalDate());
                    e.setOrganizador(rs.getString("Organizador"));
                    e.setTotal(rs.getDouble("Total"));
                    e.setNotas(rs.getString("Notas"));
                    e.setFuncionario(rs.getInt("Funcionario"));

                    events.add(e);
                }
            } finally {
                rs.close();
                statement.close();
            }

            return events;
        } catch (SQLException ex) {
            throw new PersistenceException("Error fetching Eventos", ex);
        }
    }

    /*
     public boolean remCandidatura(Connection connection, int id) throws PersistenceException {
        
     try {
     //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
     PreparedStatement statement = connection.prepareStatement(DELETE_CANDIDATURA);

     statement.setInt(1, id);

     try {
     statement.executeUpdate();
     } finally {
     statement.close();
     //connection.close();
     }
     return true;
     } catch (SQLException ex) {
     return false;
     }
     }
     */
    public boolean remEvento(Connection connection, int idEvento) {
        try {
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(DELETE_EVENTO);

            statement.setInt(1, idEvento);

            try {
                statement.executeUpdate();
            } finally {
                statement.close();
                //connection.close();
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public void addEvento(Connection connection, Evento e) throws PersistenceException {
        String query;
        int autoGeneratedKeys;
        query = INSERT_EVENTO;
        autoGeneratedKeys = Statement.RETURN_GENERATED_KEYS;
        try {
            //Connection connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            PreparedStatement statement = connection.prepareStatement(query, autoGeneratedKeys);

            statement.setString(1, e.getNome().get());
            statement.setDate(2, java.sql.Date.valueOf(e.getData()));
            statement.setString(3, e.getOrganizador());
            statement.setString(4, e.getNotas());
            statement.setInt(5, e.getFuncionario());

            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();

            try {
                if (keys.next()) {
                    e.setIdEvento(keys.getInt(1));
                } else {
                    throw new PersistenceException("Error generating id for Evento: " + e);
                }
            } finally {
                keys.close();
                statement.close();
                //connection.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error saving Evento: " + e, ex);
        }
    }

    public boolean existeEvento(Connection connection, Evento e) {
        throw new UnsupportedOperationException();
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
        } else if (object instanceof EventoDAO) {
            EventoDAO lEventoDAOObject = (EventoDAO) object;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }
}
