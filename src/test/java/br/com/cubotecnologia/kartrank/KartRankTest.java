package br.com.cubotecnologia.kartrank;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.service.KartRankParser;
import br.com.cubotecnologia.kartrank.service.KartRankUtils;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankTest {
    KartRank kartRank = new KartRank(new KartRankParser(new KartRankUtils()));

    @Test
    public void testReadFile() {
        List<Kart> karts = kartRank.readFile(this.getClass().getResourceAsStream("/input1.txt"));
        assertThat(karts.isEmpty(), Is.is(false));
        assertThat(karts.size(), Is.is(23));
    }

}