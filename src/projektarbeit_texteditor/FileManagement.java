package projektarbeit_texteditor;

import java.io.*;

public class FileManagement {
    public static void save() {
        try (FileWriter writer = new FileWriter("Test-Datei");) {
            writer.write("Test-Text");
            System.out.println("Datei geschrieben.");
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
        } catch (IOException e) {
            System.out.println("Fehler IOException: " + e);
        }
    }

    public static void read() {
        String filePath = "//Users//Julius//Downloads//test.txt";
        try (BufferedReader reader = new BufferedReader((new FileReader(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
        } catch (IOException e) {
            System.out.println("Fehler IOException: " + e);
        }
    }
}
