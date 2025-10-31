# AVL-Baum Traversierung

## Projektbeschreibung

Dieses Java-Programm implementiert einen AVL-Baum mit verschiedenen Traversierungsmethoden. Der Baum wird auf Basis einer eingegebenen Zahlenreihe erstellt, wobei die AVL-Baum-Eigenschaften automatisch durch Rotationen gewährleistet werden.

## Funktionen

- **Einfügen**: Zahlen werden entsprechend der AVL-Regeln in den Baum eingefügt
- **Automatisches Balancing**: Rotationen werden bei Bedarf durchgeführt
- **AVL-Prüfung**: Balance-Faktoren werden für jeden Knoten berechnet und ausgegeben
- **Baumdarstellung**: Textbasierte visuelle Darstellung des Baums
- **Traversierung**: Vier verschiedene Traversierungsarten (preorder, inorder, postorder, levelorder)

## Verwendung

1. Programm ausführen
2. Zahlenreihe eingeben (z.B. `8, 4, 9, 7, 2, 13, 11, 46`)
3. Traversierungsart wählen (`preorder`, `inorder`, `postorder`, oder `levelorder`)

## Algorithmus-Komplexität

### Best Case

#### Einfügen (Insertion)
- **Zeitkomplexität**: O(log n)
- **Platzkomplexität**: O(log n) (rekursiver Stack)
- **Beschreibung**: Im besten Fall ist der Baum bereits ausbalanciert, und wir müssen nur den Pfad von der Wurzel zu einem Blatt durchlaufen. Da ein AVL-Baum immer ausbalanciert ist, ist die Höhe immer O(log n), wobei n die Anzahl der Knoten ist.

#### Rotation
- **Zeitkomplexität**: O(1)
- **Platzkomplexität**: O(1)
- **Beschreibung**: Eine Rotation ist eine konstante Operation, die nur die Zeiger neu setzt, ohne den gesamten Baum zu durchlaufen.

#### Traversierung
- **Preorder/Inorder/Postorder**:
  - **Zeitkomplexität**: O(n)
  - **Platzkomplexität**: O(log n) (rekursiver Stack)
  - **Beschreibung**: Jeder Knoten wird genau einmal besucht. Die Rekursionstiefe entspricht der Höhe des Baums (O(log n)).
  
- **Levelorder**:
  - **Zeitkomplexität**: O(n)
  - **Platzkomplexität**: O(n) (Queue kann alle Knoten einer Ebene enthalten)
  - **Beschreibung**: Jeder Knoten wird genau einmal besucht. Im schlimmsten Fall kann die Queue alle Knoten der letzten Ebene enthalten (bis zu n/2 Knoten).

#### Balance-Prüfung
- **Zeitkomplexität**: O(1)
- **Platzkomplexität**: O(1)
- **Beschreibung**: Die Berechnung des Balance-Faktors ist eine konstante Operation (Subtraktion der Höhen der Kinder).

### Worst Case

#### Einfügen (Insertion)
- **Zeitkomplexität**: O(log n)
- **Platzkomplexität**: O(log n) (rekursiver Stack)
- **Beschreibung**: Auch im schlimmsten Fall bleibt die Komplexität O(log n), da ein AVL-Baum durch die automatischen Rotationen immer ausbalanciert bleibt. Selbst wenn wir eine bereits sortierte Sequenz einfügen (was bei einem normalen BST O(n) wäre), bleibt es bei O(log n) dank der Rotationen.

#### Rotation
- **Zeitkomplexität**: O(1)
- **Platzkomplexität**: O(1)
- **Beschreibung**: Eine Rotation bleibt immer eine konstante Operation, unabhängig von der Baumgröße.

#### Traversierung
- **Preorder/Inorder/Postorder**:
  - **Zeitkomplexität**: O(n)
  - **Platzkomplexität**: O(log n) (rekursiver Stack)
  - **Beschreibung**: Auch im schlimmsten Fall werden alle n Knoten besucht. Die Rekursionstiefe bleibt O(log n), da der AVL-Baum ausbalanciert ist.
  
- **Levelorder**:
  - **Zeitkomplexität**: O(n)
  - **Platzkomplexität**: O(n)
  - **Beschreibung**: Im schlimmsten Fall enthält die Queue alle Knoten der breitesten Ebene. Bei einem vollständig ausbalancierten Baum kann dies bis zu n/2 Knoten sein, daher O(n) für den Platz.

#### Balance-Prüfung
- **Zeitkomplexität**: O(1)
- **Platzkomplexität**: O(1)
- **Beschreibung**: Die Balance-Prüfung bleibt immer konstant.

### Zusammenfassung

| Operation | Best Case | Worst Case |
|-----------|-----------|------------|
| **Einfügen** | O(log n) Zeit, O(log n) Platz | O(log n) Zeit, O(log n) Platz |
| **Rotation** | O(1) Zeit, O(1) Platz | O(1) Zeit, O(1) Platz |
| **Traversierung (Pre/In/Post)** | O(n) Zeit, O(log n) Platz | O(n) Zeit, O(log n) Platz |
| **Traversierung (Levelorder)** | O(n) Zeit, O(n) Platz | O(n) Zeit, O(n) Platz |
| **Balance-Prüfung** | O(1) Zeit, O(1) Platz | O(1) Zeit, O(1) Platz |

**Wichtig**: Die AVL-Baum-Eigenschaft gewährleistet, dass die Höhe des Baums immer O(log n) bleibt, was auch im schlimmsten Fall zu einer logarithmischen Komplexität für Einfüge- und Suchoperationen führt. Dies ist ein großer Vorteil gegenüber normalen Binary Search Trees, die im schlimmsten Fall degenerieren können (z.B. bei sortierten Eingaben) und dann O(n) Komplexität haben.

## Technische Details

- **Programmiersprache**: Java
- **Build-Tool**: Maven
- **Java Version**: 24 (konfiguriert in pom.xml)

## Testfälle

### Testfall 1: Beispiel aus Aufgabenstellung
- **Eingabe**: `8, 4, 9, 7, 2, 13, 11, 46`
- **Erwartung**: Baum wird korrekt erstellt, alle AVL-Bedingungen erfüllt

### Testfall 2: Sortierte Eingabe
- **Eingabe**: `1, 2, 3, 4, 5`
- **Erwartung**: Baum bleibt ausbalanciert durch Rotationen

### Testfall 3: Duplikate
- **Eingabe**: `5, 5, 5`
- **Erwartung**: Duplikate werden ignoriert (nur ein Knoten mit Wert 5)

### Testfall 4: Leere Eingabe
- **Eingabe**: `` (leer)
- **Erwartung**: Leeres Array wird zurückgegeben

### Testfall 5: Verschiedene Traversierungen
- **Eingabe**: `8, 4, 9, 7, 2, 13, 11, 46`
- **Traversierungen**: Alle vier Arten testen
- **Erwartung**: Korrekte Reihenfolge für jede Traversierungsart

