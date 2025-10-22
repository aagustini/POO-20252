package aagustini.poo;

import java.time.LocalDate;


public  class Pessoa {
    private int ID;
    private String nome;
    private String email;
    private String pais;
    private LocalDate dataNascimento; 

    private static int proximoID = 1;

    // Construtor atualizado
    public Pessoa(String nome, String email, String pais, LocalDate dataNascimento) {
        this.ID   = proximoID++;
        this.nome = nome;
        this.email = email;
        this.pais = pais;
        this.dataNascimento = dataNascimento; 
    }

    public int getID() { return ID; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPais() { return pais; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa [nome=" + nome + ", email=" + email + ", pais=" + pais + ", dataNascimento=" + dataNascimento
                + "]";
    } 


    
}