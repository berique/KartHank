package br.com.cubotecnologia.kartrank.business;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Piloto;
import br.com.cubotecnologia.kartrank.model.Sumario;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.*;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankBusiness implements IKartRankBusiness {

    public List<Sumario> calcularRanking(List<Kart> karts) {
        Map<Piloto, List<LocalTime>> tempoPorPiloto = mapaDeTempoPorPiloto(karts);
        List<Duration> durations = new ArrayList<Duration>();
        Map<Duration, Piloto> duracaoPorPiloto = calculaTempoPorPiloto(tempoPorPiloto, durations);
        Collections.sort(durations, new Comparator<Duration>() {
            public int compare(Duration o1, Duration o2) {
                if (o1.isShorterThan(o2)) {
                    return 1;
                }
                return -1;
            }
        });
        List<Sumario> ranking = new ArrayList<Sumario>();
        int cnt = 1;
        for ( Duration duration: durations) {
            Duration[] arrDuracaoPiloto = duracaoPorPiloto.keySet().toArray(new Duration[]{});
            for (int x = 0; x <arrDuracaoPiloto.length; x++) {
                Piloto duracaoPiloto = duracaoPorPiloto.get(arrDuracaoPiloto[x]);
                if ( duracaoPiloto.equals(duration)) {
                    Piloto piloto = duracaoPorPiloto.get(duracaoPiloto);
                    Sumario.SumarioBuilder sumarioBuilder = Sumario.builder() //
                            .piloto(piloto) //
                            .posicaoChegada(cnt++)
                            .quantidadeVoltas(calculaQuantidadeVoltas(piloto, karts))
                            .tempoTotalProva(calculaTempoTotalProva(piloto, karts));
                    if ( x != 0 ) {

                    }
                    ranking.add(sumarioBuilder.build());
                }
            }
        }
        return ranking;
    }

    private Period calculaTempoTotalProva(Piloto piloto, List<Kart> karts) {
        Period tempoTotalProva = Period.ZERO;
        PeriodFormatter pf =
                new PeriodFormatterBuilder()
                        .minimumPrintedDigits(2).printZeroAlways()
                        .appendHours().appendLiteral(":").appendMinutes().toFormatter();
        for ( Kart kart: karts) {
            if ( kart.getPiloto().equals(piloto)) {
                tempoTotalProva.plus(kart.getTempoVolta());
            }
        }
        return tempoTotalProva;
    }

    private Integer calculaQuantidadeVoltas(Piloto piloto, List<Kart> karts) {
        int quantidadeVoltas = 0;
        for ( Kart kart: karts) {
            if ( kart.getPiloto().equals(piloto)) {
                quantidadeVoltas++;
            }
        }
        return quantidadeVoltas;
    }

    private Map<Duration, Piloto> calculaTempoPorPiloto(Map<Piloto, List<LocalTime>> tempoPorPiloto, List<Duration> durations) {
        Map<Duration, Piloto> duracaoPorPiloto = new HashMap<Duration, Piloto>();
        for (Piloto codigoPiloto : tempoPorPiloto.keySet()) {
            List<LocalTime> listTempo = tempoPorPiloto.get(codigoPiloto);
            LocalTime max = Collections.max(listTempo);
            LocalTime min = Collections.min(listTempo);
            Duration duration = new Duration(min.toDateTimeToday().toDateTime(), max.toDateTimeToday().toDateTime());
            duracaoPorPiloto.put(duration, codigoPiloto);
            durations.add(duration);
        }
        return duracaoPorPiloto;
    }

    private Map<Piloto, List<LocalTime>> mapaDeTempoPorPiloto(List<Kart> karts) {
        Map<Piloto, List<LocalTime>> tempoPorPiloto = new HashMap<Piloto, List<LocalTime>>();
        for (Kart kart : karts) {
            if (tempoPorPiloto.get(kart.getPiloto()) == null) {
                tempoPorPiloto.put(kart.getPiloto(), new ArrayList<LocalTime>());
            }
            tempoPorPiloto.get(kart.getPiloto()).add(kart.getHora());
        }
        return tempoPorPiloto;
    }
}
