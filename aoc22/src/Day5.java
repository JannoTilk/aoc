import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/day5.txt"));
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

    private static String part1(ArrayList<String> list) {
        List<Stack<Character>> stackList = createStacks(list);

        // Change order
        for (int i = 10; i < list.size(); i++) {
            String[] splitted = list.get(i).split(" ");
            int move = Integer.parseInt(splitted[1]);
            int from = Integer.parseInt(splitted[3]);
            int to = Integer.parseInt(splitted[5]);
            for (int m = 0; m < move; m++) {
                Character c = stackList.get(from-1).pop();
                stackList.get(to-1).push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Stack<Character> s: stackList) {
            sb.append(s.peek());
        }
        return sb.toString();
    }

    private static String part2(ArrayList<String> list) {
        List<Stack<Character>> stackList = createStacks(list);

        for (int i = 10; i < list.size(); i++) {
            String[] splitted = list.get(i).split(" ");
            int move = Integer.parseInt(splitted[1]);
            int from = Integer.parseInt(splitted[3]);
            int to = Integer.parseInt(splitted[5]);
            Stack<Character> tempStack = new Stack<>();
            for (int m = 0; m < move; m++) {
                Character c = stackList.get(from-1).pop();
                tempStack.push(c);
            }

            for (int m = 0; m < move; m++) {
                stackList.get(to-1).push(tempStack.pop());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Stack<Character> s: stackList) {
            sb.append(s.peek());
        }
        return sb.toString();
    }

    private static List<Stack<Character>> createStacks(ArrayList<String> list) {
        // Create empty stacks
        List<Stack<Character>> stackList = new ArrayList<>();
        for (int t = 0; t < 9; t++) {
            Stack<Character> stk = new Stack<>();
            stackList.add(stk);

        }

        // Initialize stacks
        for (int j = 7; j >= 0; j--) {
            int stackCounter = 0;
            for (int k = 1; k < list.get(j).length(); k = k + 4) {
                if (list.get(j).charAt(k) != ' ') {
                    Stack<Character> stk = stackList.get(stackCounter);
                    stk.push(list.get(j).charAt(k));
                }
                stackCounter++;
            }

        }
        return stackList;
    }
}
