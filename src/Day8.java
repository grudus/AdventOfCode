public class Day8 {

    private int allCharacters(String[] s) {
        int wynik = 0;
        for (int i = 0; i < s.length; i++) {
            wynik += s[i].length();
        }
        return wynik;
    }

    public int wynik1(String s, int lvl) {
        int dodatkowe = 0;
        int characters = 0;
        String[] lines = s.split("\\n");

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length() - 1; j++) {
                if (lines[i].charAt(j) == '"') {dodatkowe ++; continue; }
                if (lines[i].charAt(j) == '\\') {
                    if (lines[i].charAt(j+1) == '\\' || lines[i].charAt(j+1) == '"') {
                        characters++;
                        dodatkowe += 2;
                        j++;
                        continue;
                    }
                    if (lines[i].charAt(j+1) == 'x') {
                        characters++;
                        dodatkowe++;
                        j += 3;
                    }
                }
                else characters++;
            }
            dodatkowe += 3;
        }

        if (lvl == 1) return allCharacters(lines) - characters;
        return dodatkowe;
    }

}
