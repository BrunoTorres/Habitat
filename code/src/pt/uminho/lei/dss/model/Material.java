/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author axelferreira
 */
public class Material {

    private int idMaterial;
    private StringProperty nome;
    private int quantidade;
    private int funcionario;

// Construtores    
    
    public Material() {
    }

    public Material(String Nome, int Quantidade) {
        this.nome = new SimpleStringProperty(Nome);
        this.quantidade = Quantidade;
    }

    

//// GETTERS & SETTERS

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }
    
    
    public String getNome() {
        return nome.get();
    }

    public void setNome(String Nome) {
        this.nome = new SimpleStringProperty(Nome);
    }

    public StringProperty getNomeProperty(){
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public SimpleStringProperty getQuantidadeProperty(){
        return new SimpleStringProperty(String.valueOf(quantidade));
    }
    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Material other = (Material) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }

        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        return true;
    }

}
