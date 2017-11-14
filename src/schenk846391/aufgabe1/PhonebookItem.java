/**
 * @author Marthe Schenk
 * Erstellt am 11.11.2017 (Final Version)
 */

package schenk846391.aufgabe1;

public class PhonebookItem {


   String name;
   int    number;

   /**
    * Konstruktor für Klasse PhonebookItem
    * erstellt Telefonbucheinträge bestehend aus Name und Nummer
    * @param name
    * @param number
    */
   public PhonebookItem(String name, int number) {
      this.name = name;
      this.number = number;
   }
}
