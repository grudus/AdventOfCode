import advent2015.Day3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day3Test {

    @Test
    public void firstStarTest() {
        assertEquals("2", new Day3(">").firstStar());
        assertEquals("4", new Day3("^>v<").firstStar());
        assertEquals("2", new Day3("^v^v^v^v^v").firstStar());
    }


    @Test
    public void secondStarTest() {
        assertEquals("3", new Day3("^v").secondStar());
        assertEquals("3", new Day3("^>v<").secondStar());
        assertEquals("11", new Day3("^v^v^v^v^v").secondStar());
    }
}