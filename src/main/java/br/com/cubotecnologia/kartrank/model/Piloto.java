package br.com.cubotecnologia.kartrank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by henriquemoreno on 21/03/17.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Piloto {
    public String codigo;
    public String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piloto piloto = (Piloto) o;

        return codigo.equals(piloto.codigo);

    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
