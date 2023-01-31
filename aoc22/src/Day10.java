import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner s = new Scanner(new File("aoc22/src/day10.txt"));
        Scanner s = new Scanner(new File("aoc22/src/day10_test.txt"));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        s.close();

//        long timeBefore1 = System.currentTimeMillis();
//        System.out.println(part1(list));
//        long timeAfter1 = System.currentTimeMillis();
//        long elapsed1 = timeAfter1 - timeBefore1;
//        System.out.println("elapsed:" + elapsed1);

        long timeBefore2 = System.currentTimeMillis();
        System.out.println(part2(list));
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
                    System.out.println(X);
                    System.out.println(cycle);
                    System.out.println(X * cycle);
                    result += X * cycle;
                }
                cycle++;
                X += Integer.parseInt(s.split(" ")[1]);
            }
            if (instruction.equals("noop")) {
                cycle++;
            }
            if (cyclesToCheck.contains(cycle)) {
                System.out.println(X);
                System.out.println(cycle);
                System.out.println(X * cycle);
                result += X * cycle;
            }
//            System.out.println(input.get(i));
        }
        return result;
    }

    private static boolean part2(List<String> input) {
        List<Integer> cyclesToCheck = new ArrayList<>(Arrays.asList(40, 80, 120, 160, 200, 240));
        List<StringBuilder> sbs = new ArrayList<>();
        int X = 1;
        int cycle = -1;
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
//            if (cyclesToCheck.contains(cycle + 1)) {
//                sbs.add(sb);
//                cycle = -1;
//                sb = new StringBuilder();
//            }
            String instruction = s.split(" ")[0];
            if (instruction.equals("addx")) {

                cycle++;
                if (cyclesToCheck.contains(cycle + 1)) {
                    sbs.add(sb);
                    cycle = -1;
                    sb = new StringBuilder();
                }
                if (cycle >= X - 1 && cycle <= X + 1) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
                cycle++;
                if (cyclesToCheck.contains(cycle + 1)) {
                    sbs.add(sb);
                    cycle = -1;
                    sb = new StringBuilder();
                }
                if (cycle >= X - 1 && cycle <= X + 1) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
                X += Integer.parseInt(s.split(" ")[1]);
            }

            if (instruction.equals("noop")) {
                cycle++;
                if (cyclesToCheck.contains(cycle + 1)) {
                    sbs.add(sb);
                    cycle = -1;
                    sb = new StringBuilder();
                }
                if (cycle >= X - 1 && cycle <= X + 1) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
        }
        for (StringBuilder sb_local : sbs) {
            System.out.println(sb_local);
        }
        return false;
    }
}
