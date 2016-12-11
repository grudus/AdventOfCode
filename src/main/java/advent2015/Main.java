package advent2015;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Day6 d = new Day6(null);

        System.out.println(d.firstStar());
        d.lights = new int[1000][1000];
        System.out.println(d.secondStar());



    }


}
