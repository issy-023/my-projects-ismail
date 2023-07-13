package practicumopdracht.data;

import models.Album;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * text album dao waar ik met save methode de data van de gebruiker opslaan
 * en met de load methode data die was opgeven van de vorige keer weer ophaal en laat zien in de applicatie
 */
public class TextAlbumDAO extends AlbumDAO {
    private static String FILENAME = "album.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        PrintWriter printWriter = null;
        /**
         * hier zet ik data die ik krijg van de gebruiker in de file
         * met een forech loop doe ik van elke album die ik heb in de arraylist
         */
        try {
            printWriter = new PrintWriter(file);



            for(Album album : albums) {
                printWriter.println(album.getNaam());
                printWriter.println(album.getArtiest());
                printWriter.println(album.getBeschrijving());
                printWriter.println(album.getPublicatiedatum());
                printWriter.println(album.isNederlandstalig());
            }

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden!");
        } catch (Exception ex) {
            System.out.println("Er is iets onverwachts gebeurd!");

            ex.printStackTrace();
        }
        finally {
            printWriter.close();
        }

        return false;

    }


    @Override
    public boolean load() {
        File file = new File(FILENAME);

/**
 * hier pak ik de de data van de text file die ik heb en daarna voeg ik het toe in de array
 */

        try(
                Scanner scanner = new Scanner(file);
        ) {
            while (scanner.hasNext()){
                String naam = scanner.nextLine();
                String artiest = scanner.nextLine();
                String beschrijving = scanner.nextLine();
                String publicatiedatum = scanner.nextLine();
                String nederlandstalig = scanner.nextLine();

/**
 * hier heb ik de twee datatypes die niet een string zijn geparse zodat ik ze in de array kon toevoegen
 */

                boolean nederlandstaligString = Boolean.parseBoolean(nederlandstalig);
                LocalDate publicatiedatumString = LocalDate.parse(publicatiedatum);

                albums.add(new Album(naam,publicatiedatumString,nederlandstaligString,artiest,beschrijving));

            }
            scanner.close();

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden!");
        } catch (Exception ex) {
            System.out.println("Er is iets onverwachts gebeurd!");

            ex.printStackTrace();
        }

        return false;
    }

}
