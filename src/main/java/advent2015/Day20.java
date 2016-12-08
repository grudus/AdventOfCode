package advent2015;

public class Day20 {

    public int wynik1(final String input) {

        final int maxPresents = Integer.parseInt(input);
        int presents = 0, counter = 840840

                ;

        while (presents < maxPresents) {
            presents = getPresents2(++counter);
        }



        return counter;
    }


    private int getPresents2(final int houses) {
        int counter = houses*11;
        final int half = houses/2;
        final int start = (houses % 50 == 0 ? houses/50 : houses/50+1);
        for (int i = start; i <= half; i++) {
            if (houses % i == 0) counter += 11*i;
        }
        return counter;

    }

    private int getPresents(final int houses) {
        int presents = 0;
        final int half = houses/2;
        for (int i = 1; i <= half; i++) {
            if (houses % i == 0) presents += 10*i;
        }
        presents += 10*houses;
        return presents;
    }

}

//982800 too high 831601
