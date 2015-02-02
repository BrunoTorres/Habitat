/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author joaorua
 */
public class Agregado {
    private int Nif;
    private int Candidatura;
    private String Nome;
    private LocalDate DataNascimento;
    private String Escolaridade;
    private String EstadoCivil;
    private String Parentesco;
    private String VinculoLaboral;
    private double Rendimento1;
    private String Profissao;
    private double Rendimento2;
    private int Candidato;
    private String Naturalidade;
    private String Nacionalidade;

    public Agregado() {
    }

    public Agregado(int Candidatura, String Nome, LocalDate DataNascimento, String Escolaridade, String EstadoCivil, String Parentesco, String VinculoLaboral, double Rendimento1, String Profissao, double Rendimento2, int Candidato, String Naturalidade, String Nacionalidade) {
        this.Candidatura = Candidatura;
        this.Nome = Nome;
        this.DataNascimento = DataNascimento;
        this.Escolaridade = Escolaridade;
        this.EstadoCivil = EstadoCivil;
        this.Parentesco = Parentesco;
        this.VinculoLaboral = VinculoLaboral;
        this.Rendimento1 = Rendimento1;
        this.Profissao = Profissao;
        this.Rendimento2 = Rendimento2;
        this.Candidato = Candidato;
        this.Naturalidade = Naturalidade;
        this.Nacionalidade = Nacionalidade;
    }

    public Agregado(int Nif, int Candidatura, String Nome, LocalDate DataNascimento, String Escolaridade, String EstadoCivil, String Parentesco, String VinculoLaboral, double Rendimento1, String Profissao, double Rendimento2, int Candidato, String Naturalidade, String Nacionalidade) {
        this.Nif = Nif;
        this.Candidatura = Candidatura;
        this.Nome = Nome;
        this.DataNascimento = DataNascimento;
        this.Escolaridade = Escolaridade;
        this.EstadoCivil = EstadoCivil;
        this.Parentesco = Parentesco;
        this.VinculoLaboral = VinculoLaboral;
        this.Rendimento1 = Rendimento1;
        this.Profissao = Profissao;
        this.Rendimento2 = Rendimento2;
        this.Candidato = Candidato;
        this.Naturalidade = Naturalidade;
        this.Nacionalidade = Nacionalidade;
    }

    public int getNif() {
        return Nif;
    }
    
    public StringProperty getNifProperty() {
        return new SimpleStringProperty(String.valueOf(Nif));
    }

    public void setNif(int Nif) {
        this.Nif = Nif;
    }

    public int getCandidatura() {
        return Candidatura;
    }

    public void setCandidatura(int Candidatura) {
        this.Candidatura = Candidatura;
    }

    public String getNome() {
        return Nome;
    }
    
    public StringProperty getNomeProperty() {
        return new SimpleStringProperty(Nome);
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate DataNascimento) {
        this.DataNascimento = DataNascimento;
    }
    
    public void setDataNascimento(java.sql.Date dataNas){
        this.DataNascimento = dataNas.toLocalDate();
    }

    public String getEscolaridade() {
        return Escolaridade;
    }

    public void setEscolaridade(String Escolaridade) {
        this.Escolaridade = Escolaridade;
    }

    public String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(String EstadoCivil) {
        this.EstadoCivil = EstadoCivil;
    }

    public String getParentesco() {
        return Parentesco;
    }
    
    public StringProperty getParentescoProperty() {
        return new SimpleStringProperty(Parentesco);
    }

    public void setParentesco(String Parentesco) {
        this.Parentesco = Parentesco;
    }

    public String getVinculoLaboral() {
        return VinculoLaboral;
    }

    public void setVinculoLaboral(String VinculoLaboral) {
        this.VinculoLaboral = VinculoLaboral;
    }

    public double getRendimento1() {
        return Rendimento1;
    }

    public void setRendimento1(double Rendimento1) {
        this.Rendimento1 = Rendimento1;
    }

    public String getProfissao() {
        return Profissao;
    }

    public void setProfissao(String Profissao) {
        this.Profissao = Profissao;
    }

    public double getRendimento2() {
        return Rendimento2;
    }

    public void setRendimento2(double Rendimento2) {
        this.Rendimento2 = Rendimento2;
    }

    public int getCandidato() {
        return Candidato;
    }

    public void setCandidato(int Candidato) {
        this.Candidato = Candidato;
    }

    public String getNaturalidade() {
        return Naturalidade;
    }

    public void setNaturalidade(String Naturalidade) {
        this.Naturalidade = Naturalidade;
    }

    public String getNacionalidade() {
        return Nacionalidade;
    }

    public void setNacionalidade(String Nacionalidade) {
        this.Nacionalidade = Nacionalidade;
    }
    
    
    
}
