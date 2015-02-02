/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.io.InputStream;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author joaorua
 */

public class Candidatura {
    private int Nr;
    private int Funcionario;
    private int EstadoCandidatura;
    private int EstadoOrcamento;
    private double Orcamento;
    private String Rua;
    private String Localidade;
    private String CodPostal;
    private int Telefone;
    private String ObsRejecao;
    private double MensalidadeAtribuida;
    private InputStream Documento;
    private double RendimentoTotal;
    private ArrayList<Agregado> agregado;
    

    public Candidatura() {
    }

    public Candidatura(int Funcionario, int EstadoCandidatura, int EstadoOrcamento, double Orcamento, String Rua, String Localidade, String CodPostal, int Telefone, String ObsRejecao, int MensalidadeAtribuida, InputStream Documento, int RendimentoTotal) {
        this.Funcionario = Funcionario;
        this.EstadoCandidatura = EstadoCandidatura;
        this.EstadoOrcamento = EstadoOrcamento;
        this.Orcamento = Orcamento;
        this.Rua = Rua;
        this.Localidade = Localidade;
        this.CodPostal = CodPostal;
        this.Telefone = Telefone;
        this.ObsRejecao = ObsRejecao;
        this.MensalidadeAtribuida = MensalidadeAtribuida;
        this.Documento = Documento;
        this.RendimentoTotal = RendimentoTotal;
    }

    public Candidatura(int Nr, int Funcionario, int EstadoCandidatura, int EstadoOrcamento, double Orcamento, String Rua, String Localidade, String CodPostal, int Telefone, String ObsRejecao, int MensalidadeAtribuida, InputStream Documento, int RendimentoTotal) {
        this.Nr = Nr;
        this.Funcionario = Funcionario;
        this.EstadoCandidatura = EstadoCandidatura;
        this.EstadoOrcamento = EstadoOrcamento;
        this.Orcamento = Orcamento;
        this.Rua = Rua;
        this.Localidade = Localidade;
        this.CodPostal = CodPostal;
        this.Telefone = Telefone;
        this.ObsRejecao = ObsRejecao;
        this.MensalidadeAtribuida = MensalidadeAtribuida;
        this.Documento = Documento;
        this.RendimentoTotal = RendimentoTotal;
    }
    
    

    public int getNr() {
        return Nr;
    }

    public StringProperty getNrProperty() {
        return new SimpleStringProperty(String.valueOf(Nr));
    }
    
    public void setNr(int Nr) {
        this.Nr = Nr;
    }

    public int getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(int Funcionario) {
        this.Funcionario = Funcionario;
    }

    public int getEstadoCandidatura() {
        return EstadoCandidatura;
    }
    
    public StringProperty getEstadoCandidaturaProperty() {
        switch (EstadoCandidatura){
            case 1: return new SimpleStringProperty("Em Analise");
            case 2: return new SimpleStringProperty("Aprovada");
            default: return new SimpleStringProperty("Rejeitada");
        }
    }

    public void setEstadoCandidatura(int EstadoCandidatura) {
        this.EstadoCandidatura = EstadoCandidatura;
    }
    
    public String getEstadoCandidaturaString() {
        switch (EstadoCandidatura){
            case 1: return "Em Analise";
            case 2: return "Aprovado";
            default: return "Rejeitado";
        }
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }

    public String getObsRejecao() {
        return ObsRejecao;
    }

    public void setObsRejecao(String ObsRejecao) {
        this.ObsRejecao = ObsRejecao;
    }

    public double getMensalidadeAtribuida() {
        return MensalidadeAtribuida;
    }

    public void setMensalidadeAtribuida(double MensalidadeAtribuida) {
        this.MensalidadeAtribuida = MensalidadeAtribuida;
    }

    public InputStream getDocumento() {
        return Documento;
    }

    public void setDocumento(InputStream Documento) {
        this.Documento = Documento;
    }

    public double getRendimentoTotal() {
        return RendimentoTotal;
    }

    public void setRendimentoTotal(double RendimentoTotal) {
        this.RendimentoTotal = RendimentoTotal;
    }

    public int getEstadoOrcamento() {
        return EstadoOrcamento;
    }
    
    public String getEstadoOrcamentoString() {
        switch (EstadoOrcamento){
            case 1: return "Em Analise";
            case 2: return "Aprovado";
            default: return "Rejeitado";
        }
    }

    public void setEstadoOrcamento(int EstadoOrcamento) {
        this.EstadoOrcamento = EstadoOrcamento;
    }

    public double getOrcamento() {
        return Orcamento;
    }

    public void setOrcamento(double Orcamento) {
        this.Orcamento = Orcamento;
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

    public ArrayList<Agregado> getAgregado() {
        return agregado;
    }

    public void setAgregado(ArrayList<Agregado> agregado) {
        this.agregado = agregado;
    }

    
    
    
}
