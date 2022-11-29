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
        part1(list);
        part2(list);
    }

    private static void part1(ArrayList<String> list) {
        long timeBefore = System.currentTimeMillis();

        int count = 0;
        for (String s: list) {
            String[] splitted = s.split(" ");
            int min = Integer.parseInt(splitted[0].split("-")[0]);
            int max = Integer.parseInt(splitted[0].split("-")[1]);
            char letter = splitted[1].replace(":","").charAt(0);
            String password = splitted[2];
            long countLetters = password.chars().filter(ch -> ch == letter).count();
            if (min <= countLetters && max >= countLetters) {
                count++;
            }
        }
        System.out.println("Part1");
        System.out.println(count);
        long timeAfter = System.currentTimeMillis();
        long elapsed = timeAfter - timeBefore;
        System.out.println("elapsed:" + elapsed);
    }

    private static void part2(ArrayList<String> list) {
        long timeBefore = System.currentTimeMillis();

        int count = 0;
        for (String s: list) {
            String[] splitted = s.split(" ");
            int positionOne = Integer.parseInt(splitted[0].split("-")[0]);
            int positionTwo = Integer.parseInt(splitted[0].split("-")[1]);
            char letter = splitted[1].replace(":","").charAt(0);
            String password = splitted[2];
            boolean positionOneContainsLetter = password.charAt(positionOne-1) == letter;
            boolean positionTwoContainsLetter = password.charAt(positionTwo-1) == letter;
            if ((positionOneContainsLetter && !positionTwoContainsLetter) || (!positionOneContainsLetter && positionTwoContainsLetter)) {
                count++;
            }
        }
        System.out.println("Part2");
        System.out.println(count);
        long timeAfter = System.currentTimeMillis();
        long elapsed = timeAfter - timeBefore;
        System.out.println("elapsed:" + elapsed);
    }
}
