package advent2015;

public class Day16 {

    private static final String[] itemsNames = {"children", "cats", "samoyeds" ,"pomeranians", "akitas",
    "vizslas", "goldfish", "trees", "cars", "perfumes"};
    private static final int[] auntSue = {3, 7, 2, 3, 0, 0, 5, 3, 2, 1};


    private boolean equals(String item, int value) {
        for (int i = 0; i < itemsNames.length; i++) {
            if (itemsNames[i].equals(item)) {
                /*if (i == 1 || i == 7) {       uncomment for 2nd star
                    if (auntSue[i] < value) return true;
                }
                else if (i == 3 || i == 6) {
                    if (auntSue[i] > value) return true;
                }
                else*/ if (auntSue[i] == value) return true;
            }
        }
        return false;
    }

    public int wynik1(String input) {
        int wynik = -1;
        input = input.replaceAll(":", "");
        input = input.replaceAll(",", "");
        String[] lines = input.split("\\n");

        for (int i = 0; i < lines.length; i++) {
            final String[] temp = lines[i].split(" ");
            if (equals(temp[2], Integer.valueOf(temp[3])) &&
                    equals(temp[4], Integer.valueOf(temp[5])) &&
                    equals(temp[6], Integer.valueOf(temp[7]))) System.out.println(i+1);
        }

        return wynik;
    }

}
