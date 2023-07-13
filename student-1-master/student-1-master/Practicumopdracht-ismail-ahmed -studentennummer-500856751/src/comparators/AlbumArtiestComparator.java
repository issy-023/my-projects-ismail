package comparators;

import models.Album;

import java.util.Comparator;

public class AlbumArtiestComparator implements Comparator<Album> {

    @Override
    public int compare(Album o1, Album o2) {
        int soorteerArtiest= o1.getArtiest().compareTo(o2.getArtiest());
        if (soorteerArtiest==0){
            return o1.getPublicatiedatum().compareTo(o2.getPublicatiedatum());
        }
        return 0;
    }
}
