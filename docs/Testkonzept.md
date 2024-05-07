# Testkonzept
Das Testkonzept soll dem Entwickler einen Leitfaden an die Hand geben, seine entwickelte Software anhand definierter Qualitätskriterien zu testen. Eine Abweichung vom Testkonzept ist nur in Ausnahmefällen erlaubt und muss mit dem kompletten Team besprochen und akzeptiert werden. Sollte dieser Fall eintreten, müssen die Gründe dafür dokumentiert werden sowie eine Risikoabschätzung untermalt werden.

## Testziele
##### Korrektheit: 
Sicherstellen, dass alle Funktionen korrekt Namen, Vornamen, Nachnamen und Titel aus einem String-Array extrahieren.

#### Robustheit: 
Die Methoden sollten auch bei ungewöhnlichen oder unerwarteten Eingaben zuverlässig funktionieren.

## Unit-Testing
Unit-Tests werden dazu verwendet, im Backend aufgerufene Methoden hinsichtlich ihrer Funktionalität zu testen. Die Testfälle sind hier zu finden.
Die nachfolgenden Testfälle werden anhand von Unit-Tests abgeprüft.

#### Abzuprüfende Testfälle - Unit-Tests
* Eingabe von Doppel-Nachnamen mit Bindestrich (Beispiel: Maier-Müller) wird als Nachname erkannt, ohne Bindestrich (Beispiel: Maier Müller) wird der erste Nachname als Vorname erkannt.
> Der spanische Sprachraum wird nicht unterstützt, dementsprechend entfällt die Betrachtung der hispanischen Eigenart, mehrere, nicht syntaktisch verbundene Nachnamen zu haben
* Eingabe von Zahlen und Sonderzeichen (ausgenommen: Punkt (.), Bindestrich (-) und Komma(,)) führen nicht zur Verarbeitung der Eingabe, sondern setzen den Benutzer über die Verwendung nicht unterstützter Sonderzeichen in Kenntnis
* Wird nur ein Wort eingegeben, wird dieses als Nachname erkannt


## UI-Testing
Aufgrund der Größe des Projekts wird auf das automatisierte Testen der UI mit Tools verzichtet. Stattdessen werden manuelle UI-Tests als ausreichend betrachtet. Deshalb wird die UI durch manuelles Testen hinsichtlich ihrer Funktionalität geprüft. Zusätzlich kann die korrekte Ausgabe der eingegebenen Namen geprüft werden. Die folgenden Funktionalitäten bzw. Erscheinungen werden abgeprüft :
#### Abzuprüfende Testfälle - UI
* Eingabe Taste splittet den eingegebenen Namen in seine Bestandteile
* Taste Laden öffnet zweite Sicht und es werden Datenbank einträge angezeigt
  * Datensätze können geladen und werden in den richtigen Feldern angezeigt
* Vorschau der Briefanrede kann durch das Betätigen der Preview Taste angezeigt werden
* Speichern der Datensätze in der Datenbank mit der Taste "" funktioniert.
* 
## Integration Tests
Auf Integration Tests wird an dieser Stelle verzichtet, da die Anwendung aus Sicht des Entwicklerteams nicht ausreichend komplex ist. Daher würde das Erstellen eines Integration Tests Zeit kosten, ohne einen nennenswerten Mehrwert darzustellen.

## Testdaten
Die Testdaten umfassen verschiedene Beispiele von Namen mit unterschiedlichen Formaten und Längen, um die Robustheit der Funktion zu gewährleisten. Zusätzlich werden spezifische Testdaten bereitgestellt, um die Abdeckung der Anforderungen sicherzustellen.

Folgende Testdaten wurden verwendet. Die erwartete Splittung wird unter dem Namen dargestellt

> Anmerkung:
>
> Zusammengeschriebene Titel (z.B. Dr.-Ing.) werden als ein Titel erkannt. Sind Titel durch Leerzeichen getrennt (z.B. Dr. Ing.) werden die Titel einzeln erfasst. Das hat den Hintergrund, dass die generierte Anrede nur einen Titel beinhaltet (gewöhnlich den Ranghöchsten)[^1].
>
> [^1]: [Anrede bei Mehrfachtiteln](https://www.sekada.de/korrespondenz/anrede-und-anschriften/artikel/korrespondenz-anrede-von-doktoren-und-professoren/)
1. Frau Sandra Berger
    * Anrede: Frau
    * Vorname: Sandra
    * Nachname: Berger
    * Geschlecht: weiblich
    * Titel: -
    * Anredesprache: Deutsch
    * Briefanrede: Sehr geehrte Frau Berger,
2. Herr Dr. Sandro Gutmensch
    * Anrede: Herr
    * Vorname: Sandro
    * Nachname: Gutmensch
    * Geschlecht: männlich
    * Titel: Dr.
    * Anredesprache: Deutsch
    * Briefanrede: Sehr geehrter Herr Dr. Gutmensch,

3. Professor Heinrich Freiherr vom Wald
    * Anrede: -
    * Vorname: Heinrich
    * Nachname: Freiherr vom Wald
    * Geschlecht: divers
    * Titel: Professor
    * Anredesprache: Deutsch
    * Briefanrede: Guten Tag Prof. Freiherr vom Wald,
4. Mrs. Doreen Faber
    * Anrede: Mrs.
    * Vorname: Doreen
    * Nachname: Faber
    * Geschlecht: weiblich
    * Titel: -
    * Anredesprache: Englisch
    * Briefanrede: Dear Mrs Faber,
5. Mme. Charlotte Noir
    * Anrede: Mme.
    * Vorname: Charlotte
    * Nachname: Noir
    * Geschlecht: weiblich
    * Titel: -
    * Anredesprache: Englisch
    * Briefanrede: Dear Mrs Noir,
6. Frau Prof. Dr. rer. nat. Maria von Leuthäuser-Schnarrenberger
    * Anrede: Frau
    * Vorname: Maria
    * Nachname: von Leuthäuser-Schnarrenberger
    * Geschlecht: weiblich
    * Anredesprache: Deutsch
    * Titel: Prof. Dr. rer. nat.
    * Briefanrede: Sehr geehrte Frau Prof. von Leuthäuser-Schnarrenberger
7. Herr Dipl.-Ing. Max von Müller
    * Anrede: Herr
    * Vorname: Max
    * Nachname: von Müller
    * Geschlecht: männlich
    * Titel: Dipl.-Ing.
    * Anredesprache: Deutsch
    * Briefanrede: Sehr geehrter Herr Dipl.-Ing. von Müller,
8. Dr. Russwurm, Winfried
    * Anrede: -
    * Vorname: Winfried
    * Nachname: Russwurm
    * Geschlecht: divers
    * Titel: Dr.
    * Anredesprache: Deutsch
    * Briefanrede: Guten Tag Dr. Russwurm
9. Herr Dr.-Ing. Dr. rer. nat. Dr. h.c. mult. Paul Steffens
    * Anrede: Herr
    * Vorname: Paul
    * Nachname: Steffens
    * Geschlecht: männlich
    * Titel:Dr. Ing. Dr. nat. Dr. h.c.
    * Anredesprache: Deutsch
    * Briefanrede: Sehr geehrter Herr Dr. Steffens,
## Testdurchführung
Die Tests werden von qualifizierten Testern durchgeführt, die mit den technischen Spezifikationen und den fachlichen Anforderungen vertraut sind.
Tests werden immer vor jedem Produktupdate durchgeführt.