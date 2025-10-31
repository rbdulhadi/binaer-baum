package com.binaerBaum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** Traversal class for AVL tree with different traversal methods. */
public class Traversal {

  /**
   * Traverses an AVL tree built from the given numbers according to the specified traversal method.
   * This method builds the tree silently (without extra console output) for use in the traversal
   * method.
   *
   * @param treeBuilderNumbers array of integers to build the tree from
   * @param traversalMethod traversal order: "preorder", "inorder", "postorder", or "levelorder"
   * @return array of integers in the traversal order
   */
  public static int[] traverse(int[] treeBuilderNumbers, String traversalMethod) {
    if (treeBuilderNumbers == null || treeBuilderNumbers.length == 0) {
      return new int[0];
    }

    // Build AVL tree from numbers (without explained output)
    AVLTree silentTree = buildTreeSilently(treeBuilderNumbers);

    // Perform traversal based on method
    List<Integer> traversalResultList = new ArrayList<>();
    AVLNode silentRoot = silentTree.getRoot();

    switch (traversalMethod.toLowerCase()) {
      case "preorder":
        preorderTraversal(silentRoot, traversalResultList);
        break;
      case "inorder":
        inorderTraversal(silentRoot, traversalResultList);
        break;
      case "postorder":
        postorderTraversal(silentRoot, traversalResultList);
        break;
      case "levelorder":
        levelOrderTraversal(silentRoot, traversalResultList);
        break;
      default:
        throw new IllegalArgumentException(
            "Unbekannte Traversierungsart: "
                + traversalMethod
                + ". Erlaubt: preorder, inorder, postorder, levelorder");
    }

    // Convert List to int array
    return traversalResultList.stream().mapToInt(Integer::intValue).toArray();
  }

  /** Builds an AVL tree from numbers without console output. */
  private static AVLTree buildTreeSilently(int[] inputNumbers) {
    AVLTree silentAvlTree = new AVLTree();
    for (int insertValue : inputNumbers) {
      insertSilently(silentAvlTree, insertValue);
    }
    return silentAvlTree;
  }

  /** Inserts a value silently into the tree. */
  private static void insertSilently(AVLTree avlTreeTarget, int insertVal) {
    AVLNode inputRoot = avlTreeTarget.getRoot();
    inputRoot = insertNodeSilently(inputRoot, insertVal);
    avlTreeTarget.setRoot(inputRoot);
  }

  private static AVLNode insertNodeSilently(AVLNode currentSilentNode, int silentValue) {
    if (currentSilentNode == null) {
      return new AVLNode(silentValue);
    }

    if (silentValue < currentSilentNode.value) {
      currentSilentNode.left = insertNodeSilently(currentSilentNode.left, silentValue);
    } else if (silentValue > currentSilentNode.value) {
      currentSilentNode.right = insertNodeSilently(currentSilentNode.right, silentValue);
    } else {
      // Duplicate, ignore
      return currentSilentNode;
    }

    currentSilentNode.updateHeight();
    return balanceSilently(currentSilentNode);
  }

  private static AVLNode balanceSilently(AVLNode possiblyUnbalanced) {
    if (possiblyUnbalanced == null) {
      return null;
    }

    possiblyUnbalanced.updateHeight();
    int balanceFactor = possiblyUnbalanced.getBalanceFactor();

    // Left heavy
    if (balanceFactor > 1) {
      if (possiblyUnbalanced.left.getBalanceFactor() >= 0) {
        return rotateRight(possiblyUnbalanced);
      } else {
        possiblyUnbalanced.left = rotateLeft(possiblyUnbalanced.left);
        return rotateRight(possiblyUnbalanced);
      }
    }
    // Right heavy
    else if (balanceFactor < -1) {
      if (possiblyUnbalanced.right.getBalanceFactor() <= 0) {
        return rotateLeft(possiblyUnbalanced);
      } else {
        possiblyUnbalanced.right = rotateRight(possiblyUnbalanced.right);
        return rotateLeft(possiblyUnbalanced);
      }
    }

    return possiblyUnbalanced;
  }

  private static AVLNode rotateRight(AVLNode parentNode) {
    AVLNode leftChild = parentNode.left;
    AVLNode movedSubtree = leftChild.right;

    leftChild.right = parentNode;
    parentNode.left = movedSubtree;

    parentNode.updateHeight();
    leftChild.updateHeight();

    return leftChild;
  }

  private static AVLNode rotateLeft(AVLNode parentNode) {
    AVLNode rightChild = parentNode.right;
    AVLNode movedSubtree = rightChild.left;

    rightChild.left = parentNode;
    parentNode.right = movedSubtree;

    parentNode.updateHeight();
    rightChild.updateHeight();

    return rightChild;
  }

  /** Preorder traversal: Root -> Left -> Right */
  private static void preorderTraversal(AVLNode preorderNode, List<Integer> preorderResultList) {
    if (preorderNode == null) {
      return;
    }
    preorderResultList.add(preorderNode.value);
    preorderTraversal(preorderNode.left, preorderResultList);
    preorderTraversal(preorderNode.right, preorderResultList);
  }

  /** Inorder traversal: Left -> Root -> Right */
  private static void inorderTraversal(AVLNode inorderNode, List<Integer> inorderResultList) {
    if (inorderNode == null) {
      return;
    }
    inorderTraversal(inorderNode.left, inorderResultList);
    inorderResultList.add(inorderNode.value);
    inorderTraversal(inorderNode.right, inorderResultList);
  }

  /** Postorder traversal: Left -> Right -> Root */
  private static void postorderTraversal(AVLNode postorderNode, List<Integer> postorderResultList) {
    if (postorderNode == null) {
      return;
    }
    postorderTraversal(postorderNode.left, postorderResultList);
    postorderTraversal(postorderNode.right, postorderResultList);
    postorderResultList.add(postorderNode.value);
  }

  /** Level-order traversal (BFS - Breadth-First Search): Level by level from top to bottom */
  private static void levelOrderTraversal(
      AVLNode levelOrderRoot, List<Integer> levelOrderResultList) {
    if (levelOrderRoot == null) {
      return;
    }

    Queue<AVLNode> bfsQueue = new LinkedList<>();
    bfsQueue.offer(levelOrderRoot);

    while (!bfsQueue.isEmpty()) {
      AVLNode nodeAtLevel = bfsQueue.poll();
      levelOrderResultList.add(nodeAtLevel.value);

      if (nodeAtLevel.left != null) {
        bfsQueue.offer(nodeAtLevel.left);
      }
      if (nodeAtLevel.right != null) {
        bfsQueue.offer(nodeAtLevel.right);
      }
    }
  }
}
