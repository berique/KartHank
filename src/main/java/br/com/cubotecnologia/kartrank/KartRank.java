package br.com.cubotecnologia.kartrank;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.service.IKartRankParser;
import br.com.cubotecnologia.kartrank.service.KartRankParser;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by henriquemoreno on 21/03/17.
 */
@Slf4j
public class KartRank {

    private final IKartRankParser kartRankParser;

    private KartRank() {
        kartRankParser = null;
        throw new RuntimeException("Este construtor é privado e não deveria ser utilizado.");
    }

    public KartRank(IKartRankParser kartRankParser) {
        this.kartRankParser = kartRankParser;
    }

    public List<Kart> readFile(InputStream fis) {
        if (fis == null) {
            throw new RuntimeException("InputStream é obrigatório.");
        }
        List<Kart> karts = new ArrayList<Kart>();
        String line;
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        try {
            int cnt = 0;
            Map<String, Integer> header = new HashMap<String, Integer>();
            while ((line = br.readLine()) != null) {
                if (cnt == 0) {
                    header = kartRankParser.readHeader(line);
                    cnt++;
                    continue;
                }
                karts.add(kartRankParser.readLine(header, line));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return karts;
    }
}
