import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day7.txt"));
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
        int sum = 0;
        HashMap<List<String>, Integer> dirMap = new HashMap<>();
        List<String> currentDir = new ArrayList<>();
        calculateDirValues(list, dirMap, currentDir);

        for (Integer value : dirMap.values()) {
            if (value <= 100000) {
                sum = sum + value;
            }
        }

        return sum;
    }

    private static int part2(ArrayList<String> list) {
        HashMap<List<String>, Integer> dirMap = new HashMap<>();
        List<String> currentDir = new ArrayList<>();
        calculateDirValues(list, dirMap, currentDir);

        int usedDiscSpace = calculateUsedDiscSpace(list);
        int availableDiscSpace = 70000000 - usedDiscSpace;
        int discSpaceNeeded = 30000000 - availableDiscSpace;

        int min = Integer.MAX_VALUE;

        for (Integer value : dirMap.values()) {
            if (value >= discSpaceNeeded) {
                min = Math.min(value, min);
            }
        }

        return min;
    }

    private static void calculateDirValues(ArrayList<String> list, HashMap<List<String>, Integer> dirMap, List<String> currentDir) {
        for (String s : list) {
            String[] splitted = s.split(" ");
            if (splitted[0].equals("dir") || splitted[1].equals("ls")) {
                continue;
            }

            if (splitted[1].equals("cd") && !splitted[2].equals("..")) {
                List<String> childDir = new ArrayList<>(currentDir);
                childDir.add(splitted[2]);
                dirMap.put(childDir, 0);
                currentDir = childDir;
                continue;
            }

            if (splitted[1].equals("cd")) {
                Integer childDirSize = dirMap.get(currentDir);

                currentDir.remove(currentDir.size() - 1);
                Integer parentDirSize = dirMap.get(currentDir) + childDirSize;

                dirMap.put(currentDir, parentDirSize);
                continue;
            }

            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(splitted[0]);
            if (m.find()) {
                Integer currentDirSize = dirMap.get(currentDir);
                currentDirSize = currentDirSize + Integer.parseInt(splitted[0]);
                dirMap.put(currentDir, currentDirSize);
            }
        }
    }

    private static int calculateUsedDiscSpace(ArrayList<String> list) {
        int count = 0;
        for (String s : list) {
            String[] splitted = s.split(" ");
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(splitted[0]);
            if (m.find()) {
                count = count + Integer.parseInt(splitted[0]);
            }
        }
        return count;
    }
}
