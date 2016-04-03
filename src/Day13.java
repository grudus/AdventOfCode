import java.util.ArrayList;
import java.util.HashMap;

public class Day13 {

    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<String[]> listName = new ArrayList<>();
    int counter = 0;

    private class Person {
        private String name;
        private HashMap<String, Integer> friends;

        private Person(String name, String friend, int happiness) {
            this.name = name;
            friends = new HashMap<>();
            friends.put(friend, happiness);
        }

        public String getName() {return name;}
        public void addFriend(String s, int i) {friends.putIfAbsent(s, i);}
        public int getHappiness(String s) {return friends.get(s);}

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(":\n");
            for (String nm : friends.keySet()) {
                sb.append(nm);
                sb.append(" -> ");
                sb.append(friends.get(nm));
                sb.append("\n");
            }
            return sb.toString();
        }

    }

    private boolean contains(String name) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getName().equals(name)) return true;
        }
        return false;
    }

    private Person getGlobal(String name) {
        for (Person p : people) if (p.getName().equals(name)) return p;
        return null;
    }

    private void printf(String[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : "]"));
        }
        System.out.println();
    }

    private int getWholeHappiness(String[] s) {
        int ans = 0;
        for (int i = 0; i < s.length-1; i++) {
            ans += getGlobal(s[i]).getHappiness(s[i+1]) + getGlobal(s[i+1]).getHappiness(s[i]);
        }
        ans += getGlobal(s[0]).getHappiness(s[s.length-1]) + getGlobal(s[s.length-1]).getHappiness(s[0]);
        return ans;
    }

//vvvvvvvvvvvvvvvvvvvv COMBINATIONS vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    private String[] copyWithoutFirst(String[] arr) {
        String[] scd = new String[arr.length - 1];
        for (int i = 0; i < scd.length; i++) {
            scd[i] = arr[i+1];
        }
        return scd;
    }

    private String[] copy(String[] main, String[] toCopy, int index) {
        String[] toReturn = new String[main.length];
        for (int i = 0; i < main.length; i++) {
            if (i < index) toReturn[i] = main[i];
            else toReturn[i] = toCopy[i-index];
        }
        return toReturn;
    }

    private void addIfAbsent(String[] toAdd) {
        int theSame;
        for (int i = 0; i < listName.size(); i++) {
            theSame = 0;
            for (int j = 0; j < listName.get(i).length; j++) {
                if (toAdd[j].equals(listName.get(i)[j])) theSame++;
            }
            if (theSame == toAdd.length) return;
        }
        listName.add(toAdd);
    }


    private void kombinacja(int mainArrayLenght, String[] tempArray, String[] toPrint) {
        int delta;
        System.out.println(counter++);

        delta = mainArrayLenght - tempArray.length;
        if (tempArray.length == 1) return;


        for (int i = 0; i < tempArray.length; i++) {
            final String frst = tempArray[i];
            tempArray[i] = tempArray[0];
            tempArray[0] = frst;
            toPrint = copy(toPrint, tempArray, delta);
            addIfAbsent(toPrint);

            kombinacja(mainArrayLenght, copyWithoutFirst(tempArray), toPrint);
        }
    }

    private String[] toArray(ArrayList<Person> ar) {
        String[] toReturn = new String[ar.size()];
        for (int i = 0; i < ar.size(); i++) toReturn[i] = ar.get(i).getName();
        return toReturn;
    }

//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ COMBINATIONS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public int wynik1(String s) {
        int wynik = 0;
        s = s.replaceAll("\\.", "");
        String[] lines = s.split("\\n");

        for (String line : lines) {
            final String[] dupa = line.split(" ");
            final int hp = (dupa[2].equals("gain") ? Integer.parseInt(dupa[3]) : 0 - Integer.parseInt(dupa[3]));

            if (!contains(dupa[0])) people.add(new Person(dupa[0], dupa[10], hp));
            else getGlobal(dupa[0]).addFriend(dupa[10], hp);
        }


        kombinacja(people.size(), toArray(people), toArray(people));
        wynik = getWholeHappiness(listName.get(0));
        for (int i = 1; i < listName.size(); i++) {
            final int temp = getWholeHappiness(listName.get(i));
            if (temp > wynik) wynik = temp;
        }



        return wynik;
    }

}
