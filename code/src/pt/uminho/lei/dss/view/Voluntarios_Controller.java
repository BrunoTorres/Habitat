package pt.uminho.lei.dss.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pt.uminho.lei.dss.model.Voluntario;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Equipa;
import pt.uminho.lei.dss.model.Projecto;
import pt.uminho.lei.dss.model.SGH;

public class Voluntarios_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_nome;

    @FXML
    private DatePicker dp_datanasc;

    @FXML
    private TextField tf_prof;

    @FXML
    private TextField tf_morada;

    @FXML
    private TextField tf_codpostal;

    @FXML
    private TextField tf_localidade;

    @FXML
    private TextField tf_telefone;

    @FXML
    private TextField tf_equipa;

    @FXML
    private TextField tf_email;

    @FXML
    private Button bVoluntarioAdd;

    @FXML
    private TextField nomeVoluntario;

    @FXML
    private Button bVoluntarioEdit;

    @FXML
    private Button button_download;

    @FXML
    private Button button_upload;

    @FXML
    private Button bVoluntarioSave;

    @FXML
    private Button bVoluntarioRem;
    
    @FXML
    private Button bEquipaRem;

    @FXML
    private Button bVoluntarioCancelar;

    // Lista de voluntários
    @FXML
    TableView<Voluntario> tableVoluntarios;
    @FXML
    TableColumn<Voluntario, String> tcFirstName;
    @FXML
    TableColumn<Voluntario, String> tcLastName;
    @FXML
    TableColumn<Voluntario, String> tcTest;

    // Lista de projetos do voluntário
    @FXML
    TableView<Projecto> tableVoluntariosProjetos;
    @FXML
    TableColumn<Projecto, String> tcProjetoNome;

    // Lista de projetos do voluntário
    @FXML
    TableView<Equipa> tableVoluntariosEquipa;
    @FXML
    TableColumn<Equipa, String> tcEquipaNome;
    @FXML
    TableColumn<Equipa, String> tcEquipaAtiva;

    @FXML
    private CheckBox cbAtivo;

    @FXML
    private TabPane tabpaneVoluntarios;

    @FXML
    private TabPane tabpaneAux;

    @FXML
    private Tab tabInfo;

    @FXML
    private Tab tabProjetos;

    @FXML
    private Tab tabEquipas;

    @FXML
    private Button bEquipaAdd;

    @FXML
    private Button bEquipaNew;

    @FXML
    private ComboBox cbEquipa;

    private Stage anterior;
    private Stage atual;

    private SGH habitat;
    private int i;
    //private Funcionario funcionario;
    //private Habitat mainApp;

    public Voluntarios_Controller() {

    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        bEquipaAdd.setMouseTransparent(true);
        bEquipaNew.setMouseTransparent(true);
        bEquipaRem.setMouseTransparent(true);
        cbEquipa.setMouseTransparent(true);
        dp_datanasc.setMouseTransparent(true);
        
        bVoluntarioEdit.setDisable(true);
        bVoluntarioRem.setDisable(true);
        bVoluntarioSave.setVisible(false);
        bVoluntarioCancelar.setVisible(false);
        button_upload.setVisible(false);
        button_download.setDisable(true);

        // preencher tabela voluntarios
        tf_nome.setEditable(false);
        dp_datanasc.setEditable(false);
        tf_prof.setEditable(false);
        tf_morada.setEditable(false);
        tf_codpostal.setEditable(false);
        tf_localidade.setEditable(false);
        tf_telefone.setEditable(false);
        tf_email.setEditable(false);
        cbAtivo.setMouseTransparent(true);
        this.tableVoluntarios.setEditable(false);
        this.tableVoluntariosEquipa.setEditable(false);
        this.tableVoluntariosProjetos.setEditable(false);

        tcFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        tcLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // preencher tabela projetos do voluntario
        tcEquipaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        tcEquipaAtiva.setCellValueFactory(cellData -> cellData.getValue().getAtiva());
        tcProjetoNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
    }

    @FXML
    void voluntariosButtonAction() throws PersistenceException, IOException {
        String nome = this.nomeVoluntario.getText();
        this.tableVoluntarios.getItems().clear();

        ArrayList<Voluntario> voluns = (ArrayList) this.habitat.getVoluntariosByName(nome);
        tcFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        tcLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        try {
            ObservableList<Voluntario> dataVoluntarios = FXCollections.observableArrayList(habitat.getVoluntariosByName(nome));
            this.tableVoluntarios.setItems(dataVoluntarios);
        } catch (PersistenceException ex) {
            Logger.getLogger(Voluntarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // preencher tabela Equipas do voluntario
        tcEquipaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        tcProjetoNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
    }
    /*
     @FXML
     void addVoluntario() throws PersistenceException {
     tf_nome.setEditable(true);
     dp_datanasc.setEditable(true);
     tf_prof.setEditable(true);
     tf_morada.setEditable(true);
     tf_codpostal.setEditable(true);
     tf_localidade.setEditable(true);
     tf_telefone.setEditable(true);
     tf_email.setEditable(true);

     tf_nome.clear();
     tf_prof.clear();
     tf_morada.clear();
     tf_codpostal.clear();
     tf_localidade.clear();
     tf_telefone.clear();
     tf_email.clear();

     }

     @FXML
     void guardarAction() throws PersistenceException {
     String nome = tf_nome.getText();
     Instant instant = dp_datanasc.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
     Date d = Date.from(instant);
     String prof = tf_prof.getText();
     String morada = tf_morada.getText();
     String cd = tf_codpostal.getText();
     String loc = tf_localidade.getText();
     int tl = Integer.valueOf(tf_telefone.getText());
     String email = tf_email.getText();

     Format formatter = new SimpleDateFormat("dd-MM-yyyy");
     String data = formatter.format(d);

     Voluntario v = new Voluntario(nome, prof, tl, data, email, morada, loc, cd, 1, null);

     this.habitat.addVoluntario(v);
     tf_nome.setEditable(true);
     dp_datanasc.setEditable(true);
     tf_prof.setEditable(true);
     tf_morada.setEditable(true);
     tf_codpostal.setEditable(true);
     tf_localidade.setEditable(true);
     tf_telefone.setEditable(true);
     tf_email.setEditable(true);

     tf_nome.clear();
     tf_prof.clear();
     tf_morada.clear();
     tf_codpostal.clear();
     tf_localidade.clear();
     tf_telefone.clear();
     tf_email.clear();

     tcFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
     tcLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
     try {
     ObservableList<Voluntario> dataVoluntarios = FXCollections.observableArrayList(habitat.getVoluntarios(habitat.getC()));
     this.tableVoluntarios.setItems(dataVoluntarios);
     } catch (PersistenceException ex) {
     Logger.getLogger(Voluntarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
     }
     // preencher tabela projetos do voluntario
     tcEquipaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
     tcProjetoNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());

     }

     @FXML
     void remVoluntarioAction() throws IOException {

     }

     /*@FXML
     void remVoluntarioAction() throws IOException {
     // Load the fxml file and create a new stage for the popup
        
     FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertDialog_css.fxml"));
     AnchorPane page = (AnchorPane) loader.load();
     Stage dialogStage = new Stage();
     dialogStage.setTitle("Edit Person");
     dialogStage.initModality(Modality.WINDOW_MODAL);
     dialogStage.initOwner(this.atual);
     Scene scene = new Scene(page);
     dialogStage.setScene(scene);

     // Set the person into the controller
     // Set the person into the controller
     PersonEditDialogController controller = loader.getController();
     controller.setDialogStage(dialogStage);
     controller.setPerson(person);


     // Show the dialog and wait until the user closes it
     dialogStage.showAndWait();

     //return controller.isOkClicked();

  

     }
     */

    public void mostraVfromProj(Voluntario vol) throws PersistenceException {
        bVoluntarioAdd.setDisable(true);
        tf_nome.setText(vol.getNome());
        dp_datanasc.setValue(vol.DataNascProperty());
        tf_prof.setText(vol.getProf());
        tf_morada.setText(vol.getRua());
        tf_codpostal.setText(vol.getCodPostal());
        tf_localidade.setText(vol.getLocalidade());
        tf_telefone.setText(String.valueOf(vol.getTelf()));
        tf_email.setText(vol.getEmail());
        cbAtivo.setSelected(vol.getAtivo());
        tabEquipas.setDisable(true);
        tabProjetos.setDisable(true);
        tabpaneVoluntarios.setMouseTransparent(true);
        button_download.setVisible(false);
        button_upload.setVisible(false);
        //tf_equipa.setText("IMPLEMENTAR :D");

    }

    @FXML
    private void tableVoluntariosOnClick() throws PersistenceException {
        bEquipaAdd.setMouseTransparent(false);
        bEquipaNew.setMouseTransparent(false);
        bEquipaRem.setMouseTransparent(false);
        cbEquipa.setMouseTransparent(false);
        
        
        bVoluntarioEdit.setDisable(false);
        bVoluntarioRem.setDisable(false);
        Voluntario v = tableVoluntarios.getSelectionModel().getSelectedItem();
        tf_nome.setText(v.getNome());
        dp_datanasc.setValue(v.DataNascProperty());
        tf_prof.setText(v.getProf());
        tf_morada.setText(v.getRua());
        tf_codpostal.setText(v.getCodPostal());
        tf_localidade.setText(v.getLocalidade());
        tf_telefone.setText(String.valueOf(v.getTelf()));
        tf_email.setText(v.getEmail());
        cbAtivo.setSelected(v.getAtivo());
        //ObservableList<Equipa> dataEquipas = FXCollections.observableArrayList(v.getEquipas());
        //
        ObservableList<Equipa> dataEquipas = FXCollections.observableArrayList(habitat.getEquipaVoluntario(v.getId()));
        int ea = habitat.getEquipaActiva(v.getId());
        for (Equipa aux : dataEquipas) {
            aux.setAtiva((aux.getIdEquipa() == ea) ? true : false);
        }
        tableVoluntariosEquipa.setItems(dataEquipas);
        ObservableList<Projecto> dataProjetos = FXCollections.observableArrayList(v.getProjetos());
        tableVoluntariosProjetos.setItems(dataProjetos);
        ObservableList<Equipa> equipas = FXCollections.observableArrayList(habitat.getEquipasByName(""));
        cbEquipa.setItems(equipas);
        button_download.setDisable(!habitat.existeVoluntarioDocumento(v.getId()));
        cbEquipa.setValue("Selecione Equipa");
        if (habitat.existeVoluntarioDocumento(v.getId())) {
            button_download.setDisable(false);
        } else {
            button_download.setDisable(true);
        }
    }

    @FXML
    private void buttonDownloadOnClick() throws PersistenceException {
        try {
            Voluntario v = tableVoluntarios.getSelectionModel().getSelectedItem();
            v.setDoc(habitat.getVoluntarioDocumento(v.getId()));
            System.out.println("DOCUMENTO: " + v.getDoc());
            InputStream initialStream = v.getDoc();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Habitat - Guardar Documento");
            //chooser.showDialog(null);
            String path = chooser.showDialog(null).getPath();
            System.out.println("PATH: " + path);
            File targetFile = new File(path + "/" + v.getNome() + ".pdf");
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
        Voluntario v = tableVoluntarios.getSelectionModel().getSelectedItem();
        if (file != null) {
            try {
                v.setDoc(new FileInputStream(file));
                button_download.setDisable(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Voluntarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            habitat.setVoluntario(v);
            bVoluntarioCancelarOnClick();
        }
    }

    @FXML
    private void bVoluntarioAddOnClick() {
        tabpaneVoluntarios.setMouseTransparent(true);
        tabpaneAux.getSelectionModel().select(tabInfo);
        tabInfo.setDisable(false);
        tabProjetos.setDisable(true);
        tabEquipas.setDisable(true);

        bVoluntarioEdit.setDisable(true);
        bVoluntarioRem.setDisable(true);
        bVoluntarioSave.setVisible(true);
        bVoluntarioCancelar.setVisible(true);

        tf_nome.setEditable(true);
        dp_datanasc.setEditable(true);
        tf_prof.setEditable(true);
        tf_morada.setEditable(true);
        tf_codpostal.setEditable(true);
        tf_localidade.setEditable(true);
        tf_telefone.setEditable(true);
        tf_email.setEditable(true);
        cbAtivo.setMouseTransparent(false);
        tf_nome.setText("");
        dp_datanasc.setValue(LocalDate.now());
        tf_prof.setText("");
        tf_morada.setText("");
        tf_codpostal.setText("");
        tf_localidade.setText("");
        tf_telefone.setText("");
        tf_email.setText("");

    }

    @FXML
    private void bVoluntarioEditOnClick() {
        if(tableVoluntarios.getSelectionModel().getSelectedItem()!=null){
        tabpaneVoluntarios.setMouseTransparent(true);
        tabpaneAux.getSelectionModel().select(tabInfo);
        tabInfo.setDisable(false);
        tabProjetos.setDisable(true);
        tabEquipas.setDisable(true);
        dp_datanasc.setMouseTransparent(false);

        bVoluntarioAdd.setDisable(true);
        bVoluntarioRem.setDisable(true);
        bVoluntarioSave.setVisible(true);
        bVoluntarioCancelar.setVisible(true);
        button_upload.setVisible(true);

        tf_nome.setEditable(true);
        dp_datanasc.setEditable(true);
        tf_prof.setEditable(true);
        tf_morada.setEditable(true);
        tf_codpostal.setEditable(true);
        tf_localidade.setEditable(true);
        tf_telefone.setEditable(true);
        tf_email.setEditable(true);
        cbAtivo.setMouseTransparent(false);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Voluntario.");
            diag.showAndWait();
        }
    }

    @FXML
    private void bVoluntarioRemOnClick() throws PersistenceException {
        if (tableVoluntarios.getSelectionModel().getSelectedItem()!=null){
        int idVoluntario = tableVoluntarios.getSelectionModel().getSelectedItem().getId();
        habitat.remVoluntario(idVoluntario);
        ObservableList<Voluntario> dataVoluntarios = FXCollections.observableArrayList(habitat.getVoluntarios());
        this.tableVoluntarios.setItems(dataVoluntarios);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Voluntario.");
            diag.showAndWait();
        }
    }

    @FXML
    private void bVoluntarioSaveOnClick() throws PersistenceException {
        if (!bVoluntarioEdit.isDisable()) {
            Voluntario v = tableVoluntarios.getSelectionModel().getSelectedItem();
            v.setNome(tf_nome.getText());
            v.setDataNasc(dp_datanasc.getValue());
            v.setProf(tf_prof.getText());
            v.setRua(tf_morada.getText());
            v.setCodPostal(tf_codpostal.getText());
            v.setLocalidade(tf_localidade.getText());
            v.setTelf(Integer.valueOf(tf_telefone.getText()));
            v.setEmail(tf_email.getText());
            v.setAtivo(cbAtivo.isSelected());
            habitat.setVoluntario(v);
            ObservableList<Voluntario> dataVoluntarios = FXCollections.observableArrayList(habitat.getVoluntarios());
            this.tableVoluntarios.setItems(dataVoluntarios);

        } else {
            Voluntario v = new Voluntario();
            v.setFuncionario(habitat.getF().getIdFuncionario());
            v.setNome(tf_nome.getText());
            v.setDataNasc(dp_datanasc.getValue());
            v.setProf(tf_prof.getText());
            v.setRua(tf_morada.getText());
            v.setCodPostal(tf_codpostal.getText());
            v.setLocalidade(tf_localidade.getText());
            v.setTelf(Integer.valueOf(tf_telefone.getText()));
            v.setEmail(tf_email.getText());
            v.setAtivo(cbAtivo.isSelected());
            habitat.addVoluntario(v);
            ObservableList<Voluntario> dataVoluntarios = FXCollections.observableArrayList(habitat.getVoluntarios());
            this.tableVoluntarios.setItems(dataVoluntarios);
        }
        bVoluntarioCancelarOnClick();
    }

    @FXML
    private void bVoluntarioCancelarOnClick() {
        tabpaneVoluntarios.setMouseTransparent(false);
        tabProjetos.setDisable(false);
        tabEquipas.setDisable(false);

        bVoluntarioAdd.setDisable(false);
        bVoluntarioEdit.setDisable(false);
        bVoluntarioRem.setDisable(false);
        bVoluntarioSave.setVisible(false);
        bVoluntarioCancelar.setVisible(false);

        tf_nome.setEditable(false);
        dp_datanasc.setEditable(false);
        tf_prof.setEditable(false);
        tf_morada.setEditable(false);
        tf_codpostal.setEditable(false);
        tf_localidade.setEditable(false);
        tf_telefone.setEditable(false);
        tf_email.setEditable(false);
        cbAtivo.setMouseTransparent(true);
        button_upload.setVisible(false);
    }

    @FXML
    private void bEquipaNewOnClick() throws PersistenceException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Equipa_Add.fxml"));
        Parent root = loader.load();
        Equipa_Add_Controller equipaC = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Adicionar Equipa");
        equipaC.setAnterior(atual);
        equipaC.setAtual(stage);
        equipaC.setHabitat(habitat);
        //doacaoControl.setMode("Adicionar");
        //doacaoControl.setDoacoes(this.doacoes);
        //doacaoControl.setFuncionario(this.habitat.getF());
        //doacaoControl.setDoador(this.tableDoadores.getSelectionModel().getSelectedItem().getIdDoador());
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            this.anterior.show();
            this.atual.close();

        });
        this.atual.hide();
    }

    @FXML
    private void bEquipaAddOnClick() throws PersistenceException {
        if (cbEquipa.getSelectionModel().getSelectedIndex() != -1) {
            Equipa e = (Equipa) cbEquipa.getSelectionModel().getSelectedItem();
            Voluntario v = (Voluntario) tableVoluntarios.getSelectionModel().getSelectedItem();
            habitat.addVoluntarioEquipa(v.getId(), e.getIdEquipa());
            ObservableList<Equipa> dataEquipas = FXCollections.observableArrayList(habitat.getEquipaVoluntario(v.getId()));
            int ea = habitat.getEquipaActiva(v.getId());
            for (Equipa aux : dataEquipas) {
                aux.setAtiva((aux.getIdEquipa() == ea) ? true : false);
            }
            tableVoluntariosEquipa.setItems(dataEquipas);
        }
    }

    @FXML
    private void bVoluntarioEquipaRemOnClick() throws PersistenceException {
        Voluntario v = (Voluntario) tableVoluntarios.getSelectionModel().getSelectedItem();
        Equipa e = tableVoluntariosEquipa.getSelectionModel().getSelectedItem();
        for (Equipa aux : tableVoluntariosEquipa.getItems()) {
            if (aux.isAtiva()) {
                aux.setAtiva(false);
                habitat.desativaEquipaActiva(v.getId());
                ObservableList<Equipa> dataEquipas = FXCollections.observableArrayList(habitat.getEquipaVoluntario(v.getId()));
                tableVoluntariosEquipa.setItems(dataEquipas);
                return;
            }
        }
        Alert diag = new Alert(Alert.AlertType.ERROR);
        diag.setTitle("Erro");
        diag.setHeaderText(null);
        diag.setContentText("Não Existe nenhuma Equipa ativa!");
        diag.showAndWait();

    }

    @FXML
    private void cbEquipaOnClick() throws PersistenceException {
        ObservableList<Equipa> equipas = FXCollections.observableArrayList(habitat.getEquipasByName(""));
        cbEquipa.setItems(equipas);
    }

    private void permissoes(int idfuncao){
        switch(idfuncao){
            case 1:
                bVoluntarioAdd.setVisible(false);
                bVoluntarioEdit.setVisible(false);
                bVoluntarioRem.setVisible(false);
                bEquipaAdd.setVisible(false);
                bEquipaNew.setVisible(false);
                bEquipaRem.setVisible(false);
                cbEquipa.setVisible(false);
                break;
            case 2:
                bVoluntarioAdd.setVisible(false);
                bVoluntarioEdit.setVisible(false);
                bVoluntarioRem.setVisible(false);
                bEquipaAdd.setVisible(false);
                bEquipaNew.setVisible(false);
                bEquipaRem.setVisible(false);
                cbEquipa.setVisible(false);
                break;
            case 3:
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

    void setInt(int i) {
        this.i = i;
    }

    public SGH getHabitat() {
        return habitat;
    }

    public void setHabitat(SGH habitat) {
        this.habitat = habitat;
        permissoes(habitat.getF().getPermissao());
        try {
            ObservableList<Voluntario> dataVoluntarios = FXCollections.observableArrayList(habitat.getVoluntarios());
            this.tableVoluntarios.setItems(dataVoluntarios);
        } catch (PersistenceException ex) {
            Logger.getLogger(Voluntarios_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
