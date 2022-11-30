import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day3.txt"));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        s.close();
        part1(list);
        part2(list);
    }

    private static void part1(ArrayList<String> list) {
        System.out.println(treeCounter(list, 3, 1));
    }

    private static void part2(ArrayList<String> list) {
        long encounter1 = treeCounter(list, 1, 1);
        long encounter2 = treeCounter(list, 3, 1);
        long encounter3 = treeCounter(list, 5, 1);
        long encounter4 = treeCounter(list, 7, 1);
        long encounter5 = treeCounter(list, 1, 2);
        long multiply = encounter1 * encounter2 * encounter3 * encounter4 * encounter5;
        System.out.println(multiply);
    }

    private static int treeCounter(ArrayList<String> list, int right, int down) {
        int rightCounter = right;
        int treeCounter = 0;
        for (int i = down; i < list.size(); i = i + down) {
            if (rightCounter > list.get(0).length() - 1) {
                rightCounter = rightCounter - list.get(0).length();
            }
            if (list.get(i).charAt(rightCounter) == '#') {
                treeCounter++;
            }
            rightCounter = rightCounter + right;
        }
        return treeCounter;
    }
}
