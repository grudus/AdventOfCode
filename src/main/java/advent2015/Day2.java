package advent2015;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    public Day2(String input) {
        super(input);
    }

    private final List<Cuboid> cuboids = Arrays.stream(inputString.split("\\n"))
            .map(line -> {
                int[] numbers = Arrays.stream(line.split("x")).mapToInt(Integer::parseInt).toArray();
                return new Cuboid(numbers[0], numbers[1], numbers[2]);
            }).collect(Collectors.toList());

    @Override
    public String firstStar() {
        return cuboids.stream().mapToInt(cuboid -> cuboid.area() + cuboid.smallestArea()).sum() + "";
    }

    @Override
    public String secondStar() {
        return cuboids.stream().mapToInt(cuboid -> cuboid.volume() + cuboid.ribbon()).sum() + "";
    }

    class Cuboid {
        final int a;
        final int b;
        final int c;

        Cuboid(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        int ribbon() {
            return Arrays.stream(new int[]{a, b, c})
                    .sorted().limit(2).reduce((i1, i2) -> i1 * 2 + i2 * 2).getAsInt();
        }

        int area() {
            return 2 * a * b + 2 * b * c + 2 * a * c;
        }

        int volume() {
            return a * b * c;
        }

        int smallestArea() {
            return Math.min(Math.min(a * b, a * c), b * c);
        }
    }
}
