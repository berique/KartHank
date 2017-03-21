package br.com.cubotecnologia.kartrank.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

/**
 * Created by henriquemoreno on 21/03/17.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Piloto {
    public String codigo;
    public String nome;
}
