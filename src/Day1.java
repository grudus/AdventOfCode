public class Day1 {

    int wynik1(String s) {
        int wynik = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') wynik++;
            else if (s.charAt(i) == ')') wynik--;
        }

        return wynik;
    }

    int wynik2(String s) {
        int wynik = 0;
        int pietro = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                pietro++;
                wynik++;
            } if (s.charAt(i) == ')') {
                pietro--;
                wynik++;
            }
            if (pietro == -1) return wynik;
        }


        return 0;
    }

}
