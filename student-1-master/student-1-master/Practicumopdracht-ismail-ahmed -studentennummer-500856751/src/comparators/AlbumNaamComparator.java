package comparators;

import models.Album;

import java.util.Comparator;

public class AlbumNaamComparator implements Comparator <Album> {

    @Override
    public int compare(Album o1, Album o2) {
        return o1.getNaam().compareTo(o2.getNaam());
    }
}
