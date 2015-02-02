/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Agregado;
import pt.uminho.lei.dss.model.Candidatura;
import pt.uminho.lei.dss.model.Evento;
import pt.uminho.lei.dss.model.SGH;

public class Eventos_Controller implements Initializable {

    @FXML
    private Button bEventoAdd;

    @FXML
    private Button bEventoEdit;

    @FXML
    private Button bEventoRem;

    @FXML
    private Button bEventoSave;

    @FXML
    private Button bEventoCancelar;

    @FXML
    private Tab tabEventos;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfOrganizador;

    @FXML
    private TextField tfTotal;

    @FXML
    private TextArea taNotas;

    @FXML
    private DatePicker dpData;
    @FXML
    private TextField textProcura;
    @FXML
    private Button okProcura;
    @FXML
    TableView<Evento> tableEventos;
    @FXML
    TableColumn<Evento, String> tcNome;
    @FXML
    TableColumn<Evento, String> tcData;

    private Stage anterior;
    private Stage atual;

    private SGH habitat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bEventoEdit.setDisable(true);
        bEventoRem.setDisable(true);
        bEventoSave.setVisible(false);
        bEventoCancelar.setVisible(false);

        tfNome.setEditable(false);
        tfOrganizador.setEditable(false);
        dpData.setMouseTransparent(true);
        tfTotal.setEditable(false);
        taNotas.setEditable(false);

        tcNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
        tcData.setCellValueFactory(cellData -> cellData.getValue().getDataProperty());

    }

    @FXML
    private void tableEventosOnClick() {
        bEventoEdit.setDisable(false);
        bEventoRem.setDisable(false);
        Evento e = tableEventos.getSelectionModel().getSelectedItem();
        tfNome.setText(e.getNome().get());
        tfOrganizador.setText(e.getOrganizador());
        dpData.setValue(e.getData());
        tfTotal.setText(String.valueOf(e.getTotal()));
        taNotas.setText(e.getNotas());
    }

    @FXML
    private void bEventoAddOnClick() {
        tabEventos.setDisable(true);
        bEventoEdit.setDisable(true);
        bEventoRem.setDisable(true);
        bEventoSave.setVisible(true);
        bEventoCancelar.setVisible(true);

        tfOrganizador.setPromptText("Introduza o Organizador do novo Evento");

        tfNome.setEditable(true);
        tfOrganizador.setEditable(true);
        dpData.setMouseTransparent(false);
        taNotas.setEditable(true);

        tfNome.clear();
        tfOrganizador.clear();
        dpData.setValue(LocalDate.now());
        tfTotal.clear();
        taNotas.clear();

    }

    @FXML
    private void bEventoEditOnClick() {
        Evento e = tableEventos.getSelectionModel().getSelectedItem();
        if (e != null) {
        tabEventos.setDisable(true);
        bEventoAdd.setDisable(true);
        bEventoRem.setDisable(true);
        bEventoSave.setVisible(true);
        bEventoCancelar.setVisible(true);

        tfNome.setEditable(true);
        tfOrganizador.setEditable(true);
        dpData.setMouseTransparent(false);
        taNotas.setEditable(true);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Evento.");
            diag.showAndWait();
        }
    }

    @FXML
    private void bEventoRemOnClick() throws PersistenceException {
        Evento e = tableEventos.getSelectionModel().getSelectedItem();
        if (e != null) {
            if (habitat.remEvento(e.getIdEvento())) {
                ObservableList<Evento> dataEventos = FXCollections.observableArrayList(habitat.getEventos());
                this.tableEventos.setItems(dataEventos);
            } else {
                // REMOVER TODAS AS DOAÇÔES???
                Alert diag = new Alert(Alert.AlertType.ERROR);
                diag.setTitle("Erro");
                diag.setHeaderText(null);
                diag.setContentText("Não Pode remover o Evento");
                diag.showAndWait();

            }
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Evento.");
            diag.showAndWait();
        }
    }

    @FXML
    private void bEventoSaveOnClick() throws PersistenceException {
        if (!bEventoEdit.isDisable()) {
            Evento e = tableEventos.getSelectionModel().getSelectedItem();
            e.setNome(new SimpleStringProperty(tfNome.getText()));
            e.setOrganizador(tfOrganizador.getText());
            e.setData(dpData.getValue());
            e.setNotas(taNotas.getText());
            habitat.setEvento(e);
        } else {
            Evento e = new Evento();
            e.setNome(new SimpleStringProperty(tfNome.getText()));
            e.setOrganizador(tfOrganizador.getText());
            e.setData(dpData.getValue());
            e.setNotas(taNotas.getText());
            e.setFuncionario(habitat.getF().getIdFuncionario());
            habitat.addEvento(e);
        }
        ObservableList<Evento> dataEventos = FXCollections.observableArrayList(habitat.getEventos());
        this.tableEventos.setItems(dataEventos);
        bEventoCancelarOnClick();
    }

    @FXML
    private void bEventoCancelarOnClick() {
        tabEventos.setDisable(false);
        bEventoAdd.setDisable(false);
        bEventoEdit.setDisable(false);
        bEventoRem.setDisable(false);
        bEventoSave.setVisible(false);
        bEventoCancelar.setVisible(false);

        tfOrganizador.setEditable(false);
        dpData.setMouseTransparent(true);
        taNotas.setEditable(false);
    }

    private void permissoes(int idfuncao) {
        switch (idfuncao) {
            case 1:
                bEventoAdd.setVisible(false);
                bEventoEdit.setVisible(false);
                bEventoRem.setVisible(false);
                break;
            case 2:
                bEventoAdd.setVisible(false);
                bEventoEdit.setVisible(false);
                bEventoRem.setVisible(false);
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public Stage getAnterior() {
        return anterior;
    }

    public void setAnterior(Stage anterior) {
        this.anterior = anterior;
        this.anterior.hide();
    }

    public Stage getAtual() {
        return atual;
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

    public void setHabitat(SGH habitat) throws PersistenceException {
        this.habitat = habitat;
        permissoes(habitat.getF().getPermissao());
        ObservableList<Evento> dataEventos = FXCollections.observableArrayList(habitat.getEventos());
        this.tableEventos.setItems(dataEventos);
    }

    @FXML
    private void procuraEvento() throws PersistenceException {
        String nome = textProcura.getText();
        if (!nome.equals("")) {

            ObservableList<Evento> dataEventos = FXCollections.observableArrayList(habitat.getEventosByName(nome));
            this.tableEventos.setItems(dataEventos);

        } else if (nome.equals("")) {

            ObservableList<Evento> dataEventos = FXCollections.observableArrayList(habitat.getEventos());
            this.tableEventos.setItems(dataEventos);

        }
    }

}
