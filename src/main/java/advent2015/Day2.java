package advent2015;

public class Day2 {

    private int max(int a, int b, int c) {
        if (a == Math.max(a, b) && a == Math.max(a, c)) return a;
        if (b == Math.max(a, b) && b == Math.max(b, c)) return b;
        else return c;
    }

    public int wynik1(String s) {
        int wynik = 0;
        String[] lines = s.split("\\n");
        String[] dims;
        int x, y, z;

        for (int i = 0; i < lines.length; i++) {
            dims = lines[i].split("x");
            x = Integer.parseInt(dims[0]);
            y = Integer.parseInt(dims[1]);
            z = Integer.parseInt(dims[2]);
            wynik += 2*x*y + 2*x*z + 2*y*z + (x == max(x, y, z) ? y*z : (y == max(x, y, z) ? x*z : x*y));
        }

        return wynik;
    }

    public int wynik2(String s) {
        int wynik = 0;
        String[] lines = s.split("\\n");
        String[] dims;
        int x, y, z;

        for (int i = 0; i < lines.length; i++) {
            dims = lines[i].split("x");
            x = Integer.parseInt(dims[0]);
            y = Integer.parseInt(dims[1]);
            z = Integer.parseInt(dims[2]);

            wynik += x*y*z + (x == max(x, y, z) ? 2*y+2*z : (y == max(x, y, z) ? 2*x+2*z : 2*x+2*y));
        }


        return wynik;
    }
}
