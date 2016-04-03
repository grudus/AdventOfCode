import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String input = "";
        String line;
        URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
        File file = new File(location.getFile() + "plik.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) input += line + "\n";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(new Day22().wynik1(input));




    }


}
