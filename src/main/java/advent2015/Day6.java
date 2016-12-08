package advent2015;

public class Day6 {

    private boolean[][] lights;
    private int[][] newLights;
    private int lvl;

    public Day6(int lvl) {
        this.lvl = lvl;
        if (lvl == 1) {
            lights = new boolean[1000][1000];
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    lights[i][j] = false;
                }
            }
        }
        else if (lvl == 2) {
            newLights = new int[1000][1000];
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    newLights[i][j] = 0;
                }
            }
        }
    }

    private void toggle1(int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (lights[i][j]) lights[i][j] = false;
                else lights[i][j] = true;
            }
        }

    }

    private void turnOn1(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                lights[i][j] = true;
            }
        }
    }

    private void turnOff1(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                lights[i][j] = false;
            }
        }
    }

    private void toggle2(int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                newLights[i][j] += 2;
            }
        }

    }

    private void turnOn2(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                newLights[i][j] += 1;
            }
        }
    }

    private void turnOff2(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (newLights[i][j] > 0) newLights[i][j]--;
            }
        }
    }

    int wynik1(String s) {
        int wynik = 0;
        String[] lines = s.split("\n");
        String[] work;
        if (lvl == 1) {
            for (int i = 0; i < lines.length; i++) {
                work = lines[i].split(" ");
                switch (work[0]) {
                    case "toggle":
                        toggle1(Integer.parseInt(work[1].split(",")[0]), Integer.parseInt(work[1].split(",")[1]),
                                Integer.parseInt(work[3].split(",")[0]), Integer.parseInt(work[3].split(",")[1]));
                        break;
                    case "turn":
                        if (work[1].matches("on"))
                            turnOn1(Integer.parseInt(work[2].split(",")[0]), Integer.parseInt(work[2].split(",")[1]),
                                    Integer.parseInt(work[4].split(",")[0]), Integer.parseInt(work[4].split(",")[1]));
                        else turnOff1(Integer.parseInt(work[2].split(",")[0]), Integer.parseInt(work[2].split(",")[1]),
                                Integer.parseInt(work[4].split(",")[0]), Integer.parseInt(work[4].split(",")[1]));
                        break;
                    default:
                        return -1;
                }
            }

            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    if (lights[i][j]) wynik++;
                }
            }

            return wynik;
        }
        else {
            for (int i = 0; i < lines.length; i++) {
                work = lines[i].split(" ");
                switch (work[0]) {
                    case "toggle":
                        toggle2(Integer.parseInt(work[1].split(",")[0]), Integer.parseInt(work[1].split(",")[1]),
                                Integer.parseInt(work[3].split(",")[0]), Integer.parseInt(work[3].split(",")[1]));
                        break;
                    case "turn":
                        if (work[1].matches("on"))
                            turnOn2(Integer.parseInt(work[2].split(",")[0]), Integer.parseInt(work[2].split(",")[1]),
                                    Integer.parseInt(work[4].split(",")[0]), Integer.parseInt(work[4].split(",")[1]));
                        else turnOff2(Integer.parseInt(work[2].split(",")[0]), Integer.parseInt(work[2].split(",")[1]),
                                Integer.parseInt(work[4].split(",")[0]), Integer.parseInt(work[4].split(",")[1]));
                        break;
                    default:
                        return -1;
                }
            }

            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    wynik += newLights[i][j];
                }
            }
            return wynik;
        }
    }

}
