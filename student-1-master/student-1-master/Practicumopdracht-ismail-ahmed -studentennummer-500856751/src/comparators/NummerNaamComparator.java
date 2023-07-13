package comparators;

import models.Nummer;

import java.util.Comparator;

public class NummerNaamComparator implements Comparator <Nummer>{
    @Override
    public int compare(Nummer o1, Nummer o2) {
        return o1.getNaam().compareTo(o2.getNaam());
    }
}
