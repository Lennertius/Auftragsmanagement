# Auftragsmanagement
Teilnehmer: Jan Schröder, Festim Sokoli, Oliver Heis, Daryan Segler, Lennert Wollmann

Hinweis: Wenn eine Überschrift mit einem Namen versehen wird, so wurde der darauffolgende Absatz von genannte Person verfasst.

## [Oliver] Einleitung
Im Rahmen unseres Projektes haben wir uns dazu entschieden, eine Anwendung zur Unterstützung des Auftragsmanagements zu entwickeln. Die Grundidee hinter dem Projekt stammt aus einem anderen Modul, aus diesem Semester. Dabei soll das Auftragsmanagement einige Funktionen abdecken. Es soll möglich sein, sich einzuloggen und anhand verschiedener Rollen unterschiedliche Rechte zu erhalten. Somit kann ein Vertriebsmitarbeiter andere Funktionen nutzen als ein Administrator. Außerdem ist eine zentrale Idee des Projektes typische CRUD (Create, Read, Update, Delete) Aufgaben abzudecken. Dabei sollen unter anderem Artikel- und Kundendaten angelegt, gepflegt, gelöscht und aktualisiert werden können. Dieselbe Funktionsweise gilt für Aufträge und Auftragspositionen. Ein weiteres zentrales Feature, ist die Preisberechnung auf Basis der Menge bestellter Produkte, wobei auch Preisnachlässe wie Rabatte oder Skonti berücksichtigt werden. Bei der Auswahl der Technologie haben wir uns bewusst dagegen entschieden, Flutter und Dart zu verwenden, da einige Personen aus unserem Team bereits Erfahrungen mit dem in unserem Projekt verwendeten Java/Spring und Angular Framework haben. Somit erschien es uns leichte, die Fähigkeiten, die wir haben zu verwenden, anstatt neue Fähigkeiten von Grund auf zu erlernen. In unserem Projekt verwenden wir Java/Spring für das Backend, also um prinzipiell die Funktion des Projektes zu gewährleisten. Im Frontend verwenden wir Angular um das UI (User Interface) darzustellen. Als Versionskontrolle haben wir GitHub verwendet, in welchem wir Issues erstellt haben, um den Überblick darüber zu behalten, was innerhalb des Projektes zutun ist und um Ideen für beispielsweise Features festzuhalten. Zur besseren Organisation und effizienteren Umsetzung haben wir unser Team in zwei Gruppen aufgeteilt: Frontend und Backend. Zwei Teammitglieder arbeiteten am Backend, drei am Frontend. Diese Aufteilung erfolgte, weil wir eingeschätzt haben, dass die Arbeit am Frontend umfangreicher und zeitintensiver ist, bspw. durch UI-Design, Benutzerinteraktionen, Styling und die Abstimmung mit den Backend zur Datenanbindung. Gleichzeitig haben wir regelmäßig gemeinsam besprochen, wie Schnittstellen aussehen sollen, um die Integration reibungslos zu gestalten.

## [Oliver] Backend

Im Backend haben wir eine feste Struktur verwendet, um eine strukturierte Arbeitsweise zu gewährleisten. Dazu haben wir einzelne Klassen für Entitäten, Controller und Services erstellt, um diese voneinander zu kapseln.
Alle Service Klassen zu den jeweiligen Entitäten haben dabei dieselben CRUD-Methoden, welche in unserem Fall folgende Methoden umfassen: erstelle Entität mit Attributen, erhalte alle Entitäten dieses Typs, erhalte alle Entitäten anhand dieser ID, lösche eine Entität anhand der ID und aktualisiere eine Entität anhand ihrer ID. Der Controller einer Entität wird verwendet, um mit dem Frontend zu kommunizieren und ruft dabei im immer eine der oben genannten Service Methoden auf. Der Controller dient als Zwischenebene um nicht direkt auf den Service zuzugreifen, sondern eine Art Abstraktionsebene zu schaffen.

### [Oliver] Datenbank
Als Datenbank verwenden wir die H2-Datenbank. H2 ist eine in Java geschriebene relationale Datenbank, die besonders häufig in Entwicklungs- und Testumgebungen verwendet wird. Sie ist einfach zu konfigurieren und lässt sich einfach in Java Anwendungen integrieren. Wir verwenden H2 aus verschiedensten Gründen. Einer dieser Gründe ist, dass kein Setup notwendig ist und H2 mit Start der Anwendung läuft. Somit muss kein externer Datenbankserver installiert werden. Außerdem hat H2 eine Webkonsole, welche eine Oberfläche hat, mit welcher wir auch SQL-Abfragen gegen die Datenbank ausführen können, um bspw. Daten entgegen zu prüfen. Des Weiteren ist sie JPA (Java Persistance API) kompatibel, und lässt sich in Spring Boot leicht integrieren.

### [Oliver] Entitäten
Im Kontext unseres Projektes dienen Entitäten dazu, die zentralen Datenmodelle des Auftragsmanagements abzubilden. Sie sind in der Regel Java Klassen, welche durch die Annotation @Entity gekennzeichnet werden und werden direkt mit einer Datenbanktabelle verknüpft.
Innerhalb der Entitäten wurden Konstruktoren, sowie Getter und Setter Methoden durch Keywords wie „@Data“, „@AllArgsConstructor“ oder „@NoArgsConstructor“ ersetzt. Diese Annotationen stammen aus der „Lombok“ Bibliothek und ersetzen diese Methoden, um den Code übersichtlicher zu gestalten und dem Entwickler die Arbeit zu erleichtern, da er weniger Code schreiben muss und diese „Snippets“ verwenden kann.

#### [Oliver] Auftrag
Eine dieser Entitäten ist beispielsweise die Entität „Auftrag“, welche einen Auftrag abbilden soll, welcher aus einer ID, einem Namen, einer Beschreibung, einem Gesamtwert und einer Gesamtmenge besteht. Mit „Gesamtwert“ ist der summierte Wert aller Artikel innerhalb eines Auftrages gemeint, während „Gesamtmenge“ die gesamte Anzahl an Artikeln innerhalb eines Auftrages beschreibt. Jeder Auftrag hat außerdem eine „ManyToOne“ Beziehung zu einem Kunden, da ein Auftrag einem Kunden Objekt zugewiesen ist, aber ein Kunden Objekt mehrere Aufträge erstellen kann. Des Weiteren besitzt jeder Auftrag eine Auftragsposition, welche einem gewissen Auftrag einen beliebigen Artikel mit einer beliebigen Menge zuordnet, sodass wir innerhalb unseres Auftrages verschiedenste Artikel Objekte mit einer vom Nutzer festgelegten Menge haben. Somit dient Auftragsposition als Bindeglied zwischen Artikel und Auftrag, um Artikel einem oder mehreren Aufträgen zuzuordnen.
Die Entität „Auftrag“ enthält das Entwurfsmuster des Observers (Beobachter). Die zentrale Idee eines Observers ist es Änderungen an einem Objekt an von diesem Objekt abhängige Strukturen zu geben. In diesen Anwendungsfall wurde der Observer verwendet, um Veränderungen an einem Objekt in der Konsole auszugeben. Somit wird beispielsweise bei der Erzeugung eines Objektes in der Konsole ausgegeben, dass ein neues Objekt erzeugt wurde. Gleichzeitig gilt die Ausgabe in der Konsole auch für andere Veränderungen an einem Objekt wie beispielsweise das Löschen oder Aktualisieren eines Objektes. Somit wird der Observer in dem Anwendungsfall des Auftrags rein, als Logging Werkzeug verwendet, um Logs zur Erzeugung von Aufträgen zu erstellen. Das nächste im Auftrag verwendete Entwurfsmuster ist der Iterator. Dieses Muster ermöglicht es uns, sowohl strukturiert als auch kontrolliert durch eine Liste oder Sammlung von Elementen zu iterieren, unabhängig davon, wie die Datenstruktur genau aussieht. In unserem Fall wird der Iterator in der Klasse AuftragIterator verwendet. Der Iterator erlaubt es uns, eine Liste von Auftrag-Objekten schrittweisen zu durchlaufen, ohne direkt mit der Liste arbeiten zu müssen. Dadurch wird die Kapselung gestärkt und die Iteration ist durch die spezielle Klasse AuftragIterator klar und wiederverwendbar gestaltet. Der Vorteil des Iterators liegt darin, dass die Iteration von der Datenhaltung getrennt wird. Dadurch ist der Zugriff auf die Aufträge einfacher und lesbarer. Das letzte Entwurfsmuster im Auftrag ist der State. Das State Muster kapselt zustandsabhängiges Verhalten in eigene Klassen und ermöglicht dem Objekt (in unserem Fall der Auftrag), sein Verhalten abhängig von seinem Zustand zu ändern, ohne dass die Logik in vielen Kontrollstrukturen wie z.B. if/else oder switch versteckt ist. Bei unserer Umsetzung hat die Entität Auftrag ein Attribut auftragStatus vom Typ AuftragStatus, welches das aktuell Verhalten des Auftrags kapselt. AuftragStatus ist ein Interface, welches von mehreren Klassen implementiert wird: NeuState, InBearbeitungState und AbgeschlossenState. Diese Klassen implementieren jeweils das Verhalten für Methoden wie bearbeiten() oder abschliessen() abhängig vom Status. Dabei bietet State auch Vorteile wie Übersichtlichkeit und Erweiterbarkeit, da wir, anstatt viele Bedingungen zu prüfen, einfach das passende Objekt mit dem passenden Verhalten verwenden. Wenn wir bspw. einen neuen Status wie „Storniert“ hinzufügen, dann muss man nur eine neue Klasse erstellen, ohne den bestehenden Code zu verändern.

#### [Lennert] Benutzer
Der Benutzer in unserem Projekt stellt den Nutzer der Software dar, der die Aufträge, Kunden, Artikel und weitere Daten verwalten will. Er ist das zentrale Element unseres Systems, da über ihn alle administrativen Tätigkeiten erfolgen und die individuellen Berechtigungen gesetzt werden. Der Benutzer besitzt dabei einige grundlegende Attribute, die seiner eindeutigen Identifikation und Zuordnung dienen. So ist beispielsweise die BenutzerId einzigartig. Sie wird automatisch generiert und garantiert, dass jeder Benutzer im System individuell erfasst wird und einzigartig ist. Das heißt, dass selbst bei identischen Vor- und Nachnamen keine Verwechslungen auftreten können.
Neben der BenutzerId gehören zu den zentralen Attributen des Benutzers auch der Benutzername und das Passwort. Diese Informationen sind essenziell, um die Authentifizierung und den Zugriff auf die Anwendung zu steuern. Darüber hinaus gibt es optionale Attribute wie das Kürzel und die Abteilung. Diese zusätzlichen Daten ermöglichen eine genauere Kategorisierung und Zuordnung des Benutzers, was insbesondere in größeren Organisationen für die interne Strukturierung von Vorteil ist.
Ein wesentlicher Bestandteil meiner Implementierung des Benutzers ist die Verwendung des Builder-Patterns. Dank dieses Entwurfsmusters können die optionalen Attribute wie Kürzel und Abteilung nur dann gesetzt werden, wenn sie tatsächlich benötigt werden. Anstatt mehrere Konstruktoren in der Klasse zu schreiben, verwenden wir hier eine seperate Builder-Klasse. Diese Builder-Klasse bietet Methoden wie withBenutzername, withPasswort, withKuerzel und withAbteilung, die jeweils den Builder selbst zurückgeben. Dadurch können die Methoden verkettet aufgerufen werden, was den Code deutlich lesbarer und verständlicher macht. Das Builder-Pattern ermöglicht es somit, komplexe Objekte in mehreren Schritten zu erstellen, ohne dass dabei die Gefahr besteht, das Objekt fehlerhaft zu hinterlassen. Zudem trägt diese Vorgehensweise dazu bei, dass neue Attribute in Zukunft problemlos ergänzt werden können, ohne dass bestehende Konstruktoren angepasst werden müssen.
Über die reine Definition der Attribute hinaus ist der Benutzer auch integraler Bestandteil der Datenbankoperationen innerhalb unseres Projekts. Mithilfe von Spring Boot und Spring Data JPA wird die Benutzerentität in Operationen nach dem CRUD-Prinzip (Create, Read, Update, Delete) eingebunden. Das erlaubt eine dynamische Verwaltung der Benutzer, bei der sie erzeugt, abgerufen, aktualisiert und gelöscht werden können. Das kann dann z.B. ein Admin machen, ohne direkt auf die Datenbank zugreifen zu müssen, sondern über eine schöne Benutzeroberfläche.
Zudem wird der gesamte Prozess durch den Einsatz eines BenutzerControllers und eines entsprechenden BenutzerServices möglich gemacht. Der Controller stellt REST-Endpunkte zur Verfügung, über die etwa neue Benutzer erstellt oder bestehende Benutzer bearbeitet werden können. Der Service übernimmt dabei die eigentliche Geschäftslogik und sorgt in Abstimmung mit dem Repository dafür, dass die CRUD-Operationen reibungslos ausgeführt werden. Ein zusätzliches BenutzerDTO unterstützt als Datenübertragungsobjekt insbesondere die Validierung der Eingabedaten, sodass sichergestellt wird, dass beispielsweise der Benutzername und das Passwort nicht leer bleiben.
Zusammengefasst bietet die Implementierung des Benutzers in unserem Projekt eine robuste, flexible und erweiterbare Lösung. Dank eindeutiger Identifikation und dem Einsatz des Builder-Patterns gelingt es, die Komplexität zu reduzieren und den Code gleichzeitig zukunftssicher zu gestalten. Außerdem gewährleisten die integrierten CRUD-Operationen eine dynamische Benutzerverwaltung, die sowohl in Bezug auf Funktionalität als auch Wartbarkeit den Ansprüchen moderner Softwaresysteme gerecht wird.

#### [Lennert] Kunde
Der Kunde in unserem Projekt ist ein externer Geschäftspartner oder Endnutzer, der in unserer Software an Aufträge gebunden ist. Anders als der Benutzer, der für die Verwaltung und Bearbeitung von Aufträgen zuständig ist, ist der Kunde immer einem Auftrag zugewiesen, da er derjenige ist, für den wir (als Firma) die Aufträge bearbeiten und ausführen. Der Kunde ist von großer Bedeutung, da wichtig zu wissen ist, für wen wir einen Auftrag ausführen und was für ein Status der Kunde bei uns hat.
Das grundlegende Attribut des Kunden ist die KundenId. Diese eindeutige Identifikation wird automatisch generiert und stellt sicher, dass jeder Kunde im System individuell erfasst wird. Dadurch können auch Fälle mit gleichen Vor- oder Nachnamen problemlos auseinandergehalten werden, da jeder Datensatz über eine einzigartige ID verfügt. Diese klare Trennung ist insbesondere in Szenarien mit zahlreichen Kundeneinträgen von großer Bedeutung, da sie Verwechslungen verhindert und eine präzise Datenverwaltung ermöglicht.
Neben der KundenId enthält die Entität Kunde wichtige persönliche Informationen. So werden der Vorname und Nachname gespeichert, um dem Kunden ein erkennbares Profil zu verleihen. Weitere Attribute wie die Adresse, Telefonnummer und E-Mail-Adresse ermöglichen eine direkte Kontaktaufnahme und spielen eine zentrale Rolle in der Kommunikation und im Marketing. Ein kritischer Aspekt der Kundenverwaltung ist der Status. Dieser wird als String-Attribut geführt und initial auf „Interessent“ gesetzt. Der Status signalisiert, in welchem Stadium der Kundenbeziehung sich ein Kunde befindet – sei es als bloßer Interessent, als aktiver Kunde oder als langfristiger Geschäftspartner. Um diesen Status flexibel zu handhaben, wird er in der Entität dynamisch interpretiert und kann sich im Verlauf der Geschäftsbeziehung ändern. Mit Methoden wie zumKunden(), zumLangkunde() und zumInteressent() wird der Übergang zwischen den Zuständen systematisch gesteuert und ermöglicht so eine reibungslose Anpassung der Kundenbeziehung.
Die Integration des Kunden in das System erfolgt durch eine saubere Schichtung. Über das KundeRepository werden essenzielle Datenbankoperationen (CRUD – Create, Read, Update, Delete) realisiert. Dies erlaubt es, Kundendaten zu erstellen, auszulesen, zu aktualisieren und zu löschen, wodurch wir eine dynamische und aktuelle Verwaltung aller Kundeninformationen sichergestellt haben. Die Nutzung von Spring Data JPA automatisiert viele Standardfunktionen, sodass die Implementierung der Datenzugriffsschicht deutlich vereinfacht wird.
Die Schnittstelle stellt der KundeController bereit, der als REST-Controller alle erforderlichen Endpunkte zur Verfügung stellt. Dieser Controller ermöglicht den externen Zugriff auf Kundenfunktionen über HTTP-Methoden wie POST, GET, PUT und DELETE. Dabei sorgt der KundeService im Hintergrund für die Verarbeitung und Validierung der Kundendaten. Neben der reinen Datenmanipulation ermöglicht der Service auch komplexere Vorgänge, wie etwa die Aktualisierung zusammenhängender Prozesse oder die Kommunikation mit einem Benachrichtigungsservice, um auf wichtige Ereignisse hinzuweisen.
Ein weiterer wichtiger Baustein ist die KundeFactory, welche die Erzeugung neuer Kundenobjekte übernimmt. Durch diese Factory-Methode wird sichergestellt, dass alle wesentlichen Kundenattribute – darunter Vorname, Nachname, Adresse, Telefonnummer, E-Mail und Status – konsistent befüllt werden. Dies trägt entscheidend zur Einheitlichkeit und Stabilität der Kundendatensätze bei und ermöglicht eine einfache Erweiterung des Kunden im Laufe der Zeit.

#### [Oliver] Notiz
Eine weitere Entität in unserem Projekt ist die Notiz. Diese Entität folgt der Idee, dass beispielsweise auf einer Messe oder anderem Event beispielsweise ein Vertriebler einen potenziellen Kunden oder Interessenten kennenlernt und sich somit Notizen zu dieser Person macht. Um diese Notizen im Nachhinein nachvollziehbar zu machen, haben wir der Entität eine ID zur eindeutigen Identifizierung, einen Zeitstempel, um zu wissen, von wann die Notiz stammt und einen Text, um beispielsweise das Gespräch aufzuzeichnen als Attribut hinzuzufügt. Jede Notiz ist mit einem Kunden Objekt verbunden, wobei jeder Kunde eine beliebige Anzahl an Notizen zugewiesen werden kann. Somit könnte der Nutzer auch im Nachhinein weitere Notizen zu Gesprächen mit dem Kunden hinzufügen. Die Notiz enthält zwei Entwurfsmuster. Das erste Entwurfsmuster ist der Command (Kommando). In der Notiz wird der Command ähnlich wie der Observer im Auftrag verwendet, und zwar, um Logs zu erstellen. Dabei ist die Idee des Commands, dass jede Aktion, welche an der Notiz vorgenommen wird, wie z.B. das Erstellen oder Löschen einer Notiz, als Befehl gekapselt und ausgeführt wird. Dabei wird gleichzeitig ein entsprechender Log-Eintrag erzeugt. Dadurch wird jede Änderung nachvollziehbar dokumentiert, was die Nachvollziehbarkeit des Systems erhöht. Das zweite in der Notiz verwendete Entwurfsmuster ist die Factory (Fabrik). Das Ziel dieses Musters ist es, die Erstellung von Objekten zu kapseln und damit den Erstellungsprozess zu vereinfachen. In unserem Fall übernimmt die Notiz-Factory die Aufgabe der Erstellung von Notiz-Objekten. Somit werden Notiz-Objekte nicht durch den Aufruf eines Konstruktors erzeugt, sondern durch den Aufruf einer Methode.

#### [Oliver] Artikel
Die Artikel Entität repräsentiert einen Artikel, der einem Auftrag zugewiesen werden kann. Die Grundidee dahinter ist, dass ein Artikel ein Produkt oder eine Leistung darstellt, welche im Rahmen eines Auftrags, beispielsweise von einem Kunden benötigt wird. Ein Artikel besteht ausfolgenden Attributen: einer eindeutigen ID, einem Namen, einer Beschreibung und einem Preis. Ähnlich wie der Auftrag ist der Artikel mit der Entität Auftragsposition verbunden, sodass die Auftragsposition als Bindeglied fungieren kann. Innerhalb des Artikels wurde das Entwurfsmuster Builder (Erbauer) implementiert. Der Builder gewährleistet eine flexible und schrittweise Erstellung von Objekten. Der Builder ermöglicht es uns einen Artikel flexibel zu gestalten, sodass wir einzelne Teile des Artikels auslassen können, bspw. die Beschreibung eines Artikels. So lässt sich z.B. ein Artikel auch ohne Beschreibung erzeugen, wobei diese später ergänzt werden kann, oder bewusst weggelassen werden kann. Im Artikel wurde auch ein zweites Entwurfsmuster verwendet, und zwar das DTO (Data Transfer Object). Ein DTO dient dazu, Daten zwischen den einzelnen Schichten der Anwendung auszutauschen, ohne dabei Geschäftslogik zu enthalten. Im Gegensatz zu der Entität Artikel, welche möglicherweise Beziehungen und zusätzliche Logik enthält, stellt das ArtikelDTO nur die relevanten Felder bereit, welche für den Datenaustausch, z.B. mit dem Frontend benötigt werden. In unserem Fall verwenden wir den Namen, die Beschreibung und den Preis des Artikels. Zusätzlich verwendet das DTO Validierungsannotationen der Lombok Bibliothek (@NotBlank), um sicherzustellen, dass sowohl der Name, als auch der Preis nicht leer sind. Damit hilft das DTO auch dabei, zu validieren, bevor die Daten an die Geschäftslogik weitergegeben werden.

#### [Oliver] AuftragPositionen
Die Entität AuftragPositionen stellt eine Auftragsposition dar und dient als Verbindung zwischen einem Auftrag und einem oder mehreren Artikeln. In einem klassischen Auftragsmanagementsystem besteht ein Auftrag häufig aus mehreren Positionen, wobei jede Position für einen bestimmten Artikel mit einer bestimmten Menge steht.
Die AuftragPosition besteht auf folgenden Attributen: einer eindeutigen ID, einem Auftrag (Referenz auf den zugehörigen Auftrag), einem Artikel (Referenz auf den zugehörigen Artikel) und Menge, welche angibt, wie viele Einheiten eines Artikels in dieser Position enthalten sind. Diese Entität ist essenziell, um die Zuordnung von mehreren Artikeln zu einem Auftrag zu ermöglichen. Gleichzeitig erlaubt sie die Angabe der Menge je Artikel, was wichtig für die spätere Preisberechnung sowie für Rabatt und Skonto ist. 

### [Oliver] Unabhängige Entwurfsmuster
Ein weiteres in unserem Projekt eingesetztes Entwurfsmuster ist die Strategy. Dieses Muster ermöglicht es, verschiedene Verhaltensweisen austauschbar zu definieren, ohne die Klasse zu verändern, die sie verwendet. In unserem Fall verwenden wir das Strategy Muster zur Umsetzung verschiedener Rabattlogiken. Die Klasse MengenRabatt ist eine konkrete Implementierung der RabattStrategy Schnittstelle. Diese Strategy berechnet Rabatte basierend auf der Anzahl der Artikel, die in einem Auftrag enthalten sind, also ein Mengenrabatt. Bei weniger als 10 Artikeln wird kein Rabatt gewährt. Bei 10 bis 24 Artikeln wird ein Rabatt von 5% auf den Gesamtpreis angewandt. Bei 25 Artikeln oder mehr gibt es einen 10% Rabatt auf den Gesamtpreis. Die Verwendung der Strategy uns Flexibilität, da wir einfach neue Rabattstrategien für z.B. Stammkunden ergänzen können. Außerdem trennen wir die Rabattberechnung von der Hauptberechnungslogik, sodass zuerst der Gesamtwert eines Auftrags berechnet wird, und erst im Anschluss der Rabatt. Dadurch wird der Rabatt nicht fehlerhaft auf einzelne Artikel berechnet sondern auf den Gesamtwert. 

### [Oliver] Testing
Während der Entwicklung unserer Anwendung haben wir darauf geachtet, dass alle Funktionen korrekt arbeiten und ob die Schnittstellen erwartungsgemäß reagieren/antworten. Dafür haben wir kein automatisiertes Testing mit Unit- oder Intergrationstests verwendet, sondern haben die Anwendung manuell getestet. Im Backend haben wir dazu primär Insomnia verwendet, oder Extensions in der jeweiligen IDE. Mithilfe von Insomnia konnten wir HTTP-Anfragen gezielt an usnere REST Schnittstellen senden, um beispielsweise neue Artikel oder Aufträge zu erstellen, bestehende Einträge zu aktualisieren oder gezielt Fehler zu provozieren, um diese zu beheben. Dadurch konnten wir testen, wie das System auf fehlerhafte Eingaben reagiert. Auch komplexere Vorgänge wie die Berechnung von Rabatten oder das Setzen von Zuständen (z.B. „in Bearbeitung“, „abgeschlossen“) wurden so manuell überprüft. Dieses manuelle Testverfahren hat uns ermöglicht, Fehler frühzeitig zu erkennen und die Korrekte Funktionalität der wichtigsten Backend Features zu gewährleisten, auch ohne automatisierte Testumgebung.



## [Jan] Frontend & Navigationsleiste

Zu Beginn der Projektentwicklung wurde zunächst die Grundstruktur der Angular-Anwendung aufgebaut.
Angular ist ein modernes Frontend-Framework, das mit TypeScript arbeitet und sich hervorragend für komponentenbasierte Webanwendungen eignet. 
Es wurde gezielt Angular gewählt, da es eine stabile Struktur vorgibt und umfangreiche Werkzeuge für die Entwicklung größere Anwendungen mit mehreren Entwicklern bietet. 
Besonders hilfreich war dabei die klare Trennung von Darstellung und Logik.
Eine Angular-Anwendung besteht im Kern aus Modulen, Komponenten, Services und dem Routing-System.
Angular ermöglicht die Entwicklung von Single-Page-Anwendungen (SPA).
SPAs sind Webanwendungen, bei denen der gesamte Anwendungsbereich auf einer einzigen HTML-Seite geladen wird.
Auch unsere Anwendung sollte sich wie eine SPA verhalten, das bedeutet, dass beim Navigieren keine vollständigen Seiten neu geladen werden, was eine flüssige Benutzererfahrung schafft. 
Angular nutzt eine Komponentenbasierte Architektur. Das bedeutet, dass Webanwendungen in kleinere, wiederverwendbare Komponenten aufgeteilt werden, die jeweils eine bestimmte Funktion erfüllen.
Angular Komponenten sind die grundlegenden Bausteine jeder Angular Anwendung. Sie definieren die Benutzeroberfläche und kapseln logische Teile der Anwendung.
Sie bestehen aus einem TypeScript, HTML und CSS-Dokument.
Diese Dateien sind logisch über den Komponenten-Dekorator miteinander verknüpft. 
Angular sorgt dabei automatisch für die Verbindung zwischen Template und Logik über sogenanntes Data Binding.
TypeScript steuert das Verhalten der Komponente. Sie enthält Methoden, die Interaktionen des Benutzers verarbeiten oder zum Beispiel Daten abrufen.
Das HTML-Dokument definiert die Darstellung der Komponente. Hier werden HTML-Elemente und Angular-Templates verwendet, um die Benutzeroberfläche zu gestalten.
Die CSS-Datei bestimmt das Aussehen der Komponente, einschließlich Farben, Schriftart und Layout.
Die Navigationsleiste ist auch eine Komponente.
Sie beinhaltet Links zu den Hauptbereichen der Anwendung wie Artikel, Kunde und Aufträge. Durch die Verwendung von „routerLink“ bleibt das Routing nahtlos und ohne das neu Laden einer Seite möglich.


Das Routing in Angular ermöglicht die Navigation zwischen Komponenten 
auf Basis von URLs.
Damit wird ohne Seitenneuladen zwischen den Seiten gewechselt.


Die Navigationsleiste ist zentral für die Benutzerführung in unserem Projekt Auftragsmanagement und dient in ihrer Funktion den Benutzer durch die Seiten der Webanwendung zu Navigieren und zu steuern, dabei wird sie zu jeder Zeit permanent angezeigt, somit ist sie unabhängig von der geladenen Seite, sofern man Authentifiziert ist.


Die Navigationsleiste wurde als eigene Angular-Komponente aufgebaut.
Sie besteht aus einem HTML-Template mit mehreren Buttons, die jeweils per „routerlink“ zu anderen Komponenten führen. Dabei wurde bewusst auf das Platzieren innerhalb des „router-outlet“ verzichtet.


#### [Jan] Warum nicht im „router-outlet“?
Das <router-outlet>-Tag in Angular dient dem dynamischen Laden von Komponenten basierend auf der aktuellen Route. Wäre die Navigationsleiste dort eingebettet, würde sie bei jedem Seitenwechsel verschwinden und neu geladen werden. Das ist aus UX- und Perfomance-Gründen ungeeignet.
Stattdessen wurde die Navigationsleiste außerhalb des router-outlet im HTML-Template platziert. So bleibt sie permanent sichtbar, unabhängig davon, welche Komponente im router-outlet angezeigt wird.
So wird eine nutzerfreundliche Benutzeroberfläche zu jedem Zeitpunkt auf der Webanwendung gewährleistet.

### [Jan] Das Command-Pattern für die Navigation
Das Command Pattern ist ein Verhaltensmuster, das dazu dient, Befehle als eigenständige Objekte zu kapseln. Dadurch lassen sich Befehle flexibel verwalten, speichern oder rückgängig machen. In unserem Projekt kam es bei der Navigation zum Einsatz.
Durch das einsetzen des Command-Entwurfsmusters für die Navigation werden die Links zentral für alle Komponenten greifbar.
Statt direkt den Router in der Komponente aufzurufen, wurde eine separate Command-Klasse implementiert, die den Navigationsbefehl kapselt.
In der klassischen Struktur eines Command-Patterns gibt es den Befehl, einen Empfänger, der die Aktion ausführt und optional einen Aufrufer, der die Ausführung steuert.
Durch das abkapseln der Befehle kann jede Komponente zentral darauf zugreifen, weil eine Komponente nicht auf die Logik einer anderen zugreifen kann.
Diese Trennung fördert die Wiederverwendbarkeit und ermöglicht es, dieselbe Navigationslogik in verschiedenen Komponenten zu verwenden, zum Beispiel auch in einem Dialog oder einer Toolbar. Um dadurch bessere Nachvollziehbarkeit für den Code zu gewährleisten. 
Das Commands lassen sich leicht isoliert als direkte Methodenaufrufe innerhalb der Komponente testen.
So wird beispielsweise beim Klick auf den Button „Artikel“ nicht direkt der Router angesprochen, sondern es wird ein „ArtikelCommand“ erstellt, das intern weiß, zu welcher Route es gehört. In der Methode wird dem Befehl auf eine neue Seite zu wechseln in eine Variable geschrieben und mit einer Methode „execute()“ ausgeführt.

## [Jan] Artikel - Formular & Artikelliste
### [Jan] Grundidee
Die Artikelliste bildet das Herzstück der Produktverwaltung innerhalb unserer Anwendung.
Ziel war es, eine dynamische Übersicht zu entwickeln, die sowohl für Nutzer mit administrativen Rechten als auch für reguläre Benutzer zugänglich und intuitiv ist.
Um den Umgang mit Artikeldaten so benutzerfreundlich wie möglich zu gestalten, wurden zwei Hauptfunktionen „Sortierung“ & „Filterung“ integriert.
Dabei habe ich mich entschieden, diese beiden Funktionalitäten klar voneinander zu trennen, sowohl optisch im Interface als auch im Code.

### [Jan] Struktur
Um die Artikel vom Backend zu rufen und anzeigen zu können brauchen wir zwei Komponenten.
Eine Komponente für den Artikel selbst, er beschreibt wie ein Artikel aussieht und wird in der Artikelliste übernommen ohne ihn zu verändern: artikel.component.
Eine weitere Komponente für die Liste und das Formular was wir sehen.
In dieser Liste wird die Komponente „Artikel“ mehrfach angezeigt, nur mit anderen Werten, basierend auf der ID.
Das Formular wird parallel angezeigt, allerdings benötigt der Nutzer Berechtigungen um das Formular sehen zu können.


Die Komponente „Artikel“ besteht im HTML Bereich aus
dem Namen oder die Bezeichnung des Artikels.
Eine Beschreibung oder genauere Informationen über das Produkt.
Und ein Preis der bestimmt mit welchem Wert dieser Artikel zum Verkauf steht.


Die Komponente „Artikelliste“ besteht aus Formular und Liste.
Das Formular, zum erstellen eines Artikel, besitzt drei Eingabefelder und einen Button mit entsprechendem Methoden Aufruf.


Die Liste teilt sich in zwei Bereiche auf.
Es gibt ein Filter & Sortierbereich eingebettet im Kopf der Liste. 
Und die Liste selbst mit den verschiedenen Artikel aus dem Backend.

### [Jan] Funktion
#### [Jan] Liste
Die Sortierung erfolgt mithilfe des Strategy Patterns, einem klassischem Verhaltensmuster aus der Softwareentwicklung. 
Es erlaubt, das Verhalten eines Objekts zur Laufzeit zu ändern, indem es verschiedene Algorithmen austauschbar kapselt.


In unserer Anwendung haben wir folgende Sortierstrategien umgesetzt:
*	Name A-Z
*	Name Z-A
*	Preis aufsteigend
*	Preis absteigend
*	Neuste zuerst
*	Älteste zuerst
  
Alle Strategien implementieren eine gemeinsame Schnittstelle „ArtikelSortierStrategie“, sodass sie über eine zentrale Methode „sortiere()“ verwendet werden können. 
Ziel war es, die Erweiterbarkeit der Sortierlogik zu ermöglichen. Neue Sortierkriterien können einfach durch neue Klassen hinzugefügt werden, ohne bestehende Logik zu verändern.


Das Filtern in Artikelliste wurde ebenfalls mit dem Strategy-Pattern gebaut.
Hier ging es darum, gezielt nach bestimmten Eigenschaften zu filtern.
In unserem Beispiel haben wir eine Suchleiste der automatisch nach mithilfe eines Observer automatisch die Liste nach dem Namen sucht.
Zudem haben wir ein Filter der eine Preispanne eingrenzt.
So kann man wenn nötig alle Artikel mit einem Mindes oder Maximalem Preis herausfiltern.
Alle Filterstrategien implementieren das Interface „ArtikelFilterStrategy“, das eine „Filter()“-Methode bereitstellt.
Mehrere Filter lassen sich problemlos kombinieren, indem sie nacheinander auf die Artikelliste angewendet werden.
Die Filterung wird sofort ausgeführt, sobald der Nutzer einen Filterwert ändert. Dies sorgt für ein direktes Feedback und ein nutzerfreundliches Erlebnis.


Der Observer ist ein Muster und wurde verwendet, um Änderungen an Datenquellen effizient und in Echtzeit an die Benutzeroberfläche weiterzugeben.
Konkret wurde der Observer eingesetzt, um die Artikelliste nach dem Erstellen eines neuen Artikels, oder Filterwechsel automatisch zu aktualisieren, ohne dass der Benutzer manuell die Seite neu laden muss.
Vorteile bei dieser Anwendung ist die Entkopplung zwischen Datenquelle und UI-Komponente, Automatisches Reagieren auf Änderung, Konsistenz der Daten im Frontend und eine saubere, wartbare Architektur

#### [Jan] Formular
Das Formular erlaubt das Erstellen neuer Artikel.
Zugriff haben nur Nutzer mit entsprechender Berechtigung.
Das Formular arbeitet ebenfalls mit einem Entwurfsmuster.
Dem Factory Pattern.
Das Factory Pattern gehört zu den kreativen Entwurfsmustern. Es verfolgt das Ziel, die Instanziierung von Objekten auszulagern und zu zentralisieren. Dadurch wird der Konstruktor einer einzigen Stelle kontrolliert, anstatt ihn überall im Code zu duplizieren.
Die Factory kapselt die Logik, wie ein Objekt erstellt wird.
Sie kann dabei Standardwerte zurücksetzen und Validierungen durchführen.
Vorteile dieser Anwendung ist eine saubere Trennung von UI und Geschäftslogik.
Die Komponente übergibt nur die Werte, die Factory übernimmt die Verantwortung für die Objektbildung.
Ein weitere Vorteil ist die Einheitlichkeit und Wartbarkeit.
Sollte sich zum Beispiel das Artikelmodell ändern, muss dies nur in der Factory geändert werden.
Die Factory könnte in Zukunft erweitert werden, um Artikeltypen zu unterscheiden oder Validierungen durchzuführen, bevor ein Objekt erstellt wird.
Über die „ArtikelFactory“ wird sichergestellt, dass alle Artikel, die in der Anwendung erstellt werden, einheitlich aufgebaut sind. Die Factory übernimmt die Erstellung eines Objekts vom Typ „Artikel_ohne_ID“, das später an das backend gesendet wird und eine ID an dieses Objekt hinzufügt.
Dies trennt die Erstellung der Objekte von der Logik des Formulars und erhöht so die Wiederverwendbarkeit und Testbarkeit.

## [Jan] Kunde – Formular & Kundenliste

Die Komponente „Kunde“ enthält eine ähnliche Struktur wie Artikel.
Die Aufteilung von Formular und Liste ist identisch.
Allerdings wurde für die Liste keine Filter oder Sortierfunktion eingebunden.
Standard wird abhängig der ID aufsteigend sortiert. 
Die Komponente kann man zudem auch nur aufrufen sofern man administrative Rechte besitzt.
Trotz struktureller Ähnlichkeiten zu den Artikeln gab es einige besondere Aspekte, die den Unterschied ausmachen.


Das Formular zur Kundenerstellung verwendet wie beim Artikel das Factory Pattern, um neue Kundenobjekte zu erzeugen. Auch hier wird die UI-Komponente von der Objekterstellung getrennt. Der Unterschied liegt im Modell.
Während der Artikel numerische Daten wie Preis verarbeitet, liegt der Fokus beim Kunden auf personenbezogenen Informationen, wie Name, Adresse, Telefonnummer und E-Mail.
Die Kommunikation mit dem Backend sowie die Aktualisierung der Liste wurde auch hier mithilfe des Observer Patterns gelöst.
Sobald ein Kunde erfolgreich erstellt wurde, wird ein Event via Subject im „KundenService“ ausgelöst, auf das die Komponente hört und entsprechend die Kundenliste aktualisiert.


Im Gegensatz zur Artikelliste wurde beim Kundenbereich bewusst auf Sortierung und Filterung verzichtet.
Der Grund war die Annahme, dass Kundendaten meist statisch oder gezielt über IDs abgefragt werden, während Artikellisten oft nach Kriterien wie Preis oder Name sortiert werden müssen. Eine einfache Darstellung und sofortiger Überblick standen hier im Fokus. Sollte der Kundenbereich in Zukunft erweitert werden,  könnten jedoch ähnliche Strategien mithilfe des Strategy Patterns nachgerüstet werden.

## Auftrag

### [Lennert] Auftrag-Formular
In unserer Benutzeroberfläche bilde ich mit Angular ein übersichtliches und intuitives Formular, das es dem Anwender ermöglicht, neue Aufträge zu erstellen. Der Auftrag repräsentiert dabei die Zentrale Funktion unseres Projektes, die nicht nur den Zugriff auf relevante Daten wie Kundeninformationen und Artikel ermöglicht, sondern auch den Kunden, und die bestellten Artikel miteinander verknüpft.
Im Auftrag-Formular sehen wir zunächst den strukturierten Aufbau der Seite. Das HTML-Template ist wie folgt gegliedert: Mit Überschriften, Eingabefeldern für den Auftragsnamen, Kunden, Beschreibungen sowie dem Erstelldatum wird sichergestellt, dass alle nötigen Informationen erfasst werden. Besonders hervorzuheben ist die Integration eines Dropdown-Menüs, über das der Nutzer aus einer Liste von Kunden wählen kann. Diese Liste wird dynamisch aus dem Backend geladen. Hierbei unterstützt Angulars reaktive Forms die Verarbeitung der Formulardaten, was die Validierung und Synchronisierung der Eingaben vereinfacht.
Komplex wurde es, als ich mich fragte, wie ich dem Benutzer die Möglichkeit geben kann Artikel einem Auftrag hinzuzufügen. Ich habe das so gelöst, dass ich eine Liste aller Artikel anzeigen lasse, wo neben jedem Artikel ein Plus- und Minus-Button steht. Mit diesem lässt sich ein Artikel mehrmals in den Auftrag setzten. Diese dynamische Darstellung ermöglicht es, während der Auftragserstellung flexibel und interaktiv Artikel einem Auftrag hinzuzufügen und sie auch wieder zu entfernen. Die Darstellung der Artikel erfolgt mithilfe meiner selbst erstellten Direktive namens *myFor, welche dem schon vorhandenen *ngFor ähnelt, ich meine Direktive allerdings für meine Zwecke modifiziert habe. 
Ein wichtiges Element meines Formulars ist die Verwendung des Iterator-Entwurfsmusters. In meiner Implementierung des ArtikelIterator wird der Iterator genutzt, um die Liste der verfügbaren Artikel schrittweise zu durchlaufen. Das Muster basiert auf dem Prinzip, dass wir eine dedizierte Zähler-Variable haben, die kontrolliert, welche Elemente der Liste bereits besucht wurden. Konkret wird in der Methode iteriereArtikel() der Iterator zurückgesetzt und anschließend ermöglicht eine While-Schleife, alle Artikel nacheinander zu extrahieren und in eine separate Liste zu übertragen. Diese Herangehensweise ermöglicht eine saubere Trennung der Logik und erleichtert das Verwalten von dynamisch veränderten Daten. Durch das Iterator-Muster kann zudem eine zukünftige Erweiterung des Artikel-Managements erfolgen, ohne dass der Prozess der Auftragserstellung verändert werden muss.
Die Kommunikation zwischen Frontend und Backend wird durch einen Service gewährleistet, der die Erstellung eines neuen Auftrags ermöglicht. Mittels HTTP-Requests wird das so entstandene Auftrag-Objekt, bestehend aus den Eingaben des Formulars und den ausgewählten Artikeln, an den Server gesendet. Dabei wird über Methoden wie erstellen() sichergestellt, dass alle erforderlichen Parameter korrekt übertragen werden. Im Erfolgsfall wird eine Bestätigung ausgegeben, während im Fehlerfall entsprechende Fehlermeldungen zur weiteren Analyse ausgegeben werden.
Zudem wird der Anwender, nach erfolgreicher Auftragserstellung, automatisch weitergeleitet, was mit Hilfe eines Command-Musters realisiert wurde. Diese zusätzliche Benutzernavigation sorgt nicht nur für eine reibungslose User Experience, sondern zeigt auch, was für Vorteile es haben kann, wenn das Frontend modular aufgebaut ist.

### [Jan] Auftragsliste & Filterung 
Die „AuftragslisteComponent“ stellte eine der Zentralen Anwendungen dar.
Ziel war es, eine dynamische und benutzerfreundliche Auftragsübersicht zu entwickeln, die sowohl auf Nutzeraktionen reagiert als auch zentrale Entwurfsmuster sinnvoll integriert.

#### [Jan] Struktur
Die „AuftragslisteComponent“ wurde als eigenständige Komponente entwickelt und besteht rein aus der Listenansicht.
Das zugehörige Formular zum Anlegen oder Bearbeiten eines Auftrags liegt auf separaten Routen und wurde in „Auftrag-Detail“ und „Auftrag-Formular“ bearbeitet.
Die Auftragsliste ist somit allein für die visuelle Darstellung, Sortierung, Filterrung und Navigation zu den Detailansichten zuständig.

#### [Jan] Observer Pattern
Ein wesentlicher Bestandteil dieser Komponente ist die Nutzung des Observer Patterns. Dabei wird der Auftragsservice als Subject verwendet. Die „AuftragslisteComponent“ hört auf das Subject und aktualisiert bei jeder Datenänderung automatisch die Liste.
So wird sichergestellt, dass beispielsweise neu erstellte oder bearbeitete Aufträge sofort in der Übersicht reflektiert werden, ohne dass ein manuelles neu Laden nötig wäre. Diese reaktive Herangehensweise fördert eine flüssige Nutzererfahrung und reduziert unnötige Datenabfragen.

#### [Jan] Mediator Pattern
Ein zentrales Element war die Einführung des Mediator Patterns in Form eines dedizierten Services „AuftragslistenMediatorService“.
Dieses Entwurfsmuster eignet sich hervorragend, um komplexe Interaktionen zwischen mehreren Komponenten oder Strategien zu koordinieren, ohne dass diese direkt voreinander abhängen.
In unserem Fall koordiniert der Mediator zwei zentrale Bereiche.
Die Filterstrategie welche den Status des Auftrags Filtert, dabei haben wir drei unterschiedliche Status Bezeichner.
Neu, In Bearbeitung und Abgeschlossen können in der Liste angezeigt werden, allerdings werden Abgeschlossene Aufträge nicht angezeigt, sofern man dies nicht im Filter explizit fordert.
Aufträge werden zudem Automatisch vom Backend mit dem Status „Neu“ versehen.
Zudem haben wir eine weitere Filterung, die Aufträge abhängig des Kunden filtern lässt.
Hierbei wird automatisch mithilfe eines DropDown-Menüs alle Kunden Visuell dargestellt und auch automatisch Aktualisiert, sofern ein weitere Kunde erstellt wurde.
Diese beiden Aspekte wurden durch eigene Strategy-Klassen gekapselt und jewils über den Mediator gesetzt.
Der Mediator aktualisierte dann bei jeder Änderung automatisch die gefilterte und sortierte Datenliste, auf die die „AuftragslisteComponent“ abonniert war.
Dadurch wurde eine klare Trennung der Verantwortlichkeit erreicht.

#### [Jan] Strategy Pattern
Zur Umsetzung der Filter- und Sortierlogik wurde das Strategy Pattern verwendet. Dieses erlaubt es, zur Laufzeit zwischen verschiedenen Algorithmen auszutauschen, ohne den Code von der Komponente oder dem Mediator anpassen zu müssen.
Die Kombination dieser Muster ermöglicht eine skalierbare, flexible und wartbare Auftragsübersicht, die zukünftige Erweiterungen erleichert.

### [Lennert] Auftrag-Detail
Die Detail-Ansicht des Auftrags ist enorm wichtig, da so der Benutzer die genauen Details des Auftrags einsehen kann. Welche Artikel? Und wie viele von diesen? An welchen Kunden? Etc. Diese Komponente bietet dem Benutzer eine gut strukturierte Übersicht, in der sämtliche relevanten Informationen übersichtlich dargestellt und der Benutzer hat gleichzeitig die Möglichkeit den Auftrag zu bearbeiten. Vorausgesetzt, der Benutzer verfügt über die entsprechenden Berechtigungen.
Die Ansicht ist in zwei wesentliche Bereiche unterteilt. Auf der linken Seite befindet sich ein Formular, das in Form einer Tabelle organisiert ist. Hier werden die wichtigen Attribute des Auftrags angezeigt. Beispielsweise wird hier der Kunde aufgezeigt. Dessen Vor- und Nachname wird in einem Dropdown-Menu angezeigt. Die Darstellung erfolgt für den normalen Nutzer ausschließlich zu Informationszwecken, da das Dropdown-Feld mittels einer Abhängigkeit vom Benutzerstatus entweder schreibgeschützt oder editierbar ist. Der Benutzer, der üblicherweise als "User" klassifiziert wird, hat eingeschränkten Zugriff, sodass wichtige Änderungen nur von Benutzern mit höheren Berechtigungen durchgeführt werden können. Wenn man nun eine erhöhte Berechtigung hat, kann man sehen, dass man das Dropdown-Menu auch öffnen kann, darin stehen dann sämtliche Kunden, die in der Datenbank der Kunden hinterlegt sind.
Zusätzlich beinhaltet das Formular ein Textfeld, in dem die Beschreibung des Auftrags hinterlegt ist. Auch hier wird über die Nutzer-Rollen sichergestellt, dass unerlaubte Änderungen unterbunden werden. Ein weiteres Auswahlfeld zeigt den Status des Auftrags, der typischerweise zwischen "Neu", "In Bearbeitung" oder "Abgeschlossen" wechselt. Auch hier gilt: Wenn der Benutzer nicht über die nötigen Rechte verfügt, bleibt das Feld deaktiviert. Die Schaltflächen "Speichern" und "Delete" sind gleichermaßen an diesen Sicherheitsmechanismus gekoppelt und werden entweder nicht angezeigt oder sind inaktiv. Diese Maßnahmen sorgen zusammen für eine klare Trennung zwischen Ansichts- und Bearbeitungsmodus und tragen so zu einem verbesserten Sicherheitskonzept bei, da nur Benutzer mit benötigten Berechtigungen in der Lage sind den Auftrag zu bearbeiten.
Der rechte Teil der Detailansicht widmet sich der Auflistung der zugehörigen Artikel. Diese werden in einem einfachen, aber intuitiven Layout dargestellt. Jeder Eintrag zeigt den Namen, die Beschreibung, den Preis und die gekaufte Menge an. Alle Artikel werden in Echtzeit aus dem entsprechenden Auftrag abgerufen. So kann der Benutzer sehen, welche Artikel in dem Entsprechenden Auftrag sind und wie oft.
Die zugrundeliegende Logik der Komponente steckt in der TypeScript-Datei. Beim Initialisieren der Detailansicht wird über den ActivatedRoute-Dienst die ID des gewünschten Auftrags aus der URL extrahiert. Diese ID wird genutzt, um den entsprechenden Auftrag via des AuftragService asynchron zu laden. Sobald die Daten verfügbar sind, wird der Auftrag in der Komponente hinterlegt und gleichzeitig werden die enthaltenen Artikelpositionen extrahiert und in eine separate Liste übertragen. Diese Operation stellt sicher, dass selbst wenn keine Positionen vorhanden sind, der Entwickler frühzeitig gewarnt wird, was zu einer besseren Wartbarkeit und Fehlersuche beiträgt.
Ein weiterer Punkt ist die Möglichkeit, den Auftrag zu bearbeiten. Über die Schaltfläche „Speichern“ kann der Benutzer seine Änderungen übernehmen, wenn die benötigten Berechtigungen vorliegen. Der dazugehörige Button ruft die Methode save() auf, in der zukünftig die Aktualisierungen weiterverarbeitet werden können. Für den Fall, dass ein Auftrag gelöscht werden soll, sorgt die Methode delete_auftrag() dafür, dass der Auftrag über den entsprechenden Service gelöscht wird. Nach erfolgreicher Löschung wird ein spezieller Command (NavigateToAuftragCommand) ausgeführt, der den Benutzer automatisch zu einer anderen Ansicht der Aufträge navigiert. Dieses Command-Pattern sorgt für eine lose Kopplung zwischen den verschiedenen Modulen der Anwendung und erleichtert die Wartung und Erweiterung des Systems. Außerdem trägt das zu einer besseren Nutzererfahrung bei, da der Nutzer nicht auf einer Detail-Seite bleibt, zu dem der Auftrag bereits gelöscht wurde.

### [Festim] Backend-Logik für Auftragserstellung und Export
Ein zentraler Bestandteil unserer Anwendung ist das serverseitige Management von Aufträgen. Dieser Teil hat die Aufgabe, nicht nur neue Aufträge in der Datenbank zu speichern, sondern auch automatisch eine strukturierte Auftragsbestätigung mit vollständiger Kostenübersicht zu erzeugen. Die Implementierung erfolgt in Java unter Verwendung des Spring Frameworks. Im Zentrum steht dabei die Klasse „AuftragService”, welche sämtliche Geschäftslogik rund um die Erstellung, Änderung und Verarbeitung von Aufträgen kapselt.
Beim Anlegen eines Auftrags wird in der Methode „createAuftrag” zunächst geprüft, ob alle Artikelreferenzen gültig sind. Über das ArtikelRepository werden die zugewiesenen Artikel geladen und anschließend dem Auftrag zugewiesen. Danach wird der Auftrag gespeichert. Anschließend beginnt die Vorbereitung des Exports: Zunächst werden die Kundendaten aus dem Auftrag extrahiert, d. h. Name, E-Mail-Adresse und Adresse. Diese Informationen werden in einem kundenDaten-Objekt zwischengespeichert, das ausschließlich dem Export dient.
Für die Artikeldaten wird eine neue Liste vom Typ „artikelDaten” angelegt. Jeder Eintrag dieser Liste enthält die Bezeichnung des Artikels, eine Menge (in unserem Fall 1) sowie den Einzelpreis. Zusätzlich berechnet die Methode „getGesamtpreis” den Gesamtwert pro Artikelposition. Anschließend werden die Kundendaten und die Artikelinformationen in einem auftragDaten-Objekt zusammengeführt. Dieses enthält schließlich alle relevanten Informationen für den Export – inklusive Auftragsnummer, Kundenzuordnung und vollständiger Preisübersicht.
Das entstandene Auftrag-Daten-Objekt wird an den Export-Fassade-Dienst übergeben, der die eigentliche Exportlogik verwaltet. Dort wird intern auf eine Singleton-Instanz des Exportdienstes zugegriffen, die die aktuell konfigurierte Exportstrategie ausführt. Standardmäßig wird die Text-Exportstrategie verwendet, die den Auftrag in eine formatierte Textdatei umwandelt. Dabei werden der Auftragstitel, die Kundendaten sowie eine strukturierte Tabelle aller Artikelpositionen exportiert. Die Datei enthält außerdem die berechnete Zwischensumme, den Steueranteil (19 %) sowie den Gesamtbetrag, der dem Kunden in der endgültigen Auftragsbestätigung angezeigt wird.
Die Textdatei wird mit einem Dateinamen, der sich aus der Auftragsnummer zusammensetzt, gespeichert. Der Speicherort liegt aktuell im lokalen Projektverzeichnis. Der Export erfolgt über die Java-NIO-Klassen Path und Files. Falls beim Schreiben ein Fehler auftritt, wird dieser durch ein Fehler-Handling mit Try-Catch behandelt.
Neben dem Erstellen von Aufträgen stellt AuftragService auch Methoden bereit, um bestehende Aufträge zu lesen („getAuftrag“), nach ID abzurufen („getAuftragById“), zu löschen („deleteAuftrag“) oder zu aktualisieren („updateAuftrag“). Letztere prüft zusätzlich, ob sich der Auftragsstatus ändert. Falls ja, wird anhand des neuen Status (z. B. „neu”, „in_bearbeitung” oder „abgeschlossen”) eine entsprechende Statusmethode aufgerufen.
Zusätzlich kommuniziert der Server über das Spring-Event-System. Nach jeder Aktion wird ein AuftragEvent erzeugt, das z. B. andere Services über die Erstellung oder Änderung eines Auftrags informiert. So wird sichergestellt, dass die Oberfläche bei Änderungen in Echtzeit reagieren kann, beispielsweise durch ein automatisches Update der Auftragsliste im Frontend.
Das Backend bildet somit die Schnittstelle zwischen den Formularen und Listenansichten im Frontend und der tatsächlichen Datenverarbeitung auf Serverseite. Es speichert nicht nur alle Auftragsdaten, sondern generiert gleichzeitig ein Dokument, das dem Kunden die vollständige Übersicht über die Bestellung liefert.
