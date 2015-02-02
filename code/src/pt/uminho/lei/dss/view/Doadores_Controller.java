package pt.uminho.lei.dss.view;

import java.io.IOException;
import pt.uminho.lei.dss.model.Doador;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Doacao;
import pt.uminho.lei.dss.model.SGH;

public class Doadores_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    // Lista de Doadores
    @FXML
    TableView<Doador> tableDoadores;
    @FXML
    TableColumn<Doador, String> tcFirstName;
    @FXML
    TableColumn<Doador, String> tcLastName;
    @FXML
    private TableColumn<Doador, String> tcEmpresa;

    @FXML
    private TableView<Doacao> tabDoacoes;
    @FXML
    private TableColumn<Doacao, String> tc_dataDoacao;
    @FXML
    private TableColumn<Doacao, String> tc_tipoDoacao;

    @FXML
    private TextField tf_nome;

    @FXML
    private ComboBox<String> combo_tipo;

    @FXML
    private ComboBox<String> cbAtividade;

    @FXML
    private TextField tf_telefone;

    @FXML
    private TextField tf_morada;

    @FXML
    private TextField tf_codpostal;

    @FXML
    private TextField tfLocal;

    @FXML
    private TextField tf_nif;
    @FXML
    private Button procuraButton;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_responsavel;

    @FXML
    private TextField tf_respcontacto;

    @FXML
    private TextField tf_site;

    @FXML
    private Button bDoadoresAdd;

    @FXML
    private Button bDoadoresEdit;

    @FXML
    private TextField textDoador;

    @FXML
    private Button bDoadoresRem;

    @FXML
    private Button bDoadoresSave;

    @FXML
    private Button bDoadoresCancelar;

    @FXML
    private Button addDoacaoButton;

    @FXML
    private Button bDoacaoEdit;

    @FXML
    private Button bDoacaoRem;

    @FXML
    private TabPane tabpaneDoadores;

    @FXML
    private TabPane tabpaneAux;

    @FXML
    private Tab tabInfo;

    @FXML
    private Tab tabDoacoes2;

    private Stage anterior;
    private Stage atual;

    private ObservableList<Doacao> doacoes;

    private SGH habitat;

    private String mode;

    //private Funcionario funcAtual;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //habitat = new SGH();
        tabDoacoes2.setDisable(true);
        bDoadoresEdit.setDisable(true);
        bDoadoresRem.setDisable(true);
        bDoadoresSave.setVisible(false);
        bDoadoresCancelar.setVisible(false);

        ArrayList<String> arr = new ArrayList<>();
        arr.add("Individual");
        arr.add("Empresa");
        ObservableList<String> options = FXCollections.observableArrayList(arr);
        combo_tipo.setItems(options);
        combo_tipo.setMouseTransparent(true);
        cbAtividade.setMouseTransparent(true);
        tcFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        tcLastName.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        tcEmpresa.setCellValueFactory(cellData -> cellData.getValue().getEmpresaStringProperty());

    }

    @FXML
    private void tableDoadoresOnClick() throws PersistenceException {
        if (!this.tableDoadores.getSelectionModel().isEmpty()) {
            tabDoacoes2.setDisable(false);
            bDoadoresEdit.setDisable(false);
            bDoadoresRem.setDisable(false);
            mostraDoador(tableDoadores.getSelectionModel().getSelectedItem());
        }
        //ObservableList<Equipa> dataEquipas = FXCollections.observableArrayList(v.getEquipas());
        //tableVoluntariosEquipa.setItems(dataEquipas);

    }
    
    public void mostraDoador(Doador d) throws PersistenceException {
        tf_nome.setText(d.getNome());
        tf_nif.setText(String.valueOf(d.getNif()));
        tf_morada.setText(d.getRua());
        tfLocal.setText(d.getLocalidade());
        tf_codpostal.setText(d.getCodPostal());
        //tf_localidade.setText(v.getLocalidade());
        tf_telefone.setText(String.valueOf(d.getTelefone()));
        tf_email.setText(d.getEmail());
        //TreeMap<String, Integer> ativs = (TreeMap<String, Integer>)habitat.getAtividades();
        //cbAtividade.
        if (d.getEmpresa() == 1) {
            combo_tipo.getSelectionModel().selectLast();
            tf_responsavel.setDisable(false);
            tf_respcontacto.setDisable(false);
            tf_responsavel.setText(d.getResponsavelEmpresa());
            tf_respcontacto.setText(String.valueOf(d.getContactoResponsavel()));
        } else {
            combo_tipo.getSelectionModel().selectFirst();
            tf_responsavel.setDisable(true);
            tf_responsavel.setText("");
            tf_respcontacto.setDisable(true);
            tf_respcontacto.setText("");
        }
        
        this.cbAtividade.setValue(d.getAtividade());

        this.tabDoacoes.getItems().clear();
        this.doacoes = FXCollections.observableArrayList(habitat.getDoador(d.getIdDoador()).getDoacoes());
        this.tabDoacoes.setItems(doacoes);

    }

    public boolean checkFields() {
        boolean ok = true;
        if (tf_nome.getText().isEmpty() || tf_nif.getText().isEmpty() || tf_email.getText().isEmpty() || combo_tipo.getValue().isEmpty() || cbAtividade.getValue().isEmpty()) {
            ok = false;
        } else {
            if (combo_tipo.getValue().equals("Empresa") && (tf_respcontacto.getText().isEmpty() || tf_responsavel.getText().isEmpty())) {
                ok = false;
            }
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

    public void setHabitat(SGH habitat) {
        this.habitat = habitat;
        this.tc_dataDoacao.setCellFactory((Callback) (new GenericCellFactory("doação", this.atual, this.tabDoacoes, this.habitat)));
        this.tc_dataDoacao.setCellValueFactory(cellData -> cellData.getValue().getDataProperty());
        this.tc_tipoDoacao.setCellFactory((Callback) (new GenericCellFactory("doação", this.atual, this.tabDoacoes, this.habitat)));
        this.tc_tipoDoacao.setCellValueFactory(cellData -> cellData.getValue().getTipoProperty());
        permissoes(habitat.getF().getPermissao());
        try {

            ObservableList<String> atividades = FXCollections.observableArrayList(this.habitat.getAtividades());
            this.cbAtividade.setItems(atividades);
            ObservableList<Doador> dataDoadores = FXCollections.observableArrayList(habitat.getDoadores());
            this.tableDoadores.setItems(dataDoadores);
        } catch (PersistenceException ex) {
            Logger.getLogger(Doadores_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void addDoacao() throws IOException, PersistenceException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Doacao_Add.fxml"));
        Parent root = loader.load();
        Doacao_Add_Controller doacaoControl = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Adicionar Doação");
        doacaoControl.setAnterior(atual);
        doacaoControl.setHabitat(habitat);
        doacaoControl.setAtual(stage);
        doacaoControl.setMode("Adicionar");
        doacaoControl.setDoacoes(this.doacoes);
        doacaoControl.setDoador(this.tableDoadores.getSelectionModel().getSelectedItem().getIdDoador());
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            this.atual.show();

        });
        this.atual.hide();

    }
    

    @FXML
    public void bDoadoresAddOnClick() {
        tabpaneDoadores.setMouseTransparent(true);
        tabpaneAux.getSelectionModel().select(tabInfo);
        tabDoacoes2.setDisable(true);
        bDoadoresEdit.setDisable(true);
        bDoadoresRem.setDisable(true);
        bDoadoresSave.setVisible(true);
        bDoadoresCancelar.setVisible(true);

        tf_nome.setEditable(true);
        tf_nome.setPromptText("Introduza o Nome do novo Doador");
        tf_nome.setText("");
        tf_nif.setEditable(true);
        tf_nif.setText("");
        combo_tipo.setDisable(false);
        combo_tipo.setValue("");
        tf_morada.setEditable(true);
        tf_morada.setText("");
        tf_codpostal.setEditable(true);
        tf_codpostal.setText("");
        tf_telefone.setEditable(true);
        tf_telefone.setText("");
        tf_email.setEditable(true);
        tf_email.setText("");
        tf_respcontacto.setEditable(true);
        tf_respcontacto.setText("");
        tf_responsavel.setEditable(true);
        tf_responsavel.setText("");
        tfLocal.setEditable(true);
        tfLocal.setText("");
        cbAtividade.setMouseTransparent(false);
        combo_tipo.setMouseTransparent(false);

    }

    @FXML
    public void bDoadoresEditOnClick() {
        if(tableDoadores.getSelectionModel().getSelectedItem()!=null){
        tabpaneDoadores.setMouseTransparent(true);
        tabpaneAux.getSelectionModel().select(tabInfo);
        tabDoacoes2.setDisable(true);
        bDoadoresAdd.setDisable(true);
        bDoadoresRem.setDisable(true);
        bDoadoresSave.setVisible(true);
        bDoadoresCancelar.setVisible(true);

        tf_nome.setEditable(true);
        tf_nif.setEditable(true);
        combo_tipo.setDisable(false);
        tf_morada.setEditable(true);
        tf_codpostal.setEditable(true);
        tf_telefone.setEditable(true);
        tf_email.setEditable(true);
        //tf_respcontacto.setEditable(true);
        //tf_responsavel.setEditable(true);

        tfLocal.setEditable(true);
        cbAtividade.setMouseTransparent(false);
        combo_tipo.setMouseTransparent(false);

        combo_tipo.setOnHidden((Event t) -> {
            if (combo_tipo.getValue().equals("Empresa")) {

                tf_responsavel.setDisable(false);
                tf_respcontacto.setDisable(false);

            }
            if (combo_tipo.getValue().equals("Individual")) {

                tf_responsavel.setDisable(true);
                tf_respcontacto.setDisable(true);
            }

        });
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Doador.");
            diag.showAndWait();
        }
    }

    @FXML
    public void bDoadoresRemOnClick() throws PersistenceException {
        Doador d =tableDoadores.getSelectionModel().getSelectedItem();
        if(d!=null){
        if (habitat.remDoador(d.getIdDoador())) {
            ObservableList<Doador> dataDoadores = FXCollections.observableArrayList(habitat.getDoadores());
            this.tableDoadores.setItems(dataDoadores);
        } else {
            // REMOVER TODAS AS DOAÇÔES???
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Não Pode remover o Doador!\nExiste uma Doacão associada a este Doador.");
            diag.showAndWait();

        }
    }
        else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Doador.");
            diag.showAndWait();
        }
    }

    @FXML
    public void bDoadoresSaveOnClick() throws PersistenceException {
        if (!bDoadoresEdit.isDisable()) {
            Doador d = tableDoadores.getSelectionModel().getSelectedItem();
            d.setNome(tf_nome.getText());
            d.setEmpresa(combo_tipo.getSelectionModel().getSelectedIndex());
            d.setTelefone(Integer.valueOf(tf_telefone.getText()));
            d.setRua(tf_morada.getText());
            d.setCodPostal(tf_codpostal.getText());
            d.setLocalidade(tfLocal.getText());
            d.setNif(Integer.valueOf(tf_nif.getText()));
            d.setEmail(tf_email.getText());
            d.setResponsavelEmpresa(tf_responsavel.getText());
            d.setContactoResponsavel(Integer.valueOf(tf_respcontacto.getText().equals("") ? "0" : tf_respcontacto.getText()));
            d.setActividade(cbAtividade.getSelectionModel().getSelectedItem());
            //d.setObs();
            habitat.setDoador(d);
        } else {
            Doador d = new Doador();
            d.setFuncionario(habitat.getF().getIdFuncionario());
            d.setNome(tf_nome.getText());
            d.setEmpresa(combo_tipo.getSelectionModel().getSelectedIndex());
            d.setTelefone(Integer.valueOf(tf_telefone.getText()));
            d.setRua(tf_morada.getText());
            d.setCodPostal(tf_codpostal.getText());
            d.setLocalidade(tfLocal.getText());
            d.setNif(Integer.valueOf(tf_nif.getText()));
            d.setEmail(tf_email.getText());
            d.setResponsavelEmpresa(tf_responsavel.getText());
            d.setContactoResponsavel(Integer.valueOf(tf_respcontacto.getText().equals("") ? "0" : tf_respcontacto.getText()));
            d.setActividade(cbAtividade.getSelectionModel().getSelectedItem());
            //d.setObs();
            habitat.addDoador(d);
        }
        ObservableList<Doador> dataDoadores = FXCollections.observableArrayList(habitat.getDoadores());
        this.tableDoadores.setItems(dataDoadores);
        bDoadoresCancelarOnClick();
    }

    @FXML
    public void bDoadoresCancelarOnClick() {
        tabpaneDoadores.setMouseTransparent(false);
        tabDoacoes2.setDisable(false);
        bDoadoresAdd.setDisable(false);
        bDoadoresEdit.setDisable(false);
        bDoadoresRem.setDisable(false);
        bDoadoresSave.setVisible(false);
        bDoadoresCancelar.setVisible(false);

        tf_nome.setEditable(false);
        tf_nif.setEditable(false);
        combo_tipo.setDisable(false);
        tf_morada.setEditable(false);
        tf_codpostal.setEditable(false);
        tf_telefone.setEditable(false);
        tf_email.setEditable(false);
        tf_respcontacto.setEditable(false);
        tf_responsavel.setEditable(false);

        cbAtividade.setMouseTransparent(true);
        combo_tipo.setMouseTransparent(true);
    }

    private void permissoes(int idfuncao) {
        switch (idfuncao) {
            case 1:
                bDoadoresAdd.setVisible(false);
                bDoadoresEdit.setVisible(false);
                bDoadoresRem.setVisible(false);
                addDoacaoButton.setVisible(false);
                bDoacaoEdit.setVisible(false);
                bDoacaoRem.setVisible(false);
                break;
            case 2:
                bDoadoresAdd.setVisible(false);
                bDoadoresEdit.setVisible(false);
                bDoadoresRem.setVisible(false);
                addDoacaoButton.setVisible(false);
                bDoacaoEdit.setVisible(false);
                bDoacaoRem.setVisible(false);
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    @FXML
    private void procuraDoador() throws PersistenceException {
        String nome = textDoador.getText();
        if (!nome.equals("")) {

            ObservableList<Doador> dataDoadores = FXCollections.observableArrayList(habitat.getDoadoresByName(nome));

            this.tableDoadores.setItems(dataDoadores);

        } else if (nome.equals("")) {
            ObservableList<Doador> dataDoadores = FXCollections.observableArrayList(habitat.getDoadoresByName(nome));

            this.tableDoadores.setItems(dataDoadores);

        }

    }

    void mostraDoadorFromProj(Doador d) throws PersistenceException {
        this.bDoacaoRem.setVisible(false);
        this.addDoacaoButton.setVisible(false);
        this.bDoadoresAdd.setDisable(true);
        this.bDoadoresEdit.setDisable(true);
        this.bDoadoresRem.setDisable(true);
        this.tableDoadores.setMouseTransparent(true);
        this.tabDoacoes2.setDisable(false);
        
        tf_nome.setText(d.getNome());
        tf_nif.setText(String.valueOf(d.getNif()));
        tf_morada.setText(d.getRua());
        tfLocal.setText(d.getLocalidade());
        tf_codpostal.setText(d.getCodPostal());
        //tf_localidade.setText(v.getLocalidade());
        tf_telefone.setText(String.valueOf(d.getTelefone()));
        tf_email.setText(d.getEmail());
        //TreeMap<String, Integer> ativs = (TreeMap<String, Integer>)habitat.getAtividades();
        //cbAtividade.
        if (d.getEmpresa() == 1) {
            combo_tipo.getSelectionModel().selectLast();
            tf_responsavel.setDisable(false);
            tf_respcontacto.setDisable(false);
            tf_responsavel.setText(d.getResponsavelEmpresa());
            tf_respcontacto.setText(String.valueOf(d.getContactoResponsavel()));
        } else {
            combo_tipo.getSelectionModel().selectFirst();
            tf_responsavel.setDisable(true);
            tf_responsavel.setText("");
            tf_respcontacto.setDisable(true);
            tf_respcontacto.setText("");
        }
        
        cbAtividade.setValue(d.getAtividade());
        //this.popTableDoacoes(d);

        this.tabDoacoes.getItems().clear();
        this.doacoes = FXCollections.observableArrayList(habitat.getDoador(d.getIdDoador()).getDoacoes());
        this.tabDoacoes.setItems(doacoes);
    }

}
