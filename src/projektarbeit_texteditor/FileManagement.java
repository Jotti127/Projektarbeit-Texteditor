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
            JOptionPane.showMessageDialog(null, "Datei gespeichert");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Datei nicht gefunden: \n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler IOException: \n" + e.getMessage());
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
            JOptionPane.showMessageDialog(null, "Datei nicht gefunden: \n" + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler IOException: \n" + e.getMessage());
        }
        return content.toString();
    }
}
