import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Set<String> fields = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            String row = list.get(i);
            if (row.contains("byr:")) {
                fields.add("byr");
            }
            if (row.contains("iyr:")) {
                fields.add("iyr");
            }
            if (row.contains("eyr:")) {
                fields.add("eyr");
            }
            if (row.contains("hgt:")) {
                fields.add("hgt");
            }
            if (row.contains("hcl:")) {
                fields.add("hcl");
            }
            if (row.contains("ecl:")) {
                fields.add("ecl");
            }
            if (row.contains("pid:")) {
                fields.add("pid");
            }
            if (row.equals("") || i == list.size() - 1) {
                if (fields.size() == 7) {
                    count++;
                }
                fields = new HashSet<>();
            }
        }
        return count;
    }

    private static int part2(ArrayList<String> list) {
        int count = 0;
        Set<String> fields = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            String row = list.get(i);
            if (row.contains("byr:")) {
                String[] splitted = row.split("byr:");
                int result = Integer.parseInt(splitted[1].substring(0, 4));
                if (result >= 1920 && result <= 2002) {
                    fields.add("byr");
                }
            }
            if (row.contains("iyr:")) {
                String[] splitted = row.split("iyr:");
                int result = Integer.parseInt(splitted[1].substring(0, 4));
                if (result >= 2010 && result <= 2020) {
                    fields.add("iyr");
                }
            }
            if (row.contains("eyr:")) {
                String[] splitted = row.split("eyr:");
                int result = Integer.parseInt(splitted[1].substring(0, 4));
                if (result >= 2020 && result <= 2030) {
                    fields.add("eyr");
                }
            }
            if (row.contains("hgt:")) {
                Pattern cmPattern = Pattern.compile("hgt:\\d{1,3}cm");
                Matcher cmMatcher = cmPattern.matcher(row);

                Pattern inchPattern = Pattern.compile("hgt:\\d{1,3}in");
                Matcher inchMatcher = inchPattern.matcher(row);

                if (cmMatcher.find()) {
                    String[] splitted = cmMatcher.group(0).split("cm");
                    int result = Integer.parseInt(splitted[0].split("hgt:")[1]);
                    if (result >= 150 && result <= 193) {
                        fields.add("hgt");
                    }
                } else if (inchMatcher.find()) {
                    String[] splitted = inchMatcher.group(0).split("in");
                    int result = Integer.parseInt(splitted[0].split("hgt:")[1]);
                    if (result >= 59 && result <= 76) {
                        fields.add("hgt");
                    }
                }
            }
            if (row.contains("hcl:")) {
                Pattern r = Pattern.compile("hcl:#[a-f|\\d]{6}");
                Matcher m = r.matcher(row);
                if (m.find()) {
                    fields.add("hcl");
                }
            }
            if (row.contains("ecl:")) {
                Pattern r = Pattern.compile("ecl:amb|ecl:blu|ecl:brn|ecl:gry|ecl:grn|ecl:hzl|ecl:oth");
                Matcher m = r.matcher(row);
                if (m.find()) {
                    fields.add("ecl");
                }
            }
            if (row.contains("pid:")) {
                Pattern r = Pattern.compile("pid:\\d+");
                Matcher m = r.matcher(row);
                if (m.find()) {
                    if (m.group(0).split("pid:")[1].length() == 9) {
                        fields.add("pid");
                    }
                }
            }
            if (row.equals("") || i == list.size() - 1) {
                if (fields.size() == 7) {
                    count++;
                }
                fields = new HashSet<>();
            }
        }
        return count;
    }
}
