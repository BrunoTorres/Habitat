package pt.uminho.lei.dss.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import pt.uminho.lei.dss.db.PersistenceException;
import pt.uminho.lei.dss.model.Doacao;
import pt.uminho.lei.dss.model.Doador;
import pt.uminho.lei.dss.model.SGH;
import pt.uminho.lei.dss.model.Voluntario;

public class GenericCellFactory implements Callback<TableColumn, TableCell> {

    private final String mode;
    private final Stage atual;
    private final TableView table;
    private SGH habitat;

    /*public GenericCellFactory(String mode, Stage atual, TableView table) {
     this.mode = mode;
     this.atual = atual;
     this.table = table;
     }*/
    public GenericCellFactory(String mode, Stage atual, TableView table, SGH hab) {
        this.mode = mode;
        this.atual = atual;
        this.table = table;
        this.habitat = hab;
    }

    @Override
    public TableCell call(TableColumn col) {
        final TableCell<Voluntario, String> cell = new TableCell<Voluntario, String>() {
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(name);
                }
            }
        };
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                switch (this.mode) {
                    case "voluntario":
                        try {
                            Voluntario v = (Voluntario) this.table.getSelectionModel().getSelectedItem();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Voluntarios.fxml"));
                            Parent root = loader.load();
                            Voluntarios_Controller vol_c = loader.getController();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            //stage.setResizable(false);
                            stage.setTitle("Voluntario");
                            vol_c.setAnterior(atual);
                            vol_c.setAtual(stage);
                            stage.show();

                            try {
                                vol_c.setHabitat(habitat);
                                vol_c.mostraVfromProj(v);
                            } catch (PersistenceException ex) {
                                Logger.getLogger(GenericCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Projetos_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "doador":
                        try {
                            Doador d = (Doador) this.table.getSelectionModel().getSelectedItem();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Doadores.fxml"));
                            Parent root = loader.load();
                            Doadores_Controller vol_c = loader.getController();
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            //stage.setResizable(false);
                            stage.setTitle("Doador");
                            vol_c.setAnterior(atual);
                            vol_c.setAtual(stage);
                            stage.show();

                            try {
                                vol_c.setHabitat(habitat);
                                vol_c.mostraDoadorFromProj(d);
                            } catch (PersistenceException ex) {
                                Logger.getLogger(GenericCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Projetos_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "doação":
                        try {
                            Doacao d = (Doacao) this.table.getSelectionModel().getSelectedItem();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Doacao_Add.fxml"));
                            Parent root = loader.load();
                            Doacao_Add_Controller doacao_c = loader.getController();
                            doacao_c.setMode("Ver");
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            //stage.setResizable(false);
                            stage.setTitle("Doação");
                            doacao_c.setAnterior(atual);
                            doacao_c.setAtual(stage);
                            stage.show();

                            try {
                                doacao_c.setHabitat(habitat);
                                doacao_c.mostraDoacao(d);
                            } catch (PersistenceException ex) {
                                Logger.getLogger(GenericCellFactory.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Projetos_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    default:
                        System.err.println("Mode ERROR!");
                }
            }
        }
        );
        return cell;
    }
;

}
