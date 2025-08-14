package poo.ep.pucrs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastroPessoas {
    private static CadastroPessoas instance;

    public static CadastroPessoas getInstance() {
        if (instance == null) 
            instance = new CadastroPessoas();
        return instance;
    }
    
    private ArrayList<Pessoa> lista;

    private CadastroPessoas() {
        lista = new ArrayList<>();

        lista.add( new Pessoa("Huguinho", "hugo@email.com", "Brasil", LocalDate.of(1950,01,10)) );
        lista.add( new Pessoa("Zezinho", "ze@email.com", "Portugal", LocalDate.of(1950,01,10)) );
        lista.add( new Pessoa("Luizinho", "luis@email.com", "Brasil", LocalDate.of(1950,01,10)) );
        lista.add( new Pessoa("Sr. Smith", "sr.smith@email.com", "EUA", LocalDate.of(1980,01,3)) );
        lista.add( new Pessoa("Sra. Smith", "sra.samit@email.com", "EUA", LocalDate.of(1985,01,7)) );
        lista.add( new Pessoa("John Doe", "john.doe@email.com", "EUA", LocalDate.of(2000,10,10)) );

    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public boolean cadastrar(String nome, String email, 
                             String pais, LocalDate dn ) {
        lista.add(new Pessoa(nome, email, pais, dn));

        return true;

    }
}
