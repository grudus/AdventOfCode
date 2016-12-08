package advent2015;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class Day {

    protected String inputString;

    public Day(String input) {
        if (input == null) {
            int day = Integer.parseInt(getClass().getSimpleName().substring(3));
            try {
                inputString = Files.lines(new File("src/main/resources/day" + day).toPath()).reduce((s1, s2) -> s1 + "\n" + s2).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else inputString = input;
    }

    public abstract String firstStar();
    public abstract String secondStar();

}
