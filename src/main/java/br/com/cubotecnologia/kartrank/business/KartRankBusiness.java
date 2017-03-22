package br.com.cubotecnologia.kartrank.business;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Piloto;
import br.com.cubotecnologia.kartrank.model.Sumario;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankBusiness implements IKartRankBusiness {

    public Map<Piloto, List<Kart>> agruparKartPorPiloto(List<Kart> karts) {
        Map<Piloto, List<Kart>> pilotoKarts = new HashMap<Piloto, List<Kart>>();
        for (Kart kart : karts) {
            if (pilotoKarts.get(kart.getPiloto()) == null) {
                pilotoKarts.put(kart.getPiloto(), new ArrayList<Kart>());
            }
            pilotoKarts.get(kart.getPiloto()).add(kart);
        }
        return pilotoKarts;
    }

    public List<Sumario> criarSumario(Map<Piloto, List<Kart>> karts) {
        List<Sumario> sumarios = new ArrayList<Sumario>();
        for (Piloto piloto : karts.keySet()) {
            criaSumarioPorPiloto(karts, sumarios, piloto);
        }
        ordenaPorDuracaoProva(sumarios);
        return sumarios;
    }

    public Duration calculaMelhorVoltaDaCorrida(List<Sumario> sumarios) {
        Duration melhorVoltaCorrida = null;
        for ( Sumario sumario: sumarios) {
            if ( melhorVoltaCorrida == null ) {
                melhorVoltaCorrida = sumario.getMelhorVolta();
            } else {
                if ( melhorVoltaCorrida.isShorterThan(sumario.getMelhorVolta())) {
                    melhorVoltaCorrida = sumario.getMelhorVolta();
                }
            }
        }
        return melhorVoltaCorrida;
    }

    private void criaSumarioPorPiloto(Map<Piloto, List<Kart>> karts, List<Sumario> sumarios, Piloto piloto) {
        List<Kart> karts1 = karts.get(piloto);
        List<LocalTime> localTimes = new ArrayList<LocalTime>();
        Duration tempoTotalProva = Duration.ZERO.ZERO;
        int quantidadeVoltas = 0;
        BigDecimal velocidadeMedia = BigDecimal.ZERO;
        Duration melhorVolta = null;
        for (Kart kart : karts1) {
            if (melhorVolta == null) {
                melhorVolta = kart.getTempoVolta();
            } else {
                if (melhorVolta.isShorterThan(kart.getTempoVolta())) {
                    melhorVolta = kart.getTempoVolta();
                }
            }
            localTimes.add(kart.getHora());
            tempoTotalProva = tempoTotalProva.plus(kart.getTempoVolta());
            velocidadeMedia = velocidadeMedia.add(kart.getVelocidadeMediaVolta());

            quantidadeVoltas++;
        }
        velocidadeMedia = velocidadeMedia.divide(new BigDecimal(karts1.size()), 3, RoundingMode.HALF_UP);

        Sumario.SumarioBuilder sumarioBuilder = Sumario.builder()
                .piloto(piloto)
                .duracaoProva(calculaDuracao(localTimes))
                .tempoTotalProva(tempoTotalProva)
                .quantidadeVoltas(quantidadeVoltas)
                .velocidadeMedia(velocidadeMedia)
                .melhorVolta(melhorVolta);

        sumarios.add(sumarioBuilder.build());
    }

    private void ordenaPorDuracaoProva(List<Sumario> sumarios) {
        Collections.sort(sumarios, new Comparator<Sumario>() {
            public int compare(Sumario o1, Sumario o2) {
                if (o1.getDuracaoProva().isShorterThan(o2.getDuracaoProva())) {
                    return 1;
                }
                return -1;
            }
        });
        Sumario[] arraySumario = sumarios.toArray(new Sumario[]{});
        for (int cnt = 0; cnt < arraySumario.length; cnt++) {
            Sumario sumarioVencedor = arraySumario[0];
            Sumario sumario = arraySumario[cnt];
            sumario.setPosicaoChegada(cnt+1);
                sumario.setAposVencedor(sumarioVencedor.getTempoTotalProva().minus(sumario.getTempoTotalProva()));
        }
    }

    private Duration calculaDuracao(List<LocalTime> localTimes) {
        LocalTime min = Collections.min(localTimes);
        LocalTime max = Collections.max(localTimes);
        return new Duration(min.toDateTimeToday().toDateTime(), max.toDateTimeToday().toDateTime());
    }
}
