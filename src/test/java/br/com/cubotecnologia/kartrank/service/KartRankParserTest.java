package br.com.cubotecnologia.kartrank.service;

import br.com.cubotecnologia.kartrank.model.ConstantsTest;
import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.KartHankEnum;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertThat;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankParserTest implements ConstantsTest {
    private KartRankParser kartRankParser = new KartRankParser(new KartRankUtils());

    @Test
    public void readHeader() throws Exception {
        Map<String, Integer> header = kartRankParser.readHeader(LINE_HEADER);
        assertThat(header.size(), Is.is(5));
        assertThat(header.get(KartHankEnum.HORA.value), Is.is(0));
    }

    @Test
    public void readLine() throws Exception {
        Map<String, Integer> header = kartRankParser.readHeader(LINE_HEADER);
        Kart kart = kartRankParser.readLine(header, LINE_BODY);
        assertThat(kart.getHora().toString(), Is.is("23:49:08.277"));
        assertThat(kart.getNumeroVoltas(), Is.is(1));
        assertThat(kart.getPiloto().getNome(), Is.is("F.MASSA"));
        assertThat(IKartRankParser.PERIOD_FORMATTER.print(kart.getTempoVolta().toPeriod()), Is.is("1:2.852"));
    }

}