package practicumopdracht.data;

import models.Album;
import models.Nummer;

import java.util.ArrayList;
import java.util.List;

public abstract class NummerDAO implements DAO<Nummer> {

    protected List<Nummer> nummers;

    public NummerDAO() {
        nummers = new ArrayList<>();
    }

    public Nummer getById(int id) {
      if (id<0||id>=nummers.size()){
          return null;
      }
      return nummers.get(id);
    }

    public List<Nummer> getAllfor(Album object) {
        ArrayList<Nummer> nummerlijst = new ArrayList<Nummer>();

        for (Nummer nummer : nummers) {
            if (nummer.getHoortBij()== object)
                nummerlijst.add(nummer);
        }
        return nummerlijst;
    }

    @Override
    public List<Nummer> getAll() {
        return nummers;
    }

    @Override
    public void addOrUpdate(Nummer model) {
        if (nummers.contains(model)) {
            System.out.println("zit " +
                    "er al in");
        } else {
            nummers.add(model);
        }

    }

    @Override
    public void remove(Nummer object) {
        nummers.remove(object);

    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
