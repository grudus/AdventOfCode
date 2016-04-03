public class Day3 {

    public int wynik1(String s) {
        int wynik = 1;
        int[][] domy;
        int maxx, maxy;
        int a, b, c, d;
        a = b = c = d = 0;
        int currX, currY;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '>': a++; break;
                case '<': b++; break;
                case '^': c++; break;
                case 'v': d++; break;
            }
        }
        maxx = Math.max(a, b);
        maxy = Math.max(c, d);

        currX = maxx;
        currY = maxy;

        domy = new int[maxx*2][maxy*2];

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '<':
                    if (domy[--currX][currY] == 0) {
                        domy[currX][currY]++;
                        wynik++;
                    }
                    break;
                case '>':
                    if (domy[++currX][currY] == 0) {
                        domy[currX][currY]++;
                        wynik++;
                    }
                    break;
                case '^':
                    if (domy[currX][++currY] == 0) {
                        domy[currX][currY]++;
                        wynik++;
                    }
                    break;
                case 'v':
                    if (domy[currX][--currY] == 0) {
                        domy[currX][currY]++;
                        wynik++;
                    }
                    break;
            }
        }

        return wynik;
    }

    int wynik2(String s) {
        int wynik = 1;
        int[][] domy;
        int maxx, maxy;
        int a, b, c, d;
        a = b = c = d = 0;
        int currSantaX, currSantaY, currRobotX, currRobotY;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '>': a++; break;
                case '<': b++; break;
                case '^': c++; break;
                case 'v': d++; break;
            }
        }
        maxx = Math.max(a, b);
        maxy = Math.max(c, d);

        currRobotX = currSantaX = maxx;
        currRobotY = currSantaY = maxy;

        domy = new int[maxx*2+1][maxy*2+1];
        domy[maxx][maxy] = 1;

        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                switch (s.charAt(i)) {
                    case '<':
                        if (domy[--currSantaX][currSantaY] == 0) {
                            domy[currSantaX][currSantaY]++;
                            wynik++;
                        }
                        break;
                    case '>':
                        if (domy[++currSantaX][currSantaY] == 0) {
                            domy[currSantaX][currSantaY]++;
                            wynik++;
                        }
                        break;
                    case '^':
                        if (domy[currSantaX][++currSantaY] == 0) {
                            domy[currSantaX][currSantaY]++;
                            wynik++;
                        }
                        break;
                    case 'v':
                        if (domy[currSantaX][--currSantaY] == 0) {
                            domy[currSantaX][currSantaY]++;
                            wynik++;
                        }
                        break;
                }
            }
            else {
                switch (s.charAt(i)) {
                    case '<':
                        if (domy[--currRobotX][currRobotY] == 0) {
                            domy[currRobotX][currRobotY]++;
                            wynik++;
                        }
                        break;
                    case '>':
                        if (domy[++currRobotX][currRobotY] == 0) {
                            domy[currRobotX][currRobotY]++;
                            wynik++;
                        }
                        break;
                    case '^':
                        if (domy[currRobotX][++currRobotY] == 0) {
                            domy[currRobotX][currRobotY]++;
                            wynik++;
                        }
                        break;
                    case 'v':
                        if (domy[currRobotX][--currRobotY] == 0) {
                            domy[currRobotX][currRobotY]++;
                            wynik++;
                        }
                        break;
                }
            }

        }



        return wynik;
    }

}
