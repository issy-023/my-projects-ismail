package models;

import java.io.Serializable;

public class Nummer implements Serializable {
    private transient Album hoortBij;
    private String naam;
    private double  beoordeling;
    private int duurInSeconden;



    public Nummer(Album hoortBij, String naam, double beoordeling, int duurInSeconden) {
        this.hoortBij = hoortBij;
        this.naam = naam;
        this.beoordeling = beoordeling;
        this.duurInSeconden = duurInSeconden;
    }

    public Nummer() {
    }

    /** hier staan alle setters en getter */
    public Album getHoortBij() {
        return hoortBij;
    }

    public void setHoortBij(Album hoortBij) {
        this.hoortBij = hoortBij;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getBeoordeling() {
        return beoordeling;
    }

    public void setBeoordeling(double beoordeling) {
        this.beoordeling = beoordeling;
    }

    public int getDuurInSeconden() {
        return duurInSeconden;
    }

    public void setDuurInSeconden(int duurInSeconden) {
        this.duurInSeconden = duurInSeconden;
    }

    @Override
    public String toString() {

        return String.format("naam van het nummer : %s \nlengte van het nummer in seconden is : %d \nbeoordeling van het nummer is :%.2f",naam,duurInSeconden,beoordeling);
    }
}
