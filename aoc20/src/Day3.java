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
        System.out.println(list);
        s.close();
        part1(list);
//        part2(list);
    }

    private static void part1(ArrayList<String> list) {
        int rightCounter = 3;
        int treeCounter = 0;
        for (int i = 1; i < list.size(); i++) {
            if (rightCounter > list.get(0).length() - 1) {
                rightCounter = rightCounter - list.get(0).length();
                System.out.println(list.get(i));
            }
            if (list.get(i).charAt(rightCounter) == '#') {
                treeCounter++;
            }
            rightCounter = rightCounter + 3;
        }
        System.out.println(treeCounter);
    }
}
