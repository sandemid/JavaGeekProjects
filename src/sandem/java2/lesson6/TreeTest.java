package sandem.java2.lesson6;

import java.util.Random;

public class TreeTest {

    public static void main(String[] args) {

        Random rand = new Random();
        int treeCount = 50;
        int maxLevel = 6;
        int nodeCount = (int) (Math.pow(2, maxLevel) - 1) / 1;

        int maxValue = 100;

        int balancedTreeCount = 0;

        boolean treeView = false;

        for (int i = 0; i < treeCount; i++) {
            Tree theTree = new TreeImpl(maxLevel);
            initTree(rand, nodeCount, maxValue, theTree);
            if (theTree.isBalanced()) {
                balancedTreeCount++;
                if (!treeView) {
                    treeView = true;
                    theTree.display();
                }
            }
        }

        System.out.println("Balanced Tree Count = " + ((balancedTreeCount / (treeCount * 1.0)) * 100) + "%");
    }

    private static void initTree(Random rand, int nodeCount, int maxValue, Tree theTree) {
        for (int j = 0; j < nodeCount; j++) {
            if (!theTree.add(new MyData(rand.nextInt(maxValue * 2 + 1) - maxValue))){
                j--;
            }
        }
    }

}
