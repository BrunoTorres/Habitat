package pt.uminho.lei.dss.db;

import java.util.Properties;

public final class HabitatDAO {
    
    private static String DB_TYPE = "mysql";
    private static String HOST = "servidor.freeum.info";
    private static String PORT = "5223";
    private static String USER = "habitat";
    private static String PASSWORD = "password";
    private static String DATABASE = "habitat";
    
    private static VoluntarioDAO VoluntarioDAO;
    private static FuncionarioDAO FuncionarioDAO;
    private static EquipaDAO EquipaDAO;
    private static ProjectoDAO ProjectoDAO;
    private static CandidaturaDAO CandidaturaDAO;
    
    private HabitatDAO() { }
    
    public static VoluntarioDAO getVoluntarioDAO() {
        if(VoluntarioDAO == null) {
            //VoluntarioDAO = new VoluntarioDAO(getURL(), USER, PASSWORD);
        }
        return VoluntarioDAO;
    }
    
    public static FuncionarioDAO getFuncionarioDAO() {
        if(FuncionarioDAO == null) {
            FuncionarioDAO = new FuncionarioDAO(getURL(), USER, PASSWORD);
        }
        return FuncionarioDAO;
    }
    
    public static EquipaDAO getEquipaDAO() {
        if(EquipaDAO == null) {
            EquipaDAO = new EquipaDAO(getURL(), USER, PASSWORD);
        }
        return EquipaDAO;
    }
    
    public static ProjectoDAO getProjectoDAO() {
        if(ProjectoDAO == null) {
            ProjectoDAO = new ProjectoDAO(getURL(), USER, PASSWORD);
        }
        return ProjectoDAO;
    }
    
    public static CandidaturaDAO getCandidaturaDAO() {
        if(CandidaturaDAO == null) {
            CandidaturaDAO = new CandidaturaDAO(getURL(), USER, PASSWORD);
        }
        return CandidaturaDAO;
    }
    
    public static void setProperties(Properties props) {
        DB_TYPE = props.getOrDefault("db_type", DB_TYPE).toString();
        HOST = props.getOrDefault("host", HOST).toString();
        PORT = props.getOrDefault("port", PORT).toString();
        USER = props.getOrDefault("user", USER).toString();
        PASSWORD = props.getOrDefault("password", PASSWORD).toString();
        DATABASE = props.getOrDefault("database", DATABASE).toString();
    }
    
    private static String getURL() {
        return "jdbc:" + DB_TYPE + "://" + HOST + ":" + PORT + "/" + DATABASE;
    }
    
}
