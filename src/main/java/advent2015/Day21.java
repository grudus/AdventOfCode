package advent2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


// #Nie do końca działa wynik2.

public class Day21 {

    private Item[] items;
    private ArrayList<Integer> prices = new ArrayList<>();
    private int bossHealth, bossAtack, bossDefence;
    private static final int PLAYER_HEALTH = 100;

    private class Player {

        private int health, damage, armor, money;

        private Player(int health, int damage, int armor) {
            this.health = health;
            this.damage = damage;
            this.armor = armor;
        }

        public void atack(Player another) {
            int delta = this.damage - another.armor;
            if (delta < 1) delta = 1;
            another.health -= delta;
        }
        public boolean isAlive() {return health > 0;}

        public void buy(int damage, int armor, int money) {
            this.damage += damage;
            this.armor += armor;
            this.money += money;
        }

    }

    private class Item {
        private int plusDagame, plusArmor, price;
        private String name;
        private Item(String n, int d, int a, int p) {
            name = n;
            plusArmor = a;
            plusDagame = d;
            price = p;
        }

        public int getDamage() {return plusDagame;}
        public int getArmor() {return plusArmor;}
        public int howMuch() {return price;}
        public boolean isWeapon() {return !isAttackRing() && plusDagame > 0;}
        public boolean isArmor() {return !isDefenceRing() && plusArmor > 0;}
        public boolean isAttackRing() {return name.startsWith("Damage");}
        public boolean isDefenceRing() {return name.startsWith("Defense");}
        public String toString() {
            return name + " " + price + "$ " + plusDagame + "dam " + plusArmor + "arm ";
        }
    }

    public int wynik1(String input) {
        int wynik = 0;
        final String[] lines = input.split("\\n");
        items = new Item[lines.length - 3];
        bossAtack = Integer.parseInt(lines[1].split(" ")[1]);
        bossDefence = Integer.parseInt(lines[2].split(" ")[1]);
        bossHealth = Integer.parseInt(lines[0].split(" ")[2]);
        Player me = new Player(100, 0, 0);
        Player boss = new Player(bossHealth, bossAtack, bossDefence);



        for (int i = 3; i < lines.length; i++) {
            final String[] temp = lines[i].split("\\s+");
            items[i-3] = new Item(temp[0], Integer.parseInt(temp[2]),
                    Integer.parseInt(temp[3]), Integer.parseInt(temp[1]));
        }


        for (int i = 1; i < items.length; i++) {
            combinationsOfIndexes(items.length, i);
        }

        Collections.sort(prices, Comparator.reverseOrder());
        return prices.get(0);
    }
// odp z przedzialu (0, 213)
    private void check(final int[] indexes) {
        int playerAtack = 0, playerDefence = 0, price = 0;
        int attackRings = 0, defenceRings = 0;
        int bossDelta, playerDelta;
        boolean armor = false, weapon = false;

        for (int i = 0; i < indexes.length; i++) {
            final Item item = items[indexes[i]];
            if (item.isAttackRing()) {
                if (attackRings < 2) {
                    ++attackRings;
                    playerAtack += item.getDamage();
                    price += item.howMuch();
                }
            }
            if (item.isDefenceRing()) {
                if (defenceRings < 2) {
                    ++defenceRings;
                    playerDefence += item.getArmor();
                    price += item.howMuch();
                }
            }
            else {
                if (item.isWeapon()) {
                    if (!weapon) {
                        playerAtack += item.getDamage();
                        price += item.howMuch();
                        weapon = true;
                    }
                }
                else if (item.isArmor()) {
                    if (!armor) {
                        playerDefence += item.getArmor();
                        price += item.howMuch();
                        armor = true;
                    }
                }
            }
        }
        if (!weapon) return;

        bossDelta = (playerAtack - bossDefence > 0 ? playerAtack - bossDefence : 1);
        playerDelta = (bossAtack - playerDefence > 0 ? bossAtack - playerDefence : 1);
        final int howManyTimesPlayerGetsDamageBeforeDead = (PLAYER_HEALTH % playerDelta == 0 ? PLAYER_HEALTH/playerDelta : PLAYER_HEALTH/playerDelta+1);
        final int howManyTimesBossGetsDamageBeforeDead = (bossHealth % bossDelta == 0 ? bossHealth/bossDelta : bossHealth/bossDelta+1);

        if (howManyTimesPlayerGetsDamageBeforeDead <= howManyTimesBossGetsDamageBeforeDead) {
            prices.add(price);
        }

    }

    private void combinationsOfIndexes(final int N, final int k) {
        if (N < 0 || k < 0 || k > N) {
            System.err.println("Arguments Error");
            return;
        }
        int[] t = new int[k];
        for (int i = 0; i < k; i++) t[i] = i;
        if (k == N) {
            check(t);
            return;
        }
        int i = k-1;
        check(t);
        while (t[0] != (N-k)) {
            if (t[i] == (N-k+i)) {
                i--;
                continue;
            }
            t[i]++;
            for (int j = i+1, m = 1; j < k; j++, m++) t[j] = t[i] + m;
            check(t);
            if (t[k-1] == N-1) continue;
            i = k - 1;

        }



    }



}
