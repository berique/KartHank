package br.com.cubotecnologia.kartrank.service;

import br.com.cubotecnologia.kartrank.model.Kart;

import java.util.Map;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public interface IKartRankParser {
    Map<String, Integer> readHeader(String header);

    Kart readLine(Map<String, Integer> kartRankeader, String line);
}
