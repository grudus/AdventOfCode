public class Day14 {

    private static final int TIME = 2503;
//  ################################ 1ST ###########################################
    private boolean isRunning(int timeSpeed, int timeRest) {
        float scala = (float)timeSpeed / ((float)(timeSpeed+timeRest));
        float dur = ((float)TIME/(timeSpeed+timeRest)) - (TIME/(timeSpeed+timeRest));
        return dur < scala;
    }

    public int wynik1(String input) {
        int wynik = 0;
        String[] lines = input.split("\\n");

        for (int i = 0; i < lines.length; i++) {
            final int timeSpeed = Integer.parseInt(lines[i].split(" ")[6]);
            final int timeRest = Integer.parseInt(lines[i].split(" ")[13]);
            final int velocity = Integer.parseInt(lines[i].split(" ")[3]);
            final int nTimes = TIME/(timeSpeed+timeRest);
            if (isRunning(timeSpeed, timeRest)) {
                final int distance = nTimes * velocity * timeSpeed + velocity * (TIME - nTimes*(timeRest+timeSpeed));
                if (distance > wynik) wynik = distance;
            }
            else {
                final int distance = (nTimes+1)*velocity*timeSpeed;
                if (distance > wynik) wynik = distance;
            }

        }

        return wynik;
    }

//    ############################# 2ND #########################################

    private class Reindeer {
        private String name;
        private int velocity, timeSpeed, timeRest, points;

        private Reindeer(String nm, int sp, int ts, int tr) {
            name = nm;
            velocity = sp;
            timeSpeed = ts;
            timeRest = tr;
        }

        public String getName() {
            return name;
        }

        public int getVelocity() {
            return velocity;
        }

        public int getTimeSpeed() {
            return timeSpeed;
        }

        public int getTimeRest() {
            return timeRest;
        }

        public int getPoints() {
            return points;
        }

        public void addPoint() {++points;}

        public int getDistance(int time) {
            int distance;
            final int nTimes = time/(timeSpeed+timeRest);
            if (isRunning(time)) distance = nTimes * velocity * timeSpeed + velocity * (time - nTimes*(timeRest+timeSpeed));
            else distance = (nTimes+1)*velocity*timeSpeed;
            return distance;
        }

        private boolean isRunning(int time) {
            float scala = (float)timeSpeed / ((float)(timeSpeed+timeRest));
            float dur = ((float)time/(timeSpeed+timeRest)) - (time/(timeSpeed+timeRest));
            return dur < scala;
        }
    }

    public int wynik2(String input) {
        int wynik = 0;
        int oneSecond;
        int winner = -1;
        String[] lines = input.split("\\n");
        Reindeer[] reindeers = new Reindeer[lines.length];
        for (int i = 0; i < lines.length; i++) {
            final String[] temp = lines[i].split(" ");
            reindeers[i] = new Reindeer(temp[0], Integer.parseInt(temp[3]), Integer.parseInt(temp[6]), Integer.parseInt(temp[13]));
        }

        for (int i = 1; i <= TIME; i++) {
            oneSecond = 0;
            winner = -1;
            for (int j = 0; j < reindeers.length; j++) {
                if (reindeers[j].getDistance(i) > oneSecond) {
                    oneSecond = reindeers[j].getDistance(i);
                    winner = j;
                }
            }
            reindeers[winner].addPoint();
        }

        for (Reindeer r : reindeers) {
            if (r.getPoints() > wynik) wynik = r.getPoints();
        }

        return wynik;
    }


}
