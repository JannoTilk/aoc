import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day2.txt"));
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
        System.out.println(list);
        int sum = 0;
        for (String s : list) {
            int roundResult = 0;
            String[] result = s.split(" ");
            String elfResult = result[0];
            String selfResult = result[1];
            switch (selfResult) {
                case "X" -> {
                    roundResult++;
                    if (elfResult.equals("A")) {
                        roundResult = roundResult + 3;
                        break;
                    }
                    if (elfResult.equals("C")) {
                        roundResult = roundResult + 6;
                    }
                }
                case "Y" -> {
                    roundResult = roundResult + 2;
                    if (elfResult.equals("B")) {
                        roundResult = roundResult + 3;
                        break;
                    }
                    if (elfResult.equals("A")) {
                        roundResult = roundResult + 6;
                    }
                }
                case "Z" -> {
                    roundResult = roundResult + 3;
                    if (elfResult.equals("C")) {
                        roundResult = roundResult + 3;
                        break;
                    }
                    if (elfResult.equals("B")) {
                        roundResult = roundResult + 6;
                    }
                }
            }
            sum = sum + roundResult;
        }
        return sum;
    }

    private static int part2(ArrayList<String> list) {
        System.out.println(list);
        int sum = 0;
        for (String s : list) {
            int roundResult = 0;
            String[] result = s.split(" ");
            String elfResult = result[0];
            String selfResult = result[1];
            switch (selfResult) {
                case "X" -> {
                    if (elfResult.equals("A")) {
                        roundResult = roundResult + 3;
                        break;
                    }
                    if (elfResult.equals("B")) {
                        roundResult = roundResult + 1;
                        break;
                    }
                    if (elfResult.equals("C")) {
                        roundResult = roundResult + 2;
                    }
                }
                case "Y" -> {
                    roundResult = roundResult + 3;
                    if (elfResult.equals("A")) {
                        roundResult = roundResult + 1;
                        break;
                    }
                    if (elfResult.equals("B")) {
                        roundResult = roundResult + 2;
                        break;
                    }
                    if (elfResult.equals("C")) {
                        roundResult = roundResult + 3;
                    }
                }
                case "Z" -> {
                    roundResult = roundResult + 6;
                    if (elfResult.equals("A")) {
                        roundResult = roundResult + 2;
                        break;
                    }
                    if (elfResult.equals("B")) {
                        roundResult = roundResult + 3;
                        break;
                    }
                    if (elfResult.equals("C")) {
                        roundResult = roundResult + 1;
                    }
                }
            }
            sum = sum + roundResult;
        }
        return sum;
    }
}
