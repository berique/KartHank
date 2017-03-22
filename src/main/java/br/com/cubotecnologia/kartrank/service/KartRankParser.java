package br.com.cubotecnologia.kartrank.service;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.KartHankEnum;
import br.com.cubotecnologia.kartrank.model.Piloto;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalTime;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by henriquemoreno on 21/03/17.
 */
@Slf4j
public class KartRankParser implements IKartRankParser {
    private final IKartRankUtils kartRankUtils;
    private Map<String, Integer> mapHeader = new HashMap<String, Integer>();

    private KartRankParser() {
        kartRankUtils = null;
        throw new RuntimeException("Este construtor é privado e não deveria ser utilizado.");
    }

    public KartRankParser(IKartRankUtils kartRankUtils) {
        this.kartRankUtils = kartRankUtils;
    }

    public Map<String, Integer> readHeader(String header) {
        if (mapHeader.isEmpty()) {
            String[] results = kartRankUtils.removeExcessAndSplit(header);
            int cnt = 0;
            for (String result : results) {
                mapHeader.put(result, cnt++);
            }
        }
        return mapHeader;
    }

    private String get(Map<String, Integer> mapHeader, KartHankEnum kartHankEnum, String[] field) {
        return field[mapHeader.get(kartHankEnum.value)];
    }

    public Kart readLine(Map<String, Integer> header, String line) throws ParseException {
        String[] fields = kartRankUtils.removeExcessAndSplit(line);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.GERMAN);
        df.setParseBigDecimal(true);

        Piloto piloto = parsePiloto(header, fields);
        return buildKart(header, fields, df, piloto);
    }

    private Kart buildKart(Map<String, Integer> header, String[] fields, DecimalFormat df, Piloto piloto) throws ParseException {
        return Kart.builder() //
                .hora(LocalTime.parse(get(header, KartHankEnum.HORA, fields))) //
                .numeroVoltas(Integer.parseInt(get(header, KartHankEnum.NUM_VOLTAS, fields)))
                .piloto(piloto)
                .tempoVolta(PERIOD_FORMATTER.parsePeriod(get(header, KartHankEnum.TEMPO_VOLTA, fields)).toStandardDuration())
                .velocidadeMediaVolta((BigDecimal) df.parseObject(get(header, KartHankEnum.VELOCIDADE_MEDIA_VOLTA, fields)))
                .build();
    }

    private Piloto parsePiloto(Map<String, Integer> header, String[] fields) {
        String[] pilotoParse = kartRankUtils.parsePiloto(get(header, KartHankEnum.PILOTO, fields));
        return new Piloto(pilotoParse[0], pilotoParse[1]);
    }
}
