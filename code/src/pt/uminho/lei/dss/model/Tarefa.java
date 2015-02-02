package pt.uminho.lei.dss.model;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Tarefa {
    
    private int id;
    private StringProperty nome;
    private boolean concluida;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int funcionario;
    private int projecto;

    public Tarefa() {
    }

    
    public Tarefa(StringProperty nome, boolean concluida, String dataInicial, String dataFinal, int funcionario) {
        this.nome = nome;
        this.concluida = concluida;
        this.dataInicial = LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dataFinal = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.funcionario = funcionario;
    }
    

    public Tarefa( String nome) {
        
         this.nome = new SimpleStringProperty(nome);
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty getNomeProperty() {
        return nome;
    }
    
    
    public String getNome(){
        return nome.get();
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }

    public void setNome(String nome){
        this.nome = new SimpleStringProperty(nome);
    }
    
    public boolean isConcluida() {
        return concluida;
    }
    
    public StringProperty getConcluida(){
        //return concluida;
        return (concluida)?(new SimpleStringProperty("Sim")):(new SimpleStringProperty("Não"));
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }
    
    public StringProperty getDataInicialProperty() {
        return new SimpleStringProperty(dataInicial.toString());
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataInicial(java.sql.Date dataIni){
        this.dataInicial = dataIni.toLocalDate();
    }
    
    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public StringProperty getDataFinalProperty() {
        return new SimpleStringProperty(dataFinal.toString());
    }
    
    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setDataFinal(java.sql.Date dataFin){
        this.dataFinal = dataFin.toLocalDate();
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public int getProjecto() {
        return projecto;
    }

    public void setProjecto(int projecto) {
        this.projecto = projecto;
    }
    
    

    
}
