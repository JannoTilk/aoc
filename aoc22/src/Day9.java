import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("aoc22/src/day9.txt"));
//        Scanner s = new Scanner(new File("aoc22/src/day9_test.txt"));
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

    private static int part1(List<String> input) {
        Set<List<Integer>> uniqueTailLocations = new HashSet<>();
        uniqueTailLocations.add(new ArrayList<>(Arrays.asList(0, 0)));
        List<Integer> XY = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        for (String s : input) {
            String[] splitted = s.split(" ");
            String direction = splitted[0];
            int steps = Integer.parseInt(splitted[1]);

            int currentHeadLocationX = XY.get(0);
            int currentHeadLocationY = XY.get(1);
            int currentTailLocationX = XY.get(2);
            int currentTailLocationY = XY.get(3);

            for (int j = 0; j < steps; j++) {
                switch (direction) {
                    case "R" -> currentHeadLocationX++;
                    case "L" -> currentHeadLocationX--;
                    case "U" -> currentHeadLocationY++;
                    case "D" -> currentHeadLocationY--;
                }
                if (currentHeadLocationX - currentTailLocationX > 1) {
                    currentTailLocationX++;
                    currentTailLocationY = currentHeadLocationY;
                }
                if (currentTailLocationX - currentHeadLocationX > 1) {
                    currentTailLocationX--;
                    currentTailLocationY = currentHeadLocationY;
                }
                if (currentHeadLocationY - currentTailLocationY > 1) {
                    currentTailLocationY++;
                    currentTailLocationX = currentHeadLocationX;
                }
                if (currentTailLocationY - currentHeadLocationY > 1) {
                    currentTailLocationY--;
                    currentTailLocationX = currentHeadLocationX;
                }

                XY = new ArrayList<>(Arrays.asList(currentHeadLocationX, currentHeadLocationY, currentTailLocationX, currentTailLocationY));
                uniqueTailLocations.add(new ArrayList<>(Arrays.asList(currentTailLocationX, currentTailLocationY)));

            }
        }
        return uniqueTailLocations.size();

    }

    private static int part2(List<String> input) {
        Set<List<Integer>> uniqueTailLocations = new HashSet<>();
        uniqueTailLocations.add(new ArrayList<>(Arrays.asList(0, 0)));
        List<List<Integer>> XY = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            XY.add(new ArrayList<>(Arrays.asList(0, 0)));
        }

        for (String s : input) {
            String[] splitted = s.split(" ");
            String direction = splitted[0];
            int steps = Integer.parseInt(splitted[1]);


            for (int j = 0; j < steps; j++) {
                int firstElementX = XY.get(0).get(0);
                int firstElementY = XY.get(0).get(1);
                switch (direction) {
                    case "R" -> firstElementX++;
                    case "L" -> firstElementX--;
                    case "U" -> firstElementY++;
                    case "D" -> firstElementY--;
                }
                XY.set(0, new ArrayList<>(Arrays.asList(firstElementX, firstElementY)));
//                System.out.println(XY.get(3));
                for (int k = 0; k < XY.size() - 1; k++) {

                    int currentHeadLocationX = XY.get(k).get(0);
                    int currentHeadLocationY = XY.get(k).get(1);
                    int currentTailLocationX = XY.get(k + 1).get(0);
                    int currentTailLocationY = XY.get(k + 1).get(1);


                    if (Math.abs(currentHeadLocationX - currentTailLocationX) < 2 && Math.abs(currentHeadLocationY - currentTailLocationY) < 2) {
                        continue;
                    }

                    if (currentHeadLocationX > currentTailLocationX) currentTailLocationX++;
                    if (currentHeadLocationX < currentTailLocationX) currentTailLocationX--;

                    if (currentHeadLocationY > currentTailLocationY) currentTailLocationY++;
                    if (currentHeadLocationY < currentTailLocationY) currentTailLocationY--;

                    List<Integer> tailXY = new ArrayList<>(Arrays.asList(currentTailLocationX, currentTailLocationY));
                    XY.set(k + 1, tailXY);

                    if (k == XY.size() - 2) {
                        List<Integer> tailLocation = XY.get(k + 1);
//                        if (!uniqueTailLocations.contains(tailLocation)) {
//                            int x = tailLocation.get(0);
//                            int y = tailLocation.get(1);
//                            System.out.println("(" + y + ", " + x + ")");
//                        }
                        uniqueTailLocations.add(tailLocation);
//                        System.out.println(uniqueTailLocations);
                    }
                }
            }

        }
//        System.out.println(uniqueTailLocations);
        return uniqueTailLocations.size();

    }
}
