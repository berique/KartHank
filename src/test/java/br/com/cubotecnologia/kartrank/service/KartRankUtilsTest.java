package br.com.cubotecnologia.kartrank.service;

import br.com.cubotecnologia.kartrank.model.Constants;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public class KartRankUtilsTest extends KartRankUtils implements Constants {
    private KartRankUtils kartRankUtils = new KartRankUtils();

    @Test
    public void testRemoveTabAndSpaceExcess() throws Exception {
        assertThat(kartRankUtils.removeTabAndSpaceExcess(LINE_HEADER), Is.is("Hora|Piloto|Nº Volta|Tempo Volta|Velocidade média da volta"));
    }

    @Test
    public void testSplit() {
        assertThat(kartRankUtils.split(kartRankUtils.removeTabAndSpaceExcess(LINE_HEADER)).length, Is.is(5));
    }

    @Test
    public void testRemoveExcessAndSplit() {
        assertThat(kartRankUtils.removeExcessAndSplit(LINE_HEADER).length, Is.is(5));
    }

    @Test
    public void testParsePiloto() {
        String[] parse = kartRankUtils.parsePiloto("038 – F.MASSA");
        assertThat(parse[0], Is.is("038"));
        assertThat(parse[1], Is.is("F.MASSA"));
    }


}