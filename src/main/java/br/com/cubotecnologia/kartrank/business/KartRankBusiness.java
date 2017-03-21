package br.com.cubotecnologia.kartrank.business;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Piloto;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

import java.util.*;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankBusiness implements IKartRankBusiness {
    public List<Kart> filter(List<Kart> karts) {
        List<Kart> filter = new ArrayList<Kart>();
        for (Kart kart : karts) {
            if (kart.getNumeroVoltas() == 4) {
                filter.add(kart);
            }
        }
        return filter;
    }

    public void calcularTempo(List<Kart> karts, List<Kart> filter) {
        Map<Piloto, List<LocalTime>> tempoPorPiloto = new HashMap<Piloto, List<LocalTime>>();
        for (Kart kart : karts) {
            boolean temNoFiltro = false;
            for (Kart kart1 : filter) {
                if (kart.getPiloto().equals(kart1.getPiloto())) {
                    temNoFiltro = true;
                }
            }
            if (!temNoFiltro) {
                continue;
            }
            if (tempoPorPiloto.get(kart.getPiloto()) == null) {
                tempoPorPiloto.put(kart.getPiloto(), new ArrayList<LocalTime>());
            }
            tempoPorPiloto.get(kart.getPiloto()).add(kart.getHora());
        }
        List<Duration> durations = new ArrayList<Duration>();
        Map<Duration, Piloto> duracaoPorPiloto = new HashMap<Duration, Piloto>();
        for (Piloto codigoPiloto : tempoPorPiloto.keySet()) {
            List<LocalTime> listTempo = tempoPorPiloto.get(codigoPiloto);
            LocalTime max = Collections.max(listTempo);
            LocalTime min = Collections.min(listTempo);
            Duration duration = new Duration(min.toDateTimeToday().toDateTime(), max.toDateTimeToday().toDateTime());
            duracaoPorPiloto.put(duration, codigoPiloto);
            durations.add(duration);
        }
        Collections.sort(durations, new Comparator<Duration>() {
            public int compare(Duration o1, Duration o2) {
                if (o1.isShorterThan(o2)) {
                    return 1;
                }
                return -1;
            }
        });
        Map<Piloto, Long> ranking = new HashMap<Piloto, Long>();
        for ( Duration duration: durations) {
            for ( Duration duracaoPiloto : duracaoPorPiloto.keySet()) {
                if ( duracaoPiloto.equals(duration)) {
                    ranking.put(duracaoPorPiloto.get(duration), duration.getMillis());
                }
            }
        }
        System.out.println(ranking);
    }

    public List<Kart> menorTempo(List<Kart> karts) {

        Collections.sort(karts, new Comparator<Kart>() {
            public int compare(Kart o1, Kart o2) {
                if (o1.getTempoVolta().isAfter(o2.getTempoVolta())) {
                    return 1;
                }
                return -1;
            }
        });
        return karts;
    }
}
