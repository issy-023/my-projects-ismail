package practicumopdracht.data;

import models.Album;
import models.Nummer;
import practicumopdracht.MainApplication;

import java.io.*;

public class ObjectNummerDAO extends NummerDAO {
    private static String FILENAME = "nummer.obj";


    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeInt(nummers.size());
            for (Nummer nummer : nummers) {
                objectOutputStream.writeObject(nummer);
                objectOutputStream.writeInt(MainApplication.getAlbumDAO().getIdFor(nummer.getHoortBij()));
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ) {
            int aantalNummers =objectInputStream.readInt();
            for (int i = 0; i < aantalNummers; i++) {
                Nummer nummer = (Nummer) objectInputStream.readObject();

               Album plaats = MainApplication.getAlbumDAO().getById(objectInputStream.readInt());

                nummer.setHoortBij(plaats);

                nummers.add(nummer);

            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("bestand is niet gevonden");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}