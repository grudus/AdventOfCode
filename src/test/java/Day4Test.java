import advent2015.Day4;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;


public class Day4Test {
    @Test
    public void firstStarTest() throws NoSuchAlgorithmException {
        assertEquals("609043", new Day4("abcdef").firstStar());
        assertEquals("1048970", new Day4("pqrstuv").firstStar());
    }

}
