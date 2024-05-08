# User Stories

### User Story 1: Anredeerkennung und Geschlechtszuweisung
Als CRM-Nutzer
möchte ich, dass das System die Anrede einer Person automatisch erkennt und daraus das Geschlecht ableitet,
damit ich korrekte Ansprachen in Kommunikationen nutzen kann.

#### Akzeptanzkriterien:
* Wenn eine Anrede wie „Herr“, „Frau“ eingegeben wird, soll das System das Geschlecht entsprechend zuweisen.
* Das System bietet ein manuelles Auswahlmenü für Geschlecht, wenn die automatische Zuordnung unsicher ist.
* Die Anrede und das Geschlecht werden in separaten Feldern in der Datenbank gespeichert.


### User Story 2: Namenserfassung und -zuordnung
Als CRM-Nutzer
möchte ich, dass das System vollständige Namen in ihre Bestandteile (Vorname, Nachname, Titel) zerlegt,
damit die Daten korrekt und strukturiert gespeichert werden können.

##### Akzeptanzkriterien:
* Das System trennt automatisch Vornamen und Nachnamen auf Basis der Eingabe.
* Bei einer Eingabe, die nur ein Wort enthält, wird dieses als Nachname behandelt.
* Titel und präfixierte oder suffigierte Namensbestandteile werden korrekt erkannt und dem Nachnamen zugeordnet.


### User Story 3: Briefanrede Generierung
Als CRM-Nutzer
möchte ich, dass das System eine standardisierte Briefanrede basierend auf der erkannten Anrede und dem Titel generiert,
damit ich formelle Schreiben und Emails angemessen formulieren kann.

#### Akzeptanzkriterien:
* Das System generiert eine Briefanrede wie „Sehr geehrter Herr Dr. Müller“.
* Nutzer können die Briefanrede manuell anpassen und ergänzen.
* Die generierte Briefanrede wird automatisch im Kontaktdatensatz gespeichert.


### User Story 4: Manuelle Korrektur und Lernfunktion
Als CRM-Nutzer
möchte ich, die Möglichkeit haben, Eingaben manuell zu korrigieren
damit die Daten korrekt im System angelegt werden können.

#### Akzeptanzkriterien:
* Nutzer können jederzeit manuelle Korrekturen an Anrede, Namen und Titeln vornehmen.
* Das System speichert manuelle Korrekturen, nachdem die Korrektur bestätigt wurde.


### User Story 5: Doppelnamen-Erkennung und -Formatierung
Als CRM-Nutzer
möchte ich, dass das System Doppelnamen automatisch erkennt und formatiert,
damit die Namensfelder korrekt strukturiert sind.

#### Akzeptanzkriterien:
* Das System erkennt und verbindet zweiteilige Nachnamen automatisch mit einem Bindestrich.
* Es erkennt spezifische Präfixe und Suffixe wie „von“, „de“, „van de“ und ordnet sie dem Nachnamen korrekt zu.
* Das System bietet die Option zur manuellen Korrektur, falls die automatische Erkennung fehlerhaft ist.