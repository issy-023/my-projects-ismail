package practicumopdracht.data;

import models.Album;

import java.time.LocalDate;

public class DummyAlbumDAO extends AlbumDAO{

    @Override
    public boolean save() {
        return super.save();
    }

    @Override
    public boolean load() {
        albums.add(new Album("Hazes nu",LocalDate.of(2001,9,1),true,"Andre hazes","top album in 2001"));
        albums.add(new Album("Into The Electric Castle",LocalDate.of(1989,4,23),false,"Ayreon ","top album in 1989"));
        return true;
    }

}
