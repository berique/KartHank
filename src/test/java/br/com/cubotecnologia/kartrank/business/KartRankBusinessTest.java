package br.com.cubotecnologia.kartrank.business;

import br.com.cubotecnologia.kartrank.BaseKartRankTest;
import br.com.cubotecnologia.kartrank.KartRank;
import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.model.Piloto;
import br.com.cubotecnologia.kartrank.model.Sumario;
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
public class KartRankBusinessTest extends BaseKartRankTest {
    private static final String[] BODY;

    static {
        String text = "Hora\t\t\t\t   Piloto\t      Nº Volta   Tempo Volta\t   Velocidade média da volta\n" +
                "23:49:08.277 \t  038 – F.MASSA\t\t  \t  1\t\t1:02.852 \t\t\t44,275\n" +
                "23:49:10.858 \t  033 – R.BARRICHELLO\t\t  1\t\t1:04.352 \t\t\t43,243\n" +
                "23:49:11.075 \t  002 – K.RAIKKONEN\t\t  1\t\t1:04.108 \t\t\t43,408\n" +
                "23:49:12.667 \t  023 – M.WEBBER\t\t\t  1\t\t1:04.414 \t\t\t43,202\n" +
                "23:49:30.976\t  015 – F.ALONSO\t\t\t  1\t\t1:18.456\t\t\t35,47\n" +
                "23:50:11.447 \t  038 – F.MASSA\t\t  \t  2\t\t1:03.170 \t\t\t44,053\n" +
                "23:50:14.860 \t  033 – R.BARRICHELLO\t\t  2\t\t1:04.002 \t\t\t43,48\n" +
                "23:50:15.057 \t  002 – K.RAIKKONEN\t\t  2\t\t1:03.982 \t\t\t43,493\n" +
                "23:50:17.472 \t  023 – M.WEBBER\t\t\t  2\t\t1:04.805 \t\t\t42,941\n" +
                "23:50:37.987\t  015 – F.ALONSO\t\t\t  2\t\t1:07.011\t\t\t41,528\n" +
                "23:51:14.216 \t  038 – F.MASSA\t\t  \t  3\t\t1:02.769 \t\t\t44,334\n" +
                "23:51:18.576 \t  033 – R.BARRICHELLO\t\t  3\t\t1:03.716 \t\t\t43,675\n" +
                "23:51:19.044 \t  002 – K.RAIKKONEN\t\t  3\t\t1:03.987 \t\t\t43,49\n" +
                "23:51:21.759 \t  023 – M.WEBBER\t\t\t  3\t\t1:04.287 \t\t\t43,287\n" +
                "23:51:46.691\t  015 – F.ALONSO\t\t\t  3\t\t1:08.704\t\t\t40,504\n" +
                "23:52:01.796\t  011 – S.VETTEL\t\t\t  1\t\t3:31.315\t\t\t13,169\n" +
                "23:52:17.003 \t  038 – F.MASS\t\t  \t  4\t\t1:02.787 \t\t\t44,321\n" +
                "23:52:22.586 \t  033 – R.BARRICHELLO\t\t  4\t\t1:04.010 \t\t\t43,474\n" +
                "23:52:22.120 \t  002 – K.RAIKKONEN\t\t  4\t\t1:03.076 \t\t\t44,118\n" +
                "23:52:25.975 \t  023 – M.WEBBER\t\t\t  4\t\t1:04.216 \t\t\t43,335\n" +
                "23:53:06.741\t  015 – F.ALONSO\t\t\t  4\t\t1:20.050\t\t\t34,763\n" +
                "23:53:39.660\t  011 – S.VETTEL\t\t\t  2\t\t1:37.864\t\t\t28,435\n" +
                "23:54:57.757\t  011 – S.VETTEL\t\t\t  3\t\t1:18.097\t\t\t35,633";
        BODY = text.split("\\r?\\n");
    }

    private KartRankBusiness kartRankBusiness = new KartRankBusiness();
    private KartRank kartRank = new KartRank(new KartRankParser(new KartRankUtils()));

    @Test
    public void testCriarSumario() {
        Map<Piloto, List<Kart>> groupByPiloto = kartRank.groupByPiloto(kartRank.parse(Arrays.asList(BODY)));
        List<Sumario> sumarios = kartRankBusiness.criarSumario(groupByPiloto);
        assertThat(sumarios.size(), Is.is(6));
    }

}