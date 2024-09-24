package co.edu.uniquindio.notas.modelos;

import java.util.ArrayList;
import java.util.List;

public class NotaPrincipal {

    private List <Nota> notas ;

    public NotaPrincipal(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }


    public void agregarNota(String titulo , String descripcion , String categoria){


        Nota nota1 = Nota.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .categoria(categoria)
                .build();

        notas.add(nota1);

    }

    public ArrayList<Nota> listarNotas(){
        return new ArrayList<>(notas);

    }



}
