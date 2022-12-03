import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day3.txt"));
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
        for (String s : list) {
            List<String> myList = new ArrayList<>(Arrays.asList(s.split("")));
            List<String> sublist1 = myList.subList(0, myList.size() / 2);
            List<String> sublist2 = myList.subList(myList.size() / 2, myList.size());
            int priority = 0;

            for (String value : sublist1) {
                if (sublist2.contains(value)) {
                    if (Character.isUpperCase(value.charAt(0))) {
                        priority = (int) value.charAt(0) - 38;
                    } else {
                        priority = (int) value.charAt(0) - 96;

                    }
                    break;
                }
            }
            sum = sum + priority;
        }
        return sum;
    }

    private static int part2(ArrayList<String> list) {
        int sum = 0;
        for (int i = 0; i < list.size() - 2; i = i + 3) {
            int priority = 0;
            List<String> sublist1 = new ArrayList<>(Arrays.asList(list.get(i).split("")));
            List<String> sublist2 = new ArrayList<>(Arrays.asList(list.get(i + 1).split("")));
            List<String> sublist3 = new ArrayList<>(Arrays.asList(list.get(i + 2).split("")));
            for (int j = 0; j < list.get(i).length(); j++) {
                String search = sublist1.get(j);

                if (sublist2.contains(search) && sublist3.contains(search)) {
                    if (Character.isUpperCase(search.charAt(0))) {
                        priority = (int) sublist1.get(j).charAt(0) - 38;
                    } else {
                        priority = (int) sublist1.get(j).charAt(0) - 96;

                    }
                    break;
                }
            }
            sum = sum + priority;

        }
        return sum;
    }

}
