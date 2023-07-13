package practicumopdracht.data;

import models.Album;

import java.util.ArrayList;
import java.util.List;

public abstract class AlbumDAO implements DAO<Album> {

    protected List<Album> albums;

    public AlbumDAO() {
        albums = new ArrayList<>();

    }

    // week 5 save methode
    public int getIdFor(Album album) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).equals(album)) {
                return albums.indexOf(album);
            }
        }
        return -1;
    }

    public Album getById(int id) {
        try {
            return albums.get(id);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public List<Album> getAll() {
        return albums;
    }

    @Override
    public void addOrUpdate(Album object) {
        if (albums.contains(object)) {
            System.out.println("zit er al in");
        } else {
            albums.add(object);
        }
    }

    @Override
    public void remove(Album object) {
        albums.remove(object);

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
