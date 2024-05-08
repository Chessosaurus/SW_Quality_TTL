# Code Conventions
Um die Codebasis einheitlich zu gestalten, werden Code Conventions festgelegt. Diese dienen den Entwicklern als Leitfaden für die Erstellung von Code. Durch das Einhalten der Code Conventions werden folgende Ziele erreicht:
* Geringere Einarbeitungszeit in zu reviewenden Code eines anderen Teammitglieds
* Reduzierung von Fehlerquellen
* höhere Produktivität beim Erstellen

## Github
Als Versionsverwaltungssystem wird Github verwendet. Dafür wird ein Repository erstellt, zu dem alle Gruppenmitglieder Zugang haben.
Folgende Vereinbahrungen wurden getroffen:
* Der `master`-Branch wird als default-branch festlegt.
* Bei Bedarf können weitere Feature-Branches erstellt werden
* In den `master`-Branch wird nur lauffähiger Code gemergt, der von min. 1 weiteren Person einem Review unterzogen wurde
* zusätzlich erstellte Feature-Branches werden nicht direkt in den `master`-Branch gemergt, sondern in einen der betreffenden 3 Feature-Branches

## Clean Code
Für das Schreiben von Code werden folgende Vereinbahrungen getroffen:
* Sprache im Code: Englisch (commit-messages dürfen auch auf deutsch sein)
* Packages sollen Klassen anhand ihrer Funktionalität klassifizieren
* Code sollte so selbsterklärend wie möglich sein, um so wenig Kommentare wie möglich verwenden zu müssen
* Methodennamen sollen (wo möglich) in folgendem Stil gewählt werden:
    * Verb, das die Aktion der Methode beschreibt
    * Objekt, auf dem die Aktion durchgeführt wird

Beispiel:
``
private void changeLastName()``
## Java
Da die Anwendung in Java geschrieben wird, wird sich zusätzlich noch an die Java Code Conventions gehalten.
Diese können über folgenden Link abgerufen werden: https://www.oracle.com/technetwork/java/codeconventions-150003.pdf
## Umgang mit Bugs
Sollten Bugs entdeckt werden, ist folgender Prozess zu durchlaufen:
1. Bekanntgabe des Bugs über die gruppeninterne Whatsappgruppe oder Teamskanal (da schnellere Reaktionszeit)
* Screenshot des Bugs
* Beschreiben der Auswirkung
* Bekanntgabe, auf welchem Branch der Bug ist
2. Versuch der Reproduzierung des Bugs durch die anderen Teammitglieder
3. Suche des Bugs (falls Ursprung nicht direkt ersichtlich: im Pair Programming)
4. Fixing des Bugs mit anschließendem Review
