package advent2015;

public class Day1 extends Day {

    public Day1(String input) {
        super(input);
    }

    int actualPosition = 0;

    @Override
    public String firstStar() {
        int c = inputString.chars().boxed().map(ch -> ch == '(' ? 1 : -1).reduce((i1, i2) -> i1 + i2).get();
        return c + "";
    }

    @Override
    public String secondStar() {
        return new Array<Character>(inputString).map(ch -> ch == '(' ? ++actualPosition : --actualPosition)
                .mapIndexed((Array.Pair::new))
                .filter(it -> it.second == -1)
                .map(it -> it.first + 1)
                .findFirst().get() + "";
    }

}
