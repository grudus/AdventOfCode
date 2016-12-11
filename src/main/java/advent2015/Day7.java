package advent2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 extends Day {

    public Day7(String input) {
        super(input);
    }

    private ArrayList<String> instructions = new ArrayList<>(Arrays.asList(inputString.split("\\n")));

    Map<String, Integer> wires = instructions.stream()
            .map(line -> line.split(" -> ")[1])
            .collect(Collectors.toMap(s -> s, s -> -1));

    private int findAValue() {
        int counter = 0;
        while (!instructions.isEmpty()) {
            counter %= instructions.size();
            String[] instruction = instructions.get(counter).split(" -> ");
            String[] leftSide = instruction[0].split("\\s+");

            if (leftSide.length == 1) {
                if (wireHasValueOrIsNumber(leftSide[0])) {
                    wires.replace(instruction[1], toInt(instruction[0]));
                    instructions.remove(counter);
                } else counter++;

            } else if (leftSide.length == 2) {
                if (wireHasValueOrIsNumber(leftSide[1])) {
                    wires.replace(instruction[1], ~toInt(leftSide[1]));
                    instructions.remove(counter);
                } else counter++;

            } else if (leftSide.length == 3) {
                if (wireHasValueOrIsNumber(leftSide[0]) && wireHasValueOrIsNumber(leftSide[2])) {
                    wires.replace(instruction[1], getLogicalValue(leftSide));
                    instructions.remove(counter);
                } else counter++;
            }
        }
        return wires.get("a");
    }

    private Integer getLogicalValue(String[] statement) {
        int val = -1;
        switch (statement[1]) {
            case "AND": val = toInt(statement[0]) & toInt(statement[2]); break;
            case "OR": val = toInt(statement[0]) | toInt(statement[2]); break;
            case "LSHIFT": val = toInt(statement[0]) << toInt(statement[2]); break;
            case "RSHIFT": val = toInt(statement[0]) >> toInt(statement[2]); break;
        }
        return val;
    }

    private int toInt(String s) {
        return s.matches("\\d+") ? Integer.parseInt(s) : wires.get(s);
    }

    private boolean wireHasValueOrIsNumber(String s) {
        return s.matches("\\d+") || wires.get(s) != -1;
    }

    @Override
    public String firstStar() {
        return findAValue() + "";
    }

    @Override
    public String secondStar() {return null;}
}
