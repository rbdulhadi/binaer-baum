package com.binaerBaum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main program logic for AVL Tree traversal application.
 */
public class AVLTreeProgram {
    private Scanner userInputScanner;

    public AVLTreeProgram(Scanner userInputScanner) {
        this.userInputScanner = userInputScanner;
    }

    public void run() {
        System.out.println("=== AVL-Baum Traversierung ===");
        
        boolean sessionActive = true;
        int[] treeInputValues = null;
        AVLTree avlTree = null;

        while (sessionActive) {
            // Get or re-enter numbers
            if (treeInputValues == null || avlTree == null) {
                treeInputValues = getNumbersInput(userInputScanner);

                System.out.println("\n=== Baum erstellen ===");
                avlTree = new AVLTree();
                for (int valueToInsert : treeInputValues) {
                    avlTree.insert(valueToInsert);
                    System.out.println(); // Empty line for readability
                }

                // Print detailed tree structure for debugging
                System.out.println("\n=== Debug: Detaillierte Baumstruktur ===");
                avlTree.printTree();

                // Print tree structure
                avlTree.printTreeFormatted();
            }

            // Main loop: Allow multiple traversals or exit
            boolean continueWithSameTree = true;
            while (continueWithSameTree) {
                // Get traversal order with error handling
                String traversalType = getTraversalOrderInput(userInputScanner);

                // Check if user wants to exit or enter new numbers
                if (traversalType == null) {
                    sessionActive = false;
                    continueWithSameTree = false;
                    break;
                } else if ("NEW_TREE".equals(traversalType)) {
                    treeInputValues = null;
                    avlTree = null;
                    continueWithSameTree = false;
                    break;
                }

                // Perform traversal
                int[] traversalResult;
                try {
                    traversalResult = Traversal.traverse(treeInputValues, traversalType);
                    
                    // Print result
                    System.out.println("\nTraversierungsergebnis (" + traversalType + "):");
                    System.out.print("[");
                    for (int idx = 0; idx < traversalResult.length; idx++) {
                        System.out.print(traversalResult[idx]);
                        if (idx < traversalResult.length - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                } catch (IllegalArgumentException error) {
                    System.err.println("Fehler: " + error.getMessage());
                }

                // Ask if user wants to continue or exit
                System.out.println(); // Empty line for readability
            }
        }

        System.out.println("\nProgramm wird beendet. Auf Wiedersehen!");
    }

    /**
     * Gets and validates numbers input from user.
     * @param scanner Scanner instance
     * @return array of valid integers
     */
    private int[] getNumbersInput(Scanner scannerInput) {
        while (true) {
            System.out.println("Geben Sie eine Reihe von Ganzzahlen ein (durch Komma getrennt):");
            String userInputLine = scannerInput.nextLine().trim();

            // Check for empty input
            if (userInputLine.isEmpty()) {
                System.err.println("Fehler: Die Eingabe darf nicht leer sein. Bitte versuchen Sie es erneut.\n");
                continue;
            }

            // Parse input numbers
            String[] numberStrings = userInputLine.split(",");
            List<Integer> parsedNumbers = new ArrayList<>();
            boolean foundParseError = false;

            for (int splitIdx = 0; splitIdx < numberStrings.length; splitIdx++) {
                String trimmedCandidate = numberStrings[splitIdx].trim();
                
                // Skip empty strings
                if (trimmedCandidate.isEmpty()) {
                    System.err.println("Warnung: Leeres Element wird übersprungen.");
                    continue;
                }

                try {
                    int currentParsedNumber = Integer.parseInt(trimmedCandidate);
                    parsedNumbers.add(currentParsedNumber);
                } catch (NumberFormatException parseErr) {
                    System.err.println("Fehler: '" + trimmedCandidate + "' ist keine gültige Ganzzahl. Bitte versuchen Sie es erneut.\n");
                    foundParseError = true;
                    break;
                }
            }

            if (!foundParseError) {
                if (parsedNumbers.isEmpty()) {
                    System.err.println("Fehler: Keine gültigen Zahlen gefunden. Bitte versuchen Sie es erneut.\n");
                    continue;
                }
                return parsedNumbers.stream().mapToInt(Integer::intValue).toArray();
            }
        }
    }

    /**
     * Gets and validates traversal order input from user.
     * @param scanner Scanner instance
     * @return validated traversal order string, null if user wants to exit, or "NEW_TREE" if user wants new input
     */
    private String getTraversalOrderInput(Scanner scannerForTraversalType) {
        final String[] validTraversalTypes = {"preorder", "inorder", "postorder", "levelorder"};
        
        while (true) {
            System.out.println("\n=== Traversierung ===");
            System.out.println("Wählen Sie eine Option:");
            System.out.println("  1. preorder");
            System.out.println("  2. inorder");
            System.out.println("  3. postorder");
            System.out.println("  4. levelorder");
            System.out.println("  5. neuer Baum (neue Werte eingeben)");
            System.out.println("  6. beenden");
            System.out.print("Eingabe (1-6 oder Name der Traversierungsart): ");
            
            String userChoice = scannerForTraversalType.nextLine().trim().toLowerCase();

            // Check for empty input
            if (userChoice.isEmpty()) {
                System.err.println("Fehler: Die Eingabe darf nicht leer sein. Bitte versuchen Sie es erneut.\n");
                continue;
            }

            // Check if user wants to exit (option 6 or "beenden"/"exit")
            if (userChoice.equals("6") || userChoice.equals("beenden") || userChoice.equals("exit") || userChoice.equals("quit")) {
                return null;
            }

            // Check if user wants new tree (option 5)
            if (userChoice.equals("5") || userChoice.equals("neuer baum") || userChoice.equals("neue eingabe") || userChoice.equals("new")) {
                return "NEW_TREE";
            }

            // Check if input is a number (1-4)
            if (userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3") || userChoice.equals("4")) {
                int selectedIdx = Integer.parseInt(userChoice);
                return validTraversalTypes[selectedIdx - 1]; // Return corresponding traversal order
            }

            // Check if order is valid (case-sensitive check per requirements)
            boolean matchesValidType = false;
            String formattedTraversalType = userChoice;
            for (String type : validTraversalTypes) {
                if (formattedTraversalType.equals(type.toLowerCase())) {
                    matchesValidType = true;
                    formattedTraversalType = type; // Use the correct case
                    break;
                }
            }

            if (matchesValidType) {
                return formattedTraversalType;
            } else {
                System.err.println("Fehler: Ungültige Eingabe '" + userChoice + "'. ");
                System.err.println("Erlaubte Werte sind: 1-6, preorder, inorder, postorder, levelorder, neuer Baum, oder beenden");
                System.err.println("Bitte versuchen Sie es erneut.\n");
            }
        }
    }
}

