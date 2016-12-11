package advent2015;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 extends Day {

    public Day6(String input) {
        super(input);
    }

    class Instruction {
        final String instruction;
        final Point startPoint;
        final Point endPoint;

        Instruction(String instruction, Point startPoint, Point endPoint) {
            this.instruction = instruction;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }
    }

    private List<Instruction> instructionList = Arrays.stream(inputString.split("\\n")).map(line -> {
        List<Point> numbers = findAll(line, "(\\d+,\\d+)+").stream().map(s ->
                new Point(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])))
                .collect(Collectors.toList());
        String[] words = line.split("\\s");
        return new Instruction((words[0].equals("toggle") ? words[0] : words[1]), numbers.get(0), numbers.get(1));
    }).collect(Collectors.toList());

    private static ArrayList<String> findAll(String string, String regex) {
        Matcher m = Pattern.compile(regex).matcher(string);
        ArrayList<String> found = new ArrayList<>();
        while (m.find())
            IntStream.range(0, m.groupCount()).mapToObj(m::group).forEach(found::add);
        return found;
    }

    int[][] lights = new int[1000][1000];

    private void changeLights(Point start, Point finish, LightListener listener) {
        for (int r = start.y; r <= finish.y; r++)
            for (int c = start.x; c <= finish.x; c++)
                lights[r][c] = listener.change(lights[r][c]);
    }


    @Override
    public String firstStar() {
        instructionList.forEach(instr -> {
            LightListener listener;
            if (instr.instruction.equals("toggle")) listener = act -> ++act % 2;
            else if (instr.instruction.equals("on")) listener = act -> 1;
            else listener = act -> 0;
            changeLights(instr.startPoint, instr.endPoint, listener);
        });
        return countLights() + "";
    }

    @Override
    public String secondStar() {
        instructionList.forEach(instr -> {
            LightListener listener;
            if (instr.instruction.equals("toggle")) listener = act -> act + 2;
            else if (instr.instruction.equals("on")) listener = act -> ++act;
            else listener = act -> Math.max(0, --act);
            changeLights(instr.startPoint, instr.endPoint, listener);
        });
        return countLights() + "";
    }

    private int countLights() {
        int c = 0;
        for (int[] light : lights) for (int j = 0; j < lights[0].length; j++) c += light[j];
        return c;
    }

    interface LightListener {
        int change(int actual);
    }
}