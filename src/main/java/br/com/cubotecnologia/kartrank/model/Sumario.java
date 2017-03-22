package br.com.cubotecnologia.kartrank.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.Duration;

import java.math.BigDecimal;

/**
 * Created by henriquemoreno on 21/03/17.
 */
@Getter
@Setter
@Builder
@ToString
public class Sumario {
    private Piloto piloto;
    private Integer posicaoChegada;
    private Integer quantidadeVoltas;
    private Duration duracaoProva;
    private Duration tempoTotalProva;
    private Duration melhorVolta;
    private BigDecimal velocidadeMedia;
    private Duration aposVencedor;
}
