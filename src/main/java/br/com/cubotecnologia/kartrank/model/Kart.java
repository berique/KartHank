package br.com.cubotecnologia.kartrank.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.Duration;
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
    private Integer numeroVoltas;
    private Duration tempoVolta;
    private Piloto piloto;
    private BigDecimal velocidadeMediaVolta;

}
