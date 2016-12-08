import advent2015.Day1;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    @Test
    public void firstStarTest() {
        assertEquals("0", new Day1("(())").firstStar());
        assertEquals("0" ,new Day1("()()").firstStar());
        assertEquals("-3", new Day1(")())())").firstStar());
        assertEquals("3", new Day1("(()(()(").firstStar());
        assertEquals("3", new Day1("))(((((").firstStar());
        assertEquals("-1", new Day1("())").firstStar());
    }

    @Test
    public void secondStarTest() {
        assertEquals("5", new Day1("()())").secondStar());
        assertEquals("1", new Day1(")").secondStar());
    }

}
