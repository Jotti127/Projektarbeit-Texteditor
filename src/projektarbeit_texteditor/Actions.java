package projektarbeit_texteditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Actions {
    public static void neu(JTextArea textArea) {
        int result = JOptionPane.showConfirmDialog(null,
                "Wollen Sie wirklich ein neues Dokument erstellen?",
                "Neues Dokument?",
                JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            textArea.setText("");
        }
    }
    public static void exit() {
        System.exit(0);
    }
    public static void saveDialog(String saveText) {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filenameFilter = new FileNameExtensionFilter("Textdateien (*.txt)", "txt");
        fileChooser.setFileFilter(filenameFilter);

        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            FileManagement.save(file, saveText);
        }
    }
    public static void loadDialog(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filenameFilter = new FileNameExtensionFilter("Textdateien (*.txt)", "txt");
        fileChooser.setFileFilter(filenameFilter);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            String fileContent = FileManagement.load(file);
            textArea.setText(fileContent);
        }
    }
}