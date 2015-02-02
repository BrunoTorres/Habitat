/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author joaorua
 */
public class Equipa {
    
    private int idEquipa;
    private StringProperty Nome;
    private int Pais;
    private int Funcionario;
    private boolean ativa;

    public Equipa() {
    }

    
    public Equipa(int idEquipa, String Nome, int Pais, int Funcionario) {
        this.idEquipa = idEquipa;
        this.Nome = new SimpleStringProperty(Nome);
        this.Pais = Pais;
        this.Funcionario = Funcionario;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
    }

    public String getNome() {
        return Nome.get();
    }

    public void setNome(String Nome) {
        this.Nome = new SimpleStringProperty(Nome);
    }

    public int getPais() {
        return Pais;
    }

    public void setPais(int Pais) {
        this.Pais = Pais;
    }

    public int getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(int Funcionario) {
        this.Funcionario = Funcionario;
    }

    public StringProperty getNomeProperty() {
        return Nome;
    }

    public void setNome(StringProperty Nome) {
        this.Nome = Nome;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
    public StringProperty getAtiva(){
        return new SimpleStringProperty(isAtiva()?"Sim":"Não");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /*
        sb.append(":: EQUIPA ID="+this.getIdEquipa()+" ::\n");
        sb.append("Nome: "+this.getNome()+"\n");
        //sb.append("EMAIL: "+this.getEmail()+"\n");
        */
        sb.append(this.getNome());
        return sb.toString();
    }
    
    
    
}
