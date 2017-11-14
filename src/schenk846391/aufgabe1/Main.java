/**
 * @author Marthe Schenk 
 * Erstellt am 14.11.2017 (Final Version)
 */

package schenk846391.aufgabe1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

   public static void main(String[] args) throws IOException, InterruptedException {

      // Ein Telefonbuch erstellen
      ArrayList<PhonebookItem> phonebook = new ArrayList<PhonebookItem>();
      phonebook.add(new PhonebookItem("Müller", 1234));
      phonebook.add(new PhonebookItem("Meyer", 1534));
      phonebook.add(new PhonebookItem("Müller", 1534));
      phonebook.add(new PhonebookItem("Peters", 5325));
      phonebook.add(new PhonebookItem("von Alm", 4321));

      while (true) {
         // User gibt Name und/oder Nummer in Konsole ein
         // BufferedReader liest Input und speichert diese in passender Variable
         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

         // Name einlesen und prüfen. User hat hier Möglichkeit das Programm zu
         // beenden
         System.out.println("Optional: Gebe einen Name ein (Schreib \"exit\" zum Abbrechen): ");
         String inputName = in.readLine();
         if (inputName.equals("exit")) break;
         if (inputName.matches("^[^a-zA-ZäöüßÄÖÜ]+")) {
            System.out.println("Kein gültiger Name eingegeben. Bitte versuchen Sie es noch einmal.");
            continue;
         }

         // Nummer einlesen und prüfen
         System.out.println("Optional: Gebe einen Nummer ein: ");
         String inputNumber = in.readLine();
         if (inputNumber.matches("[^0-9]+")) {
            System.out.println("Keine gültige Nummer eingegeben. Bitte versuchen Sie es noch einmal");
            continue;
         }

         // Such-Threads erstellen und starten

         // Keine Eingaben gemacht
         if (inputName.equals("") && inputNumber.equals("")) {
            System.out.println("Keine Eingaben gemacht. Bitte versuchen Sie es noch einmal");
            continue;
         }

         // User hat nur Nummer eingegeben
         if (inputName.equals("")) {
            MyThread tnum = new MyThread(inputNumber, phonebook, new ArrayList<PhonebookItem>());
            tnum.start();
            tnum.join();
            if (tnum.result.size() == 0) System.out.println("Die suche nach " + tnum.input + " war erfolglos.");
            for (PhonebookItem item : tnum.result)
               System.out.println(item.name + " " + item.number);
            continue;
         }

         // User hat nur Name eingegeben
         if (inputNumber.equals("")) {
            MyThread tnam = new MyThread(inputName, phonebook, new ArrayList<PhonebookItem>());
            tnam.start();
            tnam.join();
            if (tnam.result.size() == 0) System.out.println("Die suche nach " + tnam.input + " war erfolglos.");
            for (PhonebookItem item : tnam.result)
               System.out.println(item.name + " " + item.number);
            continue;

            // User hat sowohl Name als Nummer eingegeben
         } else {

            MyThread tnam = new MyThread(inputName, phonebook, new ArrayList<PhonebookItem>());
            MyThread tnum = new MyThread(inputNumber, phonebook, new ArrayList<PhonebookItem>());

            tnam.start();
            tnum.start();
            tnam.join();
            tnum.join();

            // Ergebnisse zusammenfassen und ausgeben
            tnam.result.addAll(tnum.result);
            if (tnam.result.size() == 0) System.out.println("Die suche nach " + tnam.input + " war erfolglos.");
            for (PhonebookItem item : tnam.result)
               System.out.println(item.name + " " + item.number);
         }
      }
   }
}
