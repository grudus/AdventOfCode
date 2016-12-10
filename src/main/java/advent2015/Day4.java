package advent2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.IntStream;

public class Day4 extends Day {

    private final MessageDigest encoder = MessageDigest.getInstance("MD5");

    public Day4(String input) throws NoSuchAlgorithmException {
        super(input);
    }

    private String findHash(String word) {
        encoder.reset();
        StringBuilder hash = new StringBuilder();
        byte[] digest = encoder.digest(word.getBytes());
        for (byte b : digest) hash.append(String.format("%02x", b));
        return hash.toString();
    }

    private long whichIndex(int zeros) {
        int counter = 0;
        String hash;
        String leadingZeros = IntStream.range(0, zeros).mapToObj(i -> "0").reduce((s1, s2) -> s1 + s2).orElse("0");
        do {
            hash = findHash(inputString + counter++);
        } while (!hash.startsWith(leadingZeros));
        return counter - 1;
    }


    @Override
    public String firstStar() {
        return whichIndex(5) + "";
    }

    @Override
    public String secondStar() {
        return whichIndex(6) + "";
    }
}
