package com.example.demo10;

import java.io.Serializable;

/**
 * Edustaa puutarhatyötä.
 * Tämä luokka toteuttaa Serializable-rajapinnan tukeakseen serialisointia.
 */
public class PuutarhaTyo implements Serializable {
    private String tyonNimi; // Työn nimi
    private double tyonHinta; // Työn hinta
    private int tyonKesto; // Työn kesto tunteina
    private boolean nurmikonLeikkaus; // Kertoo, sisältyykö nurmikon leikkaus työhön
    private boolean kukkienIstutus; // Kertoo, sisältyykö kukkien istutus työhön
    private boolean haravointi; // Kertoo, sisältyykö haravointi työhön

    /**
     * Luo PuutarhaTyo-olion annetuilla parametreilla.
     * @param tyonNimi Työn nimi
     * @param tyonHinta Työn hinta
     * @param tyonKesto Työn kesto tunteina
     * @param nurmikonLeikkaus Kertoo, sisältyykö nurmikon leikkaus työhön
     * @param kukkienIstutus Kertoo, sisältyykö kukkien istutus työhön
     * @param haravointi Kertoo, sisältyykö haravointi työhön
     */
    public PuutarhaTyo(String tyonNimi, double tyonHinta, int tyonKesto, boolean nurmikonLeikkaus, boolean kukkienIstutus, boolean haravointi) {
        this.tyonNimi = tyonNimi;
        this.tyonHinta = tyonHinta;
        this.tyonKesto = tyonKesto;
        this.nurmikonLeikkaus = nurmikonLeikkaus;
        this.kukkienIstutus = kukkienIstutus;
        this.haravointi = haravointi;
    }

    // Lisää getterit ja setterit tarvittaessa

    /**
     * Palauttaa merkkijonoesityksen puutarhatyöstä.
     * @return Merkkijonoesitys puutarhatyöstä
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tyonNimi)
                .append(" (Hinta: €").append(tyonHinta)
                .append(", Kesto: ").append(tyonKesto).append(" tuntia)");

        if (nurmikonLeikkaus) {
            stringBuilder.append(", Nurmikon leikkaus");
        }
        if (kukkienIstutus) {
            stringBuilder.append(", Kukkien istutus");
        }
        if (haravointi) {
            stringBuilder.append(", Haravointi");
        }

        return stringBuilder.toString();
    }
}