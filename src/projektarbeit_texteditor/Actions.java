package projektarbeit_texteditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
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

    public static void increaseFontSize(JTextArea textArea) {
        Font currentFont = textArea.getFont();
        Font font = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 2);
        textArea.setFont(font);
    }

    public static void decreaseFontSize(JTextArea textArea) {
        Font currentFont = textArea.getFont();
        Font font = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() - 2);
        textArea.setFont(font);
    }

    public static void find(JTextArea textArea) {
        String text = textArea.getText().toLowerCase();
        String userInput = JOptionPane.showInputDialog("Suche nach:");
        if (userInput == null || userInput.isBlank()) return;

        Highlighter highlighter = textArea.getHighlighter();
        highlighter.removeAllHighlights();

        int start = 0;
        boolean found = false;

        while (true) {
            int match = text.indexOf(userInput.toLowerCase(), start);
            if (match == -1) {
                if (found) {
                    JOptionPane.showMessageDialog(null, "Keine weiteren Teffer für:\n" + userInput);
                    break;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Keine Teffer für:\n" + userInput);
                    break;
                }
            }
            try {
                highlighter.addHighlight(match, match + userInput.length(),
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
                textArea.setCaretPosition(match);
                found = true;
                int result = JOptionPane.showConfirmDialog(null, "Wollen Sie weitersuchen?",
                        "Weitersuchen?", JOptionPane.YES_NO_OPTION);

                if (result != JOptionPane.YES_OPTION) break;

                start = match + userInput.length();
            } catch (BadLocationException e) {
                System.out.println("Fehler BadLocationException " + e);
            }
        }
    }


    public static int countWords (JTextArea textArea){
        String text = textArea.getText();

        if (text.isEmpty())
            return 0;

        else {
            String[] words = text.trim().split("\\s+");
            return words.length;
        }
    }
}