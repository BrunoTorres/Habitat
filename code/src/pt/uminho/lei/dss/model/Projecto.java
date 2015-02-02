/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author joaorua
 */
public class Projecto {
    private int idProjecto; 
    private StringProperty Nome;
    private double CustoEstimado;
    private double CustoFinal;
    private LocalDate DataInicial;
    private LocalDate DataFinal;
    private int NumTarefas;
    private int PercentagemConcluida;
    private int TarefasConcluidas;
    private String Rua;
    private String Localidade;
    private String CodPostal;
    private int Funcionario;
    private int Candidatura_Nr;
    private int Estado;
    //private HashMap<Integer, String> Voluntarios;
    private ArrayList<Voluntario> Voluntarios;
    private ArrayList<Tarefa> tarefas;
    private ArrayList<Doador> doadores;

  

    

    public Projecto() {
    }
    
    public Projecto (String nome, int id){
        this.Nome.set(nome);
        this.idProjecto = id;
    }

    public Projecto(String Nome, double CustoEstimado, double CustoFinal, LocalDate DataInicial, LocalDate DataFinal, int NumTarefas, int PercentagemConcluida, int TarefasConcluidas, String Rua, String Localidade, String CodPostal, int Funcionario, int Candidatura_Nr, int Estado) {
        this.Nome = new SimpleStringProperty(Nome);
        this.CustoEstimado = CustoEstimado;
        this.CustoFinal = CustoFinal;
        this.DataInicial = DataInicial;
        this.DataFinal = DataFinal;
        this.NumTarefas = NumTarefas;
        this.PercentagemConcluida = PercentagemConcluida;
        this.TarefasConcluidas = TarefasConcluidas;
        this.Rua = Rua;
        this.Localidade = Localidade;
        this.CodPostal = CodPostal;
        this.Funcionario = Funcionario;
        this.Candidatura_Nr = Candidatura_Nr;
        this.Estado = Estado;
        this.tarefas= new ArrayList<>();
        this.doadores = new ArrayList<>();
    }

    public Projecto(int idProjecto, String Nome, double CustoEstimado, double CustoFinal, LocalDate DataInicial, LocalDate DataFinal, int NumTarefas, int PercentagemConcluida, int TarefasConcluidas, String Rua, String Localidade, String CodPostal, int Funcionario, int Candidatura_Nr, int Estado) {
        this.idProjecto = idProjecto;
        this.Nome = new SimpleStringProperty(Nome);
        this.CustoEstimado = CustoEstimado;
        this.CustoFinal = CustoFinal;
        this.DataInicial = DataInicial;
        this.DataFinal = DataFinal;
        this.NumTarefas = NumTarefas;
        this.PercentagemConcluida = PercentagemConcluida;
        this.TarefasConcluidas = TarefasConcluidas;
        this.Rua = Rua;
        this.Localidade = Localidade;
        this.CodPostal = CodPostal;
        this.Funcionario = Funcionario;
        this.Candidatura_Nr = Candidatura_Nr;
        this.Estado = Estado;
        this.tarefas= new ArrayList<>();
        this.doadores = new ArrayList<>();
    }

    public int getIdProjecto() {
        return idProjecto;
    }

    public void setIdProjecto(int idProjecto) {
        this.idProjecto = idProjecto;
    }

    public String getNome() {
        return Nome.get();
    }

    public void setNome(String Nome) {
        this.Nome = new SimpleStringProperty(Nome);
    }

    public StringProperty getNomeProperty() {
        return Nome;
    }

    public void setNome(StringProperty Nome) {
        this.Nome = Nome;
    }
    
    public double getCustoEstimado() {
        return CustoEstimado;
    }

    public void setCustoEstimado(double CustoEstimado) {
        this.CustoEstimado = CustoEstimado;
    }

    public double getCustoFinal() {
        return CustoFinal;
    }

    public void setCustoFinal(double CustoFinal) {
        this.CustoFinal = CustoFinal;
    }

    public LocalDate getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(java.sql.Date dataInicial){
        this.DataInicial = dataInicial.toLocalDate();
    }
    
    public void setDataInicial(LocalDate DataInicial) {
        this.DataInicial = DataInicial;
    }

    public LocalDate getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(LocalDate DataFinal) {
        this.DataFinal = DataFinal;
    }
    
    public void setDataFinal(java.sql.Date dataFinal){
        this.DataFinal = dataFinal.toLocalDate();
    }

    public int getNumTarefas() {
        return NumTarefas;
    }

    public void setNumTarefas(int NumTarefas) {
        this.NumTarefas = NumTarefas;
    }

    public int getPercentagemConcluida() {
        return PercentagemConcluida;
    }

    public void setPercentagemConcluida(int PercentagemConcluida) {
        this.PercentagemConcluida = PercentagemConcluida;
    }

    public int getTarefasConcluidas() {
        return TarefasConcluidas;
    }

    public void setTarefasConcluidas(int TarefasConcluidas) {
        this.TarefasConcluidas = TarefasConcluidas;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public String getLocalidade() {
        return Localidade;
    }

    public void setLocalidade(String Localidade) {
        this.Localidade = Localidade;
    }

    public String getCodPostal() {
        return CodPostal;
    }

    public void setCodPostal(String CodPostal) {
        this.CodPostal = CodPostal;
    }

    public int getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(int Funcionario) {
        this.Funcionario = Funcionario;
    }

    public int getCandidatura_Nr() {
        return Candidatura_Nr;
    }

    public void setCandidatura_Nr(int Candidatura_Nr) {
        this.Candidatura_Nr = Candidatura_Nr;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    public String getEstadoString() {
        switch (Estado){
            case 1: return "Em Espera";
            case 2: return "A Decorrer";
            default: return "Concluido";
        }
    }

    public ArrayList<Voluntario> getVoluntarios() {
        return Voluntarios;
    }

    public void setVoluntarios(ArrayList<Voluntario> Voluntarios) {
        this.Voluntarios = Voluntarios;
    }
    
    public void addVoluntario(Voluntario v) {
        this.Voluntarios.add(v);
    }
    
    public void remVoluntario(Voluntario v) {
        this.Voluntarios.remove(v);
    }
    
    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
    
     public ArrayList<Doador> getDoadores() {
        return doadores;
    }

    public void setDoadores(ArrayList<Doador> doadores) {
        this.doadores = doadores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(":: Projeto ID="+this.getIdProjecto()+" ::\n");
        sb.append("Nome: "+this.getNome()+"\n");
        //sb.append("doadores:"+this.getDoadores().toString());
        sb.append("doadores:"+this.getDoadores().toString());
        //sb.append("EMAIL: "+this.getEmail()+"\n");
        return sb.toString();
    }
    
    
    
}
