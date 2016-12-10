import advent2015.Day2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day2Test {

    @Test
    public void firstStarTest() {
        assertEquals("58", new Day2("2x3x4").firstStar());
        assertEquals("43", new Day2("1x1x10").firstStar());
    }


    @Test
    public void secondStarTest() {
        assertEquals("34", new Day2("2x3x4").secondStar());
        assertEquals("14", new Day2("1x1x10").secondStar());
    }

}
