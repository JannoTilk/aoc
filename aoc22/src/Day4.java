import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day4.txt"));
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
        int count = 0;

        for (String s : list) {
            String[] pairs = s.split(",");
            int firstPairMin = Integer.parseInt(pairs[0].split("-")[0]);
            int firstPairMax = Integer.parseInt(pairs[0].split("-")[1]);
            int secondPairMin = Integer.parseInt(pairs[1].split("-")[0]);
            int secondPairMax = Integer.parseInt(pairs[1].split("-")[1]);

            if (firstPairMin >= secondPairMin && firstPairMax <= secondPairMax) {
                count++;
                continue;
            }
            if (firstPairMin <= secondPairMin && firstPairMax >= secondPairMax) {
                count++;
            }
        }
        return count;
    }
    private static int part2(ArrayList<String> list) {
        int count = 0;

        for (String s : list) {
            String[] pairs = s.split(",");
            int firstPairMin = Integer.parseInt(pairs[0].split("-")[0]);
            int firstPairMax = Integer.parseInt(pairs[0].split("-")[1]);
            int secondPairMin = Integer.parseInt(pairs[1].split("-")[0]);
            int secondPairMax = Integer.parseInt(pairs[1].split("-")[1]);

            if (firstPairMax >= secondPairMin && firstPairMin <= secondPairMin || firstPairMin <= secondPairMax && firstPairMax >= secondPairMin) {
                count++;
                continue;
            }
            if (firstPairMax <= secondPairMin && firstPairMin >= secondPairMin || firstPairMin >= secondPairMax && firstPairMax <= secondPairMin) {
                count++;
            }
        }
        return count;
    }
}
