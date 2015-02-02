package pt.uminho.lei.dss.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pt.uminho.lei.dss.model.Candidatura;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Agregado;
import pt.uminho.lei.dss.model.SGH;

public class Candidatura_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane tabpaneListaCandidaturas;

    @FXML
    private TabPane tabpaneAux;

    @FXML
    private Tab tabInfo;

    @FXML
    private Tab tabAgregado;

    @FXML
    private Button bCandidaturaAdd;

    @FXML
    private Button bCandidaturaEdit;

    @FXML
    private Button bCandidaturaRem;

    @FXML
    private Button bCandidaturaSave;

    @FXML
    private Button bCandidaturaCancelar;

    @FXML
    TableView<Candidatura> tableCandidaturas;
    @FXML
    TableColumn<Candidatura, String> tcCandidaturaNumero;
    @FXML
    TableColumn<Candidatura, String> tcCandidaturaEstado;

    @FXML
    private ComboBox comboEstadoCandidatura;

    @FXML
    private TextField tfMorada;

    @FXML
    private TextField textProcuraCandi;
    @FXML
    private Button okProcura;

    @FXML
    private TextField tfCodPostal;

    @FXML
    private TextField tfLocalidade;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfMensalidade;

    @FXML
    private ComboBox comboEstadoOrcamento;

    @FXML
    private TextField tfOrcamento;

    @FXML
    private TextField tfRendimento;

    @FXML
    private TextField tfObservacoes;

    @FXML
    TableView<Agregado> tableAgregado;
    @FXML
    TableColumn<Agregado, String> tcAgregadoNome;
    @FXML
    TableColumn<Agregado, String> tcAgregadoParentesco;
    @FXML
    TableColumn<Agregado, String> tcAgregadoNif;

    @FXML
    private TextField tfAgreNome;

    @FXML
    private DatePicker dpAgreDataNasc;

    @FXML
    private TextField tfAgreParentesco;

    @FXML
    private TextField tfAgreProfissao;

    @FXML
    private ComboBox cbAgreEstadoCivil;

    @FXML
    private TextField tfAgreEscolaridade;

    @FXML
    private TextField tfAgreNif;

    @FXML
    private CheckBox cbAgreCandidato;

    @FXML
    private TextField tfAgreVinculoLaboral;

    @FXML
    private TextField tfAgreRendimento1;

    @FXML
    private TextField tfAgreRendimento2;

    @FXML
    private TextField tfAgreNaturalidade;

    @FXML
    private TextField tfAgreNacionalidade;

    @FXML
    private Button button_download;

    @FXML
    private Button button_upload;

    @FXML
    private Button bAgregadoAdd;

    @FXML
    private Button bAgregadoEdit;

    @FXML
    private Button bAgregadoRem;

    @FXML
    private Button bAgregadoSave;

    @FXML
    private Button bAgregadoCancelar;

    @FXML
    private Circle circleEstado;

    private Stage anterior;
    private Stage atual;

    private SGH habitat;

    //private Funcionario funcionario;
    public Candidatura_Controller() {
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabAgregado.setDisable(true);

        ArrayList<String> estados = new ArrayList<>();
        estados.add("Rejeitada");
        estados.add("Em Analise");
        estados.add("Aprovada");
        ObservableList<String> options = FXCollections.observableArrayList(estados);
        comboEstadoCandidatura.setItems(options);

        ArrayList<String> estados2 = new ArrayList<>();
        estados2.add("Rejeitado");
        estados2.add("Em Analise");
        estados2.add("Aprovado");
        ObservableList<String> options2 = FXCollections.observableArrayList(estados2);
        comboEstadoOrcamento.setItems(options2);

        ArrayList<String> estados3 = new ArrayList<>();
        estados3.add("Solteiro");
        estados3.add("Casado");
        estados3.add("Divorciado");
        estados3.add("Viuvo");
        ObservableList<String> options3 = FXCollections.observableArrayList(estados3);
        cbAgreEstadoCivil.setItems(options3);

        bCandidaturaSave.setVisible(false);
        bCandidaturaCancelar.setVisible(false);
        bCandidaturaEdit.setDisable(true);
        bCandidaturaRem.setDisable(true);

        //Disable TextFields Candidatura
        tfMorada.setEditable(false);
        tfCodPostal.setEditable(false);
        tfLocalidade.setEditable(false);
        tfMensalidade.setEditable(false);
        tfObservacoes.setEditable(false);
        tfOrcamento.setEditable(false);
        tfRendimento.setEditable(false);
        tfTelefone.setEditable(false);
        comboEstadoCandidatura.setMouseTransparent(true);
        comboEstadoOrcamento.setMouseTransparent(true);

        //Disable TextFields Agregado
        tfAgreEscolaridade.setEditable(false);
        tfAgreNif.setEditable(false);
        tfAgreNome.setEditable(false);
        tfAgreParentesco.setEditable(false);
        tfAgreProfissao.setEditable(false);
        tfAgreRendimento1.setEditable(false);
        tfAgreRendimento2.setEditable(false);
        tfAgreVinculoLaboral.setEditable(false);
        cbAgreCandidato.setMouseTransparent(true);
        cbAgreEstadoCivil.setMouseTransparent(true);
        tfAgreNaturalidade.setEditable(false);
        tfAgreNacionalidade.setEditable(false);
        button_download.setDisable(true);
        button_upload.setDisable(true);

        bAgregadoSave.setVisible(false);
        bAgregadoCancelar.setVisible(false);
        bAgregadoEdit.setDisable(true);
        bAgregadoRem.setDisable(true);

        //
        tcCandidaturaNumero.setCellValueFactory(cellData -> cellData.getValue().getNrProperty());
        tcCandidaturaEstado.setCellValueFactory(cellData -> cellData.getValue().getEstadoCandidaturaProperty());

    }

    @FXML
    private void tableCandidaturasOnClick() throws PersistenceException {
        if (!this.tableCandidaturas.getSelectionModel().isEmpty()) {
            tabAgregado.setDisable(false);
            bCandidaturaEdit.setDisable(false);
            bCandidaturaRem.setDisable(false);
            this.tabAgregado.setDisable(false);
            Candidatura c = tableCandidaturas.getSelectionModel().getSelectedItem();
            tfMorada.setText(c.getRua());
            tfRendimento.setText(String.valueOf(c.getRendimentoTotal()));
            tfCodPostal.setText(c.getCodPostal());
            tfLocalidade.setText(c.getLocalidade());
            tfOrcamento.setText(String.valueOf(c.getOrcamento()));
            tfTelefone.setText(String.valueOf(c.getTelefone()));
            tfMensalidade.setText(String.valueOf(c.getMensalidadeAtribuida()));
            comboEstadoCandidatura.getSelectionModel().select(c.getEstadoCandidatura());
            comboEstadoOrcamento.getSelectionModel().select(c.getEstadoOrcamento());
            if (c.getDocumento() != null) {
                button_download.setDisable(false);
            }
            //
            tcAgregadoNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
            tcAgregadoParentesco.setCellValueFactory(cellData -> cellData.getValue().getParentescoProperty());
            tcAgregadoNif.setCellValueFactory(cellData -> cellData.getValue().getNifProperty());

            try {
                ObservableList<Agregado> dataAgregados = FXCollections.observableArrayList(habitat.getCandidatura(c.getNr()).getAgregado());
                this.tableAgregado.setItems(dataAgregados);
            } catch (PersistenceException ex) {
                Logger.getLogger(Candidatura_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (comboEstadoCandidatura.getSelectionModel().getSelectedIndex() == 2) {
                circleEstado.setFill(Color.web("#21ff23", 1));
            } else if (comboEstadoCandidatura.getSelectionModel().getSelectedIndex() == 0) {
                circleEstado.setFill(Color.RED);
            } else {
                circleEstado.setFill(Color.YELLOW);
            }
            button_download.setDisable(!habitat.existeCandidaturaDocumento(c.getNr()));
            button_upload.setDisable(false);

        }
    }

    @FXML
    private void tableAgregadoOnClick() {
        if (!tableAgregado.getSelectionModel().isEmpty()) {
            bAgregadoEdit.setDisable(false);
            bAgregadoRem.setDisable(false);
            Agregado a = tableAgregado.getSelectionModel().getSelectedItem();
            tfAgreNome.setText(a.getNome());
            dpAgreDataNasc.setValue(a.getDataNascimento());
            tfAgreParentesco.setText(a.getParentesco());
            tfAgreProfissao.setText(a.getProfissao());
            cbAgreEstadoCivil.setValue(a.getEstadoCivil());
            tfAgreEscolaridade.setText(a.getEscolaridade());
            tfAgreNif.setText(String.valueOf(a.getNif()));
            cbAgreCandidato.setSelected(a.getCandidato() != 0);
            tfAgreVinculoLaboral.setText(a.getVinculoLaboral());
            tfAgreRendimento1.setText(String.valueOf(a.getRendimento1()));
            tfAgreRendimento2.setText(String.valueOf(a.getRendimento2()));
            tfAgreNaturalidade.setText(a.getNaturalidade());
            tfAgreNacionalidade.setText(a.getNacionalidade());
        }
    }

    @FXML
    private void bCandidaturaAddOnClick() {

        tabpaneListaCandidaturas.setMouseTransparent(true);
        tabpaneAux.getSelectionModel().select(tabInfo);
        tabAgregado.setDisable(true);

        bCandidaturaEdit.setDisable(true);
        bCandidaturaRem.setDisable(true);
        bCandidaturaSave.setVisible(true);
        bCandidaturaCancelar.setVisible(true);

        //Enable TextFields Candidatura
        tfMorada.setEditable(true);
        tfMorada.setText("");
        tfMorada.setPromptText("Introduza a Morada da Nova Candidatura");
        tfCodPostal.setEditable(true);
        tfCodPostal.setText("");
        tfLocalidade.setEditable(true);
        tfLocalidade.setText("");
        tfMensalidade.setEditable(true);
        tfMensalidade.setText("");
        tfObservacoes.setEditable(true);
        tfObservacoes.setText("");
        tfOrcamento.setEditable(true);
        tfOrcamento.setText("");
        tfRendimento.setEditable(false);
        tfRendimento.setText("");
        tfTelefone.setEditable(true);
        tfTelefone.setText("");
        comboEstadoCandidatura.setMouseTransparent(false);
        comboEstadoOrcamento.setMouseTransparent(false);
        
        tableCandidaturas.getItems().clear();
        tableAgregado.getItems().clear();
        
    }

    @FXML
    private void bCandidaturaEditOnClick() {
        if (tableCandidaturas.getSelectionModel().getSelectedItem() != null) {
            tabpaneListaCandidaturas.setMouseTransparent(true);
            tabpaneAux.getSelectionModel().select(tabInfo);
            tabAgregado.setDisable(true);
            bCandidaturaAdd.setDisable(true);
            bCandidaturaRem.setDisable(true);
            bCandidaturaSave.setVisible(true);
            bCandidaturaCancelar.setVisible(true);
            button_upload.setVisible(true);

            //Enable TextFields Candidatura
            tfMorada.setEditable(true);
            tfCodPostal.setEditable(true);
            tfLocalidade.setEditable(true);
            tfMensalidade.setEditable(true);
            tfObservacoes.setEditable(true);
            tfOrcamento.setEditable(true);
            tfRendimento.setEditable(false);
            tfTelefone.setEditable(true);
            comboEstadoCandidatura.setMouseTransparent(false);
            comboEstadoOrcamento.setMouseTransparent(false);

            comboEstadoCandidatura.setOnHidden((Event t) -> {
                if (comboEstadoCandidatura.getValue().equals("Aprovada")) {
                    circleEstado.setFill(Color.web("#21ff23", 1));
                } else if (comboEstadoCandidatura.getValue().equals("Rejeitada")) {
                    circleEstado.setFill(Color.RED);
                } else {
                    circleEstado.setFill(Color.YELLOW);
                }
            });
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione uma Candidatura.");
            diag.showAndWait();
        }
    }

    @FXML
    private void bCandidaturaRemOnClick() throws PersistenceException {

        if (tableCandidaturas.getSelectionModel().getSelectedItem() != null) {
            Candidatura c = tableCandidaturas.getSelectionModel().getSelectedItem();
            if (habitat.remCandidatura(c.getNr())) {

                ObservableList<Candidatura> dataCandidaturas = FXCollections.observableArrayList(habitat.getCandidaturas());
                this.tableCandidaturas.setItems(dataCandidaturas);
            } else {
                // REMOVER TODAS AS DOAÇÔES???
                Alert diag = new Alert(Alert.AlertType.ERROR);
                diag.setTitle("Erro");
                diag.setHeaderText(null);
                diag.setContentText("Não Pode remover a Candidatura");
                diag.showAndWait();

            }
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione uma Candidatura.");
            diag.showAndWait();
        }
    }

    @FXML
    private void bCandidaturaSaveOnClick() throws PersistenceException {
        try {
            if (!bCandidaturaEdit.isDisable()) {
                Candidatura c = tableCandidaturas.getSelectionModel().getSelectedItem();
                c.setRua(tfMorada.getText());
                c.setCodPostal(tfCodPostal.getText());
                c.setLocalidade(tfLocalidade.getText());
                c.setEstadoCandidatura(comboEstadoCandidatura.getSelectionModel().getSelectedIndex());
                c.setTelefone(Integer.valueOf(tfTelefone.getText()));
                c.setMensalidadeAtribuida(Double.valueOf(tfMensalidade.getText()));
                c.setOrcamento(Double.valueOf(tfOrcamento.getText()));
                c.setEstadoOrcamento(comboEstadoOrcamento.getSelectionModel().getSelectedIndex());
                //c.setRendimentoTotal(Double.valueOf(tfRendimento.getText()));
                c.setObsRejecao(tfObservacoes.getText());
                habitat.setCandidatura(c);
            } else {
                Candidatura c = new Candidatura();
                c.setFuncionario(habitat.getF().getIdFuncionario());
                c.setRua(tfMorada.getText());
                c.setCodPostal(tfCodPostal.getText());
                c.setLocalidade(tfLocalidade.getText());
                c.setEstadoCandidatura(comboEstadoCandidatura.getSelectionModel().getSelectedIndex());
                c.setTelefone(Integer.valueOf(tfTelefone.getText()));
                c.setMensalidadeAtribuida(Double.valueOf(tfMensalidade.getText()));
                c.setOrcamento(Double.valueOf(tfOrcamento.getText()));
                c.setEstadoOrcamento(comboEstadoOrcamento.getSelectionModel().getSelectedIndex());
                c.setRendimentoTotal(0);
                c.setObsRejecao(tfObservacoes.getText());
                habitat.addCandidatura(c);
            }

            tabpaneListaCandidaturas.setMouseTransparent(false);
            tabAgregado.setDisable(false);
            bCandidaturaAdd.setDisable(false);
            bCandidaturaEdit.setDisable(false);
            bCandidaturaRem.setDisable(false);
            bCandidaturaCancelar.setVisible(false);
            bCandidaturaSave.setVisible(false);
            button_upload.setVisible(false);

            //Disable TextFields Candidatura
            tfMorada.setEditable(false);
            tfCodPostal.setEditable(false);
            tfLocalidade.setEditable(false);
            tfMensalidade.setEditable(false);
            tfObservacoes.setEditable(false);
            tfOrcamento.setEditable(false);
            tfRendimento.setEditable(false);
            tfTelefone.setEditable(false);
            comboEstadoCandidatura.setMouseTransparent(true);
            comboEstadoOrcamento.setMouseTransparent(true);

            ObservableList<Candidatura> dataCandidaturas = FXCollections.observableArrayList(habitat.getCandidaturas());
            this.tableCandidaturas.setItems(dataCandidaturas);
        } catch (NumberFormatException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Dados inválidos");
            a.showAndWait();
        }

    }

    @FXML
    private void bCandidaturaCancelarOnClick() {
        tabpaneListaCandidaturas.setMouseTransparent(false);
        tabAgregado.setDisable(false);
        bCandidaturaAdd.setDisable(false);
        bCandidaturaEdit.setDisable(false);
        bCandidaturaRem.setDisable(false);
        bCandidaturaSave.setVisible(false);
        bCandidaturaCancelar.setVisible(false);
        button_upload.setVisible(false);

        //Disable TextFields Candidatura
        tfMorada.setEditable(false);
        tfCodPostal.setEditable(false);
        tfLocalidade.setEditable(false);
        tfMensalidade.setEditable(false);
        tfObservacoes.setEditable(false);
        tfOrcamento.setEditable(false);
        tfRendimento.setEditable(false);
        tfTelefone.setEditable(false);
        comboEstadoCandidatura.setMouseTransparent(true);
        comboEstadoOrcamento.setMouseTransparent(true);

    }

    @FXML
    private void bAgregadoAddOnClick() {
        tabpaneListaCandidaturas.setMouseTransparent(true);
        tabInfo.setDisable(true);
        tabpaneAux.getSelectionModel().select(tabAgregado);
        bAgregadoEdit.setDisable(true);
        bAgregadoRem.setDisable(true);
        bAgregadoCancelar.setVisible(true);
        bAgregadoSave.setVisible(true);

        //Enable TextFields Agregado
        tfAgreEscolaridade.setEditable(true);
        tfAgreEscolaridade.setText("");
        tfAgreNif.setEditable(true);
        tfAgreNif.setText("");
        tfAgreNome.setEditable(true);
        tfAgreNome.setText("");
        tfAgreNome.setPromptText("Introduza o Nome do novo Agregado");
        tfAgreParentesco.setEditable(true);
        tfAgreParentesco.setText("");
        tfAgreProfissao.setEditable(true);
        tfAgreProfissao.setText("");
        tfAgreRendimento1.setEditable(true);
        tfAgreRendimento1.setText("");
        tfAgreRendimento2.setEditable(true);
        tfAgreRendimento2.setText("");
        tfAgreVinculoLaboral.setEditable(true);
        tfAgreVinculoLaboral.setText("");
        tfAgreNaturalidade.setEditable(true);
        tfAgreNaturalidade.setText("");
        tfAgreNacionalidade.setEditable(true);
        tfAgreNacionalidade.setText("");
        cbAgreCandidato.setMouseTransparent(false);
        cbAgreEstadoCivil.setMouseTransparent(false);
        cbAgreEstadoCivil.getSelectionModel().select(0);
    }

    @FXML
    private void bAgregadoEditOnClick() {
        tabpaneListaCandidaturas.setMouseTransparent(true);
        tabInfo.setDisable(true);
        tabpaneAux.getSelectionModel().select(tabAgregado);
        bAgregadoAdd.setDisable(true);
        bAgregadoRem.setDisable(true);
        bAgregadoCancelar.setVisible(true);
        bAgregadoSave.setVisible(true);

        //Enable TextFields Agregado
        tfAgreEscolaridade.setEditable(true);
        tfAgreNif.setEditable(true);
        tfAgreNome.setEditable(true);
        tfAgreParentesco.setEditable(true);
        tfAgreProfissao.setEditable(true);
        tfAgreRendimento1.setEditable(true);
        tfAgreRendimento2.setEditable(true);
        tfAgreVinculoLaboral.setEditable(true);
        cbAgreCandidato.setMouseTransparent(false);
        cbAgreEstadoCivil.setMouseTransparent(false);
        tfAgreNaturalidade.setEditable(true);
        tfAgreNacionalidade.setEditable(true);
    }

    @FXML
    private void bAgregadoRemOnClick() throws PersistenceException {
        int nif = tableAgregado.getSelectionModel().getSelectedItem().getNif();
        habitat.remAgregado(nif);

        ObservableList<Agregado> dataAgregados = FXCollections.observableArrayList(habitat.getCandidatura(tableCandidaturas.getSelectionModel().getSelectedItem().getNr()).getAgregado());
        this.tableAgregado.setItems(dataAgregados);
    }

    @FXML
    private void bAgregadoSaveOnClick() throws PersistenceException, SQLException {
        try {
            if (!bAgregadoEdit.isDisable()) {
                Agregado a = tableAgregado.getSelectionModel().getSelectedItem();
                a.setCandidatura(tableCandidaturas.getSelectionModel().getSelectedItem().getNr());
                a.setNome(tfAgreNome.getText());
                a.setDataNascimento(dpAgreDataNasc.getValue());
                a.setParentesco(tfAgreParentesco.getText());
                a.setProfissao(tfAgreProfissao.getText());
                a.setEstadoCivil((String) cbAgreEstadoCivil.getSelectionModel().getSelectedItem());
                a.setEscolaridade(tfAgreEscolaridade.getText());
                a.setNif(Integer.valueOf(tfAgreNif.getText()));
                a.setCandidato(cbAgreCandidato.isSelected() ? 1 : 0);
                a.setVinculoLaboral(tfAgreVinculoLaboral.getText());
                a.setRendimento1(Double.valueOf(tfAgreRendimento1.getText()));
                a.setRendimento2(Double.valueOf(tfAgreRendimento2.getText()));
                a.setNaturalidade(tfAgreNaturalidade.getText());
                a.setNacionalidade(tfAgreNacionalidade.getText());

                habitat.setAgregado(a);

            } else {
                Agregado a = new Agregado();
                a.setCandidatura(tableCandidaturas.getSelectionModel().getSelectedItem().getNr());

                a.setNome(tfAgreNome.getText());
                a.setDataNascimento(dpAgreDataNasc.getValue());
                a.setParentesco(tfAgreParentesco.getText());
                a.setProfissao(tfAgreProfissao.getText());
                a.setEstadoCivil((String) cbAgreEstadoCivil.getSelectionModel().getSelectedItem());
                a.setEscolaridade(tfAgreEscolaridade.getText());
                a.setNif(Integer.valueOf(tfAgreNif.getText()));
                a.setCandidato(cbAgreCandidato.isSelected() ? 1 : 0);
                a.setVinculoLaboral(tfAgreVinculoLaboral.getText());
                a.setRendimento1(Double.valueOf(tfAgreRendimento1.getText()));
                a.setRendimento2(Double.valueOf(tfAgreRendimento2.getText()));
                a.setNaturalidade(tfAgreNaturalidade.getText());
                a.setNacionalidade(tfAgreNacionalidade.getText());

                habitat.addAgregado(a);

            }

            tabpaneListaCandidaturas.setMouseTransparent(false);
            tabInfo.setDisable(false);
            //Disable TextFields Agregado
            tfAgreEscolaridade.setEditable(false);
            tfAgreNif.setEditable(false);
            tfAgreNome.setEditable(false);
            tfAgreParentesco.setEditable(false);
            tfAgreProfissao.setEditable(false);
            tfAgreRendimento1.setEditable(false);
            tfAgreRendimento2.setEditable(false);
            tfAgreVinculoLaboral.setEditable(false);
            cbAgreCandidato.setMouseTransparent(true);
            cbAgreEstadoCivil.setMouseTransparent(true);

            bAgregadoAdd.setDisable(false);
            bAgregadoEdit.setDisable(false);
            bAgregadoRem.setDisable(false);
            bAgregadoSave.setVisible(false);
            bAgregadoCancelar.setVisible(false);
            ObservableList<Agregado> dataAgregados = FXCollections.observableArrayList(habitat.getCandidatura(tableCandidaturas.getSelectionModel().getSelectedItem().getNr()).getAgregado());
            this.tableAgregado.setItems(dataAgregados);
            tfRendimento.setText(String.valueOf(habitat.getCandidatura(tableCandidaturas.getSelectionModel().getSelectedItem().getNr()).getRendimentoTotal()));
        } catch (NumberFormatException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Dados inválidos");
            a.showAndWait();
        }
    }

    @FXML
    private void bAgregadoCancelarOnClick() {
        tabpaneListaCandidaturas.setMouseTransparent(false);
        tabInfo.setDisable(false);
        bAgregadoAdd.setDisable(false);
        bAgregadoEdit.setDisable(false);
        bAgregadoRem.setDisable(false);

        //Disable TextFields Agregado
        tfAgreEscolaridade.setEditable(false);
        tfAgreNif.setEditable(false);
        tfAgreNome.setEditable(false);
        tfAgreParentesco.setEditable(false);
        tfAgreProfissao.setEditable(false);
        tfAgreRendimento1.setEditable(false);
        tfAgreRendimento2.setEditable(false);
        tfAgreVinculoLaboral.setEditable(false);
        cbAgreCandidato.setMouseTransparent(true);
        cbAgreEstadoCivil.setMouseTransparent(true);
        tfAgreNaturalidade.setEditable(false);
        tfAgreNacionalidade.setEditable(false);

        bAgregadoSave.setVisible(false);
        bAgregadoCancelar.setVisible(false);

    }

    @FXML
    private void buttonDownloadOnClick() throws PersistenceException {
        try {
            Candidatura c = tableCandidaturas.getSelectionModel().getSelectedItem();
            c.setDocumento(habitat.getCandidaturaDocumento(c.getNr()));
            System.out.println("DOCUMENTO: " + c.getDocumento());
            InputStream initialStream = c.getDocumento();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Habitat - Guardar Documento");
            //chooser.showDialog(null);
            String path = chooser.showDialog(null).getPath();
            System.out.println("PATH: " + path);
            File targetFile = new File(path + "/" + c.getNr() + ".pdf");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            System.out.println("Guardar Documento!!");
        } catch (IOException ex) {
            Logger.getLogger(Voluntarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void buttonUploadOnClick() throws PersistenceException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        Candidatura c = tableCandidaturas.getSelectionModel().getSelectedItem();
        if (file != null) {
            try {
                c.setDocumento(new FileInputStream(file));
                button_download.setDisable(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Voluntarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            habitat.setCandidatura(c);
            bCandidaturaCancelarOnClick();
        }
    }

    private void permissoes(int idfuncao) {
        switch (idfuncao) {
            case 1:

                break;
            case 2:
                bCandidaturaAdd.setVisible(false);
                bCandidaturaEdit.setVisible(false);
                bCandidaturaRem.setVisible(false);
                bAgregadoAdd.setVisible(false);
                bAgregadoEdit.setVisible(false);
                bAgregadoRem.setVisible(false);

            case 3:
                bCandidaturaAdd.setVisible(false);
                bCandidaturaEdit.setVisible(false);
                bCandidaturaRem.setVisible(false);
                bAgregadoAdd.setVisible(false);
                bAgregadoEdit.setVisible(false);
                bAgregadoRem.setVisible(false);
                break;
            default:
                break;
        }
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
            ObservableList<Candidatura> dataCandidaturas = FXCollections.observableArrayList(habitat.getCandidaturas());
            this.tableCandidaturas.setItems(dataCandidaturas);
        } catch (PersistenceException ex) {
            Logger.getLogger(Candidatura_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void procuraCandidatura() throws PersistenceException {
        String nome = textProcuraCandi.getText();
        if (!nome.equals("")) {
            try {
                int nr = Integer.parseInt(nome);
                if (habitat.getCandidatura(nr) != null) {
                    ObservableList<Candidatura> dataCandidaturas = FXCollections.observableArrayList(habitat.getCandidatura(nr));
                    this.tableCandidaturas.setItems(dataCandidaturas);
                } else {
                    tableCandidaturas.getItems().clear();
                }
            } catch (NumberFormatException ex) {
                Alert a = new Alert(AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText("A pesquisa de candidaturas é feita pelo seu respetivo nº");
                a.showAndWait();
            }

        } else if (nome.equals("")) {

            ObservableList<Candidatura> dataCandidaturas = FXCollections.observableArrayList(habitat.getCandidaturas());
            this.tableCandidaturas.setItems(dataCandidaturas);

        }

    }
}
