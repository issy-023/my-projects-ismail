package practicumopdracht.data;

import models.Album;
import models.Nummer;
import practicumopdracht.MainApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextNummerDAO extends NummerDAO{

    private static String FILENAME = "nummer.txt";


    @Override
    public boolean save() {
        File file = new File(FILENAME);
        PrintWriter printWriter = null;
        try{
            printWriter = new PrintWriter(file);

            for (Nummer nummer: nummers){
                printWriter.println(MainApplication.getAlbumDAO().getIdFor(nummer.getHoortBij()));
                printWriter.println(nummer.getNaam());
                printWriter.println(nummer.getDuurInSeconden());
                printWriter.println(nummer.getBeoordeling());
            }
            printWriter.close();
            return true;
        }catch (FileNotFoundException e) {
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
        try {
          Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String hoortBij = scanner.nextLine();
                String name = scanner.nextLine();
                String duurinSeconden = scanner.nextLine();
                String beoordeling = scanner.nextLine();

                int albumNummerPlaats = Integer.parseInt(hoortBij);
                Album nummerBijAlbum = MainApplication.getAlbumDAO().getById(albumNummerPlaats);

                double beoordelingString = Double.parseDouble(beoordeling);
                int duurinSecondenString = Integer.parseInt(duurinSeconden);

                nummers.add(new Nummer(nummerBijAlbum, name,beoordelingString,duurinSecondenString));
            }

        } catch (FileNotFoundException exception){
            System.err.print("File not found");
            return false;
        } catch (Exception exception) {
            System.err.print("Something went wrong");
            return false;
        }
        return true;
    }
    }

