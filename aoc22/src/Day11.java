import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    static List<Monkey> monkeys = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("aoc22/src/day11.txt"));
//        Scanner s = new Scanner(new File("aoc22/src/day11_test.txt"));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNext()) {
            list.add(s.nextLine());
        }
        s.close();

        long timeBefore1 = System.currentTimeMillis();
        System.out.println(part1(list, 20));
        long timeAfter1 = System.currentTimeMillis();
        long elapsed1 = timeAfter1 - timeBefore1;
        System.out.println("elapsed:" + elapsed1);

//        long timeBefore2 = System.currentTimeMillis();
//        part2(list);
//        long timeAfter2 = System.currentTimeMillis();
//        long elapsed2 = timeAfter2 - timeBefore2;
//        System.out.println("elapsed:" + elapsed2);

    }

    private static int part1(List<String> input, int rounds) {
        int pointer = 0;
        createInitialMonkeys(input, pointer);
        for (int i = 0; i < rounds; i++) {
            for (Monkey m : monkeys) {
                inspect(m);
            }
        }

        List<Integer> inspections = new ArrayList<>();
        for (Monkey m : monkeys) {
            inspections.add(m.getInspections());
            System.out.println(m);
        }
        inspections.sort(Collections.reverseOrder());

        return inspections.get(0) * inspections.get(1);
    }

    private static void createInitialMonkeys(List<String> input, int pointer) {
        Monkey monkey = new Monkey();

        for (int i = 0; i <= input.size(); i++) {
            if (pointer % 7 == 0) {
                if (i != 0) {
                    monkeys.add(monkey);
                }
                int id = Integer.parseInt(input.get(i).split(" ")[1].replace(":", ""));
                monkey = new Monkey();
                monkey.setId(id);
                pointer = 0;
            }
            if (pointer == 1) {
                String itemsStr = input.get(i).split(": ")[1];
                List<Integer> items = new ArrayList<>(Arrays.stream(itemsStr.split("\\s*,\\s*")).map(Integer::parseInt).toList());
                monkey.setItems(items);
            }
            if (pointer == 2) {
                String operation = input.get(i).split("= ")[1];
                monkey.setOperation(operation);
            }
            if (pointer == 3) {
                int divisible = Integer.parseInt(input.get(i).split("by ")[1]);
                monkey.setDivisible(divisible);
            }
            if (pointer == 4) {
                int monkeyToThrowWhenConditionPassed = Integer.parseInt(input.get(i).split("monkey ")[1]);
                monkey.setMonkeyToThrowWhenConditionPassed(monkeyToThrowWhenConditionPassed);
            }
            if (pointer == 5) {
                int monkeyToThrowWhenConditionFailed = Integer.parseInt(input.get(i).split("monkey ")[1]);
                monkey.setMonkeyToThrowWhenConditionFailed(monkeyToThrowWhenConditionFailed);
            }
            pointer++;
        }
        monkeys.add(monkey);
    }

    private static void inspect(Monkey currentMonkey) {
        List<Integer> copyOfItems = new ArrayList<>(currentMonkey.getItems());
        for (int item : copyOfItems) {
            currentMonkey.setInspections(currentMonkey.getInspections() + 1);
            int newValue = operation(currentMonkey.getOperation(), item) / 3;

            if (newValue % currentMonkey.getDivisible() == 0) {
                getMonkeyById(monkeys, currentMonkey.monkeyToThrowWhenConditionPassed).addItem(newValue);
                currentMonkey.getItems().remove(0);
            } else {
                getMonkeyById(monkeys, currentMonkey.monkeyToThrowWhenConditionFailed).addItem(newValue);
                currentMonkey.getItems().remove(0);
            }
        }
    }

    private static int operation(String input, int old) {
        int result = 0;
        int secondNumber;
        String[] parts = input.split(" ");
        if (parts[2].equals("old")) {
            secondNumber = old;
        } else {
            secondNumber = Integer.parseInt(parts[2]);
        }
        switch (parts[1]) {
            case "+" -> result = old + secondNumber;
            case "*" -> result = old * secondNumber;
        }
        return result;
    }

    private static Monkey getMonkeyById(List<Monkey> monkeys, int id) {
        return monkeys.stream().filter(a -> a.getId() == id).toList().get(0);
    }

    private static class Monkey {
        int id;
        List<Integer> items;
        int inspections;
        int divisible;
        int monkeyToThrowWhenConditionPassed;
        int monkeyToThrowWhenConditionFailed;

        String operation;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Integer> getItems() {
            return items;
        }

        public void setItems(List<Integer> items) {
            this.items = items;
        }

        public void addItem(Integer item) {
            this.items.add(item);
        }

        public int getInspections() {
            return inspections;
        }

        public void setInspections(int inspections) {
            this.inspections = inspections;
        }

        public int getDivisible() {
            return divisible;
        }

        public void setDivisible(int divisible) {
            this.divisible = divisible;
        }

        public int getMonkeyToThrowWhenConditionPassed() {
            return monkeyToThrowWhenConditionPassed;
        }

        public void setMonkeyToThrowWhenConditionPassed(int monkeyToThrowWhenConditionPassed) {
            this.monkeyToThrowWhenConditionPassed = monkeyToThrowWhenConditionPassed;
        }

        public int getMonkeyToThrowWhenConditionFailed() {
            return monkeyToThrowWhenConditionFailed;
        }

        public void setMonkeyToThrowWhenConditionFailed(int monkeyToThrowWhenConditionFailed) {
            this.monkeyToThrowWhenConditionFailed = monkeyToThrowWhenConditionFailed;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "id=" + id +
                    ", items=" + items +
                    ", inspections=" + inspections +
                    ", divisible=" + divisible +
                    ", monkeyToThrowWhenConditionPassed=" + monkeyToThrowWhenConditionPassed +
                    ", monkeyToThrowWhenConditionFailed=" + monkeyToThrowWhenConditionFailed +
                    ", operation='" + operation + '\'' +
                    '}';
        }
    }
}



