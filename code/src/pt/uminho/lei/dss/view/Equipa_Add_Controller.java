/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Equipa;
import pt.uminho.lei.dss.model.SGH;



public class Equipa_Add_Controller implements Initializable {
  
    @FXML
    private Button bCancelar;
    
    @FXML
    private Button bConfirmar;
    
    @FXML
    private TextField tfNome;
    
    @FXML
    private ComboBox cbPais;
    
    private Stage anterior;
    private Stage atual;

    private SGH habitat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfNome.setPromptText("Introduza o Nome da nova Equipa");
        
        
        
    }    

    public Stage getAnterior() {
        return anterior;
    }

    public Stage getAtual() {
        return atual;
    }


    public SGH getHabitat() {
        return habitat;
    }

    public void setHabitat(SGH habitat) throws PersistenceException {
        this.habitat = habitat;
        ObservableList<String> paises = FXCollections.observableArrayList(this.habitat.getPaises());
        this.cbPais.setItems(paises);
    }
    
    public void setAnterior(Stage anterior) {
        this.anterior = anterior;
        this.anterior.hide();
    }

    public void setAtual(Stage atual) {
        this.atual = atual;
        this.atual.setOnCloseRequest((WindowEvent event) -> {
            anterior.show();
        });
    }
    
    @FXML
    public void bCancelarOnClick() {
        this.anterior.show();
        this.atual.close();
    }
    
    @FXML
    public void bConfirmarOnClick() throws PersistenceException {
        Equipa e = new Equipa();
        e.setFuncionario(habitat.getF().getIdFuncionario());
        e.setNome(tfNome.getText());
        e.setPais(habitat.getPaisId((String) cbPais.getSelectionModel().getSelectedItem()));
        habitat.addEquipa(e);
        
        bCancelarOnClick();
    }
}
