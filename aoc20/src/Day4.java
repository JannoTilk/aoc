import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
//        System.out.println(part2(list));
        long timeAfter2 = System.currentTimeMillis();
        long elapsed2 = timeAfter2 - timeBefore2;
        System.out.println("elapsed:" + elapsed2);
    }

    private static int part1(ArrayList<String> list) {
        int count = 0;
        Set<String> fields = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("byr")) {
                fields.add("byr");
            }
            if (list.get(i).contains("iyr")) {
                fields.add("iyr");
            }
            if (list.get(i).contains("eyr")) {
                fields.add("eyr");
            }
            if (list.get(i).contains("hgt")) {
                fields.add("hgt");
            }
            if (list.get(i).contains("hcl")) {
                fields.add("hcl");
            }
            if (list.get(i).contains("ecl")) {
                fields.add("ecl");
            }
            if (list.get(i).contains("pid")) {
                fields.add("pid");
            }
            if (list.get(i).equals("") || i == list.size() - 1) {
                if (fields.size() == 7) {
                    count++;
                }
                fields = new HashSet<>();
            }
        }
        return count;
    }
}
