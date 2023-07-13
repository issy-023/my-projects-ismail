package practicumopdracht.data;

import models.Album;

import java.io.*;
import java.time.LocalDate;

public class BinaryAlbumDAO extends AlbumDAO {
    private static String FILENAME = "album.dat";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)

        ) {
            dataOutputStream.writeInt(albums.size());

            for (Album album : albums) {
                dataOutputStream.writeUTF(album.getNaam());
                dataOutputStream.writeUTF(album.getArtiest());
                dataOutputStream.writeUTF(album.getBeschrijving());
                dataOutputStream.writeUTF(String.valueOf(album.getPublicatiedatum()));
                dataOutputStream.writeUTF(String.valueOf(album.isNederlandstalig()));

            }

        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden");
        } catch (IOException e) {
            System.out.println(" er is iets onverwachts gebeurd");
        }

        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);

        ) {
            int aantalAlbums = dataInputStream.readInt();

            for (int i = 0; i < aantalAlbums; i++) {
                String naam = dataInputStream.readUTF();
                String artiest = dataInputStream.readUTF();
                String beschrijving = dataInputStream.readUTF();
                LocalDate publicatieDatum = LocalDate.parse(dataInputStream.readUTF());
                boolean isNederlandstig = dataInputStream.readBoolean();




                albums.add(new Album(naam, publicatieDatum, isNederlandstig, artiest, beschrijving));


            }
        } catch (FileNotFoundException e) {
            System.out.println("bestand niet gevonden");
        } catch (IOException e) {
            System.out.println(" Er is iets onverwachts gebeurd!");
            e.printStackTrace();
        }

        return false;
    }
}
