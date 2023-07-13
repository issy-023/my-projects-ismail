package models;

import java.time.LocalDate;

public class Album {
    private String naam;
    private LocalDate publicatiedatum;
    private boolean nederlandstalig;
    private String artiest;
    private String beschrijving;


    public Album(String naam, LocalDate publicatiedatum, boolean nederlandstalig, String artiest, String beschrijving) {
        this.naam = naam;
        this.publicatiedatum = publicatiedatum;
        this.nederlandstalig = nederlandstalig;
        this.artiest = artiest;
        this.beschrijving = beschrijving;


    }

    public Album() {
    }

    /**
     * hier staan alle setters en getter
     */
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public LocalDate getPublicatiedatum() {
        return publicatiedatum;
    }

    public void setPublicatiedatum(LocalDate publicatiedatum) {
        this.publicatiedatum = publicatiedatum;
    }


    public boolean isNederlandstalig() {
        return nederlandstalig;
    }

    public void setNederlandstalig(boolean nederlandstalig) {
        this.nederlandstalig = nederlandstalig;
    }

    public String getArtiest() {
        return artiest;
    }

    public void setArtiest(String artiest) {
        this.artiest = artiest;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("album naam is : %s \nnaam van de artiest is : %s \nbeschrijving van de album is : %s\n", naam, artiest, beschrijving));
        if (nederlandstalig == true) {
            stringBuilder.append("nederlandstalig: ja het nummer is nederlandstalig");
        } else {
            stringBuilder.append("nederlandstalig: nee  het nummer is niet nederlandstalig");
        }
        stringBuilder.append(String.format("\nde album is gepubliceerd in : %s ",publicatiedatum));

        return stringBuilder.toString();
    }
}
