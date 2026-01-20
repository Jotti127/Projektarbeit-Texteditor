package projektarbeit_texteditor;

import javax.swing.*;

public class Actions {
    public static void neu(JTextArea textArea) {
        int result = JOptionPane.showConfirmDialog(null,
                "Wollen Sie wirklich ein neues Dokument erstellen?",
                "Neues Dokument?",
                JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            textArea.setText("");
        }
        else return;
    }
    public static void exit() {
        System.exit(0);
    }
}
