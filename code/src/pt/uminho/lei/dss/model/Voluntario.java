/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author JoaoMano
 */
public class Voluntario {
    
    private int id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty nome;
    private String prof;
    private int telf;
    private LocalDate dataNasc;
    private String email;
    private String rua;
    private String localidade;
    private String codPostal;
    private int funcionario;
    private InputStream doc;
    private boolean ativo;
    private ArrayList<Projecto> projectos;
    private ArrayList<Equipa> equipas;
    
    public Voluntario(){
        
    }
    
    public Voluntario(String fn, String ln){
        this.firstName = new SimpleStringProperty(fn);
        this.lastName = new SimpleStringProperty(ln);
    }

    public Voluntario(int id, String nome, String prof, int telf, String dataNasc, String email, String rua, String localidade, String codPostal,int funcionario, InputStream doc,boolean ativo) {
        this.id = id;
        this.nome = new SimpleStringProperty(nome);
        String[] nn = nome.split(" ");
        this.firstName = new SimpleStringProperty(nn[0]);
        this.lastName = new SimpleStringProperty((nn.length > 1)?nn[nn.length-1]:"");
        this.prof = prof;
        this.telf = telf;
        this.dataNasc = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.email = email;
        this.rua = rua;
        this.localidade = localidade;
        this.codPostal = codPostal;
        this.funcionario = funcionario;
        this.doc = doc;
        this.ativo = ativo;
        this.projectos = null;
        this.equipas = null;
    }
    public Voluntario( String nome, String prof, int telf, String dataNasc, String email, String rua, String localidade, String codPostal,int funcionario, InputStream doc) {
        this.nome = new SimpleStringProperty(nome);
        String[] nn = nome.split(" ");
        this.firstName = new SimpleStringProperty(nn[0]);
        this.lastName = new SimpleStringProperty((nn.length > 1)?nn[nn.length-1]:"");
        this.prof = prof;
        this.telf = telf;
        this.dataNasc = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.email = email;
        this.rua = rua;
        this.localidade = localidade;
        this.codPostal = codPostal;
        this.funcionario = funcionario;
        this.doc = null;
        this.ativo = true;
        this.projectos = null;
        this.equipas = null;
    }
    
    public Voluntario(int id, String nome, String prof, int telf, String dataNasc, String email, String rua, String localidade, String codPostal,int funcionario, InputStream doc,boolean ativo,Projecto p,Equipa e) {
        this.id = id;
        this.nome = new SimpleStringProperty(nome);
        String[] nn = nome.split(" ");
        this.firstName = new SimpleStringProperty(nn[0]);
        this.lastName = new SimpleStringProperty((nn.length > 1)?nn[nn.length-1]:"");
        this.prof = prof;
        this.telf = telf;
        this.dataNasc = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.email = email;
        this.rua = rua;
        this.localidade = localidade;
        this.codPostal = codPostal;
        this.funcionario = funcionario;
        this.doc = doc;
        this.ativo = ativo;
        this.projectos = projectos;
        this.equipas = equipas;
        
    }
    
    public String getFirstName(){
        return this.firstName.get();
    }
    
    public StringProperty firstNameProperty(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName.get();
    }
    
    public StringProperty lastNameProperty(){
        return this.lastName;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    
    public String getProf() {
        return prof;
    }
    
    public int getTelf() {
        return telf;
    }
    
    public LocalDate DataNascProperty() {
        return dataNasc;
    }

    public String getDataNasc() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateFormatter.format(dataNasc);
    }
    
    public String getEmail() {
        return email;
    }

    public String getRua() {
        return rua;
    }

    public String getLocalidade() {
        return localidade;
    }
    
    public String getCodPostal() {
        return codPostal;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public InputStream getDoc() {
        return doc;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public StringProperty getNomeProperty() {
        return nome;
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }

    
    public void setProf(String prof) {
        this.prof = prof;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setDataNasc(java.sql.Date dataNas){
        this.dataNasc = dataNas.toLocalDate();
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public void setDoc(InputStream doc) {
        this.doc = doc;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Projecto> getProjetos() {
        return projectos;
    }

    public void setProjectos(ArrayList<Projecto> projectos) {
        this.projectos = new ArrayList<>(projectos);
    }

    public ArrayList<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(ArrayList<Equipa> equipas) {
        this.equipas = new ArrayList<>(equipas);
    }

    public void addEquipa(Equipa e){
        equipas.add(e);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /*
        sb.append(":: VOLUNTARIO ID="+this.getId()+" ::\n");
        sb.append("Nome: "+this.getNome()+"\n");
        sb.append("EMAIL: "+this.getEmail()+"\n");
        sb.append("------------------------------------------\n");
        this.getProjetos().stream().forEach((p) -> {
            sb.append(p.toString());
        });
        sb.append("------------------------------------------\n");
        this.getEquipas().stream().forEach((e) -> {
            sb.append(e.toString());
        });
        sb.append("-------------------FIM--------------------\n");
        */
        sb.append(this.getNome());
        return sb.toString();
    }
    
    
    
}    