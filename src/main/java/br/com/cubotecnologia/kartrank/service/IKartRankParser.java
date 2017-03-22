package br.com.cubotecnologia.kartrank.service;

import br.com.cubotecnologia.kartrank.model.Kart;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public interface IKartRankParser {
    PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendMinutes()
            .appendLiteral(":")
            .printZeroAlways()
            .appendSecondsWithOptionalMillis()
            .toFormatter();

    Map<String, Integer> readHeader(String header);

    Kart readLine(Map<String, Integer> header, String line) throws ParseException;
}
