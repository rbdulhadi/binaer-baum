package com.binaerBaum;

/**
 * Represents a node in an AVL tree.
 */
public class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int value) {
        this.value = value;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    /**
     * Calculates the balance factor of this node.
     * Balance factor = height(left) - height(right)
     * @return balance factor
     */
    public int getBalanceFactor() {
        int leftHeight = (left == null) ? 0 : left.height;
        int rightHeight = (right == null) ? 0 : right.height;
        return leftHeight - rightHeight;
    }

    /**
     * Updates the height of this node based on its children.
     */
    public void updateHeight() {
        int leftHeight = (left == null) ? 0 : left.height;
        int rightHeight = (right == null) ? 0 : right.height;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }
}

