package pt.uminho.lei.dss.view;

import pt.uminho.lei.dss.model.Voluntario;
import pt.uminho.lei.dss.model.Projecto;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Doador;
import pt.uminho.lei.dss.model.Equipa;
import pt.uminho.lei.dss.model.Funcionario;
import pt.uminho.lei.dss.model.Material;
import pt.uminho.lei.dss.model.SGH;
import pt.uminho.lei.dss.model.Tarefa;

public class Projetos_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bProjetoAdd;

    @FXML
    private Button bProjetoEdit;

    @FXML
    private Button bProjetoRem;

    @FXML
    private Button bProjetoSave;

    @FXML
    private Button bProjetoCancelar;

    @FXML
    private TableView<Projecto> tableProjAtivos;
    @FXML
    private TableColumn<Projecto, String> tcAtivosNome;

    @FXML
    private TableView<Projecto> tableProjEspera;
    @FXML
    private TableColumn<Projecto, String> tcEsperaNome;

    @FXML
    private TableView<Projecto> tableProjConcluidos;
    @FXML
    private TableColumn<Projecto, String> tcConcluidosNome;

    @FXML
    private TableView<Voluntario> tableVoluntarios;
    @FXML
    private TableColumn<Voluntario, String> tcVoluntarioNome;

    @FXML
    private BarChart<?, ?> tarefasGrafico;

    @FXML
    private TableView<Doador> tableDoadores;
    @FXML
    private TableColumn<Doador, String> tcDoadoresName;

    //Table tarefas
    @FXML
    private TableView<Tarefa> tableTarefas;
    @FXML
    private TableColumn<Tarefa, String> tcTarefasConcluida;
    @FXML
    private TableColumn<Tarefa, String> tcTarefasDataInicial;
    @FXML
    private TableColumn<Tarefa, String> tcTarefasNome;

    @FXML
    private Button bTarefaAdd;

    @FXML
    private Button bTarefaRem;

    @FXML
    private Button bTarefaEdit;

    @FXML
    private Button bTarefaSave;

    @FXML
    private Button bTarefaCancel;

    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_custoestimado;

    @FXML
    private TextField tf_morada;

    @FXML
    private TextField tf_codpostal;

    @FXML
    private TextField tf_localidade;

    @FXML
    private ComboBox cb_responsavel;

    @FXML
    private ComboBox combo_candidatura;

    @FXML
    private DatePicker dp_datainicio;

    @FXML
    private DatePicker dp_datafim;

    @FXML
    private ComboBox estadoBox;

    @FXML
    private Circle circleEstado;

    @FXML
    private TextField tfTarefaNome;

    @FXML
    private DatePicker dpTarefaDataInicio;

    @FXML
    private DatePicker dpTarefaDataFim;

    @FXML
    private CheckBox cbTarefaConcluida;

    @FXML
    private TabPane tabpaneProjetos;

    @FXML
    private TabPane tabpaneProjInfo;

    @FXML
    private Tab tabProjAtivos;

    @FXML
    private Tab tabProjEspera;

    @FXML
    private Tab tabProjConcluidos;

    @FXML
    private Tab tabProjetoInfo;

    @FXML
    private Tab tabVoluntarios;

    @FXML
    private Tab tabDoadores;

    @FXML
    private Tab tabTarefas;

    @FXML
    private Tab tabResumo;

    @FXML
    private Button bVoluntarioAdd;

    @FXML
    private Button bEquipaAdd;

    @FXML
    private Button bVoluntarioRem;

    @FXML
    private ComboBox cbVolEquipa;

    @FXML
    private TextField tfResumoTarefas;

    @FXML
    private TextField tfResumoTarefasC;

    @FXML
    private TextField tfResumoTarefasP;

    @FXML
    private Button bAddMat;
    @FXML
    private Button bRemMat;
    @FXML
    private Button bSaveMat;
    @FXML
    private Button bCancelMat;
    @FXML
    private TextField tfNomeMat;
    @FXML
    private TableView tableMateriais;
    @FXML
    private TableColumn<Material, String> tcNomeMat;

    @FXML
    private Button bSearch;
    @FXML
    private TextField tfSearch;

    private Stage anterior;
    private Stage atual;

    private SGH habitat;
    private Projecto selectedProj;

    public Projetos_Controller() {
    }

    public SGH getHabitat() {
        return habitat;
    }

    public void setHabitat(SGH habitat) {
        this.habitat = habitat;
        permissoes(habitat.getF().getPermissao());
        try {
            tcAtivosNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
            tcConcluidosNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
            tcEsperaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
            ArrayList<Projecto> allProj = (ArrayList<Projecto>) habitat.getProjetos();
            ArrayList<Projecto> pconcluidos = new ArrayList<>();
            ArrayList<Projecto> pespera = new ArrayList<>();
            ArrayList<Projecto> pativos = new ArrayList<>();
            for (Projecto p : allProj) {
                switch (p.getEstado()) {
                    case 0:
                        pconcluidos.add(p);
                        break;
                    case 1:
                        pespera.add(p);
                        break;
                    default:
                        pativos.add(p);
                        break;
                }
            }
            ObservableList<Projecto> data1 = FXCollections.observableArrayList(pconcluidos);
            this.tableProjConcluidos.setItems(data1);
            ObservableList<Projecto> data2 = FXCollections.observableArrayList(pespera);
            this.tableProjEspera.setItems(data2);
            ObservableList<Projecto> data3 = FXCollections.observableArrayList(pativos);
            this.tableProjAtivos.setItems(data3);
        } catch (PersistenceException ex) {
            Logger.getLogger(Projetos_Controller.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> estados = new ArrayList<>();
        estados.add("Concluido");
        estados.add("Em Espera");
        estados.add("Ativo");
        ObservableList<String> options = FXCollections.observableArrayList(estados);
        estadoBox.setItems(options);

        bProjetoEdit.setDisable(true);

        tfResumoTarefas.setEditable(false);
        tfResumoTarefasC.setEditable(false);
        tfResumoTarefasP.setEditable(false);
        bVoluntarioAdd.setMouseTransparent(true);
        bVoluntarioRem.setMouseTransparent(true);
        cbVolEquipa.setMouseTransparent(true);
        bTarefaAdd.setMouseTransparent(true);
        bTarefaCancel.setMouseTransparent(true);
        bTarefaEdit.setMouseTransparent(true);
        bTarefaSave.setMouseTransparent(true);
        bTarefaRem.setMouseTransparent(true);
        bEquipaAdd.setMouseTransparent(true);
        bAddMat.setMouseTransparent(true);
        bRemMat.setMouseTransparent(true);
        bSaveMat.setVisible(false);
        bCancelMat.setVisible(false);
        tfNomeMat.setEditable(false);

        bProjetoRem.setDisable(true);
        bProjetoSave.setVisible(false);
        bProjetoCancelar.setVisible(false);
        tf_nome.setEditable(false);
        dp_datainicio.setMouseTransparent(true);
        dp_datafim.setMouseTransparent(true);
        tf_morada.setEditable(false);
        tf_codpostal.setEditable(false);
        tf_localidade.setEditable(false);
        tf_custoestimado.setEditable(false);
        cb_responsavel.setMouseTransparent(true);
        combo_candidatura.setMouseTransparent(true);
        this.tf_codpostal.setEditable(false);
        bTarefaSave.setVisible(false);
        bTarefaCancel.setVisible(false);
        tfTarefaNome.setEditable(false);
        dpTarefaDataInicio.setMouseTransparent(true);
        dpTarefaDataFim.setMouseTransparent(true);
        cbTarefaConcluida.setMouseTransparent(true);
        estadoBox.setMouseTransparent(true);
        bVoluntarioRem.setDisable(true);
        tcTarefasNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        tcTarefasDataInicial.setCellValueFactory(cellData -> cellData.getValue().getDataInicialProperty());
        tcTarefasConcluida.setCellValueFactory(cellData -> cellData.getValue().getConcluida());

        tcNomeMat.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());

        tfSearch.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                try {
                    searchProjs();
                } catch (PersistenceException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("ERRO");
                    a.setHeaderText(null);
                    a.setContentText("Erro ao procurar funcionário");
                }
            }
        });

    }

    @FXML
    private void tableProjAtivosOnClick() throws PersistenceException {
        if (!this.tableProjAtivos.getSelectionModel().isEmpty()) {
            Projecto p = tableProjAtivos.getSelectionModel().getSelectedItem();
            this.selectedProj = p;
            this.mostraProj(p);
        }
    }

    @FXML
    private void tableProjEsperaOnClick() throws PersistenceException {
        if (!this.tableProjEspera.getSelectionModel().isEmpty()) {
            Projecto p = tableProjEspera.getSelectionModel().getSelectedItem();
            this.selectedProj = p;
            this.mostraProj(p);
        }
    }

    @FXML
    private void tableProjConcluidosOnClick() throws PersistenceException {

        if (!this.tableProjConcluidos.getSelectionModel().isEmpty()) {
            Projecto p = tableProjConcluidos.getSelectionModel().getSelectedItem();
            this.selectedProj = p;
            this.mostraProj(p);
        }

    }

    @FXML
    private void tableVoluntarioOnClick() {

        bVoluntarioRem.setDisable(false);
        /*
         try {
         Voluntario v = tableVoluntarios.getSelectionModel().getSelectedItem();

         FXMLLoader loader = new FXMLLoader(getClass().getResource("Voluntarios.fxml"));
         Parent root = loader.load();
         Voluntarios_Controller vol_c = loader.getController();
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.setResizable(false);
         stage.setTitle("Voluntario");
         vol_c.setAnterior(this.atual);
         vol_c.setAtual(stage);
         stage.show();

         vol_c.mostraVfromProj(v);
         } catch (IOException ex) {
         Logger.getLogger(Projetos_Controller.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    @FXML
    private void tableTarefaOnClick() {
        Tarefa t = tableTarefas.getSelectionModel().getSelectedItem();
        tfTarefaNome.setText(t.getNome());
        dpTarefaDataInicio.setValue(t.getDataInicial());
        dpTarefaDataFim.setValue(t.getDataFinal());
        cbTarefaConcluida.setSelected(t.isConcluida());
    }

    @FXML
    private void tarefaAddOnClick() {
        tableTarefas.setMouseTransparent(true);
        tableProjAtivos.setMouseTransparent(true);
        tableProjConcluidos.setMouseTransparent(true);
        tableProjEspera.setMouseTransparent(true);

        tfTarefaNome.setEditable(true);
        dpTarefaDataInicio.setMouseTransparent(false);
        dpTarefaDataFim.setMouseTransparent(false);
        cbTarefaConcluida.setMouseTransparent(false);
        tfTarefaNome.setText("");
        dpTarefaDataInicio.setValue(LocalDate.now());
        dpTarefaDataFim.setValue(LocalDate.now());
        tfTarefaNome.setPromptText("Insira o Nome da Nova Tarefa");
        bTarefaEdit.setDisable(true);
        bTarefaRem.setDisable(true);
        bTarefaSave.setVisible(true);
        bTarefaCancel.setVisible(true);
    }

    @FXML
    private void tarefaRemOnClick() throws PersistenceException {
        Projecto p;
        if (tabProjAtivos.isSelected()) {
            p = tableProjAtivos.getSelectionModel().getSelectedItem();
        } else if (tabProjEspera.isSelected()) {
            p = tableProjEspera.getSelectionModel().getSelectedItem();
        } else {
            p = tableProjConcluidos.getSelectionModel().getSelectedItem();
        }
        Tarefa t = tableTarefas.getSelectionModel().getSelectedItem();
        if (p != null && t != null) {
            System.out.println(t.getId());
            habitat.remTarefaProjecto(t.getId(), p.getIdProjecto());
            ObservableList<Tarefa> data3 = FXCollections.observableArrayList(this.habitat.getTarefasProjecto(p.getIdProjecto()));
            this.tableTarefas.setItems(data3);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione uma Tarefa.");
            diag.showAndWait();
        }
    }

    @FXML
    private void tarefaEditOnClick() throws PersistenceException {
        if (tableTarefas.getSelectionModel().getSelectedItem() != null) {
            tableTarefas.setMouseTransparent(true);
            tableProjAtivos.setMouseTransparent(true);
            tableProjConcluidos.setMouseTransparent(true);
            tableProjEspera.setMouseTransparent(true);
            bTarefaSave.setVisible(true);
            bTarefaCancel.setVisible(true);
            bTarefaAdd.setDisable(true);
            bTarefaRem.setDisable(true);

            tfTarefaNome.setEditable(true);
            dpTarefaDataInicio.setMouseTransparent(false);
            dpTarefaDataFim.setMouseTransparent(false);
            cbTarefaConcluida.setMouseTransparent(false);
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione uma Tarefa.");
            diag.showAndWait();
        }
    }

    @FXML
    private void tarefaSaveOnClick() throws PersistenceException {

        tfResumoTarefas.setEditable(false);
        tfResumoTarefasC.setEditable(false);
        tfResumoTarefasP.setEditable(false);
        bVoluntarioAdd.setMouseTransparent(true);
        bVoluntarioRem.setMouseTransparent(true);
        cbVolEquipa.setMouseTransparent(true);
        bTarefaAdd.setMouseTransparent(true);
        bTarefaCancel.setMouseTransparent(true);
        bTarefaEdit.setMouseTransparent(true);
        bTarefaSave.setMouseTransparent(true);
        bTarefaRem.setMouseTransparent(true);
        bEquipaAdd.setMouseTransparent(true);

        Projecto p;
        if (tabProjAtivos.isSelected()) {
            p = tableProjAtivos.getSelectionModel().getSelectedItem();
        } else if (tabProjEspera.isSelected()) {
            p = tableProjEspera.getSelectionModel().getSelectedItem();
        } else {
            p = tableProjConcluidos.getSelectionModel().getSelectedItem();
        }
        Tarefa novo = new Tarefa();
        novo.setNome(tfTarefaNome.getText());
        novo.setConcluida(cbTarefaConcluida.isSelected());
        novo.setDataInicial(dpTarefaDataInicio.getValue());
        novo.setDataFinal(dpTarefaDataFim.getValue());
        Tarefa old = habitat.getTarefaByName(tfTarefaNome.getText());
        if (old != null) {
            old.setProjecto(p.getIdProjecto());
            old.setConcluida(cbTarefaConcluida.isSelected());
            old.setDataInicial(dpTarefaDataInicio.getValue());
            old.setDataFinal(this.dpTarefaDataFim.getValue());
            habitat.setTarefaProjecto(old);
        } else {
            if (!bTarefaEdit.isDisable()) {
                habitat.remTarefaProjecto(tableTarefas.getSelectionModel().getSelectedItem().getId(), p.getIdProjecto());
            }
            novo.setFuncionario(habitat.getF().getIdFuncionario());
            habitat.addTarefa(novo);
            int idTarefa = habitat.getTarefaByName(novo.getNome()).getId();
            //System.out.println("TAREFA ID: "+idTarefa);
            habitat.addTarefaProjeto(idTarefa, p.getIdProjecto(), novo.isConcluida(), novo.getDataInicial(), novo.getDataFinal());

        }
        ObservableList<Tarefa> data3 = FXCollections.observableArrayList(this.habitat.getTarefasProjecto(p.getIdProjecto()));
        this.tableTarefas.setItems(data3);
        bTarefaCancel.setVisible(false);
        bTarefaSave.setVisible(false);
        bTarefaAdd.setDisable(false);
        bTarefaEdit.setDisable(false);
        bTarefaRem.setDisable(false);

        tfTarefaNome.setEditable(false);
        dpTarefaDataInicio.setMouseTransparent(true);
        dpTarefaDataFim.setMouseTransparent(true);
        cbTarefaConcluida.setMouseTransparent(true);

        tableTarefas.setMouseTransparent(false);
        tableProjAtivos.setMouseTransparent(false);
        tableProjConcluidos.setMouseTransparent(false);
        tableProjEspera.setMouseTransparent(false);
    }

    @FXML
    private void tarefaCancelOnClick() throws PersistenceException {
        tfTarefaNome.setText("");
        bTarefaAdd.setDisable(false);
        bTarefaEdit.setDisable(false);
        bTarefaRem.setDisable(false);
        bTarefaSave.setVisible(false);
        bTarefaCancel.setVisible(false);
        tfTarefaNome.setEditable(false);
        dpTarefaDataInicio.setMouseTransparent(true);
        dpTarefaDataFim.setMouseTransparent(true);
        cbTarefaConcluida.setMouseTransparent(true);
        tableTarefas.setMouseTransparent(false);
        tableProjAtivos.setMouseTransparent(false);
        tableProjConcluidos.setMouseTransparent(false);
        tableProjEspera.setMouseTransparent(false);
    }

    @FXML
    private void ProjetoAddOnClick() throws PersistenceException {
        ObservableList<Integer> candidaturas = FXCollections.observableArrayList(habitat.getCandidaturasSemProjeto());
        if (candidaturas.isEmpty()) {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Não existe nenhuma Candidatura sem Projeto associado!\nAdicionar Projeto Cancelado.");
            diag.showAndWait();
            return;
        }
//        tabpaneProjInfo.getSelectionModel().select(tabProjetoInfo);
        tabpaneProjetos.setMouseTransparent(true);
        tabVoluntarios.setDisable(true);
        tabDoadores.setDisable(true);
        tabTarefas.setDisable(true);
//        tabResumo.setDisable(true);

        tf_nome.setEditable(true);
        tf_nome.setText("");
        tf_nome.setPromptText("Introduza o Nome do Novo Projeto");
        tf_custoestimado.setEditable(true);
        tf_custoestimado.setText("");
        tf_morada.setEditable(true);
        tf_morada.setText("");
        tf_codpostal.setEditable(true);
        tf_codpostal.setText("");
        tf_localidade.setEditable(true);
        tf_localidade.setText("");
        cb_responsavel.setValue(habitat.getF().getNome());
        dp_datainicio.setMouseTransparent(false);
        dp_datainicio.setValue(LocalDate.now());
        dp_datafim.setMouseTransparent(false);
        dp_datafim.setValue(LocalDate.now());
        estadoBox.setMouseTransparent(false);
        combo_candidatura.setValue("");
        combo_candidatura.setMouseTransparent(false);
        combo_candidatura.setItems(candidaturas);
        bProjetoCancelar.setVisible(true);
        bProjetoSave.setVisible(true);
        bProjetoEdit.setDisable(true);
        bProjetoRem.setDisable(true);

    }

    @FXML
    private void ProjetoEditOnClick() throws PersistenceException {
        Projecto p;
        if (tabProjAtivos.isSelected()) {
            p = tableProjAtivos.getSelectionModel().getSelectedItem();
        } else if (tabProjEspera.isSelected()) {
            p = tableProjEspera.getSelectionModel().getSelectedItem();
        } else {
            p = tableProjConcluidos.getSelectionModel().getSelectedItem();
        }
        if (p != null) {
            //tabpaneProjInfo.getSelectionModel().select(tabProjetoInfo);
            tabpaneProjetos.setMouseTransparent(true);
            tabVoluntarios.setDisable(true);
            tabDoadores.setDisable(true);
            tabTarefas.setDisable(true);
            //tabResumo.setDisable(true);
            bProjetoCancelar.setVisible(true);
            bProjetoSave.setVisible(true);
            bProjetoAdd.setDisable(true);
            bProjetoRem.setDisable(true);

            tf_nome.setEditable(true);
            tf_custoestimado.setEditable(true);
            tf_morada.setEditable(true);
            tf_codpostal.setEditable(true);
            tf_localidade.setEditable(true);
            dp_datainicio.setMouseTransparent(false);
            dp_datafim.setMouseTransparent(false);
            estadoBox.setMouseTransparent(false);
            combo_candidatura.setMouseTransparent(false);
            cb_responsavel.setMouseTransparent(false);
            ObservableList<Funcionario> funcs = FXCollections.observableArrayList(habitat.getFuncionarios());
            cb_responsavel.setItems(funcs);
            cb_responsavel.getSelectionModel().select(0);
            estadoBox.setOnHidden((Event t) -> {
                if (estadoBox.getValue().equals("Ativo")) {
                    circleEstado.setFill(Color.web("#21ff23", 1));
                } else if (estadoBox.getValue().equals("Concluido")) {
                    circleEstado.setFill(Color.RED);
                } else {
                    circleEstado.setFill(Color.YELLOW);
                }
            });
        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Projeto.");
            diag.showAndWait();
        }
    }

    @FXML
    private void ProjetoRemOnClick() throws PersistenceException {
        Projecto p;
        if (tabProjAtivos.isSelected()) {
            p = tableProjAtivos.getSelectionModel().getSelectedItem();
        } else if (tabProjEspera.isSelected()) {
            p = tableProjEspera.getSelectionModel().getSelectedItem();
        } else {
            p = tableProjConcluidos.getSelectionModel().getSelectedItem();
        }
        if (p != null) {
            if (habitat.remProjeto(p.getIdProjecto()) == false) {
                Alert diag = new Alert(Alert.AlertType.ERROR);
                diag.setTitle("Erro");
                diag.setHeaderText(null);
                diag.setContentText("Não Pode remover o Projeto!");
                diag.showAndWait();
            } else {

                ArrayList<Projecto> allProj = (ArrayList<Projecto>) habitat.getProjetos();
                ArrayList<Projecto> pconcluidos = new ArrayList<>();
                ArrayList<Projecto> pespera = new ArrayList<>();
                ArrayList<Projecto> pativos = new ArrayList<>();
                for (Projecto pro : allProj) {
                    switch (pro.getEstado()) {
                        case 0:
                            pconcluidos.add(pro);
                            break;
                        case 1:
                            pespera.add(pro);
                            break;
                        default:
                            pativos.add(pro);
                            break;
                    }
                }
                ObservableList<Projecto> data1 = FXCollections.observableArrayList(pconcluidos);
                this.tableProjConcluidos.setItems(data1);
                ObservableList<Projecto> data2 = FXCollections.observableArrayList(pespera);
                this.tableProjEspera.setItems(data2);
                ObservableList<Projecto> data3 = FXCollections.observableArrayList(pativos);
                this.tableProjAtivos.setItems(data3);
            }

        } else {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("Erro");
            diag.setHeaderText(null);
            diag.setContentText("Selecione um Projeto.");
            diag.showAndWait();
        }
    }

    @FXML
    private void ProjetoSaveOnClick() throws PersistenceException {
        if (!bProjetoEdit.isDisable()) {
            Projecto p;
            if (tabProjAtivos.isSelected()) {
                p = tableProjAtivos.getSelectionModel().getSelectedItem();
            } else if (tabProjEspera.isSelected()) {
                p = tableProjEspera.getSelectionModel().getSelectedItem();
            } else {
                p = tableProjConcluidos.getSelectionModel().getSelectedItem();
            }
            p.setNome(tf_nome.getText());
            p.setCandidatura_Nr((int) combo_candidatura.getValue());
            p.setDataInicial(dp_datainicio.getValue());
            p.setDataFinal(dp_datafim.getValue());
            p.setCustoEstimado(Double.valueOf(tf_custoestimado.getText()));
            p.setRua(tf_morada.getText());
            p.setLocalidade(tf_localidade.getText());
            p.setCodPostal(tf_codpostal.getText());
            p.setEstado(estadoBox.getSelectionModel().getSelectedIndex());
            Funcionario faux = (Funcionario) cb_responsavel.getSelectionModel().getSelectedItem();
            p.setFuncionario(faux.getIdFuncionario());
            habitat.setProjeto(p);
        } else {
            Projecto p = new Projecto();
            p.setNome(tf_nome.getText());
            p.setCandidatura_Nr((int) combo_candidatura.getValue());
            p.setDataInicial(dp_datainicio.getValue());
            p.setDataFinal(dp_datafim.getValue());
            p.setCustoEstimado(Double.valueOf(tf_custoestimado.getText()));
            p.setRua(tf_morada.getText());
            p.setLocalidade(tf_localidade.getText());
            p.setCodPostal(tf_codpostal.getText());
            p.setEstado(estadoBox.getSelectionModel().getSelectedIndex());
            p.setFuncionario(habitat.getF().getIdFuncionario());
            p.setVoluntarios(new ArrayList<>());
            p.setDoadores(new ArrayList<>());
            p.setTarefas(new ArrayList<>());
            habitat.addProjeto(p);
        }
        tabpaneProjetos.setMouseTransparent(false);
        tabVoluntarios.setDisable(false);
        tabDoadores.setDisable(false);
        tabTarefas.setDisable(false);
//        tabResumo.setDisable(false);

        tf_nome.setEditable(false);
        tf_custoestimado.setEditable(false);
        tf_morada.setEditable(false);
        tf_codpostal.setEditable(false);
        tf_localidade.setEditable(false);
        dp_datainicio.setMouseTransparent(true);
        dp_datafim.setMouseTransparent(true);
        estadoBox.setMouseTransparent(true);
        combo_candidatura.setMouseTransparent(true);

        bProjetoCancelar.setVisible(false);
        bProjetoSave.setVisible(false);
        bProjetoEdit.setDisable(false);
        bProjetoRem.setDisable(false);
        bProjetoAdd.setDisable(false);

        ArrayList<Projecto> allProj = (ArrayList<Projecto>) habitat.getProjetos();
        ArrayList<Projecto> pconcluidos = new ArrayList<>();
        ArrayList<Projecto> pespera = new ArrayList<>();
        ArrayList<Projecto> pativos = new ArrayList<>();
        for (Projecto p : allProj) {
            switch (p.getEstado()) {
                case 0:
                    pconcluidos.add(p);
                    break;
                case 1:
                    pespera.add(p);
                    break;
                default:
                    pativos.add(p);
                    break;
            }
        }
        ObservableList<Projecto> data1 = FXCollections.observableArrayList(pconcluidos);
        this.tableProjConcluidos.setItems(data1);
        ObservableList<Projecto> data2 = FXCollections.observableArrayList(pespera);
        this.tableProjEspera.setItems(data2);
        ObservableList<Projecto> data3 = FXCollections.observableArrayList(pativos);
        this.tableProjAtivos.setItems(data3);

    }

    @FXML
    private void ProjetoCancelarOnClick() throws PersistenceException {

        tfResumoTarefas.setEditable(false);
        tfResumoTarefasC.setEditable(false);
        tfResumoTarefasP.setEditable(false);
        bVoluntarioAdd.setMouseTransparent(true);
        bVoluntarioRem.setMouseTransparent(true);
        cbVolEquipa.setMouseTransparent(true);
        bTarefaAdd.setMouseTransparent(true);
        bTarefaCancel.setMouseTransparent(true);
        bTarefaEdit.setMouseTransparent(true);
        bTarefaSave.setMouseTransparent(true);
        bTarefaRem.setMouseTransparent(true);
        bEquipaAdd.setMouseTransparent(true);

        tabpaneProjetos.setMouseTransparent(false);
        tabVoluntarios.setDisable(false);
        tabDoadores.setDisable(false);
        tabTarefas.setDisable(false);
//        tabResumo.setDisable(false);
        tfResumoTarefas.setEditable(false);
        tfResumoTarefasC.setEditable(false);
        tfResumoTarefasP.setEditable(false);

        tf_nome.setEditable(false);
        tf_custoestimado.setEditable(false);
        tf_morada.setEditable(false);
        tf_codpostal.setEditable(false);
        tf_localidade.setEditable(false);
        dp_datainicio.setMouseTransparent(true);
        dp_datafim.setMouseTransparent(true);
        estadoBox.setMouseTransparent(true);
        combo_candidatura.setMouseTransparent(true);

        bProjetoCancelar.setVisible(false);
        bProjetoSave.setVisible(false);
        bProjetoEdit.setDisable(false);
        bProjetoRem.setDisable(false);
        bProjetoAdd.setDisable(false);
    }

    @FXML
    private void VoluntarioAddOnClick() throws PersistenceException {
        bVoluntarioRem.setDisable(true);
        if (bVoluntarioAdd.getText().equals("Adicionar Voluntário")) {
            bVoluntarioAdd.setText("Guardar ");
            bEquipaAdd.setText("Cancelar");
            cbVolEquipa.setValue("Escolha o Voluntario");
            ObservableList<Voluntario> voluntarios = FXCollections.observableArrayList(habitat.getVoluntarios());
            cbVolEquipa.setItems(voluntarios);
        } else {
            Projecto p;
            if (tabProjAtivos.isSelected()) {
                p = tableProjAtivos.getSelectionModel().getSelectedItem();
            } else if (tabProjEspera.isSelected()) {
                p = tableProjEspera.getSelectionModel().getSelectedItem();
            } else {
                p = tableProjConcluidos.getSelectionModel().getSelectedItem();
            }
            if (bVoluntarioAdd.getText().equals("Guardar ")) {
                Voluntario v = (Voluntario) cbVolEquipa.getSelectionModel().getSelectedItem();
                habitat.addVoluntarioProjeto(p.getIdProjecto(), v.getId());
                p.addVoluntario(v);
                ObservableList<Voluntario> data = FXCollections.observableArrayList(p.getVoluntarios());
                this.tableVoluntarios.setItems(data);
                bVoluntarioAdd.setText("Adicionar Voluntário");
                bEquipaAdd.setText("Adicionar Equipa");
            } else {
                Equipa e = (Equipa) cbVolEquipa.getSelectionModel().getSelectedItem();
                for (Voluntario v : habitat.getEquipaVoluntarios(e.getIdEquipa())) {
                    habitat.addVoluntarioProjeto(p.getIdProjecto(), v.getId());
                    p.addVoluntario(v);
                }
                ObservableList<Voluntario> data = FXCollections.observableArrayList(p.getVoluntarios());
                this.tableVoluntarios.setItems(data);
            }
            bVoluntarioAdd.setText("Adicionar Voluntário");
            bEquipaAdd.setText("Adicionar Equipa");
            bVoluntarioRem.setDisable(false);
            cbVolEquipa.setValue("");
        }
    }

    @FXML
    private void EquipaAddOnClick() throws PersistenceException {
        bVoluntarioRem.setDisable(true);
        if (bEquipaAdd.getText().equals("Adicionar Equipa")) {
            bVoluntarioAdd.setText("Guardar");
            bEquipaAdd.setText("Cancelar");
            cbVolEquipa.setValue("Escolha a Equipa");
            ObservableList<Equipa> equipas = FXCollections.observableArrayList(habitat.getEquipasByName(""));
            cbVolEquipa.setItems(equipas);
        } else {
            bVoluntarioAdd.setText("Adicionar Voluntário");
            bEquipaAdd.setText("Adicionar Equipa");
            bVoluntarioRem.setDisable(false);
            cbVolEquipa.setValue("");
        }
    }

    @FXML
    private void VoluntarioRemOnClick() throws PersistenceException {
        Projecto p;
        if (tabProjAtivos.isSelected()) {
            p = tableProjAtivos.getSelectionModel().getSelectedItem();
        } else if (tabProjEspera.isSelected()) {
            p = tableProjEspera.getSelectionModel().getSelectedItem();
        } else {
            p = tableProjConcluidos.getSelectionModel().getSelectedItem();
        }
        Voluntario v = tableVoluntarios.getSelectionModel().getSelectedItem();
        habitat.remVoluntarioProjeto(p.getIdProjecto(), v.getId());
        p.remVoluntario(v);
        ObservableList<Voluntario> data = FXCollections.observableArrayList(p.getVoluntarios());
        this.tableVoluntarios.setItems(data);
    }

    private void permissoes(int idfuncao) {
        switch (idfuncao) {
            case 1:
                bProjetoAdd.setVisible(false);
                bProjetoEdit.setVisible(false);
                bProjetoRem.setVisible(false);
                bTarefaAdd.setVisible(false);
                bTarefaEdit.setVisible(false);
                bTarefaRem.setVisible(false);
                bEquipaAdd.setVisible(false);
                bVoluntarioAdd.setVisible(false);
                bVoluntarioRem.setVisible(false);
                cbVolEquipa.setVisible(false);
                break;
            case 2:
                break;
            case 3:
                bProjetoAdd.setVisible(false);
                bProjetoEdit.setVisible(false);
                bProjetoRem.setVisible(false);
                bTarefaAdd.setVisible(false);
                bTarefaEdit.setVisible(false);
                bTarefaRem.setVisible(false);
                bEquipaAdd.setVisible(false);
                bVoluntarioAdd.setVisible(false);
                bVoluntarioRem.setVisible(false);
                cbVolEquipa.setVisible(false);
                break;
            default:
                break;
        }
    }

    private void mostraProj(Projecto p) throws PersistenceException {
        bProjetoAdd.setDisable(false);
        bProjetoEdit.setDisable(false);
        bProjetoRem.setDisable(false);
        tfResumoTarefas.setEditable(false);
        tfResumoTarefasC.setEditable(false);
        tfResumoTarefasP.setEditable(false);
        bVoluntarioAdd.setMouseTransparent(true);
        bVoluntarioRem.setMouseTransparent(true);
        cbVolEquipa.setMouseTransparent(true);
        bTarefaAdd.setMouseTransparent(true);
        bTarefaCancel.setMouseTransparent(true);
        bTarefaEdit.setMouseTransparent(true);
        bTarefaSave.setMouseTransparent(true);
        bTarefaRem.setMouseTransparent(true);
        bEquipaAdd.setMouseTransparent(true);
        bAddMat.setMouseTransparent(false);
        bRemMat.setMouseTransparent(false);

        tfResumoTarefas.setEditable(false);
        tfResumoTarefasC.setEditable(false);
        tfResumoTarefasP.setEditable(false);
        bVoluntarioAdd.setMouseTransparent(false);
        bVoluntarioRem.setMouseTransparent(false);
        cbVolEquipa.setMouseTransparent(false);
        bTarefaAdd.setMouseTransparent(false);
        bTarefaCancel.setMouseTransparent(false);
        bTarefaEdit.setMouseTransparent(false);
        bTarefaSave.setMouseTransparent(false);
        bTarefaRem.setMouseTransparent(false);
        bEquipaAdd.setMouseTransparent(false);

        tf_nome.setText(p.getNome());
        dp_datainicio.setValue(p.getDataInicial());
        dp_datafim.setValue(p.getDataFinal());
        tf_morada.setText(p.getRua());
        tf_codpostal.setText(p.getCodPostal());
        tf_localidade.setText(p.getLocalidade());
        tf_custoestimado.setText(String.valueOf(p.getCustoEstimado()));
        estadoBox.getSelectionModel().select(p.getEstado());
        cb_responsavel.setValue(habitat.getFuncionario(p.getFuncionario()).getNome());
        combo_candidatura.setValue(p.getCandidatura_Nr());
        switch (p.getEstado()) {
            case 2:
                circleEstado.setFill(Color.web("#21ff23", 1));
                break;
            case 1:
                circleEstado.setFill(Color.YELLOW);
                break;
            default:
                circleEstado.setFill(Color.RED);
                break;
        }

        tfResumoTarefas.setText(String.valueOf(p.getNumTarefas()));
        tfResumoTarefasC.setText(String.valueOf(p.getTarefasConcluidas()));
        tfResumoTarefasP.setText(String.valueOf(p.getTarefasConcluidas() / p.getNumTarefas()));
        tcVoluntarioNome.setCellFactory((Callback) (new GenericCellFactory("voluntario", this.atual, tableVoluntarios, this.habitat)));
        tcVoluntarioNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        //tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ObservableList<Voluntario> data = FXCollections.observableArrayList(p.getVoluntarios());
        this.tableVoluntarios.setItems(data);

        this.tcDoadoresName.setCellFactory((Callback) (new GenericCellFactory("doador", this.atual, this.tableDoadores, this.habitat)));
        tcDoadoresName.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        //tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ObservableList<Doador> data2 = FXCollections.observableArrayList(this.habitat.getProjeto(p.getIdProjecto()).getDoadores());
        this.tableDoadores.setItems(data2);

        this.tcTarefasNome.setCellFactory((Callback) (new GenericCellFactory("tarefa", this.atual, this.tableTarefas, this.habitat)));
        ObservableList<Tarefa> data3 = FXCollections.observableArrayList(this.habitat.getTarefasProjecto(p.getIdProjecto()));
        this.tableTarefas.setItems(data3);

        tcNomeMat.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
        ObservableList<Material> mats = FXCollections.observableArrayList(this.habitat.getMateriaisProjeto(p.getIdProjecto()));
        this.tableMateriais.setItems(mats);
    }

    @FXML
    private void addMat() {
        this.bSaveMat.setVisible(true);
        this.bCancelMat.setVisible(true);
        this.bRemMat.setDisable(true);

        this.tfNomeMat.setEditable(true);
        this.tfNomeMat.clear();
    }

    @FXML
    private void remMat() throws PersistenceException {
        boolean changed = false;
        if (!this.tableMateriais.getSelectionModel().isEmpty()) {
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText(null);
            a.setContentText("Deseja remover este material deste projeto?");
            Optional<ButtonType> r = a.showAndWait();
            if (r.get() == ButtonType.OK) {
                Material m = (Material) this.tableMateriais.getSelectionModel().getSelectedItem();
                this.habitat.remMaterialProjeto(this.selectedProj.getIdProjecto(), m.getIdMaterial());
                changed = true;
                this.bSaveMat.setVisible(false);
                this.bCancelMat.setVisible(false);
                this.bRemMat.setDisable(false);

                this.tfNomeMat.setEditable(false);
                this.tfNomeMat.clear();
            }
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Selecione um material na tabela");
            a.showAndWait();
        }
        if(changed){
            ObservableList<Material> mats = FXCollections.observableArrayList(this.habitat.getMateriaisProjeto(selectedProj.getIdProjecto()));
            this.tableMateriais.setItems(mats);
        }
    }

    @FXML
    private void saveMat() throws PersistenceException {
        boolean changed = false;
        if (this.tfNomeMat.getText().isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Preencha o nome do material");
            a.showAndWait();
        } else {
            Material m = new Material();
            if (this.habitat.existeMaterial(this.tfNomeMat.getText())) {
                m.setIdMaterial(this.habitat.getMaterialbyName(this.tfNomeMat.getText()).get(0).getIdMaterial());
                m.setFuncionario(this.habitat.getF().getIdFuncionario());
                m.setNome(this.tfNomeMat.getText());
                this.habitat.addMaterialProjeto(this.selectedProj.getIdProjecto(), m);
                changed = true;
            } else {
                Alert a = new Alert(AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText("Não existe um material com o nome introduzido no sistema");
                a.showAndWait();
            }
        }
        if (changed) {
            this.bSaveMat.setVisible(false);
            this.bCancelMat.setVisible(false);
            this.bRemMat.setDisable(false);

            this.tfNomeMat.setEditable(false);
            this.tfNomeMat.clear();
            ObservableList<Material> mats = FXCollections.observableArrayList(this.habitat.getMateriaisProjeto(selectedProj.getIdProjecto()));
            this.tableMateriais.setItems(mats);
        }
    }

    @FXML
    private void cancelMat() {
        this.bSaveMat.setVisible(false);
        this.bCancelMat.setVisible(false);
        this.bRemMat.setDisable(false);

        this.tfNomeMat.setEditable(false);
        this.tfNomeMat.clear();
    }

    @FXML
    private void tableMateriaisClicked() {
        if (!this.tableMateriais.getSelectionModel().isEmpty()) {
            Material m = (Material) this.tableMateriais.getSelectionModel().getSelectedItem();
            this.tfNomeMat.setText(m.getNomeProperty().get());
        }
    }

    @FXML
    private void searchProjs() throws PersistenceException {
        ArrayList<Projecto> projs;
        String nome = tfSearch.getText();
        if (nome.isEmpty()) {
            projs = (ArrayList<Projecto>) habitat.getProjetos();
        } else {
            projs = (ArrayList<Projecto>) this.habitat.getProjetosByName(nome);
        }
        ObservableList<Projecto> projsA = FXCollections.observableArrayList();
        ObservableList<Projecto> projsE = FXCollections.observableArrayList();
        ObservableList<Projecto> projsC = FXCollections.observableArrayList();

        for (Projecto p : projs) {
            switch (p.getEstado()) {
                case 2:
                    projsA.add(p);
                    break;
                case 1:
                    projsE.add(p);
                    break;
                default:
                    projsC.add(p);
                    break;
            }
        }
        this.tableProjAtivos.setItems(projsA);
        this.tableProjEspera.setItems(projsE);
        this.tableProjConcluidos.setItems(projsC);
    }

}
