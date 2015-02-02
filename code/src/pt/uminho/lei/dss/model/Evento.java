package pt.uminho.lei.dss.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Evento {

    private int idEvento;
    private StringProperty nome;
    private LocalDate data;
    private String organizador;
    private double total;
    private String notas;
    private int funcionario;
    
    

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public StringProperty getNome() {
        return nome;
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }
    
    public StringProperty getDataProperty() {
        return new SimpleStringProperty(data.toString());
    }
    

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }
    
    @Override
    public int hashCode() {
        int lHashCode = 0;
        if (lHashCode == 0) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Evento) {
            Evento lEventoObject = (Evento) object;
            boolean lEquals = true;
            return lEquals;
        }
        return false;
    }

    
}
