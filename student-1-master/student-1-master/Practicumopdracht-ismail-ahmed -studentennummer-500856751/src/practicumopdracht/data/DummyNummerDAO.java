package practicumopdracht.data;

import models.Nummer;
import practicumopdracht.MainApplication;

public class DummyNummerDAO extends NummerDAO {
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        Nummer nummer1 = new Nummer(MainApplication.getAlbumDAO().getById(1),"isgggl",1,1);
        Nummer nummer2 = new Nummer(MainApplication.getAlbumDAO().getById(0),"baaal",1,1);
        Nummer nummer3 = new Nummer(MainApplication.getAlbumDAO().getById(0),"oaaawwil",1,1);
        nummers.add(nummer1);
        nummers.add(nummer2);
        nummers.add(nummer3);

        return true;
    }
}
