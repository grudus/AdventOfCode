package advent2015;

import java.util.ArrayList;

public class Day12 {

    private class Brace {
        private int index;
        private boolean opening;

    }

    private boolean contains(String regex, char c) {
        for (char ch : regex.toCharArray()) if (c == ch) return true;
        return false;
    }


    private String[] split(String sentence, String regex) {
        int howMany = 0;
        String[] toReturn;
        int temp = 0;
        int next = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (contains(regex, sentence.charAt(i))) {
                if (i == sentence.length() - 1) break;
                if (i < sentence.length() - 1 && contains(regex, sentence.charAt(i+1))) continue;
                howMany++;
            }
        }
        howMany++;
        toReturn = new String[howMany];
        howMany = 0;

        for (int i = 0; i < sentence.length(); i++) {
            if (contains(regex, sentence.charAt(i))) {
                if (i == sentence.length() - 1) {next++; break;}
                if (i < sentence.length() - 1 && contains(regex, sentence.charAt(i+1))) {next++; continue;}
                toReturn[howMany++] = sentence.substring(temp, i-next);
                temp = i+1;
                next = 0;
            }
        }
        toReturn[howMany] = sentence.substring(temp, sentence.length()-next);

        return toReturn;
    }

    private boolean isInteger(String s) {
        try {
            Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public int wynik1(String input) {
        int wynik = 0;

        String[] dupa = split(input, " ,{}[]:");
        for (String s : dupa) if (isInteger(s)) wynik += Integer.valueOf(s);
        return wynik;
    }

    private String remove(String string, int begin, int end) {
        char[] array = new char[string.length() - end + begin - 1];
        for (int i = 0; i < begin; i++) {
            array[i] = string.charAt(i);
        }
        for (int i = end+1; i < string.length(); i++) array[i-end+begin-1] = string.charAt(i);

        return new String(array);
    }

    public int wynik2(String input) {
        String s = input;
        ArrayList<Integer> openingBraces = new ArrayList<>();
        boolean inArray = false;
        boolean wasRed = false;
        int objectsDepth = 0, arraysDepth = 0, objectsInArray = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                inArray = true;
                arraysDepth++;
            }
            else if (s.charAt(i) == ']') {
                arraysDepth--;
                inArray = (arraysDepth > 0 && objectsInArray == 0);
            }
            else if (s.charAt(i) == '{') {
                if (wasRed) objectsDepth++;
                if (inArray || objectsInArray > 0) objectsInArray++;
                openingBraces.add(i);
                inArray = (objectsInArray == 0);
            }
            else if (s.charAt(i) == '}') {
                if (objectsInArray > 0) objectsInArray--;
                inArray = (arraysDepth > 0 && objectsInArray == 0);
                if (objectsDepth > 0) {
                    objectsDepth--;
                    openingBraces.remove(openingBraces.size() - 1);
                    continue;
                }
                if (!wasRed) {
                    openingBraces.remove(openingBraces.size() - 1);
                }
                else {
                    s = remove(s, openingBraces.get(openingBraces.size()-1), i);
                    i = openingBraces.get(openingBraces.size()-1);
                    openingBraces.remove(openingBraces.size()-1);
                    wasRed = false;
                }
            }
            else if (!inArray && i < s.length() - 2 && s.substring(i, i+3).equals("red")) {
                wasRed = true;
            }
        }

        System.out.println(s);
        return wynik1(s);
    }

}
