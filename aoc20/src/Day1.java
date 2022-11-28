import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day1.txt"));
        ArrayList<Integer> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(Integer.parseInt(s.next()));
        }
        s.close();
        part1(list);
        part2(list);
    }

    private static void part1(ArrayList<Integer> list) {
        long timeBefore = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == 2020) {
                    System.out.println("Part 1");
                    System.out.println(list.get(i) + " " + list.get(j));
                    System.out.println(list.get(i) * list.get(j));
                }
            }
        }
        long timeAfter = System.currentTimeMillis();
        long elapsed = timeAfter - timeBefore;
        System.out.println("elapsed:" + elapsed);
    }

    private static void part2(ArrayList<Integer> list) {
        long timeBefore = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == 2020) {
                        System.out.println("Part 2");
                        System.out.println(list.get(i) + " " + list.get(j) + " " + list.get(k));
                        System.out.println(list.get(i) * list.get(j) * list.get(k));
                    }
                }
            }
        }
        long timeAfter = System.currentTimeMillis();
        long elapsed = timeAfter - timeBefore;
        System.out.println("elapsed:" + elapsed);
    }
}