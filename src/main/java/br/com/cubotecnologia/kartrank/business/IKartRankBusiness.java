package br.com.cubotecnologia.kartrank.business;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Sumario;

import java.util.List;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public interface IKartRankBusiness {
     List<Sumario> calcularRanking(List<Kart> karts);
}
