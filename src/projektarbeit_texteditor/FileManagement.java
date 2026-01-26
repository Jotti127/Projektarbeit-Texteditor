package projektarbeit_texteditor;

//Importierte Klassen
import javax.swing.*;
import java.io.*;

//FileManagement nur für Datei-Handling
public class FileManagement {

    //Datei-Handling speichern
    public static void save(File file, String saveText) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(saveText);
            System.out.println("Datei geschrieben.");
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
        } catch (IOException e) {
            System.out.println("Fehler IOException: " + e);
        }
    }

    //Datei-Handling laden
    public static String load(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader((new FileReader(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
        } catch (IOException e) {
            System.out.println("Fehler IOException: " + e);
        }
        return content.toString();
    }
}
