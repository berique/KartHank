package br.com.cubotecnologia;

import br.com.cubotecnologia.kartrank.KartRank;
import br.com.cubotecnologia.kartrank.business.IKartRankBusiness;
import br.com.cubotecnologia.kartrank.business.KartRankBusiness;
import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Sumario;
import br.com.cubotecnologia.kartrank.present.KartRankPresent;
import br.com.cubotecnologia.kartrank.service.KartRankParser;
import br.com.cubotecnologia.kartrank.service.KartRankUtils;

import java.io.FileInputStream;
import java.util.List;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class Main {

    private final KartRank kartRank;
    private final IKartRankBusiness kartRankBusiness;

    public Main() {
        kartRank = new KartRank(new KartRankParser(new KartRankUtils()));
        kartRankBusiness = new KartRankBusiness();
    }

    public static void main(String[] args) throws Exception {
        new Main().start(args);
    }

    public void start(String[] args) throws Exception {
        String fileName = "input1.txt";
        if ( args.length == 1) {
            fileName = args[0];
        }
        List<Kart> karts = kartRank.parse(kartRank.readFile(new FileInputStream(fileName)));
        List<Sumario> sumarios = kartRankBusiness.criarSumario(kartRank.groupByPiloto(karts));
        KartRankPresent kartRankPresent = new KartRankPresent();
        kartRankPresent.show(sumarios, kartRankBusiness.calculaMelhorVoltaDaCorrida(sumarios));
    }

}
