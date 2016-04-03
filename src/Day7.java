import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Day7 {
    private final String[] OPTIONS = {"NOT", "AND", "OR", "LSHIFT", "RSHIFT", "->"};
    private TreeMap<String, Integer> wires = new TreeMap<>();
    private static final int MAX = (int)Math.pow(2, 16) - 1;

    private boolean inOptions(String s) {
        for (int i = 0; i < OPTIONS.length; i++) {
            if (s.matches(OPTIONS[i])) return true;
        }
        return false;
    }

    private boolean isNumberOrWireWithValue(String wire) {
        if (inOptions(wire)) return false;
        if (wire.matches("[0-9]+")) return true;
        if (wires.get(wire) == -1) return false;
        return true;
    }

    private int getValue(String wire) {
        if (wire.matches("[0-9]+")) return Integer.valueOf(wire);
        else return wires.get(wire);
    }

    private int howManyWiresHaveValue() {
        int wynik = 0;
        List<String> keys = new ArrayList<>(wires.keySet());
        for (int i = 0; i < keys.size(); i++) {
            if (wires.get(keys.get(i)) > -1) ++wynik;
        }
        return wynik;
    }

    public int wynik1(String s) {
        int wynik = 0;
        String[] lines = s.split("\n");
        String[] oneLine;

        for (int i = 0; i < lines.length; i++) {
            oneLine = lines[i].split(" ");
            if (oneLine.length == 3 && oneLine[1].matches("->")) {
                if (oneLine[0].matches("[0-9]+")) {
                    if (wires.containsKey(oneLine[2])) wires.replace(oneLine[2], Integer.valueOf(oneLine[0]));
                    else wires.put(oneLine[2], Integer.valueOf(oneLine[0]));
                }
            }
            for (int j = 0; j < oneLine.length; j++) {
                if (inOptions(oneLine[j])) continue;
                if (oneLine[j].matches("[0-9]+")) continue;
                wires.putIfAbsent(oneLine[j], -1);
            }
        }

        int petla = 0;

        while (wires.get("a") == -1 && petla < 10000) {
            for (int i = 0; i < lines.length; i++) {
                oneLine = lines[i].split(" ");
                if (oneLine.length == 5) {
                    if (isNumberOrWireWithValue(oneLine[0]) && isNumberOrWireWithValue(oneLine[2])) {
                        switch (oneLine[1]) {
                            case "AND": wires.replace(oneLine[4], (getValue(oneLine[0]) & getValue(oneLine[2]))); break;
                            case "OR": wires.replace(oneLine[4], (getValue(oneLine[0]) | getValue(oneLine[2]))); break;
                            case "LSHIFT": wires.replace(oneLine[4], (getValue(oneLine[0]) << getValue(oneLine[2]))); break;
                            case "RSHIFT": wires.replace(oneLine[4], (getValue(oneLine[0]) >> getValue(oneLine[2]))); break;
                            default: return -1;
                        }
                    }
                }
                else if (oneLine.length == 4) {
                    if (isNumberOrWireWithValue(oneLine[1])) wires.replace(oneLine[3], MAX - getValue(oneLine[1]));
                }
                else if (oneLine.length == 3) {
                    if (isNumberOrWireWithValue(oneLine[0])) wires.replace(oneLine[2], getValue(oneLine[0]));
                }
                else {System.out.println(lines[i]); return -2;}

            }
            ++petla;
        }

        System.out.println(howManyWiresHaveValue());
        System.out.println(wires);
        return wires.get("a");
    }

}
