import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day1.txt"));
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

    public static int part1(ArrayList<String> list) {
        int max = 0;
        int elfCalories = 0;
        for (String s : list) {
            if (s.equals("")) {
                max = Math.max(max, elfCalories);
                elfCalories = 0;
                continue;
            }
            elfCalories = elfCalories + Integer.parseInt(s);
        }
        return max;
    }

    public static int part2(ArrayList<String> list) {
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        int elfCalories = 0;
        for (String s : list) {
            if (s.equals("")) {
                if (elfCalories > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = elfCalories;
                } else if (elfCalories > max2) {
                    max3 = max2;
                    max2 = elfCalories;
                } else if (elfCalories > max3) {
                    max3 = elfCalories;
                }
                elfCalories = 0;
                continue;
            }
            elfCalories = elfCalories + Integer.parseInt(s);
        }
        return max1 + max2 + max3;
    }
}