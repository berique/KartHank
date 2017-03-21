package br.com.cubotecnologia.kartrank.model;

/**
 * Created by henriquemoreno on 21/03/17.
 */
public enum KartHankEnum {
    HORA("Hora"),
    PILOTO("Piloto"),
    NUM_VOLTAS("Nº Volta"),
    TEMPO_VOLTA("Tempo Volta"),
    VELOCIDADE_MEDIA_VOLTA("Velocidade média da volta");

    public final String value;

    KartHankEnum(String value) {
        this.value = value;
    }
}
