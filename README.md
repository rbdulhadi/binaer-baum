# AVL-Baum Traversierung

## Projektbeschreibung

Dieses Java-Programm implementiert einen interaktiven AVL-Baum mit verschiedenen Traversierungsmethoden und automatischer Ausbalancierung. Die Zahlen werden frei eingegeben, der Baum garantiert die AVL-Eigenschaften (balanciert, geringe Tiefe) und stellt Rotationen dabei automatisch sicher.

## Features & Hauptfunktionen

- **Benutzerfreundliche, menügesteuerte Konsole**: Mehrere Traversierungen pro Baum möglich, jederzeit neue Eingabe oder Beenden
- **AVL-Einfügen mit automatischem Balancing** (alle Rotationen)
- **Textbasierte Baumdarstellung** (strukturiert, hierarchisch)
- **Vier Traversierungsarten**:
    - `preorder` (Root, Links, Rechts)
    - `inorder` (Links, Root, Rechts)
    - `postorder` (Links, Rechts, Root)
    - `levelorder` (Level für Level; BFS)
- **AVL-Prüfung & Balancefaktoren-Ausgabe**
- **Validierung der Eingabe und Fehlermeldungen**

## Verwendung & Ablauf
1. **Programmstart**
2. **Menügesteuerter Ablauf:**
    - Zahlenfolge (durch Komma getrennt) eingeben, z.B. `8,4,9,7,2,13,11,46`
    - Der AVL-Baum wird erstellt und in schöner ASCII-Form angezeigt
    - Menü zur Auswahl der Traversierung:
        1. preorder
        2. inorder
        3. postorder
        4. levelorder (Breitensuche / BFS)
        5. neuer Baum (andere Eingabe)
        6. beenden
    - Nach jeder Traversierung: weitere Auswahl oder neues Zahlen-Set möglich
    - Ausgabe und AVL-Prüfung erfolgen fortlaufend

### Beispiel-Interaktion
```
=== AVL-Baum Traversierung ===
Geben Sie eine Reihe von Ganzzahlen ein (durch Komma getrennt):
8,4,9,7,2,13,11,46

=== Baumdarstellung ===
        8                
    /       \            
    4        11        
  /   \      /   \      
  2    7    9    13    
 / \   / \   / \   / \   
                      46  

... (Menü folgt)
```

## Was ist BFS (Levelorder)?
Die Option `levelorder` entspricht der Breitensuche (Breadth-First Search): Es werden alle Knoten Ebene für Ebene ausgegeben, von oben nach unten und links nach rechts. Dies geschieht mit einer Queue im Programm.

## Fehlerbehandlung
- **Zahlenvalidierung**: duplizierte Ganzzahlen werden ignoriert
- **Leere Elemente oder Fehlerhafte Eingabe**: Warnung & erneute Eingabe
- **Ungültige Menüwahl**: Deutliche Fehlermeldung, keine Beendigung
- **Beenden & neue Eingabe jederzeit möglich**

## Algorithmus-Komplexität

- **Einfügen & Suche:** O(log n) (AVL-Baum bleibt immer balanciert)
- **Rotationen:** O(1)
- **Traversierungen:** Pre/In/Post: O(n) Zeit, O(log n) Platz, Levelorder (BFS): O(n) Zeit und Platz
- **Balance-Prüfung:** Immer O(1)
- Analog zur Theorie, siehe Tabelle weiter unten

## Technische Details
- **Sprache:** Java
- **Build:** Maven (Java-Version im pom.xml)
- **Hauptklassen:**
  - `Main` (nur Einstieg!)
  - `AVLTreeProgram` (gesamte Logik)
  - `AVLTree` (AVL-Logik)
  - `TreeVisualizer` (ASCII-Ausgabe)
  - `Traversal` (alle Traversierungsarten)

## Testfälle

### Testfall 1: Beispiel
- **Eingabe**: `8, 4, 9, 7, 2, 13, 11, 46`
- **Erwartung**: Baum wird korrekt erstellt, alle AVL-Bedingungen erfüllt

### Testfall 2: Sortierte Eingabe
- **Eingabe**: `1, 2, 3, 4, 5`
- **Erwartung**: Baum bleibt ausbalanciert durch Rotationen

### Testfall 3: Duplikate
- **Eingabe**: `5,5,5`
- **Erwartung**: Nur ein Knoten mit Wert 5, Rest ignoriert

### Testfall 4: Neue Eingabe
- **Ablauf**: Nach einer Traversierung Menü-Option "neuer Baum" wählen, neue Zahlen eingeben

### Testfall 5: BFS/Levelorder
- **Traversal**: "levelorder" → Ausgabe Ebene für Ebene von links nach rechts (Breitensuche)

## Komplexitätsübersicht
| Operation                      | Best Case          | Worst Case         |
|--------------------------------|-------------------|--------------------|
| **Einfügen**                   | O(log n), O(log n)| O(log n), O(log n) |
| **Rotation**                   | O(1), O(1)        | O(1), O(1)         |
| **Traversierung (pre/in/post)**| O(n), O(log n)    | O(n), O(log n)     |
| **Levelorder (BFS)**           | O(n), O(n)        | O(n), O(n)         |

**Hinweis:** Der AVL-Baum bleibt, im Gegensatz zu einem normalen Binary Search Tree, immer balanciert!

