package br.com.cubotecnologia.kartrank.present;

import br.com.cubotecnologia.kartrank.model.Sumario;
import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.List;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankPresent {

    private static final PeriodFormatter formatter = new PeriodFormatterBuilder() //
            .appendDays() //
            .appendSuffix("d") //
            .appendHours() //
            .appendSuffix("h") //
            .appendMinutes() //
            .appendSuffix("m") //
            .appendSeconds() //
            .appendSuffix("s") //
            .appendMillis3Digit() //
            .appendSuffix("ms") //
            .toFormatter();
    public static final String PRINTF = "%15s%15s%25s%25s%25s%25s%25s\n";
    public static final int REPEAT = 200;

    public void show(List<Sumario> sumarios, Duration melhorVoltaCorrida) {
        System.out.printf(PRINTF, "Posição Chegada", "Código Piloto", "Nome Piloto", "Qtde Voltas Completadas", "Tempo Total de Prova", "Melhor volta", "Tempo após o vencedor");
        System.out.println(repeatString("-", REPEAT));
        for ( Sumario sumario: sumarios) {
            String posicaoChegada = sumario.getPosicaoChegada().toString();
            String codigoPiloto = sumario.getPiloto().getCodigo();
            String nomePiloto = sumario.getPiloto().getNome();
            String quantidadeVoltas = sumario.getQuantidadeVoltas().toString();
            String tempoTotalProva = formatter.print(sumario.getTempoTotalProva().toPeriod());
            String melhorVolta = formatter.print(sumario.getMelhorVolta().toPeriod());
            String aposVencedor = formatter.print(sumario.getAposVencedor().toPeriod());
            System.out.printf(PRINTF, posicaoChegada, codigoPiloto, nomePiloto, quantidadeVoltas, tempoTotalProva, melhorVolta, aposVencedor);
        }
        System.out.println(repeatString("-", REPEAT));
        System.out.printf("%20s%25s\n","Melhor Volta da Corrida:", formatter.print(melhorVoltaCorrida.toPeriod()));
    }

    private String repeatString(String chr, int timesRepeat) {
        return new String(new char[timesRepeat]).replace("\0", chr);
    }
}
