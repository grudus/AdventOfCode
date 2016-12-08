package advent2015;

public class Day18 {

    private boolean[][] lights;
    private int[][] hmnao;

    public int wynik1(final String input) {
        int wynik = 0;
        final String[] lines = input.split("\\n");

        lights = new boolean[lines.length][lines[0].length()];
        hmnao = new int[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                hmnao[i][j] = -1;
                switch (lines[i].charAt(j)) {
                    case '#': lights[i][j] = true; break;
                    case '.': lights[i][j] = false; break;
                }
            }
        }
        for (int n = 0; n < 2*lights.length; n++) {
            for (int i = 0; i < lights.length; i++) {
                for (int j = 0; j < lights[i].length; j++) {
                    if (i == 0 && (j == 0 || j == lights[i].length-1) || i == lines.length-1 && (j == 0 || j == lights[i].length-1)) continue;
                    if (n % 2 == 0) howManyNeighborsAreOn(i, j);
                    else {
                        final int temp = hmnao[i][j];
                        if (lights[i][j]) {
                            if (temp != 2 && temp != 3) lights[i][j] = false;
                        }
                        else {
                            if (temp == 3) lights[i][j] = true;
                        }

                    }
                }
            }
        }

        for (int i = 0; i < lights.length; i++) {
            for (int j = 0; j < lights[i].length; j++) {
                if (lights[i][j]) wynik++;
            }
//            System.out.println();
        }

        return wynik;
    }

    private int howManyNeighborsAreOn(final int y, final int x) {
        int result = 0;
        int yStart, yEnd, xStart, xEnd;
        yStart = yEnd = xStart = xEnd = -1;
        if (y > 0 && y < lights.length-1) {
            yStart = y-1;
            yEnd = y+2;
        }
        else if (y == 0) {
                yStart = y;
                yEnd = y+2;
        }
        else if (y == lights.length - 1) {
            yStart = y-1;
            yEnd = y+1;
        }

        if (x > 0 && x < lights[y].length-1) {
            xStart = x - 1;
            xEnd = x + 2;
        }
        else if (x == 0) {
            xStart = x;
            xEnd = x+2;
        }
        else if (x == lights[y].length-1) {
            xStart = x-1;
            xEnd = x+1;
            }

        for (int i = yStart; i < yEnd; i++) {
            for (int j = xStart; j < xEnd; j++) {
                if (i == y && x == j) continue;
                if (lights[i][j]) result++;
            }
        }
        hmnao[y][x] = result;
        return result;
    }


}
