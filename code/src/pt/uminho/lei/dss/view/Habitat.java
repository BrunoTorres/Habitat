/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.view;

import java.util.Properties;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.uminho.lei.dss.db.FuncionarioDAO;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.db.HabitatDAO;
import pt.uminho.lei.dss.model.Voluntario;


/**
 *
 * @author JoaoMano
 */
public class Habitat extends Application {
    
    //private ObservableList<Voluntario> vols = FXCollections.observableArrayList();
    //private HabitatDAO HabitatDAO;
    
    public Habitat() throws PersistenceException {
        /*Properties props = new Properties();
        props.setProperty("user", "habitat");
        props.setProperty("password", "password");
        props.setProperty("host", "servidor.freeum.info");
        props.setProperty("port", "5223");
        props.setProperty("db_type", "mysql");
        HabitatDAO.setProperties(props);*/
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Login_Controller log_c;
        
        Parent root = loader.load();
        log_c = loader.getController();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Login");
        
        log_c.setAtual(stage);
        
        FuncionarioDAO funcionarioDAO = HabitatDAO.getFuncionarioDAO();
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
