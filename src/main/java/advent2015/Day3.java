package advent2015;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day {


    public Day3(String input) {
        super(input);
    }

    enum Direction {
        N(0, 1), E(1, 0), S(0, -1), W(-1, 0);

        final int dx, dy;

        Direction(int dx, int dy) {this.dx = dx; this.dy = dy;}
    }

    private Point santaPosition = new Point(0, 0);
    private Point robotPosition = new Point(0, 0);

    private List<Direction> directions = inputString.chars().mapToObj(Char -> {
        switch ((char) Char) {
            case '^': return Direction.N;
            case '>': return Direction.E;
            case 'v': return Direction.S;
            default: return Direction.W;
        }
    }).collect(Collectors.toList());

    private List<Point> santaPositions() {
        return directions.stream().map(dir -> changePositionAndGet(dir, santaPosition)).collect(Collectors.toList());
    }

    private List<Point> santaAndRobotPositions() {
        return new Array<>(directions).mapIndexed((index, dir) ->
                (index % 2 == 0 ? changePositionAndGet(dir, santaPosition)
                        : changePositionAndGet(dir, robotPosition))).toList();
    }

    private Point changePositionAndGet(Direction dir, Point position) {
        position.setLocation(position.x + dir.dx, position.y + dir.dy);
        return position.getLocation();
    }

    @Override
    public String firstStar() {
        return countDistinctPositions(santaPositions()) + "";
    }

    @Override
    public String secondStar() {
        return countDistinctPositions(santaAndRobotPositions()) + "";
    }

    private long countDistinctPositions(List<Point> positions) {
        return positions.stream().distinct().count()
                + (positions.contains(new Point(0, 0)) ? 0 : 1);
    }
}
