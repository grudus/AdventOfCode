public class Day11 {

    private String password = new String();

    private int abc() {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) <= 'x' && password.charAt(i) + 1 == password.charAt(i+1) && password.charAt(i) + 2 == password.charAt(i+2)) return i;
        }
        return -1;
    }

    private boolean hasILO() {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == 'i' || password.charAt(i) == 'l' || password.charAt(i) == 'o') return true;
        }
        return false;
    }

    private boolean hasTwoPairsWithoutOverlap() {
        int pairs = 0;
        for (int i = 0; i < password.length()-1; i++) {
            if (password.charAt(i) == password.charAt(i+1)) {
                if (i < password.length() - 3 && password.substring(i, i+2).equals(password.substring(i+2, i+4))) return true;
                if (i < password.length() - 2) {
                    if (password.charAt(i) == password.charAt(i+2)) continue;
                    else pairs++;
                }
                else pairs++;
            }
            if (pairs == 2) return true;
        }
        return pairs == 2;
    }

    private boolean meetsTheConditions() {
        return hasTwoPairsWithoutOverlap() && !hasILO() && (abc() > -1);
    }

    private String increment(int index, char[] dupa) {
        if (index > 0 && dupa[index] == 'z') {
            dupa[index] = 'a';
            increment(index-1, dupa);
        }
        else dupa[index]++;
       return new String(dupa);
    }


    public String wynik1(String input, int index) {
        password = input;

        while (!meetsTheConditions())
        password = increment(password.length()-1, password.toCharArray());

        if (index == 1) return password;
        else {
            return wynik1(increment(password.length()-1, password.toCharArray()), 1);
        }
    }




}
