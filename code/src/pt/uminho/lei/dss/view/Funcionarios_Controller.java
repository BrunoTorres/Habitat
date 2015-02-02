/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.view;

import javafx.scene.paint.Color;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Funcionario;
import pt.uminho.lei.dss.model.SGH;

public class Funcionarios_Controller implements Initializable {

    @FXML
    private TextField tfLocalidade;

    @FXML
    private TextField tfUserName;

    @FXML
    private DatePicker tfData;

    @FXML
    private TextField tfPermissao;

    @FXML
    private TextField tfPass;

    @FXML
    private TextField tfMorada;

    @FXML
    private TextField tfTel;

    @FXML
    private Button saveButton;

    @FXML
    private TextField tfNome;

    @FXML
    private Button okProcuraButton;

    @FXML
    private TextField tfSalario;

    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private ComboBox<String> comboAtivo;

    @FXML
    private TextField tfLastLogin;

    @FXML
    private Button addButton;

    @FXML
    private TextField tfEmail;

    @FXML
    private TableView<Funcionario> tableFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> tcPermissoes;
    @FXML
    private TableColumn<Funcionario, String> tcUserName;
    @FXML
    private TableColumn<Funcionario, String> tcEstado;

    @FXML
    private TextField tfNif;

    @FXML
    private Circle circuloEstado;

    @FXML
    private Button remButton;
    @FXML
    private Button cancelButton;

    @FXML
    private Button editButton;

    @FXML
    private TextField tfProcura;

    @FXML
    private TextField tfCodpostal;

    private Stage anterior;
    private Stage atual;
    private SGH habitat;

    public Funcionarios_Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //habitat = new SGH();

        ArrayList<String> arrPer = new ArrayList<>();
        arrPer.add("Administrador");
        arrPer.add("Famílias");
        arrPer.add("Obras");
        arrPer.add("Fundos");
        ObservableList<String> options = FXCollections.observableArrayList(arrPer);
        comboEstado.setItems(options);

        circuloEstado.setFill(Color.GREY);
        
        tfProcura.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                try {
                    procuraFuncionario();
                } catch (PersistenceException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("ERRO");
                    a.setHeaderText(null);
                    a.setContentText("Erro ao procurar funcionário");
                }
            }
        });

        ArrayList<String> arrAt = new ArrayList<>();
        arrAt.add("Ativo");
        arrAt.add("Inativo");

        ObservableList<String> options2 = FXCollections.observableArrayList(arrAt);
        comboAtivo.setItems(options2);

        tcUserName.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
        tcPermissoes.setCellValueFactory(cellData -> cellData.getValue().getPermissaoProperty());
        tcEstado.setCellValueFactory(cellData -> cellData.getValue().getActivoProperty());

        tfData.setMouseTransparent(true);
        comboEstado.setMouseTransparent(true);
        comboAtivo.setMouseTransparent(true);

        tfNome.setEditable(false);
        tfCodpostal.setEditable(false);
        tfEmail.setEditable(false);
        tfLastLogin.setEditable(false);
        tfLocalidade.setEditable(false);
        tfTel.setEditable(false);
        tfUserName.setEditable(false);
        tfPass.setEditable(false);
        tfSalario.setEditable(false);
        tfMorada.setEditable(false);
        tfNif.setEditable(false);

        saveButton.setVisible(false);
        cancelButton.setVisible(false);

    }

    @FXML
    private void tableFuncionariosOnClick() {
        if (!this.tableFuncionarios.getSelectionModel().isEmpty()) {
            tfData.setMouseTransparent(true);
            comboEstado.setMouseTransparent(true);
            comboAtivo.setMouseTransparent(true);

            tfNome.setEditable(false);
            tfCodpostal.setEditable(false);
            tfEmail.setEditable(false);
            tfLastLogin.setEditable(false);
            tfLocalidade.setEditable(false);
            tfTel.setEditable(false);
            tfUserName.setEditable(false);
            tfPass.setEditable(false);
            tfSalario.setEditable(false);
            tfMorada.setEditable(false);
            tfNif.setEditable(false);

            Funcionario f = tableFuncionarios.getSelectionModel().getSelectedItem();
            tfNome.setText(f.getNome());
            tfData.setValue(f.getDataNascimento());
            tfCodpostal.setText(f.getCodPostal());
            tfEmail.setText(f.getEmail());
            tfLastLogin.setText(f.getUltimoLogin());
            tfLocalidade.setText(f.getLocalidade());
            tfTel.setText(String.valueOf(f.getTelefone()));
            tfUserName.setText(f.getUserName());
            tfPass.setText(f.getPassword());
            tfSalario.setText(String.valueOf(f.getSalario()));
            tfMorada.setText(f.getRua());
            tfNif.setText(String.valueOf(f.getNif()));

            if (f.getPermissao() == 0) {
                comboEstado.getSelectionModel().select(0);
            } else if (f.getPermissao() == 1) {
                comboEstado.getSelectionModel().select(1);
            } else if (f.getPermissao() == 2) {
                comboEstado.getSelectionModel().select(2);
            } else if (f.getPermissao() == 3) {
                comboEstado.getSelectionModel().select(3);
            }

            if (f.getActivo() == true) {
                comboAtivo.getSelectionModel().select(0);
                circuloEstado.setFill(Color.web("#21ff23", 1));
            } else {
                comboAtivo.getSelectionModel().select(1);
                circuloEstado.setFill(Color.RED);
            }
        }

    }

    @FXML
    private void addFuncionariosOnClick() {
        editButton.setDisable(true);

        tableFuncionarios.setMouseTransparent(true);
        remButton.setDisable(true);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);

        tfNome.setText("");

        tfCodpostal.setText("");
        tfEmail.setText("");
        tfLastLogin.setText("");
        tfLocalidade.setText("");
        tfTel.setText("");
        tfUserName.setText("");
        tfPass.setText("");
        tfSalario.setText("");
        tfMorada.setText("");
        tfNif.setText("");
        

        tfData.setMouseTransparent(false);
        comboEstado.setMouseTransparent(false);
        comboAtivo.getSelectionModel().select(0);
        circuloEstado.setFill(Color.web("#21ff23", 1));

        tfNome.setEditable(true);
        tfCodpostal.setEditable(true);
        tfEmail.setEditable(true);
        tfLastLogin.setEditable(false);
        tfLastLogin.setDisable(true);
        tfLocalidade.setEditable(true);
        tfTel.setEditable(true);
        tfUserName.setEditable(true);
        tfPass.setEditable(true);
        tfSalario.setEditable(true);
        tfMorada.setEditable(true);
        tfNif.setEditable(true);

    }

    @FXML
    private void cancelFuncionariosOnClick() {
        if (editButton.isDisabled()) {
            tfNome.setText("");

            tfCodpostal.setText("");
            tfEmail.setText("");
            tfLastLogin.setText("");
            tfLocalidade.setText("");
            tfTel.setText("");
            tfUserName.setText("");
            tfPass.setText("");
            tfSalario.setText("");
            tfMorada.setText("");
            tfNif.setText("");

            tfData.setValue(null);
            comboEstado.setValue(null);
            comboAtivo.setValue(null);
            circuloEstado.setFill(Color.GREY);
        } else {
            Funcionario f = tableFuncionarios.getSelectionModel().getSelectedItem();
            tfNome.setText(f.getNome());
            tfData.setValue(f.getDataNascimento());
            tfCodpostal.setText(f.getCodPostal());
            tfEmail.setText(f.getEmail());
            tfLastLogin.setText(f.getUltimoLogin());
            tfLocalidade.setText(f.getLocalidade());
            tfTel.setText(String.valueOf(f.getTelefone()));
            tfUserName.setText(f.getUserName());
            tfPass.setText(f.getPassword());
            tfSalario.setText(String.valueOf(f.getSalario()));
            tfMorada.setText(f.getRua());
            tfNif.setText(String.valueOf(f.getNif()));

            if (f.getPermissao() == 0) {
                comboEstado.getSelectionModel().select(0);
            } else if (f.getPermissao() == 1) {
                comboEstado.getSelectionModel().select(1);
            } else if (f.getPermissao() == 2) {
                comboEstado.getSelectionModel().select(2);
            } else if (f.getPermissao() == 3) {
                comboEstado.getSelectionModel().select(3);
            }

            if (f.getActivo() == true) {
                comboAtivo.getSelectionModel().select(0);
                circuloEstado.setFill(Color.web("#21ff23", 1));
            } else {
                comboAtivo.getSelectionModel().select(1);
                circuloEstado.setFill(Color.RED);
            }
        }

        tfData.setMouseTransparent(true);
        comboEstado.setMouseTransparent(true);
        comboAtivo.setMouseTransparent(true);

        tfNome.setEditable(false);
        tfCodpostal.setEditable(false);
        tfEmail.setEditable(false);
        tfLastLogin.setEditable(false);
        tfLocalidade.setEditable(false);
        tfTel.setEditable(false);
        tfUserName.setEditable(false);
        tfPass.setEditable(false);
        tfSalario.setEditable(false);
        tfMorada.setEditable(false);
        tfNif.setEditable(false);

        addButton.setDisable(false);
        editButton.setDisable(false);
        remButton.setDisable(false);

        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        tableFuncionarios.setMouseTransparent(false);
//        saveButton.setDisable(false);

    }

    @FXML
    private void remFuncionariosOnClick() throws PersistenceException {
        Funcionario v = tableFuncionarios.getSelectionModel().getSelectedItem();
        if (v != null) {
            editButton.setDisable(true);
            addButton.setDisable(true);
            remButton.setDisable(true);
            tableFuncionarios.setMouseTransparent(true);

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Remover funcionário");
            a.setHeaderText(null);
            a.setContentText("Deseja remover o funcionário " + v.getNome());
            Optional<ButtonType> r = a.showAndWait();

            if (r.get() == ButtonType.OK) {
                if (habitat.remFuncionario(v.getIdFuncionario())) {
                    try {
                        ObservableList<Funcionario> dataFunc = FXCollections.observableArrayList(habitat.getFuncionarios());
                        this.tableFuncionarios.setItems(dataFunc);
                    } catch (PersistenceException ex) {
                        Logger.getLogger(Funcionarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    editButton.setDisable(false);
                    tableFuncionarios.setMouseTransparent(false);
                    addButton.setDisable(false);

                } else {
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setTitle("Impossível remover funcionário");
                    al.setHeaderText(null);
                    al.setContentText("Não é possível remover o funcionário " + v.getNome() + " uma vez que o mesmo realizou várias tarefas no sistema");
                    //Optional<ButtonType> r = a.showAndWait();
                    al.showAndWait();
                    editButton.setDisable(false);
                    addButton.setDisable(false);
                    tableFuncionarios.setMouseTransparent(false);
                }

            }
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro ao remover funcionário");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um funcionário.");
            diag.showAndWait();
        }
        addButton.setDisable(false);
        remButton.setDisable(false);
        editButton.setDisable(false);
    }

    @FXML
    private void editFuncionariosOnClick() throws PersistenceException {
        Funcionario v = tableFuncionarios.getSelectionModel().getSelectedItem();
        if (v != null) {
            tableFuncionarios.setMouseTransparent(true);
            addButton.setDisable(true);
            remButton.setDisable(true);
            saveButton.setVisible(true);
            cancelButton.setVisible(true);

            tfNome.setText(v.getNome());
            tfData.setValue(v.getDataNascimento());
            tfCodpostal.setText(v.getCodPostal());
            tfEmail.setText(v.getEmail());
            tfLastLogin.setText(v.getUltimoLogin());
            tfLocalidade.setText(v.getLocalidade());
            tfTel.setText(String.valueOf(v.getTelefone()));
            tfUserName.setText(v.getUserName());
            tfPass.setText(v.getPassword());
            tfSalario.setText(String.valueOf(v.getSalario()));
            tfMorada.setText(v.getRua());
            tfNif.setText(String.valueOf(v.getNif()));

            tfData.setMouseTransparent(false);
            comboEstado.setMouseTransparent(false);
            comboAtivo.setMouseTransparent(false);

            tfNome.setEditable(true);
            tfCodpostal.setEditable(true);
            tfEmail.setEditable(true);
            tfLastLogin.setEditable(true);
            tfLocalidade.setEditable(true);
            tfTel.setEditable(true);
            tfUserName.setEditable(true);
            tfPass.setEditable(true);
            tfSalario.setEditable(true);
            tfMorada.setEditable(true);
            tfNif.setEditable(true);

            if (v.getPermissao() == 0) {
                comboEstado.getSelectionModel().select(0);
            } else if (v.getPermissao() == 1) {
                comboEstado.getSelectionModel().select(1);
            } else if (v.getPermissao() == 2) {
                comboEstado.getSelectionModel().select(2);
            } else if (v.getPermissao() == 3) {
                comboEstado.getSelectionModel().select(3);
            }

            if (v.getActivo() == true) {
                comboAtivo.getSelectionModel().select(0);
                circuloEstado.setFill(Color.web("#21ff23", 1));
            } else if (v.getActivo() == false) {
                comboAtivo.getSelectionModel().select(1);
                circuloEstado.setFill(Color.RED);
            }

            // comboAtivo.setOnMouse(null);
            comboAtivo.setOnHidden((Event t) -> {
                if (comboAtivo.getValue().equals("Ativo")) {
                    circuloEstado.setFill(Color.web("#21ff23", 1));
                }
                if (comboAtivo.getValue().equals("Inativo")) {
                    circuloEstado.setFill(Color.RED);
                }

                //Do what you want to do
            });

            /*
             if (comboAtivo.getValue().equals("Ativo")) {
            
             circuloEstado.setFill(Color.web("#21ff23", 1));
             } else 
             circuloEstado.setFill(Color.RED);
        
        
             */
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro ao editar funcionário");
            diag.setContentText("Selecione um funcionário");
            diag.showAndWait();

        }
    }

    @FXML
    protected void procuraFuncionario() throws PersistenceException {
        String nome = tfProcura.getText();
        if (!nome.equals("")) {

            tcUserName.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
            tcPermissoes.setCellValueFactory(cellData -> cellData.getValue().getPermissaoProperty());
            tcEstado.setCellValueFactory(cellData -> cellData.getValue().getActivoProperty());

            try {
                ObservableList<Funcionario> dataFunc = FXCollections.observableArrayList(habitat.getFuncionariobyName(nome));
                this.tableFuncionarios.setItems(dataFunc);
            } catch (PersistenceException ex) {
                Logger.getLogger(Funcionarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (nome.equals("")) {
            tcUserName.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
            tcPermissoes.setCellValueFactory(cellData -> cellData.getValue().getPermissaoProperty());
            tcEstado.setCellValueFactory(cellData -> cellData.getValue().getActivoProperty());

            try {
                ObservableList<Funcionario> dataFunc = FXCollections.observableArrayList(habitat.getFuncionarios());
                this.tableFuncionarios.setItems(dataFunc);
            } catch (PersistenceException ex) {
                Logger.getLogger(Funcionarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void saveFuncionariosOnClick() throws PersistenceException {
        boolean changed = false;
        if (!addButton.isDisable()) {

            if (checkFields()) {
                try {
                    String nome = tfNome.getText();
                    LocalDate data = tfData.getValue();
                    String cod = tfCodpostal.getText();
                    String email = tfEmail.getText();
                    String local = tfLocalidade.getText();
                    int tel = Integer.valueOf(tfTel.getText());
                    String user = tfUserName.getText();
                    String pass = tfPass.getText();
                    int salario = Integer.valueOf(tfSalario.getText());
                    String morada = tfMorada.getText();
                    int nif = Integer.valueOf(tfNif.getText());

                    int p = 0;
                    String permi = comboEstado.getSelectionModel().selectedItemProperty().getValue();
                    switch (permi) {
                        case "Administrador":
                            p = 0;
                            break;
                        case "Famílias":
                            p = 1;
                            break;
                        case "Obras":
                            p = 2;
                            break;
                        case "Fundos":
                            p = 3;
                            break;

                    }

                    LocalDateTime dataUltimo = LocalDateTime.now();

                    Funcionario f = new Funcionario(nome, "Ativo", user, pass, p, true, email, data, tel, nif, salario, morada, local, cod, dataUltimo);
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Adicionar funcionário");
                    a.setHeaderText(null);
                    a.setContentText("Deseja adicionar o funcionário?");
                    Optional<ButtonType> r = a.showAndWait();
                    if (r.get() == ButtonType.OK) {
                        this.habitat.addFuncionario(f);
                        changed = true;
                    }
                } catch (NumberFormatException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Dados inválidos");
                    a.setHeaderText(null);
                    a.setContentText("Certifique-se de que todos os campos estão preenchidos corretamente");
                    a.showAndWait();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Dados insuficientes");
                a.setHeaderText(null);
                a.setContentText("Certifique-se de que todos os campos estão preenchidos");
                a.showAndWait();
            }
        } else {

            try {
                String nome = tfNome.getText();
                LocalDate data = tfData.getValue();
                String cod = tfCodpostal.getText();
                String email = tfEmail.getText();
                String local = tfLocalidade.getText();
                int tel = Integer.valueOf(tfTel.getText());
                String user = tfUserName.getText();
                String pass = tfPass.getText();
                int salario = Integer.valueOf(tfSalario.getText());
                String morada = tfMorada.getText();
                int nif = Integer.valueOf(tfNif.getText());

                int p = 0;
                String permi = comboEstado.getSelectionModel().selectedItemProperty().getValue();
                switch (permi) {
                    case "Administrador":
                        p = 0;
                        break;
                    case "Famílias":
                        p = 1;
                        break;
                    case "Obras":
                        p = 2;
                        break;
                    case "Fundos":
                        p = 3;
                        break;

                }

                boolean flag = true;
                switch (comboAtivo.getValue()) {
                    case "Ativo":
                        flag = true;
                        break;
                    case "Inativo":
                        flag = false;
                        break;
                }

                Funcionario f = tableFuncionarios.getSelectionModel().getSelectedItem();

                f.setNome(nome);

                f.setLocalidade(local);
                f.setCodPostal(cod);
                f.setEmail(email);
                f.setTelefone(tel);
                f.setUserName(user);
                f.setPassword(pass);
                f.setSalario(salario);
                f.setRua(morada);
                f.setNif(nif);
                f.setDataNascimento(data);
                f.setPermissao(p);
                f.setActivo(flag);

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Editar funcionário");
                a.setHeaderText(null);
                a.setContentText("Confirma as alterações ao funcionário?");
                Optional<ButtonType> r = a.showAndWait();
                if (r.get() == ButtonType.OK) {
                    this.habitat.setFuncionario(f);
                    changed = true;
                }
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Dados inválidos");
                a.setHeaderText(null);
                a.setContentText("Certifique-se de que todos os campos estão preenchidos corretamente");
                a.showAndWait();
            }
        }

        if (changed) {
            tcUserName.setCellValueFactory(cellData -> cellData.getValue().getUserNameProperty());
            tcPermissoes.setCellValueFactory(cellData -> cellData.getValue().getPermissaoProperty());
            tcEstado.setCellValueFactory(cellData -> cellData.getValue().getActivoProperty());
            //tcEstado.setCellValueFactory(cellData -> cellData.getValue().getE); 
            try {
                ObservableList<Funcionario> dataFunc = FXCollections.observableArrayList(habitat.getFuncionarios());
                this.tableFuncionarios.setItems(dataFunc);
            } catch (PersistenceException ex) {
                Logger.getLogger(Funcionarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            tableFuncionarios.setMouseTransparent(false);
            addButton.setDisable(false);
            remButton.setDisable(false);
            editButton.setDisable(false);
        }

    }

    public boolean checkFields() {
        boolean ok = false;
        if (!tfNome.getText().isEmpty() && !tfData.getValue().equals("") && !tfEmail.getText().isEmpty() && !comboEstado.getValue().equals("")
                && !tfTel.getText().isEmpty() && !tfUserName.getText().isEmpty() && !tfPass.getText().isEmpty() && !tfSalario.getText().isEmpty()
                && !tfNif.getText().isEmpty()) {
            ok = true;
        }
        return ok;
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

    public void setButton() {
        //cancelButton.setVisible(false);
    }

    public SGH getHabitat() {
        return habitat;
    }

    public void setHabitat(SGH habitat) {
        this.habitat = habitat;
        try {
            ObservableList<Funcionario> dataFunc = FXCollections.observableArrayList(habitat.getFuncionarios());
            this.tableFuncionarios.setItems(dataFunc);
        } catch (PersistenceException ex) {
            Logger.getLogger(Funcionarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
