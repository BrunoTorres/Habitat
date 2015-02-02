package pt.uminho.lei.dss.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Doacao {

    private int idDoacao;
    private int Funcionario;
    private LocalDate Data;
    private int Quantidade;
    private double Quantia;
    private String Obs;
    private int Tipo;
    private int Material;
    private int Doador;
    private int Projeto;
    private int Evento;

    public Doacao() {
    }

    public int getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(int idDoacao) {
        this.idDoacao = idDoacao;
    }

    public int getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(int Funcionario) {
        this.Funcionario = Funcionario;
    }

    public LocalDate getData() {
        return Data;
    }
    
    public StringProperty getDataProperty(){
        return new SimpleStringProperty(Data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public void setData(LocalDate Data) {
        this.Data = Data;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public double getQuantia() {
        return Quantia;
    }

    public void setQuantia(double Quantia) {
        this.Quantia = Quantia;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }

    public int getTipo() {
        return Tipo;
    }

    public StringProperty getTipoProperty(){
        switch (Tipo){
                case 1:
                    return new SimpleStringProperty("Monetária"); 
                case 2:
                    return new SimpleStringProperty("Material");
                default: 
                    return new SimpleStringProperty("Serviço");
        }
    }
    
    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    public int getMaterial() {
        return Material;
    }

    public void setMaterial(int Material) {
        this.Material = Material;
    }

    public int getDoador() {
        return Doador;
    }

    public void setDoador(int Doador) {
        this.Doador = Doador;
    }

    public int getProjeto() {
        return Projeto;
    }

    public void setProjeto(int Projeto) {
        this.Projeto = Projeto;
    }

    public int getEvento() {
        return Evento;
    }

    public void setEvento(int Evento) {
        this.Evento = Evento;
    }
    
    public void setData(java.sql.Date data){
        this.Data = data.toLocalDate();
    }
    
    public int hashCode() {
        int lHashCode = 0;
        if (lHashCode == 0) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Doacao) {
            Doacao lDoacaoObject = (Doacao) object;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }
}
