public class Day10 {

    private String lookAndSay(String prev) {
        StringBuilder sb = new StringBuilder();
        int theSame = 1;
        for (int i = 0; i < prev.length(); i++) {
            if (i < prev.length() - 1 && prev.charAt(i) == prev.charAt(i+1)) {
                ++theSame;
                continue;
            }
            if (theSame == 1) {
                sb.append("1");
                sb.append(prev.charAt(i));
                continue;
            }
            else {
                sb.append(String.valueOf(theSame));
                sb.append(prev.charAt(i));
            }
            theSame = 1;
        }

        return sb.toString();
    }

    public int wynik1(String s) {
        String temp = "1113122113";

        for (int i = 0; i < 50; i++) {
            temp = lookAndSay(temp);
        }

        return temp.length();
    }
}
