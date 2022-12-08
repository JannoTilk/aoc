import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day8.txt"));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        s.close();

        long timeBefore1 = System.currentTimeMillis();
        System.out.println(part1(list));
        long timeAfter1 = System.currentTimeMillis();
        long elapsed1 = timeAfter1 - timeBefore1;
        System.out.println("elapsed:" + elapsed1);

        long timeBefore2 = System.currentTimeMillis();
        System.out.println(part2(list));
        long timeAfter2 = System.currentTimeMillis();
        long elapsed2 = timeAfter2 - timeBefore2;
        System.out.println("elapsed:" + elapsed2);

    }

    private static int part1(ArrayList<String> list) {
        int size = list.size();
        int corners = size + size - 1 + size - 1 + size - 2;
        int count = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            for (int j = 1; j < list.get(i).length() - 1; j++) {
                if (isVisibleFromLeft(j, list.get(i)) ||
                        isVisibleFromRight(j, list.get(i)) ||
                        isVisibleFromTop(j, i, list) ||
                        isVisibleFromBottom(j, i, list)) {
                    count++;
                }
            }
        }
        return count + corners;
    }

    private static int part2(ArrayList<String> list) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i < list.size() - 1; i++) {
            for (int j = 1; j < list.get(i).length() - 1; j++) {
                int leftScore = lowerTreesOnTheLeft(j, list.get(i));
                int rightScore = lowerTreesOnTheRight(j, list.get(i));
                int topScore = lowerTreesOnTheTop(j, i, list);
                int bottomScore = lowerTreesOnTheBottom(j, i, list);
                int currentTreeValue = leftScore * rightScore * topScore * bottomScore;
                maxValue = Math.max(maxValue, currentTreeValue);
            }
        }
        return maxValue;
    }

    private static int lowerTreesOnTheLeft(int treeLocationInRow, String treeRow) {
        int counter = 0;
        for (int i = treeLocationInRow - 1; i >= 0; i--) {
            int currentTreeHeight = treeRow.charAt(i) - '0';
            int treeHeightToCompare = treeRow.charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                counter++;
                return counter;
            }
            counter++;
        }
        return counter;
    }

    private static int lowerTreesOnTheRight(int treeLocationInRow, String treeRow) {
        int counter = 0;
        for (int i = treeLocationInRow + 1; i < treeRow.length(); i++) {
            int currentTreeHeight = treeRow.charAt(i) - '0';
            int treeHeightToCompare = treeRow.charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                counter++;
                return counter;
            }
            counter++;
        }
        return counter;
    }

    private static int lowerTreesOnTheTop(int treeLocationInRow, int treeLocationInColumn, ArrayList<String> list) {
        int counter = 0;
        for (int i = treeLocationInColumn - 1; i >= 0; i--) {
            int currentTreeHeight = list.get(i).charAt(treeLocationInRow) - '0';
            int treeHeightToCompare = list.get(treeLocationInColumn).charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                counter++;
                return counter;
            }
            counter++;
        }
        return counter;
    }

    private static int lowerTreesOnTheBottom(int treeLocationInRow, int treeLocationInColumn, ArrayList<String> list) {
        int counter = 0;
        for (int i = treeLocationInColumn + 1; i < list.size(); i++) {
            int currentTreeHeight = list.get(i).charAt(treeLocationInRow) - '0';
            int treeHeightToCompare = list.get(treeLocationInColumn).charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                counter++;
                return counter;
            }
            counter++;
        }
        return counter;
    }

    private static boolean isVisibleFromLeft(int treeLocationInRow, String treeRow) {
        for (int i = 0; i < treeLocationInRow; i++) {
            int currentTreeHeight = treeRow.charAt(i) - '0';
            int treeHeightToCompare = treeRow.charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVisibleFromRight(int treeLocationInRow, String treeRow) {
        for (int i = treeRow.length() - 1; i > treeLocationInRow; i--) {
            int currentTreeHeight = treeRow.charAt(i) - '0';
            int treeHeightToCompare = treeRow.charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVisibleFromTop(int treeLocationInRow, int treeLocationInColumn, ArrayList<String> list) {
        for (int i = 0; i < treeLocationInColumn; i++) {
            int currentTreeHeight = list.get(i).charAt(treeLocationInRow) - '0';
            int treeHeightToCompare = list.get(treeLocationInColumn).charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVisibleFromBottom(int treeLocationInRow, int treeLocationInColumn, ArrayList<String> list) {
        for (int i = list.size() - 1; i > treeLocationInColumn; i--) {
            int currentTreeHeight = list.get(i).charAt(treeLocationInRow) - '0';
            int treeHeightToCompare = list.get(treeLocationInColumn).charAt(treeLocationInRow) - '0';
            if (currentTreeHeight >= treeHeightToCompare) {
                return false;
            }
        }
        return true;
    }
}
