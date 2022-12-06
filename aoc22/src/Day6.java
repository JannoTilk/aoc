import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day6.txt"));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        s.close();

        long timeBefore1 = System.currentTimeMillis();
        System.out.println(comSystem(list.get(0), 4));
        long timeAfter1 = System.currentTimeMillis();
        long elapsed1 = timeAfter1 - timeBefore1;
        System.out.println("elapsed:" + elapsed1);

        long timeBefore2 = System.currentTimeMillis();
        System.out.println(comSystem(list.get(0), 14));
        long timeAfter2 = System.currentTimeMillis();
        long elapsed2 = timeAfter2 - timeBefore2;
        System.out.println("elapsed:" + elapsed2);
    }

    private static int comSystem(String input, int distinct) {
        char[] chars = input.toCharArray();

        Queue<Character> q = createQueue(chars, distinct - 1);
        int marker = 0;

        for (int i = distinct - 1; i < chars.length - distinct - 1; i++) {
            q.add(chars[i]);
            if (q.stream().distinct().count() == distinct) {
                marker = i + 1;
                break;
            }
            q.poll();
        }
        return marker;
    }

    private static Queue<Character> createQueue(char[] chars, int length) {
        Queue<Character> q = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            q.add(chars[i]);
        }
        return q;
    }
}
