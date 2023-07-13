package comparators;

import models.Nummer;

import java.util.Comparator;

public class NummerBeoordeling implements Comparator<Nummer> {
    @Override
    public int compare(Nummer o1, Nummer o2) {
        return Double.compare(o1.getBeoordeling(),o2.getBeoordeling());
    }
}
