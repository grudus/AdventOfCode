import java.util.*;

public class Day19 {

    private HashSet<String> molecules = new HashSet<>();
    private ArrayList<String> hashSet = new ArrayList<>();
    private ArrayList<String> oneLetterNames = new ArrayList<>();
    private ArrayList<String> twoLettersNames = new ArrayList<>();
    private ArrayList<Molecule> mols = new ArrayList<>();
    private HashMap<String, String> backward = new HashMap<>();

    private final class Molecule {
        private String name;
        private ArrayList<String> possibleMolecules;

        private Molecule(String name, String possible) {
            this.name = name;
            possibleMolecules = new ArrayList<>();
            possibleMolecules.add(possible);
        }

        public void addMolecule(String name) {
            possibleMolecules.add(name);
        }

        public int getNumberOfMolecules() {
            return possibleMolecules.size();
        }

        public String getName() {return name;}

        public String getMoleculeAtIndex(final int index) {return possibleMolecules.get(index);}

        @Override
        public boolean equals(Object m) {
            return (m instanceof Molecule && ((Molecule) m).getName().equals(name));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(name);
            sb.append(" => ");
            for (String s : possibleMolecules) {
                sb.append(s);
                sb.append(" ");
            }
            return sb.toString();
        }

    }

    private Molecule get(ArrayList<Molecule> m, String name) {
        for (Molecule temp : m) if(temp.getName().equals(name)) return temp;
        return null;
    }

    private boolean contains(ArrayList<Molecule> m, String name) {
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getName().equals(name)) return true;
        }
        return false;
    }
    
    private void initialize(final String[] lines) {
        for (int i = 0; i < lines.length-2; i++) {
            final String frst = lines[i].split(" ")[0];
            final String scnd = lines[i].split(" ")[2];
            backward.put(scnd, frst);
            if (frst.length() == 1 && !oneLetterNames.contains(frst)) oneLetterNames.add(frst);
            if (frst.length() == 2 && !twoLettersNames.contains(frst)) twoLettersNames.add(frst);
            if (contains(mols, frst)) get(mols, frst).addMolecule(scnd);

            else mols.add(new Molecule(frst, scnd));
        }
    }
    
    private void fusion(final String medicine, ArrayList<String> set) {
        boolean isNull = set == null;
        for (int i = 0; i < medicine.length(); i++) {
            if (oneLetterNames.contains(medicine.substring(i, i + 1))) {
                final Molecule mlcl = get(mols, medicine.substring(i, i + 1));
                StringBuilder sb;
                for (int j = 0; j < mlcl.getNumberOfMolecules(); j++) {
                    sb = new StringBuilder(medicine.substring(0, i));
                    sb.append(mlcl.getMoleculeAtIndex(j));
                    sb.append(medicine.substring(i + 1));
                    if (isNull) molecules.add(sb.toString());
                    else addIfAbsent(set, sb.toString());
                }
            } else if (i < medicine.length() - 1 && twoLettersNames.contains(medicine.substring(i, i + 2))) {
                final Molecule mlcl = get(mols, medicine.substring(i, i + 2));
                StringBuilder sb;
                for (int j = 0; j < mlcl.getNumberOfMolecules(); j++) {
                    sb = new StringBuilder(medicine.substring(0, i));
                    sb.append(mlcl.getMoleculeAtIndex(j));
                    sb.append(medicine.substring(i + 2));
                    if (isNull) molecules.add(sb.toString());
                    else addIfAbsent(set, sb.toString());
                }
            }
        }
    }

    private String fission(String molecule) {
        int licznikPewnosci = 0;
        while (licznikPewnosci < 66 && !molecule.equals("e")) {
            for (String key : backward.keySet()) {
                if (molecule.contains(key)) {
                    molecule = molecule.replaceAll(key, backward.get(key));
                }
            }
            licznikPewnosci++;
        }
        return molecule;
    }




    public int wynik1(final String input) {
        final String[] lines = input.split("\\n");
        final String TO_CALIBRATE = lines[lines.length-1];

        initialize(lines);
        fusion(TO_CALIBRATE, null);

        return molecules.size();
    }

    private void addIfAbsent(ArrayList<String> arrayList, String s) {
        arrayList.forEach(string -> {
            if (string.equals(s)) return;
        });
        arrayList.add(s);
    }

    private void add(ArrayList<String> to, ArrayList<String> from) {
        to.addAll(from);
    }

    private void clean(ArrayList<String> arrayList) {arrayList.clear();}

    public int wynik2(final String input) {
        int wynik = 0;
        final String[] lines = input.split("\\n");
        final String medicine = lines[lines.length-1];
        
        initialize(lines);

//
//        while (wynik < 10 && !hashSet.contains(medicine)) {
//            final ArrayList<String> tempSet = new ArrayList<>();
//            hashSet.forEach(mol -> fusion(mol, tempSet));
//            clean(hashSet);
//            add(hashSet, tempSet);
//            wynik++;
//        }
        System.out.println(fission(medicine));
        return wynik;
    }



}
