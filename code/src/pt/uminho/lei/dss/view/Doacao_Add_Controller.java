package pt.uminho.lei.dss.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Doacao;
import pt.uminho.lei.dss.model.Evento;
import pt.uminho.lei.dss.model.Material;
import pt.uminho.lei.dss.model.Projecto;
import pt.uminho.lei.dss.model.SGH;

public class Doacao_Add_Controller implements Initializable {

    @FXML
    private ComboBox cbTipo;

    @FXML
    private ComboBox cbProjeto;

    @FXML
    private ComboBox cbEvento;

    @FXML
    private TextField tfMaterial;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private TextField tfQuantia;

    @FXML
    private DatePicker dpData;

    @FXML
    private Button bOk;

    @FXML
    private Button bCancel;

    //private Funcionario f;
    private Stage atual;
    private Stage anterior;
    private String mode;
    private SGH habitat;
    private int doador;

    private ObservableList<Doacao> doacoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.habitat = new SGH();

        ArrayList<String> tipos = new ArrayList<>();
        tipos.add("Dinheiro");
        tipos.add("Material");
        tipos.add("Serviço");
        ObservableList<String> tips = FXCollections.observableArrayList(tipos);
        this.cbTipo.setItems(tips);
        this.cbTipo.getSelectionModel().selectFirst();
        this.tfQuantia.setDisable(false);
        this.tfQuantidade.setDisable(true);
        this.tfMaterial.setDisable(true);
        cbEvento.setCellFactory(new Callback<ListView<Evento>, ListCell<Evento>>() {
            @Override
            public ListCell<Evento> call(ListView<Evento> p) {
                return new ListCell<Evento>() {
                    @Override
                    protected void updateItem(Evento e, boolean empty) {
                        super.updateItem(e, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setText(e.getNome().get());
                        }
                    }
                };
            }
        });
        cbEvento.setConverter(new StringConverter<Evento>() {
            @Override
            public String toString(Evento e) {
                return e.getNome().get();
            }

            @Override
            public Evento fromString(String s) {
                return null;
            }

        });
        cbProjeto.setCellFactory(new Callback<ListView<Projecto>, ListCell<Projecto>>() {
            @Override
            public ListCell<Projecto> call(ListView<Projecto> p) {
                return new ListCell<Projecto>() {
                    @Override
                    protected void updateItem(Projecto p, boolean empty) {
                        super.updateItem(p, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setText(p.getNome());
                        }
                    }
                };
            }
        });
        cbProjeto.setConverter(new StringConverter<Projecto>() {
            @Override
            public String toString(Projecto p) {
                return p.getNome();
            }

            @Override
            public Projecto fromString(String s) {
                return null;
            }

        });
    }

    @FXML
    public void cbTipoAction() {
        switch (cbTipo.getSelectionModel().getSelectedItem().toString()) {
            case "Material":
                this.tfQuantia.setDisable(true);
                this.tfQuantidade.setDisable(false);
                this.tfMaterial.setDisable(false);
                break;
            case "Dinheiro":
                this.tfQuantia.setDisable(false);
                this.tfQuantidade.setDisable(true);
                this.tfMaterial.setDisable(true);
                break;
            default:
                this.tfQuantia.setDisable(true);
                this.tfQuantidade.setDisable(true);
                this.tfMaterial.setDisable(true);
                break;
        }
    }

    public Stage getAtual() {
        return atual;
    }

    public void setAtual(Stage atual) {
        this.atual = atual;
    }

    public Stage getAnterior() {
        return anterior;
    }

    public void setAnterior(Stage anterior) {
        this.anterior = anterior;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setDoador(int doador) {
        this.doador = doador;
    }

    public void setDoacoes(ObservableList<Doacao> doas) throws PersistenceException {
        this.doacoes = doas;
        ObservableList<Evento> events = FXCollections.observableArrayList(this.habitat.getEventos());
        this.cbEvento.setItems(events);
        ObservableList<Projecto> projs = FXCollections.observableArrayList(this.habitat.getProjetos());
        this.cbProjeto.setItems(projs);
    }

    public SGH getHabitat() {
        return habitat;
    }

    public void setHabitat(SGH habitat) throws PersistenceException {
        this.habitat = habitat;
    }

    /*public void setController(Doadores_Controller dControl) {
     this.doadorControl = dControl;
     }*/
    public boolean checkFields() {
        boolean ok = true;

        if (dpData.getEditor().getText().isEmpty() || cbTipo.getValue().equals("")) {
            ok = false;
        } else {
            switch (cbTipo.getValue().toString()) {
                case "Dinheiro":
                    if (tfQuantia.getText().isEmpty()) {
                        ok = false;
                    }
                    break;
                case "Material": {
                    if (tfQuantidade.getText().isEmpty() || tfMaterial.getText().isEmpty()) {
                        ok = false;
                    }
                }
                break;
                default:
                    ok = true;
            }

        }
        return ok;
    }

    public void mostraDoacao(Doacao d) throws PersistenceException {
        this.bCancel.setVisible(false);
        this.bOk.setVisible(false);
        this.cbEvento.setMouseTransparent(true);
        this.cbProjeto.setMouseTransparent(true);
        this.cbTipo.setMouseTransparent(true);
        this.tfMaterial.setEditable(false);
        this.tfQuantia.setEditable(false);
        this.tfQuantidade.setEditable(false);
        this.dpData.setMouseTransparent(true);

        this.cbTipo.getSelectionModel().select(d.getTipo() - 1);
        if (d.getProjeto() != 0) {
            Projecto p = this.habitat.getProjeto(d.getProjeto());
            ObservableList<Projecto> projs = FXCollections.observableArrayList();
            projs.add(p);
            this.cbProjeto.setItems(projs);
            this.cbProjeto.getSelectionModel().select(0);
        }
        if (d.getEvento() != 0) {
            Evento e = this.habitat.getEvento(d.getEvento());
            ObservableList<Evento> evs = FXCollections.observableArrayList();
            evs.add(e);
            this.cbEvento.setItems(evs);
            this.cbEvento.getSelectionModel().select(0);
        }
        this.dpData.setValue(d.getData());
        //System.out.println(this.habitat.getNomeMaterial(d.getMaterial()));
        switch (d.getTipo()) {
            case 1:
                this.tfQuantia.setText(String.valueOf(d.getQuantia()));
                break;
            case 2:
                this.tfMaterial.setText(this.habitat.getNomeMaterial(d.getMaterial()));
                this.tfQuantidade.setText(String.valueOf(d.getQuantidade()));
                break;
            default:
                break;
        }
    }

    @FXML
    public void butOkAction() {
        if (this.mode.equals("Adicionar")) {
            if (checkFields()) {
                try {
                    Doacao d = new Doacao();
                    d.setData(this.dpData.getValue());
                    d.setDoador(this.doador);
                    if (!this.cbEvento.getSelectionModel().isEmpty()) {
                        d.setEvento(((Evento) this.cbEvento.getSelectionModel().getSelectedItem()).getIdEvento());
                    } else {
                        d.setEvento(-1);
                    }
                    if (!this.cbProjeto.getSelectionModel().isEmpty()) {
                        d.setProjeto(((Projecto) this.cbProjeto.getSelectionModel().getSelectedItem()).getIdProjecto());
                    } else {
                        d.setProjeto(-1);
                    }
                    d.setFuncionario(this.habitat.getF().getIdFuncionario());

                    switch (cbTipo.getValue().toString()) {
                        case "Dinheiro":
                            d.setTipo(1);
                            d.setQuantia(Double.parseDouble(this.tfQuantia.getText()));
                            d.setMaterial(-1);
                            d.setQuantidade(0);
                            this.habitat.addDoacao(d);
                            this.doacoes.add(d);
                            break;
                        case "Material":
                            d.setTipo(2);
                            d.setQuantia(0);
                            if (!this.habitat.existeMaterial(this.tfMaterial.getText())) {
                                Alert diag = new Alert(Alert.AlertType.CONFIRMATION);
                                diag.setTitle("Adicionar material inexistente?");
                                diag.setHeaderText(null);
                                diag.setContentText("O material que introduziu (" + this.tfMaterial.getText() + ") não existe.\nDeseja adicioná-lo?");
                                Optional<ButtonType> res = diag.showAndWait();
                                if (res.get() == ButtonType.OK) {
                                    Material m = new Material();
                                    m.setFuncionario(this.habitat.getF().getIdFuncionario());
                                    m.setNome(this.tfMaterial.getText());
                                    m.setQuantidade(0);
                                    this.habitat.addMaterial(m);
                                    d.setMaterial(this.habitat.getMaterialbyName(this.tfMaterial.getText()).get(0).getIdMaterial());
                                    d.setQuantidade(Integer.parseInt(this.tfQuantidade.getText()));
                                    this.habitat.addDoacao(d);
                                    this.doacoes.add(d);
                                }
                            } else {
                                d.setMaterial(this.habitat.getMaterialbyName(this.tfMaterial.getText()).get(0).getIdMaterial());
                                d.setQuantidade(Integer.parseInt(this.tfQuantidade.getText()));
                                this.habitat.addDoacao(d);
                                this.doacoes.add(d);
                            }
                            break;
                        default:
                            d.setQuantia(0);
                            d.setQuantidade(0);
                            d.setMaterial(-1);
                            d.setTipo(3);
                            this.habitat.addDoacao(d);
                            this.doacoes.add(d);
                            break;
                    }

                    this.anterior.show();
                    this.atual.close();

                } catch (PersistenceException ex) {
                    Logger.getLogger(Doacao_Add_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                Alert diag = new Alert(Alert.AlertType.ERROR);
                diag.setTitle("Erro");
                diag.setHeaderText(null);
                diag.setContentText("Formulário preenchido de forma incorreta");
                diag.showAndWait();
            }
        }
    }
}
/*public void popCbEventos(){
 cbEvento.setItems(FXCollections.observableArrayList(this.habitat.getEventos()));
 }*/
