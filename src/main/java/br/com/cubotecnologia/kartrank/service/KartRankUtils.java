package br.com.cubotecnologia.kartrank.service;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankUtils implements IKartRankUtils {

    protected String removeTabAndSpaceExcess(String line) {
        return line.replaceAll("\\s{2,}|\t{2,}", "|");
    }

    protected String[] split(String line) {
        return line.split("[|]");
    }

    public String[] removeExcessAndSplit(String line) {
        return split(removeTabAndSpaceExcess(line));
    }

    public String[] parsePiloto(String piloto) {
        String trim = piloto.replaceAll(" ", "");
        String[] split = trim.split("[–-]");
        return split;
    }
}
