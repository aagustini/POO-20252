package poo.ep.pucrs;

import java.time.LocalDate;

// Dentro da classe MainView

public  class Pessoa {
    private String nome;
    private String email;
    private String pais;
    private LocalDate dataNascimento; 

    // Construtor atualizado
    public Pessoa(String nome, String email, String pais, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.pais = pais;
        this.dataNascimento = dataNascimento; 
    }

    // Getters...
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPais() { return pais; }
    public LocalDate getDataNascimento() { return dataNascimento; } 

}