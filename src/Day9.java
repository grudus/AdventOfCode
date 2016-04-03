import java.util.ArrayList;
import java.util.HashMap;

public class Day9 {

    private ArrayList<String[]> listName = new ArrayList<>();
    private ArrayList<Place> allPlaces = new ArrayList<>();

    private class Place {
        private String name;
        private HashMap<String, Integer> placesToGo;

        private Place(String name, String destiny, int distace) {
            this.name = name;
            placesToGo = new HashMap<>();
            placesToGo.putIfAbsent(destiny, distace);
        }

        public String getName() {return name;}
        public void addPlaceToGo(String place, int distance) {placesToGo.putIfAbsent(place, distance);}

        public int getDistance(String place) {return placesToGo.get(place);}

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(name);
            ArrayList<String> list = new ArrayList<>(placesToGo.keySet());
            sb.append(":\n");
            for (int i = 0; i < placesToGo.size(); i++) {
                sb.append(list.get(i));
                sb.append(" -> ");
                sb.append(placesToGo.get(list.get(i)));
                sb.append("\n");
            }
            return sb.toString();
        }

    }


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

    private boolean alreadyExist(ArrayList<Place> list, String place) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(place)) return true;
        }
        return false;
    }

    private Place getPlace(ArrayList<Place> list, String place) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(place)) return list.get(i);
        }
        return null;
    }


    private String[] toArray(ArrayList<Place> ar) {
        String[] toReturn = new String[ar.size()];
        for (int i = 0; i < ar.size(); i++) toReturn[i] = ar.get(i).getName();
        return toReturn;
    }

    private int wholeDistance(String[] string) {
        int distance = 0;
        for (int i = 0; i < string.length-1; i++) {
            distance += getPlace(allPlaces, string[i]).getDistance(string[i+1]);
        }
        return distance;
    }


    public int wynik1(String s) {
        int min, length;
        String[] lines = s.split("\\n");
        String place, destiny;
        for (int i = 0; i < lines.length; i++) {
            place = lines[i].split(" ")[0];
            destiny = lines[i].split(" ")[2];

            if (!alreadyExist(allPlaces, place)) {
                allPlaces.add(new Place(place, destiny, Integer.valueOf(lines[i].split(" ")[4])));
            }
            else getPlace(allPlaces, place).addPlaceToGo(destiny, Integer.parseInt(lines[i].split(" ")[4]));
            if (!alreadyExist(allPlaces, destiny)) allPlaces.add(new Place(destiny, place, Integer.valueOf(lines[i].split(" ")[4])));
            else getPlace(allPlaces, destiny).addPlaceToGo(place, Integer.parseInt(lines[i].split(" ")[4]));
        }

        kombinacja(allPlaces.size(), toArray(allPlaces), toArray(allPlaces));
        min = wholeDistance(listName.get(0));

        for (int i = 1; i < listName.size(); i++) {
            length = wholeDistance(listName.get(i));
            if (length < min) min = length;
        }


        return min;
    }
}
























