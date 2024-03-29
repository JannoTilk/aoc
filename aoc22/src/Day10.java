import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("aoc22/src/day10.txt"));
//        Scanner s = new Scanner(new File("aoc22/src/day10_test.txt"));
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
        part2(list);
        long timeAfter2 = System.currentTimeMillis();
        long elapsed2 = timeAfter2 - timeBefore2;
        System.out.println("elapsed:" + elapsed2);

    }

    private static int part1(List<String> input) {
        List<Integer> cyclesToCheck = new ArrayList<>(Arrays.asList(20, 60, 100, 140, 180, 220));
        int X = 1;
        int cycle = 1;
        int result = 0;
        for (String s : input) {
            String instruction = s.split(" ")[0];
            if (instruction.equals("addx")) {
                cycle++;
                if (cyclesToCheck.contains(cycle)) {
                    result += X * cycle;
                }
                cycle++;
                X += Integer.parseInt(s.split(" ")[1]);
            }
            if (instruction.equals("noop")) {
                cycle++;
            }
            if (cyclesToCheck.contains(cycle)) {
                result += X * cycle;
            }
        }
        return result;
    }

    private static void part2(List<String> input) {
        int widthCRT = 40;
        List<StringBuilder> sbs = new ArrayList<>();
        int X = 1;
        int cycle = -1;
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            String instruction = s.split(" ")[0];
            if (instruction.equals("addx")) {
                if (widthCRT == cycle + 1) {
                    sbs.add(sb);
                    cycle = -1;
                    sb = new StringBuilder();
                }
                cycle++;

                generateCRTValue(X, cycle, sb);
                if (widthCRT == cycle + 1) {
                    sbs.add(sb);
                    cycle = -1;
                    sb = new StringBuilder();
                }
                cycle++;

                generateCRTValue(X, cycle, sb);

                X += Integer.parseInt(s.split(" ")[1]);
            }

            if (instruction.equals("noop")) {
                if (widthCRT == cycle + 1) {
                    sbs.add(sb);
                    cycle = -1;
                    sb = new StringBuilder();
                }
                cycle++;

                generateCRTValue(X, cycle, sb);

            }
            if (widthCRT == cycle + 1 && sbs.size() == 5) {
                sbs.add(sb);
            }
        }
        for (StringBuilder sb_local : sbs) {
            System.out.println(sb_local);
        }
    }

    private static void generateCRTValue(int X, int cycle, StringBuilder sb) {
        if (cycle >= X - 1 && cycle <= X + 1) {
            sb.append("#");
        } else {
            sb.append(".");
        }
    }
}
