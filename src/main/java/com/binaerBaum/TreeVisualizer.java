package com.binaerBaum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Helper class for top-down tree visualization.
 * Produces readable tree output like:
 *
 *      8
 *     / \
 *    4   9
 *   / \    \
 *  2   7   13
 *          / \
 *        11  46
 */
public class TreeVisualizer {

    public static void printTree(AVLNode rootNode) {
        if (rootNode == null) {
            System.out.println("(empty)");
            return;
        }

        int totalLevels = calculateHeight(rootNode);
        int initialSpacing = (int) Math.pow(2, totalLevels) * 2;

        // Collect nodes per level using BFS
        Queue<AVLNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(rootNode);

        List<List<String>> outputLevels = new ArrayList<>();

        for (int currentLevel = 0; currentLevel < totalLevels; currentLevel++) {
            int nodesAtCurrentLevel = bfsQueue.size();
            List<String> levelNodes = new ArrayList<>();

            for (int nodeIdx = 0; nodeIdx < nodesAtCurrentLevel; nodeIdx++) {
                AVLNode currentTreeNode = bfsQueue.poll();
                if (currentTreeNode == null) {
                    levelNodes.add(null);
                    bfsQueue.add(null);
                    bfsQueue.add(null);
                } else {
                    levelNodes.add(String.valueOf(currentTreeNode.value));
                    bfsQueue.add(currentTreeNode.left);
                    bfsQueue.add(currentTreeNode.right);
                }
            }
            outputLevels.add(levelNodes);
        }

        // Print level by level with proper spacing and branches
        int displaySpacing = initialSpacing / 2;

        for (int outputLevelIdx = 0; outputLevelIdx < outputLevels.size(); outputLevelIdx++) {
            List<String> levelNodes = outputLevels.get(outputLevelIdx);
            int spaceBefore = displaySpacing / 2;

            // Print node values line
            printSpaces(spaceBefore);
            for (int nodeAtLevelIdx = 0; nodeAtLevelIdx < levelNodes.size(); nodeAtLevelIdx++) {
                String displayValue = levelNodes.get(nodeAtLevelIdx);
                System.out.print(displayValue == null ? " " : displayValue);
                printSpaces(displaySpacing);
            }
            System.out.println();

            // Print connecting lines
            if (outputLevelIdx < outputLevels.size() - 1) {
                int branchSpacing = displaySpacing / 2;
                int branchWidth = branchSpacing / 2;

                printSpaces(spaceBefore - branchWidth);
                for (int branchIdx = 0; branchIdx < levelNodes.size(); branchIdx++) {
                    String branchNode = levelNodes.get(branchIdx);
                    if (branchNode != null) {
                        System.out.print("/");
                        printSpaces(branchWidth * 2 - 1);
                        System.out.print("\\");
                    } else {
                        printSpaces(branchWidth * 2 + 1);
                    }
                    printSpaces(displaySpacing - branchWidth);
                }
                System.out.println();
            }

            displaySpacing /= 2;
        }
    }

    private static void printSpaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }

    private static int calculateHeight(AVLNode someNode) {
        if (someNode == null) return 0;
        return 1 + Math.max(calculateHeight(someNode.left), calculateHeight(someNode.right));
    }
}
