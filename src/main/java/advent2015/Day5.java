package advent2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 extends Day {
    private final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public Day5(String input) {
        super(input);
    }

    private boolean containsThreeVowels(String s) {
        return s.chars().filter(c -> vowels.contains((char) c)).count() > 2;
    }

    private boolean containsSiblingChar(String s) {
        return IntStream.range(0, s.length() - 1).boxed().anyMatch(i -> s.charAt(i) == s.charAt(i + 1));
    }

    private boolean containsNaughtyChars(String s) {
        return s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy");
    }

    public boolean isNice(String s) {
        return !containsNaughtyChars(s) && containsSiblingChar(s) && containsThreeVowels(s);
    }

    public boolean isNicer(String s) {
        return containsPairTwice(s) && hasOxo(s);
    }

    public boolean hasOxo(String s) {
        return IntStream.range(0, s.length() - 2).boxed().anyMatch(i -> s.charAt(i) == s.charAt(i + 2));
    }

    public boolean containsPairTwice(String s) {
        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < s.length() - 1; i++) {
            if (i < s.length() - 3 && s.substring(i, i + 4).equals(times(s.charAt(i), 4))) {
                pairs.add(s.substring(i, i + 2));
                pairs.add(s.substring(i, i + 2));
                continue;
            }

            if (i < s.length() - 2 && s.substring(i, i + 3).equals(times(s.charAt(i), 3)))
                continue;

            pairs.add(s.substring(i, i + 2));
        }
        return pairs.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting())).values().stream().anyMatch(l -> l > 1);
    }

    private String times(char s, int pow) {
        return IntStream.range(0, pow).mapToObj(i -> s + "").reduce(((s1, s2) -> s1 + s2)).orElse("");
    }

    @Override
    public String firstStar() {
        return Arrays.stream(inputString.split("\\n")).filter(this::isNice).count() + "";
    }

    @Override
    public String secondStar() {
        return Arrays.stream(inputString.split("\\n")).filter(this::isNicer).count() + "";
    }
}
