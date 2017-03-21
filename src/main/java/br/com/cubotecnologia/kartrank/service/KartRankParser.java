package br.com.cubotecnologia.kartrank.service;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.KartHankEnum;
import br.com.cubotecnologia.kartrank.model.Piloto;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by henriquemoreno on 21/03/17.
 */
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

    public Kart readLine(Map<String, Integer> header, String line) {
        String[] fields = kartRankUtils.removeExcessAndSplit(line);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.GERMAN);
        df.setParseBigDecimal(true);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("mm:ss.SSS");

        try {
            String[] pilotoParse = kartRankUtils.parsePiloto(get(header, KartHankEnum.PILOTO, fields));
            return Kart.builder() //
                    .hora(LocalTime.parse(get(header, KartHankEnum.HORA, fields))) //
                    .numeroVoltas(Integer.parseInt(get(header, KartHankEnum.NUM_VOLTAS, fields)))
                    .piloto(new Piloto(pilotoParse[0],pilotoParse[1]))
                    .tempoVolta(fmt.parseLocalTime(get(header, KartHankEnum.TEMPO_VOLTA, fields)))
                    .velocidadeMediaVolta((BigDecimal) df.parseObject(get(header, KartHankEnum.VELOCIDADE_MEDIA_VOLTA, fields)))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
