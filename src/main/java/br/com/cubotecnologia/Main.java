package br.com.cubotecnologia;

import br.com.cubotecnologia.kartrank.KartRank;
import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.service.KartRankParser;
import br.com.cubotecnologia.kartrank.service.KartRankUtils;

import java.io.FileInputStream;
import java.util.List;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class Main {

    private final KartRank kartRank;

    public static void main(String[] args) {
        new Main().start();
    }

    public Main() {
        kartRank = new KartRank(new KartRankParser(new KartRankUtils()));
    }

    public void start() {

    }

}
