package br.com.cubotecnologia.kartrank.business;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Piloto;
import br.com.cubotecnologia.kartrank.model.Sumario;
import org.joda.time.Duration;

import java.util.List;
import java.util.Map;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public interface IKartRankBusiness {
    Map<Piloto, List<Kart>> agruparKartPorPiloto(List<Kart> karts);

    List<Sumario> criarSumario(Map<Piloto, List<Kart>> karts);

    Duration calculaMelhorVoltaDaCorrida(List<Sumario> sumarios);
}
