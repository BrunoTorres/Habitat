/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Funcionario;
import pt.uminho.lei.dss.model.Material;
import pt.uminho.lei.dss.model.SGH;

/**
 * FXML Controller class
 *
 * @author joaorua
 */
public class Stock_Controller implements Initializable {

    private SGH habitat;

    @FXML
    TableView<Material> tableStockDisp;
    @FXML
    TableColumn<Material, String> tcStockDispNome;
    @FXML
    TableColumn<Material, String> tcStockDispQuantidade;

    @FXML
    TableView<Material> tableStockIndisp;
    @FXML
    TableColumn<Material, String> tcStockIndispNome;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfQuantidade;
    
    @FXML
    private Button bStockAdd;
    
    @FXML
    private Button bStockEdit;
    
    @FXML
    private Button bStockRem;
    
    @FXML
    private Button bStockSave;
    
    @FXML
    private Button bStockCancelar;
    
    @FXML
    private TabPane tabpaneStock;
       @FXML
    private TextField textProcura;
    
    @FXML
    private Tab tabDisponivel;
    
      @FXML
    private Button okProcura;
    @FXML
    private Tab tabIndisponivel;
    
    
    private Stage anterior;
    private Stage atual;

    private Funcionario funcionario;

    public Stock_Controller() {
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

    public SGH getHabitat() {
        return habitat;
    }

    public void setHabitat(SGH habitat) {
        this.habitat = habitat;
        permissoes(habitat.getF().getPermissao());
        try {
            ObservableList<Material> data = FXCollections.observableArrayList(habitat.getStock());
            this.tableStockDisp.setItems(data);

            ObservableList<Material> data2 = FXCollections.observableArrayList(habitat.getSemStock());
            this.tableStockIndisp.setItems(data2);
        } catch (PersistenceException ex) {
            System.out.println("ERRO " + ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //habitat = new SGH();
        bStockEdit.setDisable(true);
        bStockRem.setDisable(true);
        bStockSave.setVisible(false);
        bStockCancelar.setVisible(false);
        
        tfNome.setEditable(false);
        tfQuantidade.setEditable(false);
        // TODO

        tcStockDispNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        tcStockDispQuantidade.setCellValueFactory(cellData -> cellData.getValue().getQuantidadeProperty());

        tcStockIndispNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        //tcStockDispQuantidade.setCellValueFactory(cellData -> cellData.getValue().getQuantidadeProperty());

    }

    @FXML
    private void tableStickDispOnClick() throws PersistenceException {
        bStockEdit.setDisable(false);
        bStockRem.setDisable(false);
        Material m = tableStockDisp.getSelectionModel().getSelectedItem();
        tfNome.setText(m.getNome());
        tfQuantidade.setText(String.valueOf(m.getQuantidade()));
        //taObs.setText(m.getObs());
    }

    @FXML
    private void tableStickIndispOnClick() throws PersistenceException {
        bStockEdit.setDisable(false);
        bStockRem.setDisable(false);
        Material m = tableStockIndisp.getSelectionModel().getSelectedItem();
        tfNome.setText(m.getNome());
        tfQuantidade.setText(String.valueOf(m.getQuantidade()));

    }
    
    @FXML
    private void bStockAddOnClick(){
        tabpaneStock.setMouseTransparent(true);
        bStockEdit.setDisable(true);
        bStockRem.setDisable(true);
        bStockCancelar.setVisible(true);
        bStockSave.setVisible(true);
        
        tfNome.setPromptText("Introduza o Nome do novo Material");
        tfNome.setEditable(true);
        tfQuantidade.setEditable(true);
        
    }
    
    @FXML
    private void bStockEditOnClick(){
        if((tableStockDisp.getSelectionModel().getSelectedItem() != null)||(tableStockIndisp.getSelectionModel().getSelectedItem()!=null)){
        tabpaneStock.setMouseTransparent(true);
        bStockAdd.setDisable(true);
        bStockRem.setDisable(true);
        bStockCancelar.setVisible(true);
        bStockSave.setVisible(true);
        
        tfNome.setEditable(true);
        tfQuantidade.setEditable(true);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Material.");
            diag.showAndWait();
        }
    }
    
    @FXML
    private void bStockRemOnClick() throws PersistenceException{
        if((tableStockDisp.getSelectionModel().getSelectedItem() != null)||(tableStockIndisp.getSelectionModel().getSelectedItem()!=null)){
        Material m=(tabpaneStock.getSelectionModel().getSelectedItem()==tabDisponivel)?tableStockDisp.getSelectionModel().getSelectedItem():tableStockIndisp.getSelectionModel().getSelectedItem();
        m.setQuantidade(0);
        habitat.setMaterial(m);
        ObservableList<Material> data = FXCollections.observableArrayList(habitat.getStock());
        this.tableStockDisp.setItems(data);

        ObservableList<Material> data2 = FXCollections.observableArrayList(habitat.getSemStock());
        this.tableStockIndisp.setItems(data2);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Material.");
            diag.showAndWait();
        }
    }
    
    @FXML
    private void bStockSaveOnClick() throws PersistenceException{
        if (!bStockEdit.isDisable()){
            Material m=(tabpaneStock.getSelectionModel().getSelectedItem()==tabDisponivel)?tableStockDisp.getSelectionModel().getSelectedItem():tableStockIndisp.getSelectionModel().getSelectedItem();
            m.setNome(tfNome.getText());
            m.setQuantidade(Integer.valueOf(tfQuantidade.getText()));
            habitat.setMaterial(m);
        } else {
            Material m = new Material();
            m.setFuncionario(habitat.getF().getIdFuncionario());
            m.setNome(tfNome.getText());
            m.setQuantidade(Integer.valueOf(tfQuantidade.getText()));
            habitat.addMaterial(m);
        }
        bStockCancelarOnClick();
        ObservableList<Material> data = FXCollections.observableArrayList(habitat.getStock());
        this.tableStockDisp.setItems(data);

        ObservableList<Material> data2 = FXCollections.observableArrayList(habitat.getSemStock());
        this.tableStockIndisp.setItems(data2);
    }
    
    @FXML
    private void bStockCancelarOnClick(){
        tabpaneStock.setMouseTransparent(false);
        bStockAdd.setDisable(false);
        bStockEdit.setDisable(false);
        bStockRem.setDisable(false);
        bStockCancelar.setVisible(false);
        bStockSave.setVisible(false);
        
        tfNome.setEditable(false);
        tfQuantidade.setEditable(false);
        
    }
    
    private void permissoes(int idfuncao){
        switch(idfuncao){
            case 1:
                bStockAdd.setVisible(false);
                bStockEdit.setVisible(false);
                bStockRem.setVisible(false);
                break;
            case 2:
                break;
            case 3:
                bStockAdd.setVisible(false);
                bStockEdit.setVisible(false);
                bStockRem.setVisible(false);
                break;
            default:
                break;
        }
    }
    
    
     @FXML
    private void procuraStock() throws PersistenceException {
        String nome = textProcura.getText();
        if (!nome.equals("")) {

            ObservableList<Material> data = FXCollections.observableArrayList(habitat.getMaterialbyName(nome));
            this.tableStockDisp.setItems(data);

            ObservableList<Material> data2 = FXCollections.observableArrayList(habitat.getMaterialbyNameSem(nome));
            this.tableStockIndisp.setItems(data2);
        
           

        }
        else if (nome.equals("")){
          
            ObservableList<Material> data = FXCollections.observableArrayList(habitat.getStock());
            this.tableStockDisp.setItems(data);

            ObservableList<Material> data2 = FXCollections.observableArrayList(habitat.getSemStock());
            this.tableStockIndisp.setItems(data2);
            
        }

    }
}
