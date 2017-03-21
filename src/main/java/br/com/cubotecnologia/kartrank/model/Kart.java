package br.com.cubotecnologia.kartrank.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.LocalTime;

import java.math.BigDecimal;

/**
 * Created by henriquemoreno on 21/03/17.
 */
@Getter
@Setter
@ToString
@Builder
public class Kart {

    private LocalTime hora;
    private String nomePiloto;
    private String codigoPiloto;
    private Integer numeroVoltas;
    private LocalTime tempoVolta;
    private BigDecimal velocidadeMediaVolta;

}
