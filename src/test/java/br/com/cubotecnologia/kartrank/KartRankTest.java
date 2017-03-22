package br.com.cubotecnologia.kartrank;

import br.com.cubotecnologia.kartrank.model.ConstantsTest;
import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Piloto;
import br.com.cubotecnologia.kartrank.service.KartRankParser;
import br.com.cubotecnologia.kartrank.service.KartRankUtils;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankTest implements ConstantsTest {
    KartRank kartRank = new KartRank(new KartRankParser(new KartRankUtils()));

    @Test
    public void testReadFile() {
        List<String> linhas = kartRank.readFile(this.getClass().getResourceAsStream("/input1.txt"));
        assertThat(linhas.size(), Is.is(24));
    }

    @Test
    public void testParse() {
        List<String> linhas = kartRank.readFile(this.getClass().getResourceAsStream("/input1.txt"));
        List<Kart> karts = kartRank.parse(linhas);
        assertThat(karts.isEmpty(), Is.is(false));
        assertThat(karts.size(), Is.is(23));
    }

    @Test
    public void testGroupByPiloto() {
        Map<Piloto, List<Kart>> result = kartRank.groupByPiloto(kartRank.parse(Arrays.asList(BODY_LINES)));
        assertThat(result.size(), Is.is(6));
        assertThat(result.get(new Piloto("011", "")).get(0).getNumeroVoltas(), Is.is(1));

    }

}