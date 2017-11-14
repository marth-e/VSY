/**
 * @author Marthe Schenk 
 * Erstellt am 14.11.2017 (Final Version)
 */

package schenk846391.aufgabe1;

import java.util.ArrayList;

public class MyThread extends Thread {

   String                   input;
   ArrayList<PhonebookItem> phonebook, result;

   /**
    * Konstruktor für Klasse MyThread erstellt Threads bestehend aus:
    * 
    * @param input
    * Konsoleninput von User gegeben
    * @param phonebook
    * Das Telefonbuch in der nach input gesucht wird
    * @param result
    * Ein Thread-eigener ArrayList, in dem die Suchergebnisse gespeichert werden
    */
   public MyThread(String input, ArrayList<PhonebookItem> phonebook, ArrayList<PhonebookItem> result) {
      this.input = input;
      this.phonebook = phonebook;
      this.result = result;
   }

   /**
    * Die Suchmethode run(): Für jeder Thread wird geprüft ob der nach Name oder
    * Nummer suchen soll. Wenn es ein Treffer gibt, wird das gesamte
    * PhonebookItem in dem ArrayList gespeichert.
    */
   public void run() {
      if (input.matches("^[a-zA-zäöüßÄÖÜ]+\\s?[a-zA-ZäöüßÄÖÜ]+")) {
         for (PhonebookItem na : phonebook) {
            if (input.equals(na.name)) {
               result.add(na);
            }
         }
      }
      if (input.matches("[0-9]+")) {
         int num = Integer.parseInt(input);
         for (PhonebookItem nu : phonebook) {
            if (num == nu.number) {
               result.add(nu);
            }
         }
      }
   }
}
