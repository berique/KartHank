package br.com.cubotecnologia.kartrank;

import br.com.cubotecnologia.kartrank.model.Kart;
import br.com.cubotecnologia.kartrank.service.IKartRankParser;
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

    public List<String> readFile(InputStream fis) {
        if (fis == null) {
            throw new RuntimeException("InputStream é obrigatório.");
        }
        String line;
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        List<String> linhas = new ArrayList<String>();

        try {
            while ((line = br.readLine()) != null) {
                linhas.add(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return linhas;
    }

    public List<Kart> parse(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new RuntimeException("InputStream é obrigatório.");
        }
        List<Kart> karts = new ArrayList<Kart>();
        int cnt = 0;
        Map<String, Integer> header = new HashMap<String, Integer>();
        for (String line : lines) {
            if (cnt == 0) {
                header = kartRankParser.readHeader(line);
                cnt++;
                continue;
            }
            karts.add(kartRankParser.readLine(header, line));
        }
        return karts;
    }
}
