import advent2015.Day5;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class Day5Test {
    
    @Test
    public void firstStarTest() {
        assertTrue(new Day5(null).isNice("ugknbfddgicrmopn"));
        assertTrue(new Day5(null).isNice("aaa"));
        assertFalse(new Day5(null).isNice("jchzalrnumimnmhp"));
        assertFalse(new Day5(null).isNice("haegwjzuvuyypxyu"));
        assertFalse(new Day5(null).isNice("dvszwmarrgswjxmb"));
    }


    @Test
    public void secondStarTest() {
        assertTrue(new Day5(null).isNicer("qjhvhtzxzqqjkmpb"));
        assertTrue(new Day5(null).isNicer("xxyxx"));
        assertFalse(new Day5(null).isNicer("uurcxstgmygtbstg"));
        assertFalse(new Day5(null).isNicer("ieodomkazucvgmuy"));
    }
}
