/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uminho.lei.dss.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario {

    private int idFuncionario;
    private String nome;
    private StringProperty estado;
    private StringProperty userName;
    private String Password;
    private IntegerProperty Permissao;
    private BooleanProperty Activo;
    private String Email;
    private LocalDate DataNascimento;
    private int Telefone;
    private int Nif;
    private int Salario;
    private String Rua;
    private String Localidade;
    private String CodPostal;
    private LocalDateTime UltimoLogin;

    public Funcionario() {
    }

    public Funcionario(String user, int perm, boolean act) {
        this.userName = new SimpleStringProperty(user);
        this.Permissao = new SimpleIntegerProperty(perm);
        this.Activo = new SimpleBooleanProperty(act);
    }

    public Funcionario(String nome, String estado, String userName, String Password, int Permissao, boolean Activo, String Email, LocalDate DataNascimento, int Telefone, int Nif, int Salario, String Rua, String Localidade, String CodPostal, LocalDateTime UltimoLogin) {
        this.nome = nome;
        this.estado = new SimpleStringProperty(estado);
        this.userName = new SimpleStringProperty(userName);
        this.Password = Password;
        this.Permissao = new SimpleIntegerProperty(Permissao);
        this.Activo = new SimpleBooleanProperty(Activo);
        this.Email = Email;
        this.DataNascimento = DataNascimento;
        this.Telefone = Telefone;
        this.Nif = Nif;
        this.Salario = Salario;
        this.Rua = Rua;
        this.Localidade = Localidade;
        this.CodPostal = CodPostal;
        this.UltimoLogin = UltimoLogin;
    }
    

    public Funcionario(int idFuncionario, String Nome, String UserName, String Password, int Permissao, boolean Activo, String Email, String DataNascimento, int Telefone, int Nif, int Salario, String Rua, String Localidade, String CodPostal, LocalDateTime UltimoLogin) {
        this.idFuncionario = idFuncionario;
        this.nome = Nome;
        this.userName = new SimpleStringProperty(UserName);
        this.Password = Password;
        this.Permissao = new SimpleIntegerProperty(Permissao);
        this.Activo = new SimpleBooleanProperty(Activo);
        this.Email = Email;
        this.DataNascimento = LocalDate.parse(DataNascimento, DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
        this.Telefone = Telefone;
        this.Nif = Nif;
        this.Salario = Salario;
        this.Rua = Rua;
        this.Localidade = Localidade;
        this.CodPostal = CodPostal;
        this.UltimoLogin = UltimoLogin;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public StringProperty getUserNameProperty() {
        return userName;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String UserName) {
        this.userName = new SimpleStringProperty(UserName);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {

        this.Password = Password;
    }

    public StringProperty getPermissaoProperty() {
        String pre;
        StringProperty s = null;

        if (this.Permissao.get() == 0) {
            pre = "Administrador";
            s = new SimpleStringProperty(pre);
        } else if (this.Permissao.get() == 1) {
            pre = "Famílias";
            s = new SimpleStringProperty(pre);
        } else if (this.Permissao.get() == 2) {
            pre = "Obras";
            s = new SimpleStringProperty(pre);
        } else if (this.Permissao.get() == 3) {
            pre = "Fundos";
            s = new SimpleStringProperty(pre);
        }

        return s;
    }

    public int getPermissao() {
        return Permissao.get();
    }

    public void setPermissao(int Permissao) {
        this.Permissao = new SimpleIntegerProperty(Permissao);
    }

    public StringProperty getActivoProperty() {

        String pre;
        StringProperty s = null;

        if (this.Activo.getValue() == true) {
            pre = "Ativo";
            s = new SimpleStringProperty(pre);
        } else {
            pre = "Inativo";
            s = new SimpleStringProperty(pre);

        }
        return s;
    }

    public boolean getActivo() {
        return Activo.get();
    }

    public void setActivo(boolean Activo) {
        this.Activo = new SimpleBooleanProperty(Activo);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public void setDataNascimento(java.sql.Date dataNas) {
        this.DataNascimento = dataNas.toLocalDate();
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }

    public int getNif() {
        return Nif;
    }

    public void setNif(int Nif) {
        this.Nif = Nif;
    }

    public int getSalario() {
        return Salario;
    }

    public void setSalario(int Salario) {
        this.Salario = Salario;
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

    public String getUltimoLogin() {
        
       // String data = new SimpleDateFormat("dd-MM-yyyy").format(this.DataNascimento);
       // l_data.setText(data);
      //String hora = new SimpleDateFormat("HH:mm:ss").format(this.DataNascimento);
       
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = this.UltimoLogin.format(formatter);

        return formattedDateTime;
    }
    
    public LocalDateTime getUltimoLoginDataTime(){
        return UltimoLogin;
    }

    public void setUltimoLogin(LocalDateTime UltimoLogin) {
        this.UltimoLogin = UltimoLogin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append(":: FUNCIONARIO ID=" + this.getIdFuncionario() + " ::\n");
        sb.append(this.getNome());
        //sb.append("USERNAME: " + this.getUserName() + "\n");
        //sb.append("PASSWORD: " + this.getPassword() + "\n");

        return sb.toString();
    }

}
