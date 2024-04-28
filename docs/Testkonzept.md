# Testkonzept
Das Testkonzept soll dem Entwickler einen Leitfaden an die Hand geben, seine entwickelte Software anhand definierter Qualitätskriterien zu testen. Eine Abweichung vom Testkonzept ist nur in Ausnahmefällen erlaubt und muss mit dem kompletten Team besprochen und akzeptiert werden. Sollte dieser Fall eintreten, müssen die Gründe dafür dokumentiert werden sowie eine Risikoabschätzung untermalt werden.

## Unit-Testing
Unit-Tests werden dazu verwendet, im Backend aufgerufene Methoden hinsichtlich ihrer Funktionalität zu testen. Die Testfälle sind hier zu finden.
Die nachfolgenden Testfälle werden anhand von Unit-Tests abgeprüft.

#### Abzuprüfende Testfälle - Unit-Tests

## UI-Testing
Aufgrund der Größe des Projekts wird auf das automatisierte Testen der UI mit Tools verzichtet. Stattdessen werden manuelle UI-Tests als ausreichend betrachtet. Deshalb wird die UI durch manuelles Testen hinsichtlich ihrer Funktionalität geprüft. Zusätzlich kann die korrekte Ausgabe der eingegebenen Namen geprüft werden. Die folgenden Funktionalitäten bzw. Erscheinungen werden abgeprüft :
#### Abzuprüfende Testfälle - UI


## Integration Tests
Auf Integration Tests wird an dieser Stelle verzichtet, da die Anwendung aus Sicht des Entwicklerteams nicht ausreichend komplex ist. Daher würde das Erstellen eines Integration Tests Zeit kosten, ohne einen nennenswerten Mehrwert darzustellen.

## Testdaten
Folgende Testdaten wurden verwendet. Die erwartete Splittung wird unter dem Namen dargestellt

> Anmerkung:
>
> Zusammengeschriebene Titel (z.B. Dr.-Ing.) werden als ein Titel erkannt. Sind Titel durch Leerzeichen getrennt (z.B. Dr. Ing.) werden die Titel einzeln erfasst. Das hat den Hintergrund, dass die generierte Anrede nur einen Titel beinhaltet (gewöhnlich den Ranghöchsten)[^1].
>
> [^1]: [Anrede bei Mehrfachtiteln](https://www.sekada.de/korrespondenz/anrede-und-anschriften/artikel/korrespondenz-anrede-von-doktoren-und-professoren/)
1. Frau Sandra Berger
    * Vorname: Sandra
    * Nachname: Berger
    * Geschlecht: Frau
    * Titel: -
    * Briefanrede: Sehr geehrte Frau Berger,
2. Herr Dr. Sandro Gutmensch
    * Vorname: Sandro
    * Nachname: Gutmensch
    * Geschlecht: Mann
    * Titel: Dr.
    * Briefanrede: Sehr geehrter Herr Dr. Gutmensch,

3. Professor Heinrich Freiherr vom Wald
    * Vorname: Heinrich
    * Nachname: Freiherr vom Wald
    * Geschlecht: Divers
    * Titel: Professor
    * Briefanrede: Guten Tag Prof. Freiherr vom Wald,
4. Mrs. Doreen Faber
    * Vorname: Doreen
    * Nachname: Faber
    * Geschlecht: Frau
    * Titel: -
    * Briefanrede: Dear Mrs Faber,
5. Mme. Charlotte Noir
    * Vorname: Charlotte
    * Nachname: Noir
    * Geschlecht: Frau
    * Titel: -
    * Briefanrede: Dear Mrs Noir,
6. Estobar y Gonzales
    * Vorname: Estobar
    * Nachname: y Gonzales
    * Geschlecht: Divers
    * Titel: -
    * Briefanrede: Guten Tag Estobar y Gonzales
7. Frau Prof. Dr. rer. nat. Maria von Leuthäuser-Schnarrenberger
    * Vorname: Maria
    * Nachname: von Leuthäuser-Schnarrenberger
    * Geschlecht: Frau
    * Titel: Prof. Dr. rer. nat.
    * Briefanrede: Sehr geehrte Frau Prof. von Leuthäuser-Schnarrenberger
8. Herr Dipl.-Ing. Max von Müller
    * Vorname: Max
    * Nachname: von Müller
    * Geschlecht: Mann
    * Titel: Dipl.-Ing.
    * Briefanrede: Sehr geehrter Herr Dipl.-Ing. von Müller,
9. Dr. Russwurm, Winfried
    * Vorname: Winfried
    * Nachname: Russwurm
    * Geschlecht: Divers
    * Titel: Dr.
    * Briefanrede:Guten Tag Dr. Russwurm
10. Herr Dr.-Ing. Dr. rer. nat. Dr. h.c. mult. Paul Steffens
    * Vorname: Paul
    * Nachname: Steffens
    * Geschlecht:
    * Titel:Dr. Ing. Dr. nat. Dr. h.c.
    * Briefanrede: Sehr geehrter Herr Dr. Steffens,