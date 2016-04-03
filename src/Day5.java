import java.util.ArrayList;

public class Day5 {

    private boolean isVowel(char c) {
        final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < vowels.length; i++) {
            if (c == vowels[i]) return true;
        }
        return false;
    }

    private boolean isNice1(String s) {
        int vowCount = 0;
        boolean doubles = false;
        for (int i = 0; i < s.length(); i++) {
            if (vowCount < 3) {
                if (isVowel(s.charAt(i))) vowCount++;
            }
            if (i < s.length() - 1) {
                if (!doubles && s.charAt(i) == s.charAt(i+1)) doubles = true;
                if (s.substring(i, i+2).matches("ab") || s.substring(i, i+2).matches("cd")
                        || s.substring(i, i+2).matches("pq") || s.substring(i, i+2).matches("xy")) return false;
            }
        }

        return (vowCount == 3 && doubles);
    }


    private boolean isNice2(String s) {
        ArrayList<String> pairs = new ArrayList<>();
        boolean isPair = false, oxo = false;
        for (int i = 0; i < s.length(); i++) {
            if (!isPair) {
                if (i < s.length()-1) {
                    if (pairs.contains(s.substring(i, i+2)) && i > 1) {
                        if (!s.substring(i, i+2).matches(s.substring(i-2, i))) {
                            if (!s.substring(i, i + 2).matches(s.substring(i - 1, i + 1))) isPair = true;
                        }
                        else isPair = true;
                    }
                    else pairs.add(s.substring(i, i+2));
                }
            }
            if (!oxo) {
                if (i < s.length() - 2) {
                    if (s.charAt(i) == s.charAt(i+2)) oxo = true;
                }
            }
        }


        return (isPair && oxo);
    }

    public int wynik(String s) {
        int wynik = 0;
        String lines[] = s.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if (isNice2(lines[i])) wynik++;
        }

        return wynik;
    }

}
