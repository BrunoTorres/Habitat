/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author JoaoMano
 */
public class Doador {

    private int idDoador;
    private StringProperty nome;
    private StringProperty firstName;
    private StringProperty lastName;
    private int empresa;
    private String email;
    private int nif;
    private int telefone;
    private String rua;
    private String localidade;
    private String codPostal;
    private String site;
    private String responsavelEmpresa;
    private int contactoResponsavel;
    private String obs;
    private int funcionario;
    private String atividade;
    private ArrayList<Doacao> doacoes;

    public Doador() {
    }

    public Doador(String nome, int empresa, int tl, String morada, String local, String cd, int nif, String email, String site, String responsavel, int contactResp, String atividade, ArrayList<Doacao> doacoes) {
        this.nome = new SimpleStringProperty(nome);
        this.empresa = empresa;
        this.telefone = tl;
        this.rua = morada;
        this.localidade = local;
        this.codPostal = cd;
        this.nif = nif;
        this.site = site;
        this.email = email;
        this.responsavelEmpresa = responsavel;
        this.contactoResponsavel = contactResp;
        this.funcionario = 1;
        this.atividade = atividade;
        this.doacoes = new ArrayList<>(doacoes);
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public StringProperty getFirstNameProperty() {
        return firstName;
    }
    
    public StringProperty getLastNameProperty() {
        return lastName;
    }
    
    public StringProperty getNomeProperty() {
        return nome;
    }
    
    public String getNome() {
        return nome.get();
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }
    
    public void setNome(String nome){
        this.nome = new SimpleStringProperty(nome);
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nf) {
        this.nif = nf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getResponsavelEmpresa() {
        return responsavelEmpresa;
    }

    public void setResponsavelEmpresa(String responsavelEmpresa) {
        this.responsavelEmpresa = responsavelEmpresa;
    }

    public int getContactoResponsavel() {
        return contactoResponsavel;
    }

    public void setContactoResponsavel(int contactoResponsavel) {
        this.contactoResponsavel = contactoResponsavel;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public void setActividade(String atividade) {
        this.atividade = atividade;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(StringProperty firstName) {
        this.firstName = firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public void setLastName(StringProperty lastName) {
        this.lastName = lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public ArrayList<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(ArrayList<Doacao> doacoes) {
        this.doacoes = doacoes;
    }
    
    public StringProperty getEmpresaStringProperty(){
        return new SimpleStringProperty((this.getEmpresa()==1)?"Sim":"Não");
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }
    
    
}
