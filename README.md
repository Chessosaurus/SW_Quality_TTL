# Releasenote

## Projektinformationen
Im Rahmen der Vorlesung "Softwarequalität" von Herrn Christian Hübscher im Sommersemester 2024 an der DHBW Stuttgart Campus Horb gilt es, einen Kontaktsplitter zu erstellen. Die Anwendung soll sich in ein bestehendes CRM-System eines Kunden integrieren und die folgenden Anforderungen erfüllen: 
Bei Problemen oder Rückfragen kontaktieren Sie bitte: 
* Tobias Hahn (i21009@hb.dhbw-stuttgart.de)
* Lars Holweger (i21012@hb.dhbw-stuttgart.de)
* Timo Zink (i21031@hb.dhbw-stuttgart.de)


## Anforderungen
* Eingegebene Anrede soll in Bestandteile zerlegt werden
* Geforderte Bestandteile:
  * Anrede
  * Standardisierte Briefanrede (generiert)
  * Titel
  * Geschlecht
  * Vorname
  * Nachname
* Sollte eine automatische Zuordnung nicht möglich sein, muss dies per Auswahl/Text-Fenster manuell gemacht werden. (immer möglich sein)
* Dabei sollte die Option bestehen ggf. neue Titel per Lernfunktion hinzuzufügen („manuell“ zu einer DB hinzufügen, lokal merken)
* Prototyp mind. für DACH
* Möglichkeit zu manueller Eingabe anbieten (entweder wenn nicht erkannt oder wenn so gewünscht)
* Minimale GUI (mit Zeile zur Texteingabe als 1 String), Titel händisch hinzufügbar im Nachhinein, ohne Vorgaben
* Testdaten: (nicht alles machbar, Benutzer führen) Annahmen/Kommentar in Release Note, was machbar/was nicht an Erkennung. Ggf. abfangen oder auf manuelle Eingabe hinweisen.
* „X“ als „neutrales Geschlecht/Divers“ / Auswählbar manuell (nachträglich)
* Keine Persistenz der eingegebenen Daten nötig nach Ende der Applikationsausführung
* Deutsche Briefanrede wegspeichern
* Keine nachträgliche Nachbearbeitungsmöglichkeit nötig
* Umgang mit Mehrfachtitel: max 2 Titel im Prototyp
* Direkt nach Eingabe reicht zur manuellen Eingabe
* Ggf. gültige Standards/Normen berücksichtigen
* „,“ davor steht ein Nachname
* Top 10 Titel (ggf. manuell erweiterbar)
* Bis zu 2 Vornamen unterstützt

## Systemvoraussetzung
* Betriebssystem: Windows 10 >, macOS, Linux
* JDK: 21

## Inbetriebnahme
Schritte zum Ausführen der mitgelieferten .jar Datei:
* Wenn die richtige Java Version vorhanden ist können Sie die Datei mit Java -jar xx.jar aufrufen, wenn Sie das Terminal im richtigen Ordner öffnen
* Falls die richtige Version nicht vorhanden ist, können Sie diese auf https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html für das passende Betriebssystem herunterladen.
Danach sollten Sie die erste Methode anwenden können.

## Benutzung
### 1. Eingabe einer Anrede

![image](https://github.com/Chessosaurus/SW_Quality_TTL/assets/52112815/00a208fd-3052-4c45-bd6f-1d46541f174c)

* In dem Eingabefeld oben links kann eine Anrede (z.B. "Herr Prof. Dr. Max Mustermann") eingegeben werden. 
* Beim klicken auf den "Eingabe"-Knopf wird dann der eingegebene Text in seine Bestandteile aufgeteilt und in den dafür vorgesehenen Ausgabe-Textfeldern aufgeführt. Je nach Bedarf können in diesen Ausgabe-Textfeldern dann Anpassungen vorgenommen werden.
* Beim anschließenden klicken auf den "Preview"-Knopf wird eine Briefanreden (z.B. "Sehr geehrter Herr Prof. Hahn,") auf Basis der eingegebenen Daten erzeugt.
* Durch klicken auf den "Zurücksetzten"-Knopf lässt sich die Eingabe zurücksetzten.
* Durch klicken auf den "Bestätigen"-Knopf wird der eingegebene Kontakt in den lokalen Speicher geladen und kann bei Bedarf wieder geladen werden.

---
### 2. Laden eines Kontaktes

* Durch das klicken auf den "Laden"-Knopf erscheint ein weiteres Fenster, welches das laden von bereits gespeicherten Kontakten.
  
![image](https://github.com/Chessosaurus/SW_Quality_TTL/assets/52112815/16b44dca-9c2d-46c7-b688-26e2536c338f)
* Hier werden alle gespeicherten Kontakte aufgelistet und können durch das klicken auf den "laden"-Button in der zugehörigen Reihe in das Hauptfenster geladen werden.
* Wenn nun Änderungen an dem Kontakt in der Hauptansicht vorgenommen werden und diese dann durch klicken auf den "Bestätigen"-Knopf gespeichert werden, dann wird der geladene Kontakt mit den Änderungen überschrieben.

---

## Zusätzliche Features und Einschränkungen
### Zusätzliche Features
* Spracherkennung anhand von deutscher oder englischer Anrede.
* Briefanrede in Deutsch und Englisch verfügbar.
* Persistente Datenhaltung nach schließen der Applikation.
* Briefanrede wird in Englisch und Deutsch gespeichert.
* Nachträgliche Datenbearbeitung ist möglich.
* Umgang mit mehr als 2 Fachtiteln möglich.
* Mehr als 2 Vornamen möglich und werden durch Bindestrich verknüpft. Das letzte Wort wird als Nachname angesehen.

---
### Einschränkungnen
* Limitation von Nachnamen Verbindungen (z.B. Freiherr vom Wald) auf "vom".
* Anrede wird für die Preview beim manuellen Bearbeiten nicht beachtet, da für die Anrede nur das Geschlecht notwendig ist.
* Es werden für die Anrede nur Standard-Anreden beachtet (z.B. Herr, Frau, Mr., Mrs, etc.). Untypische Anreden wie beispielsweise „Hehe“ werden nicht beachtet  aber gespeichert.
* Ist das Geschlecht „divers“ wird in der Briefanrede nur „Guten Tag [Titel] [Nachname]“ ausgegeben.
