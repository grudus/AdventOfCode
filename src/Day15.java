public class Day15 {

    public int wynik1(String input) {
        int wynik = 0;
        input = input.replaceAll(",", "");
        final String[] lines = input.split("\\n");
        int[][] ingredients = new int[lines.length][5];

        for (int i = 0; i < lines.length; i++) {
            int j = 0;
            for (String temp : lines[i].split(" ")) {
                if (temp.matches("-?[0-9]+")) ingredients[i][j++] = Integer.valueOf(temp);
            }
        }


        int sum;
        long time = System.nanoTime();
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k <= 100; k++) {
                    for (int l = 0; l <= 100; l++) {
                        if (i + j + k + l == 100) {
//                            if((i*ingredients[0][4] + j*ingredients[1][4] + k*ingredients[2][4] + l*ingredients[3][4]) != 500) continue;
                            sum = 1;
                            for (int m = 0; m < 4; m++) {
                                sum *= (i*ingredients[0][m] + j*ingredients[1][m] + k*ingredients[2][m] + l*ingredients[3][m]);
                                if (sum < 0) sum = 0;
                            }
                            if (sum > wynik) {
                                wynik = sum;
                            }
                        }
                    }
                }
            }
        }

        System.out.println((System.nanoTime() - time) / 1000000);
        return wynik;
    }

}
