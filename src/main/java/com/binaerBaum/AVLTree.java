package com.binaerBaum;

/** AVL Tree implementation with insertion, balancing, and visualization. */
public class AVLTree {
  private AVLNode treeRoot;

  public AVLTree() {
    this.treeRoot = null;
  }

  public AVLNode getRoot() {
    return treeRoot;
  }

  public void setRoot(AVLNode newRoot) {
    this.treeRoot = newRoot;
  }

  /**
   * Inserts a value into the AVL tree.
   *
   * @param insertValue the value to insert
   * @return true if inserted, false if duplicate
   */
  public boolean insert(int insertValue) {
    if (treeRoot == null) {
      treeRoot = new AVLNode(insertValue);
      System.out.println("Einfügen: " + insertValue + " (Wurzel)");
      return true;
    }

    boolean inserted = insertHelper(treeRoot, insertValue);
    if (inserted) {
      treeRoot = balanceNode(treeRoot);
    }
    return inserted;
  }

  private boolean insertHelper(AVLNode currentNode, int insertValue) {
    if (insertValue == currentNode.value) {
      System.out.println(
          "Einfügen: " + insertValue + " - Zahl bereits vorhanden, wird übersprungen");
      return false;
    }

    boolean insertionPerformed = false;
    if (insertValue < currentNode.value) {
      if (currentNode.left == null) {
        currentNode.left = new AVLNode(insertValue);
        insertionPerformed = true;
        System.out.println("Einfügen: " + insertValue + " (links von " + currentNode.value + ")");
      } else {
        insertionPerformed = insertHelper(currentNode.left, insertValue);
        currentNode.left = balanceNode(currentNode.left);
      }
    } else {
      if (currentNode.right == null) {
        currentNode.right = new AVLNode(insertValue);
        insertionPerformed = true;
        System.out.println("Einfügen: " + insertValue + " (rechts von " + currentNode.value + ")");
      } else {
        insertionPerformed = insertHelper(currentNode.right, insertValue);
        currentNode.right = balanceNode(currentNode.right);
      }
    }

    if (insertionPerformed) {
      currentNode.updateHeight();
    }

    return insertionPerformed;
  }

  /**
   * Balances a node if necessary and returns the balanced node.
   *
   * @param unbalancedNode the node to balance
   * @return the balanced node (may be different from input)
   */
  private AVLNode balanceNode(AVLNode unbalancedNode) {
    if (unbalancedNode == null) {
      return null;
    }

    unbalancedNode.updateHeight();
    int balanceFactor = unbalancedNode.getBalanceFactor();

    // Print AVL check
    System.out.println(
        "AVL-Prüfung für Knoten " + unbalancedNode.value + ": Balance-Faktor = " + balanceFactor);

    // Left heavy
    if (balanceFactor > 1) {
      int leftChildBalance = unbalancedNode.left.getBalanceFactor();
      if (leftChildBalance >= 0) {
        // Left-Left case: Right rotation
        System.out.println("  → Rechts-Rotation erforderlich (Links-Links-Fall)");
        return rotateRight(unbalancedNode);
      } else {
        // Left-Right case: Left-Right rotation
        System.out.println("  → Links-Rechts-Rotation erforderlich (Links-Rechts-Fall)");
        unbalancedNode.left = rotateLeft(unbalancedNode.left);
        return rotateRight(unbalancedNode);
      }
    }
    // Right heavy
    else if (balanceFactor < -1) {
      int rightChildBalance = unbalancedNode.right.getBalanceFactor();
      if (rightChildBalance <= 0) {
        // Right-Right case: Left rotation
        System.out.println("  → Links-Rotation erforderlich (Rechts-Rechts-Fall)");
        return rotateLeft(unbalancedNode);
      } else {
        // Right-Left case: Right-Left rotation
        System.out.println("  → Rechts-Links-Rotation erforderlich (Rechts-Links-Fall)");
        unbalancedNode.right = rotateRight(unbalancedNode.right);
        return rotateLeft(unbalancedNode);
      }
    } else {
      System.out.println("  → Balance OK (keine Rotation erforderlich)");
    }

    return unbalancedNode;
  }

  /** Performs a right rotation. */
  private AVLNode rotateRight(AVLNode parentNode) {
    AVLNode leftChild = parentNode.left;
    AVLNode transferSubtree = leftChild.right;

    leftChild.right = parentNode;
    parentNode.left = transferSubtree;

    parentNode.updateHeight();
    leftChild.updateHeight();

    System.out.println("    Rotation durchgeführt: Rechts-Rotation um " + parentNode.value);
    return leftChild;
  }

  /** Performs a left rotation. */
  private AVLNode rotateLeft(AVLNode parentNode) {
    AVLNode rightChild = parentNode.right;
    AVLNode transferSubtree = rightChild.left;

    rightChild.left = parentNode;
    parentNode.right = transferSubtree;

    parentNode.updateHeight();
    rightChild.updateHeight();

    System.out.println("    Rotation durchgeführt: Links-Rotation um " + parentNode.value);
    return rightChild;
  }

  /** Prints the tree in a hierarchical structure. */
  public void printTree() {

    System.out.println("\nBaumstruktur:");
    printTreeRecursive(treeRoot, "", true);
  }

  private void printTreeRecursive(AVLNode printNode, String prefix, boolean isLast) {
    if (printNode == null) {
      return;
    }

    System.out.print(prefix);
    System.out.print(isLast ? "└── " : "├── ");
    System.out.println(
        printNode.value
            + " (Höhe: "
            + printNode.height
            + ", Balance: "
            + printNode.getBalanceFactor()
            + ")");

    if (printNode.left != null || printNode.right != null) {
      String childPrefix = prefix + (isLast ? "    " : "│   ");
      if (printNode.right != null) {
        printTreeRecursive(printNode.right, childPrefix, printNode.left == null);
      }
      if (printNode.left != null) {
        printTreeRecursive(printNode.left, childPrefix, true);
      }
    }
  }

  /**
   * Tree visualization in the format specified in requirements. Example output: 8 / \ 4 9 / \ \ 2 7
   * 13 / \ 11 46
   */
  public void printTreeFormatted() {

    System.out.println("\nBaumdarstellung:");
    TreeVisualizer.printTree(treeRoot);
  }
}
