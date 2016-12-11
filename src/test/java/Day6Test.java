import advent2015.Day6;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day6Test {

    @Test
    public void secondStarTest() {
        assertEquals("1", new Day6("turn on 0,0 through 0,0").secondStar());
        assertEquals("2000000", new Day6("toggle 0,0 through 999,999").secondStar());
    }
}
